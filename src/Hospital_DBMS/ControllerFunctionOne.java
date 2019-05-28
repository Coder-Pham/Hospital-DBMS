package Hospital_DBMS;

import helpers.Info;
import helpers.examinationReport2;
import helpers.treatmentReport2;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerFunctionOne implements Initializable {

    private boolean init = true;
    private Statement qr = Info.connection.createStatement();    // Query Statement.
    private ResultSet rs;					                    // The record we select.

    @FXML
    private Label info1, info2, detail1, detail2;

    @FXML
    private TextField PID;

    @FXML
    private VBox report;

    @FXML
    private TableView<treatmentReport2> table;

    @FXML
    private TableView<examinationReport2> table2;

    public ControllerFunctionOne() throws SQLException {
    }

    public GridPane createInformation(String inPNAME, String inPNUMBER){

        // Creating labels
        Label label_PNAME = new Label("Patient's Full Name: ");
        label_PNAME.setFont(Font.font("System",20));

        // Creating Text Filed
        TextField PNAME = new TextField();
        PNAME.setId("PNAME");
        PNAME.setEditable(false);
        PNAME.setText(inPNAME);

        Label label_PNUMBER = new Label("Patient's Phone Number: ");
        label_PNUMBER.setFont(Font.font("System",20));

        // Creating Text Filed
        TextField PNUMBER = new TextField();
        PNUMBER.setId("PNUMBER");
        PNUMBER.setEditable(false);
        PNUMBER.setText(inPNUMBER);

        //Creating a Grid Pane
        GridPane gridPane = new GridPane();

        //Setting size for the pane
        gridPane.setMinSize(10, 50);

        //Setting the column constraint
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(23.5);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(23.25);
        ColumnConstraints col3 = new ColumnConstraints();
        col3.setPercentWidth(27);
        ColumnConstraints col4 = new ColumnConstraints();
        col4.setPercentWidth(25.5);
        gridPane.getColumnConstraints().addAll(col1,col2,col3,col4);

        //Setting the Grid alignment
        gridPane.setHalignment(label_PNAME, HPos.LEFT);
        gridPane.setMargin(label_PNAME, new Insets(0,0,0,20));
        gridPane.setHalignment(label_PNUMBER, HPos.LEFT);
        gridPane.setMargin(label_PNUMBER, new Insets(0,0,0,20));

        //Arranging all the nodes in the grid
        gridPane.add(label_PNAME, 0, 0);
        gridPane.add(PNAME, 1, 0);
        gridPane.add(label_PNUMBER, 2, 0);
        gridPane.add(PNUMBER, 3, 0);

        gridPane.setId("Information");
        return gridPane;

    }

    public void getInformation(String PID) throws SQLException {
        try {
            rs = qr.executeQuery("SELECT PFNAME, PLNAME, PPHONE FROM  PATIENT, (SELECT * FROM OUTPATIENT WHERE PID_OUT =" + PID+ ") O WHERE PID = O.PID_OUT");
        } catch (SQLException e) {
            this.init = true;
            return;
        }

        if(rs.next() == true) {
            this.report.getChildren().add(info1);
            this.report.getChildren().add(1,this.createInformation(rs.getString("PFNAME") + " " + rs.getString("PLNAME"), rs.getString("PPHONE")));
            this.getReport(PID);
        }
        else{

            try {
                rs = qr.executeQuery("SELECT PFNAME, PLNAME, PPHONE FROM  PATIENT, (SELECT * FROM INPATIENT WHERE PID_IN =" + PID + ") I WHERE PID = I.PID_IN");
            } catch (SQLException e) {
                this.init = true;
                return;
            }

            if(rs.next() == true) {
                this.report.getChildren().add(info2);
                this.report.getChildren().add(1,this.createInformation(rs.getString("PFNAME") + " " + rs.getString("PLNAME"), rs.getString("PPHONE")));
                this.exportReport(PID);
            }
            else{
                this.init = true;
                return;
            }

        }
    }

    public ObservableList<examinationReport2> reportExamination(String PID){
        this.report.getChildren().add(detail1);
        ObservableList<examinationReport2> examinationReports = FXCollections.observableArrayList();
        try {
            rs = qr.executeQuery("SELECT EXID , EXDATE, EXDiAGNOSIS, MNAME, MEFFECTS, EXSECONDEXAMINATIONDATE, EXFEE, MPRICE FROM OUTPATIENT OP, (SELECT * FROM  EXAMINATION E, "
                    + "(SELECT EID_DOC AS USES_EID_DOC, EXID AS USES_EXID, PID_OUT AS USES_PID_OUT, MNAME, MEFFECTS, MPRICE  FROM USES_EXAM U, "
                    + "MEDICATION M WHERE U.MID = M.MID ) A WHERE E.EID_DOC = A.USES_EID_DOC AND E.PID_OUT = A.USES_PID_OUT AND E.EXID = A.USES_EXID) F "
                    + "WHERE OP.PID_OUT = F.PID_OUT AND F.PID_OUT = " + PID);
        } catch (SQLException e) {
            this.init = true;
            return null;
        }

        long total = 0;

        try {
            ArrayList<String> storedID = new ArrayList<>();
            while(rs.next()) {
                boolean checks = false;
                for(int i = 0; i < storedID.size();i++){
                    if(storedID.get(i).equals(rs.getString("EXID"))){
                        checks = true;
                        break;
                    }
                }
                if(!checks){
                    long EXFEE = rs.getLong("EXFEE");// if data is null `output` would be null, so there is no chance of NPE unless `rs` is `null`

                    long MPRICE = rs.getLong("MPRICE");// if data is null `output` would be null, so there is no chance of NPE unless `rs` is `null`
                    total = total +  EXFEE + MPRICE;
                    examinationReports.add(new examinationReport2(rs.getString("EXID"), rs.getString("EXDIAGNOSIS"), rs.getString("EXDATE"),
                            rs.getString("EXSECONDEXAMINATIONDATE"), rs.getString("MNAME"), rs.getString("MEFFECTS"), String.valueOf(EXFEE),
                            String.valueOf(MPRICE), String.valueOf(total)));
                    storedID.add(rs.getString("EXID"));
                }
                else{
                    long MPRICE = rs.getLong("MPRICE");// if data is null `output` would be null, so there is no chance of NPE unless `rs` is `null`
                    total = total + MPRICE;
                    examinationReports.add(new examinationReport2("---", "---", "---", "---",
                            rs.getString("MNAME"), rs.getString("MEFFECTS"), "---", String.valueOf(MPRICE), String.valueOf(total)));
                }
            }
        } catch (SQLException e) {
            this.init = true;
            return null;
        }
        return examinationReports;
    }

    public TableView<examinationReport2> generateExaminationTable(String PID){

        TableColumn<examinationReport2, String> ID = new TableColumn<>("Examination ID");
        ID.setMinWidth(140);
        ID.setCellValueFactory(new PropertyValueFactory<>("examinationID"));

        TableColumn<examinationReport2, String> diag = new TableColumn<>("Examination Diagnosis");
        diag.setMinWidth(140);
        diag.setCellValueFactory(new PropertyValueFactory<>("examinationDiagnosis"));

        TableColumn<examinationReport2, String> ad = new TableColumn<>("Examination Date");
        ad.setMinWidth(140);
        ad.setCellValueFactory(new PropertyValueFactory<>("examinationDate"));

        TableColumn<examinationReport2, String> dis = new TableColumn<>("Second Examination Date");
        dis.setMinWidth(140);
        dis.setCellValueFactory(new PropertyValueFactory<>("examinationSecondDate"));

        TableColumn<examinationReport2, String> medname = new TableColumn<>("Medication Name");
        medname.setMinWidth(140);
        medname.setCellValueFactory(new PropertyValueFactory<>("medicationName"));

        TableColumn<examinationReport2, String> medeff = new TableColumn<>("Medication Effects");
        medeff.setMinWidth(140);
        medeff.setCellValueFactory(new PropertyValueFactory<>("medicationEffect"));

        TableColumn<examinationReport2, String>fee = new TableColumn<>("Examination Fee");
        fee.setMinWidth(140);
        fee.setCellValueFactory(new PropertyValueFactory<>("examinationFee"));

        TableColumn<examinationReport2, String> medprice = new TableColumn<>("Medication Price");
        medprice.setMinWidth(140);
        medprice.setCellValueFactory(new PropertyValueFactory<>("medicationPrice"));

        this.table2 = new TableView<>();
        this.table2.setItems(reportExamination(PID));
        this.table2.getColumns().addAll(ID, diag, ad, dis, medname, medeff, fee, medprice);
        return table2;
    }

    public ObservableList<treatmentReport2> reportTreatment(String PID){
        this.report.getChildren().add(detail2);
        ObservableList<treatmentReport2> treatmentReports = FXCollections.observableArrayList();
        try {
            rs = qr.executeQuery("SELECT TRID , TRSTART, TREND, TRRESULT, PADMISSIONDATE, PDISCHARGEDATE, PDIAGNOSIS, PSICKROOM, MNAME, MEFFECTS, PFEE, MPRICE "
                    + "FROM INPATIENT I, (SELECT * FROM  TREATMENT T, (SELECT EID_DOC AS USES_EID_DOC, TRID AS USES_TRID, PID_IN AS USES_PID_IN, MNAME, MEFFECTS, "
                    + "MPRICE  FROM USES_TREAT U, MEDICATION M WHERE U.MID = M.MID ) A WHERE T.EID_DOC = A.USES_EID_DOC AND "
                    + "T.PID_IN = A.USES_PID_IN AND T.TRID = A.USES_TRID) F WHERE I.PID_IN = F.PID_IN AND F.PID_IN = " + PID);
        } catch (SQLException e) {
            this.init = true;
            return null;
        }

        long total = 0;

        try {
            ArrayList<String> storedID = new ArrayList<>();
            while(rs.next()) {
                boolean checks = false;
                for(int i = 0; i < storedID.size();i++){
                    if(storedID.get(i).equals(rs.getString("TRID"))){
                        checks = true;
                        break;
                    }
                }
                if(!checks){
                    long PFEE = rs.getLong("PFEE");// if data is null `output` would be null, so there is no chance of NPE unless `rs` is `null`

                    long MPRICE = rs.getLong("MPRICE");// if data is null `output` would be null, so there is no chance of NPE unless `rs` is `null`
                    total = total +  PFEE + MPRICE;
                    treatmentReports.add(new treatmentReport2(rs.getString("TRID"), rs.getString("TRSTART"), rs.getString("TREND"), rs.getString("TRRESULT"),
                            rs.getString("PADMISSIONDATE"), rs.getString("PDISCHARGEDATE"), rs.getString("PDIAGNOSIS"), rs.getString("PSICKROOM"),
                            rs.getString("MNAME"), rs.getString("MEFFECTS"), String.valueOf(rs.getLong("PFEE")), String.valueOf(rs.getLong("MPRICE")), String.valueOf(PFEE + MPRICE) ));
                    storedID.add(rs.getString("TRID"));
                }
                else{
                    long MPRICE = rs.getLong("MPRICE");// if data is null `output` would be null, so there is no chance of NPE unless `rs` is `null`
                    total = total + MPRICE;
                    treatmentReports.add(new treatmentReport2("---", "---", "---", "---",
                            "---", "---", "---", rs.getString("PSICKROOM"),
                            rs.getString("MNAME"), rs.getString("MEFFECTS"), "---", String.valueOf(rs.getLong("MPRICE")), String.valueOf(MPRICE) ));
                }
            }
        } catch (SQLException e) {
            this.init = true;
            return null;
        }
        return treatmentReports;
    }

    public TableView<treatmentReport2> generateTreatmentTable(String PID){

        TableColumn<treatmentReport2, String> ID = new TableColumn<>("Treatment ID");
        ID.setMinWidth(100);
        ID.setCellValueFactory(new PropertyValueFactory<>("treatmentID"));

        TableColumn<treatmentReport2, String> start = new TableColumn<>("Treatment Start");
        start.setMinWidth(100);
        start.setCellValueFactory(new PropertyValueFactory<>("treatmentStart"));

        TableColumn<treatmentReport2, String> end = new TableColumn<>("Treatment End");
        end.setMinWidth(100);
        end.setCellValueFactory(new PropertyValueFactory<>("treatmentEnd"));

        TableColumn<treatmentReport2, String> result = new TableColumn<>("Treatment Result");
        result.setMinWidth(100);
        result.setCellValueFactory(new PropertyValueFactory<>("treatmentResult"));

        TableColumn<treatmentReport2, String> ad = new TableColumn<>("Admission Date");
        ad.setMinWidth(100);
        ad.setCellValueFactory(new PropertyValueFactory<>("admissionDate"));

        TableColumn<treatmentReport2, String> dis = new TableColumn<>("Discharge Date");
        dis.setMinWidth(100);
        dis.setCellValueFactory(new PropertyValueFactory<>("dischargeDate"));

        TableColumn<treatmentReport2, String> diag = new TableColumn<>("Treatment Diagnosis");
        diag.setMinWidth(100);
        diag.setCellValueFactory(new PropertyValueFactory<>("treatmentDiagnosis"));

        TableColumn<treatmentReport2, String> sick = new TableColumn<>("Treatment Sickroom");
        sick.setMinWidth(100);
        sick.setCellValueFactory(new PropertyValueFactory<>("treatmentSickroom"));

        TableColumn<treatmentReport2, String> medname = new TableColumn<>("Medication Name");
        medname.setMinWidth(100);
        medname.setCellValueFactory(new PropertyValueFactory<>("medicationName"));

        TableColumn<treatmentReport2, String> medeff = new TableColumn<>("Medication Effects");
        medeff.setMinWidth(100);
        medeff.setCellValueFactory(new PropertyValueFactory<>("medicationEffect"));

        TableColumn<treatmentReport2, String> fee = new TableColumn<>("Treatment Fee");
        fee.setMinWidth(100);
        fee.setCellValueFactory(new PropertyValueFactory<>("treatmentFee"));

        TableColumn<treatmentReport2, String> medprice = new TableColumn<>("Medication Price");
        medprice.setMinWidth(100);
        medprice.setCellValueFactory(new PropertyValueFactory<>("medicationPrice"));

        this.table = new TableView<treatmentReport2>();
        this.table.setItems(reportTreatment(PID));
        this.table.getColumns().addAll(ID, start, end, result, ad, dis, diag, sick, medname, medeff, fee, medprice);
        return table;
    }

    public void exportReport(String PID){
        this.report.getChildren().add(generateTreatmentTable(PID));
    }

    public void getReport(String PID){
        this.report.getChildren().add(generateExaminationTable(PID));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {

    }

    public void searchAction(Event event) throws SQLException {
        if(!init){
            this.report.getChildren().remove(0,4);
        }
        else{
            info1 = new Label("Information of Outpatient: ");
            info1.setFont(Font.font("Calibri", FontWeight.BOLD, FontPosture.ITALIC,36));
            info1.setMinHeight(100);
            info1.setMaxWidth(Double.MAX_VALUE);
            info1.setAlignment(Pos.CENTER);

            info2 = new Label("Information of Inpatient: ");
            info2.setFont(Font.font("Calibri", FontWeight.BOLD, FontPosture.ITALIC,36));
            info2.setMinHeight(100);
            info2.setMaxWidth(Double.MAX_VALUE);
            info2.setAlignment(Pos.CENTER);

            detail1 = new Label("Examination: ");
            detail1.setFont(Font.font("Calibri", FontWeight.BOLD, FontPosture.ITALIC,36));
            detail1.setMinHeight(100);
            detail1.setMaxWidth(Double.MAX_VALUE);
            detail1.setAlignment(Pos.CENTER);

            detail2 = new Label("Treatment: ");
            detail2.setFont(Font.font("Calibri", FontWeight.BOLD, FontPosture.ITALIC,36));
            detail2.setMinHeight(100);
            detail2.setMaxWidth(Double.MAX_VALUE);
            detail2.setAlignment(Pos.CENTER);
            init = false;
        }
        this.getInformation(String.valueOf(PID.getText()));
    }

    @FXML
    public void onBackButtonPressed() throws IOException {

        LoginController.secondStage.setScene(LoginController.menuScene);

    }
}
