package Hospital_DBMS;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import helpers.Info;
import helpers.treatmentReport;
import helpers.examinationReport;
import helpers.employeeReport;
import javafx.scene.text.Font;
import java.sql.*;

public class ControllerFunctionFour implements Initializable {

    private boolean init = true;
    private boolean doc = false;
    private Statement qr = Info.connection.createStatement();    // Query Statement.
    private ResultSet rs;					                    // The record we select.

    @FXML
    private Label label_title;

    @FXML
    private Label label_report;

    @FXML
    private TextField PID;

    @FXML
    private VBox report;

    @FXML
    private TableView<treatmentReport> table;

    @FXML
    private TableView<examinationReport> table2;

    @FXML
    private TableView<employeeReport> table3;

    public ControllerFunctionFour() throws SQLException {
    }

    public GridPane createInformation(String inPNAME, String inPID, String inPGENDER, String inPDOB, String inPNUMBER, String inPADDRESS, String inPDOC, String inPNURSE){

        // Creating labels
        Label label_PNAME = new Label("Full Name: ");
        label_PNAME.setFont(Font.font("System",20));

        // Creating Text Filed
        TextField PNAME = new TextField();
        PNAME.setId("PNAME");
        PNAME.setEditable(false);
        PNAME.setText(inPNAME);

        Label label_PID = new Label("ID: ");
        label_PID.setFont(Font.font("System",20));

        // Creating Text Filed
        TextField PID = new TextField();
        PID.setId("PID");
        PID.setEditable(false);
        PID.setText(inPID);

        Label label_PGENDER = new Label("Gender: ");
        label_PGENDER .setFont(Font.font("System",20));

        // Creating Text Filed
        TextField PGENDER = new TextField();
        PGENDER.setId("PGENDER");
        PGENDER.setEditable(false);
        PGENDER.setText(inPGENDER);

        Label label_PDOB = new Label("Date Of Birth: ");
        label_PDOB.setFont(Font.font("System",20));

        // Creating Text Filed
        TextField PDOB = new TextField();
        PDOB.setId("PDOB");
        PDOB.setEditable(false);
        PDOB.setText(inPDOB);

        Label label_PNUMBER = new Label("Phone Number: ");
        label_PNUMBER.setFont(Font.font("System",20));

        // Creating Text Filed
        TextField PNUMBER = new TextField();
        PNUMBER.setId("PNUMBER");
        PNUMBER.setEditable(false);
        PNUMBER.setText(inPNUMBER);

        Label label_PADDRESS = new Label("Address: ");
        label_PADDRESS.setFont(Font.font("System",20));

        // Creating Text Filed
        TextField PADDRESS = new TextField();
        PADDRESS.setId("PADDRESS");
        PADDRESS.setEditable(false);
        PADDRESS.setText(inPADDRESS);

        Label label_PDOC = new Label("Doctor's ID: ");
        label_PDOC.setFont(Font.font("System",20));

        // Creating Text Filed
        TextField PDOC = new TextField();
        PDOC.setId("PDOC");
        PDOC.setEditable(false);
        PDOC.setText(inPDOC);

        Label label_PNURSE = new Label("Nurse's ID: ");
        label_PNURSE.setFont(Font.font("System", 20));

        // Creating Text Filed
        TextField PNURSE = new TextField();
        PNURSE.setId("PNURSE");
        PNURSE.setEditable(false);
        PNURSE.setText(inPNURSE);

        //Creating a Grid Pane
        GridPane gridPane = new GridPane();

        //Setting size for the pane
        gridPane.setMinSize(10, 100);

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
        gridPane.setColumnSpan(PNAME,3);
        gridPane.setHalignment(label_PID, HPos.LEFT);
        gridPane.setMargin(label_PID, new Insets(0,0,0,20));
        gridPane.setHalignment(label_PGENDER, HPos.LEFT);
        gridPane.setMargin(label_PGENDER, new Insets(0,0,0,20));
        gridPane.setHalignment(label_PDOB, HPos.LEFT);
        gridPane.setMargin(label_PDOB, new Insets(0,0,0,20));
        gridPane.setHalignment(label_PNUMBER, HPos.LEFT);
        gridPane.setMargin(label_PNUMBER, new Insets(0,0,0,20));
        gridPane.setHalignment(label_PADDRESS, HPos.LEFT);
        gridPane.setMargin(label_PADDRESS, new Insets(0,0,0,20));
        gridPane.setPadding(new Insets(0,0,150,20));
        gridPane.setColumnSpan(PADDRESS,3);
        gridPane.setHalignment(label_PDOC, HPos.LEFT);
        gridPane.setMargin(label_PDOC, new Insets(0,0,0,20));
        gridPane.setHalignment(label_PNURSE, HPos.LEFT);
        gridPane.setMargin(label_PNURSE, new Insets(0,0,0,20));

        //Arranging all the nodes in the grid
        gridPane.add(label_PNAME, 0, 0);
        gridPane.add(PNAME, 1, 0);
        gridPane.add(label_PID, 0, 1);
        gridPane.add(PID, 1, 1);
        gridPane.add(label_PGENDER, 2, 1);
        gridPane.add(PGENDER, 3, 1);
        gridPane.add(label_PDOB, 0, 2);
        gridPane.add(PDOB, 1, 2);
        gridPane.add(label_PNUMBER, 2, 2);
        gridPane.add(PNUMBER, 3, 2);
        gridPane.add(label_PADDRESS, 0, 3);
        gridPane.add(PADDRESS, 1, 3);

        gridPane.setId("Information");
        return gridPane;

    }

    public void getInformation(String PID) throws SQLException {
        try {
            rs = qr.executeQuery("SELECT * FROM  PATIENT, (SELECT * FROM OUTPATIENT WHERE PID_OUT =" + PID+ ") O WHERE PID = O.PID_OUT");
        } catch (SQLException e) {
            this.init = true;
            return;
        }

        if(rs.next() == true) {
            String EID = rs.getString("EID_DOC");// if data is null `output` would be null, so there is no chance of NPE unless `rs` is `null`
            if(EID== null){// if you fetched null value then initialize output with blank string
                EID= "";
            }
            this.report.getChildren().add(1,this.createInformation(rs.getString("PFNAME") + " " + rs.getString("PLNAME"), rs.getString("PID"), rs.getString("PGENDER"), rs.getString("PDOB"), rs.getString("PPHONE"), rs.getString("PADDRESS"), EID, "NULL"));
            this.getEmployeetReport(EID, "NULL");
            this.getReport(PID);
        }
        else{

            try {
                rs = qr.executeQuery("SELECT * FROM  PATIENT, (SELECT * FROM INPATIENT WHERE PID_IN =" + PID + ") I WHERE PID = I.PID_IN");
            } catch (SQLException e) {
                this.init = true;
                return;
            }

            if(rs.next() == true) {
                String EID = rs.getString("EID_DOC");// if data is null `output` would be null, so there is no chance of NPE unless `rs` is `null`
                if(EID== null){// if you fetched null value then initialize output with blank string
                    EID= "";
                }
                String NUR = rs.getString("EID_NUR");// if data is null `output` would be null, so there is no chance of NPE unless `rs` is `null`
                if(NUR== null){// if you fetched null value then initialize output with blank string
                    NUR= "";
                }
                this.report.getChildren().add(1,this.createInformation(rs.getString("PFNAME") + " " + rs.getString("PLNAME"), rs.getString("PID"), rs.getString("PGENDER"), rs.getString("PDOB"), rs.getString("PPHONE"), rs.getString("PADDRESS"), EID, NUR));
                this.getEmployeetReport(EID, NUR);
                this.exportReport(PID);
            }
            else{
                this.init = true;
                return;
            }

        }
    }

    public ObservableList<examinationReport> reportExamination(String PID){
        ObservableList<examinationReport> examinationReports = FXCollections.observableArrayList();
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
                    examinationReports.add(new examinationReport(rs.getString("EXID"), rs.getString("EXDIAGNOSIS"), rs.getString("EXDATE"),
                            rs.getString("EXSECONDEXAMINATIONDATE"), rs.getString("MNAME"), rs.getString("MEFFECTS"), String.valueOf(EXFEE),
                            String.valueOf(MPRICE), String.valueOf(total)));
                    storedID.add(rs.getString("EXID"));
                }
                else{
                    long MPRICE = rs.getLong("MPRICE");// if data is null `output` would be null, so there is no chance of NPE unless `rs` is `null`
                    total = total + MPRICE;
                    examinationReports.add(new examinationReport("---", "---", "---", "---",
                            rs.getString("MNAME"), rs.getString("MEFFECTS"), "---", String.valueOf(MPRICE), String.valueOf(total)));
                }
            }
            examinationReports.add(new examinationReport("---","---","---","---","---","---","---","SUM",String.valueOf(total) ));

        } catch (SQLException e) {
            this.init = true;
            return null;
        }
        return examinationReports;
    }

    public TableView<examinationReport> generateExaminationTable(String PID){

        TableColumn<examinationReport, String> ID = new TableColumn<>("Examination ID");
        ID.setMinWidth(110);
        ID.setCellValueFactory(new PropertyValueFactory<>("examinationID"));

        TableColumn<examinationReport, String> diag = new TableColumn<>("Examination Diagnosis");
        diag.setMinWidth(110);
        diag.setCellValueFactory(new PropertyValueFactory<>("examinationDiagnosis"));

        TableColumn<examinationReport, String> ad = new TableColumn<>("Examination Date");
        ad.setMinWidth(110);
        ad.setCellValueFactory(new PropertyValueFactory<>("examinationDate"));

        TableColumn<examinationReport, String> dis = new TableColumn<>("Second Examination Date");
        dis.setMinWidth(110);
        dis.setCellValueFactory(new PropertyValueFactory<>("examinationSecondDate"));

        TableColumn<examinationReport, String> medname = new TableColumn<>("Medication Name");
        medname.setMinWidth(110);
        medname.setCellValueFactory(new PropertyValueFactory<>("medicationName"));

        TableColumn<examinationReport, String> medeff = new TableColumn<>("Medication Effects");
        medeff.setMinWidth(110);
        medeff.setCellValueFactory(new PropertyValueFactory<>("medicationEffect"));

        TableColumn<examinationReport, String>fee = new TableColumn<>("Examination Fee");
        fee.setMinWidth(110);
        fee.setCellValueFactory(new PropertyValueFactory<>("examinationFee"));

        TableColumn<examinationReport, String> medprice = new TableColumn<>("Medication Price");
        medprice.setMinWidth(110);
        medprice.setCellValueFactory(new PropertyValueFactory<>("medicationPrice"));

        TableColumn<examinationReport, String> total = new TableColumn<>("Total");
        total.setMinWidth(110);
        total.setCellValueFactory(new PropertyValueFactory<>("total"));
        total.setStyle("-fx-alignment: CENTER_RIGHT;");

        this.table2 = new TableView<>();
        this.table2.setItems(reportExamination(PID));
        this.table2.getColumns().addAll(ID, diag, ad, dis, medname, medeff, fee, medprice, total);
        return table2;
    }

    public ObservableList<employeeReport> reportEmployee(String EID, String NUR) {
        ObservableList<employeeReport> employeeReports = FXCollections.observableArrayList();
        try {
            rs = qr.executeQuery("SELECT E.EID, EPHONE, EFNAME, ELNAME, EDOB, EGENDER, ESPECIALITY, EADDRESS, ESTARTDATE, DID, DTITLE FROM EMP_PHONE P, (SELECT EID, EFNAME, ELNAME, EDOB, EGENDER, ESPECIALITY, EADDRESS, ESTARTDATE, DID AS SUB_DID FROM EMPLOYEE) E, (SELECT DID, DTITLE, EID AS SUB_EID FROM DEPARTMENT) D WHERE E.SUB_DID = D.DID AND P.EID = E.EID AND E.EID = " + EID);

        } catch (SQLException e) {
            this.init = true;
            return null;
        }

        try {
            ArrayList<String> storedID = new ArrayList<>();

            employeeReports.add(new employeeReport("DOCTOR", "---", "---", "---", "---", "---", "---", "---", "---", "---"));

            while (rs.next()) {

                boolean _check = false;
                for (int i = 0; i < storedID.size(); i++) {
                    if (storedID.get(i).equals(rs.getString("EID"))) {
                        _check = true;
                        break;
                    }
                }
                if (!_check) {
                    employeeReports.add(new employeeReport(rs.getString("EID"), rs.getString("EPHONE"), rs.getString("EFNAME") + " " + rs.getString("ELNAME"), rs.getString("EDOB"),
                            rs.getString("EGENDER"), rs.getString("ESPECIALITY"), rs.getString("EADDRESS"), rs.getString("ESTARTDATE"),
                            rs.getString("DID"), rs.getString("DTITLE")));

                    storedID.add(rs.getString("EID"));
                } else {
                    employeeReports.add(new employeeReport("", rs.getString("EPHONE"), "", "",
                            "", "", "", "", "", ""));
                }
            }

        } catch (SQLException e) {
            this.init = true;
            return null;
        }

    if(!(NUR.equals("NULL"))){
        employeeReports.add(new employeeReport("NURSE", "---", "---", "---", "---", "---", "---", "---", "---", "---"));

        try {
            rs = qr.executeQuery("SELECT E.EID, EPHONE, EFNAME, ELNAME, EDOB, EGENDER, ESPECIALITY, EADDRESS, ESTARTDATE, DID, DTITLE FROM EMP_PHONE P, (SELECT EID, EFNAME, ELNAME, EDOB, EGENDER, ESPECIALITY, EADDRESS, ESTARTDATE, DID AS SUB_DID FROM EMPLOYEE) E, (SELECT DID, DTITLE, EID AS SUB_EID FROM DEPARTMENT) D WHERE E.SUB_DID = D.DID AND P.EID = E.EID AND E.EID = " + NUR);

        } catch (SQLException e) {
            this.init = true;
            return null;
        }

        try {
            ArrayList<String> storedID = new ArrayList<>();

            while (rs.next()) {
                boolean checks = false;
                for (int i = 0; i < storedID.size(); i++) {
                    if (storedID.get(i).equals(rs.getString("EID"))) {
                        checks = true;
                        break;
                    }
                }
                if (!checks) {
                    employeeReports.add(new employeeReport(rs.getString("EID"), rs.getString("EPHONE"), rs.getString("EFNAME") + " " + rs.getString("ELNAME"), rs.getString("EDOB"),
                            rs.getString("EGENDER"), rs.getString("ESPECIALITY"), rs.getString("EADDRESS"), rs.getString("ESTARTDATE"),
                            rs.getString("DID"), rs.getString("DTITLE")));
                    storedID.add(rs.getString("EID"));
                } else {
                    employeeReports.add(new employeeReport("", rs.getString("EPHONE"), "", "",
                            "", "", "", "", "", ""));
                }
            }

        } catch (SQLException e) {
            this.init = true;
            return null;
        }
    }

        return employeeReports;
    }

    public ObservableList<treatmentReport> reportTreatment(String PID){
        ObservableList<treatmentReport> treatmentReports = FXCollections.observableArrayList();
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
                    treatmentReports.add(new treatmentReport(rs.getString("TRID"), rs.getString("TRSTART"), rs.getString("TREND"), rs.getString("TRRESULT"),
                            rs.getString("PADMISSIONDATE"), rs.getString("PDISCHARGEDATE"), rs.getString("PDIAGNOSIS"), rs.getString("PSICKROOM"),
                            rs.getString("MNAME"), rs.getString("MEFFECTS"), String.valueOf(rs.getLong("PFEE")), String.valueOf(rs.getLong("MPRICE")), String.valueOf(PFEE + MPRICE) ));
                    storedID.add(rs.getString("TRID"));
                }
                else{
                    long MPRICE = rs.getLong("MPRICE");// if data is null `output` would be null, so there is no chance of NPE unless `rs` is `null`
                    total = total + MPRICE;
                    treatmentReports.add(new treatmentReport("---", "---", "---", "---",
                            "---", "---", "---", rs.getString("PSICKROOM"),
                            rs.getString("MNAME"), rs.getString("MEFFECTS"), "---", String.valueOf(rs.getLong("MPRICE")), String.valueOf(MPRICE) ));
                }
            }
            treatmentReports.add(new treatmentReport("---","---","---","---","---","---","---","---","---","---","---","SUM",String.valueOf(total) ));

        } catch (SQLException e) {
            this.init = true;
            return null;
        }
        return treatmentReports;
    }

    public TableView<treatmentReport> generateTreatmentTable(String PID){

        TableColumn<treatmentReport, String> ID = new TableColumn<>("Treatment ID");
        ID.setMinWidth(100);
        ID.setCellValueFactory(new PropertyValueFactory<>("treatmentID"));

        TableColumn<treatmentReport, String> start = new TableColumn<>("Treatment Start");
        start.setMinWidth(100);
        start.setCellValueFactory(new PropertyValueFactory<>("treatmentStart"));

        TableColumn<treatmentReport, String> end = new TableColumn<>("Treatment End");
        end.setMinWidth(100);
        end.setCellValueFactory(new PropertyValueFactory<>("treatmentEnd"));

        TableColumn<treatmentReport, String> result = new TableColumn<>("Treatment Result");
        result.setMinWidth(100);
        result.setCellValueFactory(new PropertyValueFactory<>("treatmentResult"));

        TableColumn<treatmentReport, String> ad = new TableColumn<>("Admission Date");
        ad.setMinWidth(100);
        ad.setCellValueFactory(new PropertyValueFactory<>("admissionDate"));

        TableColumn<treatmentReport, String> dis = new TableColumn<>("Discharge Date");
        dis.setMinWidth(100);
        dis.setCellValueFactory(new PropertyValueFactory<>("dischargeDate"));

        TableColumn<treatmentReport, String> diag = new TableColumn<>("Treatment Diagnosis");
        diag.setMinWidth(100);
        diag.setCellValueFactory(new PropertyValueFactory<>("treatmentDiagnosis"));

        TableColumn<treatmentReport, String> sick = new TableColumn<>("Treatment Sickroom");
        sick.setMinWidth(100);
        sick.setCellValueFactory(new PropertyValueFactory<>("treatmentSickroom"));

        TableColumn<treatmentReport, String> medname = new TableColumn<>("Medication Name");
        medname.setMinWidth(100);
        medname.setCellValueFactory(new PropertyValueFactory<>("medicationName"));

        TableColumn<treatmentReport, String> medeff = new TableColumn<>("Medication Effects");
        medeff.setMinWidth(100);
        medeff.setCellValueFactory(new PropertyValueFactory<>("medicationEffect"));

        TableColumn<treatmentReport, String> fee = new TableColumn<>("Treatment Fee");
        fee.setMinWidth(100);
        fee.setCellValueFactory(new PropertyValueFactory<>("treatmentFee"));

        TableColumn<treatmentReport, String> medprice = new TableColumn<>("Medication Price");
        medprice.setMinWidth(100);
        medprice.setCellValueFactory(new PropertyValueFactory<>("medicationPrice"));

        TableColumn<treatmentReport, String> total = new TableColumn<>("Total");
        total.setMinWidth(100);
        total.setCellValueFactory(new PropertyValueFactory<>("total"));
        total.setStyle("-fx-alignment: CENTER_RIGHT;");

        this.table = new TableView<treatmentReport>();
        this.table.setItems(reportTreatment(PID));
        this.table.getColumns().addAll(ID, start, end, result, ad, dis, diag, sick, medname, medeff, fee, medprice, total);
        return table;
    }

    public void exportReport(String PID){
        this.report.getChildren().add(generateTreatmentTable(PID));
    }

    public void getReport(String EID){
        this.report.getChildren().add(generateExaminationTable(EID));
    }

    public TableView<employeeReport> generateEmployeeTable(String EID, String NUR){

        TableColumn<employeeReport, String> ID = new TableColumn<>("Employee's ID");
        ID.setMinWidth(100);
        ID.setCellValueFactory(new PropertyValueFactory<>("EID"));

        TableColumn<employeeReport, String> phone = new TableColumn<>("Employee's Phone");
        phone.setMinWidth(100);
        phone.setCellValueFactory(new PropertyValueFactory<>("EPHONE"));

        TableColumn<employeeReport, String> name = new TableColumn<>("Employee's Name");
        name.setMinWidth(100);
        name.setCellValueFactory(new PropertyValueFactory<>("ENAME"));

        TableColumn<employeeReport, String> dob = new TableColumn<>("Employee's DOB");
        dob.setMinWidth(100);
        dob.setCellValueFactory(new PropertyValueFactory<>("EDOB"));

        TableColumn<employeeReport, String> gender = new TableColumn<>("Employee's Gender");
        gender.setMinWidth(100);
        gender.setCellValueFactory(new PropertyValueFactory<>("EGENDER"));

        TableColumn<employeeReport, String> spec = new TableColumn<>("Employee's Speciality");
        spec.setMinWidth(100);
        spec.setCellValueFactory(new PropertyValueFactory<>("ESPECIALITY"));

        TableColumn<employeeReport, String> addr = new TableColumn<>("Employee's Address");
        addr.setMinWidth(100);
        addr.setCellValueFactory(new PropertyValueFactory<>("EADDRESS"));

        TableColumn<employeeReport, String> std = new TableColumn<>("Employee's Start Date");
        std.setMinWidth(100);
        std.setCellValueFactory(new PropertyValueFactory<>("ESTARTDATE"));

        TableColumn<employeeReport, String> did = new TableColumn<>("Department's ID");
        did.setMinWidth(100);
        did.setCellValueFactory(new PropertyValueFactory<>("DID"));

        TableColumn<employeeReport, String> title = new TableColumn<>("Department's Title");
        title.setMinWidth(100);
        title.setCellValueFactory(new PropertyValueFactory<>("DTITLE"));

        this.table3 = new TableView<employeeReport>();
        this.table3.setItems(reportEmployee(EID,NUR));
        this.table3.getColumns().addAll(ID, phone, name, dob, gender, spec, addr, std, did, title);
        return table3;
    }

    public void getEmployeetReport(String PID, String NUR){
        this.report.getChildren().add(2, generateEmployeeTable(PID,NUR));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        label_title.setVisible(false);
        label_report.setVisible(false);
    }

    public void searchAction(Event event) throws SQLException {
        if(!init){
            this.report.getChildren().remove(4);
            this.report.getChildren().remove(2);
            this.report.getChildren().remove(1);
        }
        else{
            label_title.setVisible(true);
            label_report.setVisible(true);
            init = false;
        }
        this.getInformation(String.valueOf(PID.getText()));
    }

    @FXML
    public void onBackButtonPressed() throws IOException {

        LoginController.secondStage.setScene(LoginController.menuScene);

    }
}
