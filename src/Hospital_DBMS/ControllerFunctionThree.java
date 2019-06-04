package Hospital_DBMS;

import helpers.Info;
import helpers.PatientFunctionThree;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ControllerFunctionThree {

    private Connection conn;
    private Statement statement;
    private String docID;
    private ResultSet docIDset;
    private ResultSet patientList;
    private ObservableList<PatientFunctionThree> patients;
    private ArrayList<String> patientIDSet;
    private String docQuery = "SELECT EID_Doc FROM DOCTOR WHERE EID_Doc = ";
    private String patientQuery = "SELECT * FROM PATIENT, INPATIENT, TREATMENT WHERE PATIENT.PID = INPATIENT.PID_In AND INPATIENT.PID_In = TREATMENT.PID_In AND TREATMENT.EID_Doc = ";


    @FXML
    private TextField DocIDInput;

    @FXML
    private TextArea errorMessage;

    @FXML
    private TableView InfoTable;

    @FXML
    private TableColumn col1;

    @FXML
    private TableColumn col2;

    @FXML
    private TableColumn col3;

    @FXML
    private TableColumn col4;

    @FXML
    private TableColumn col5;

    @FXML
    private TableColumn col6;

    @FXML
    private TableColumn col7;

    @FXML
    private TableColumn col8;

    @FXML
    private TableColumn col9;

    @FXML
    private TableColumn col10;

    @FXML
    private TableColumn col11;

    @FXML
    private TableColumn col12;

    @FXML
    private TableColumn col13;

    @FXML
    private TableColumn col14;

    @FXML
    private TableColumn col15;

    @FXML
    private TableColumn col16;

    @FXML
    private ScrollPane scrollpane;

    public void initialize() {
        scrollpane.setFitToWidth(true);
        scrollpane.setFitToHeight(true);

        errorMessage.clear();

        conn = Info.connection;

        patients = FXCollections.observableArrayList();

        patientIDSet = new ArrayList<>();
    }

    @FXML
    public void onConfirmButtonPressed() throws SQLException {

        InfoTable.getItems().clear();

        docID = DocIDInput.getText();

        statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

        docIDset = statement.executeQuery(docQuery + docID);
        if (docIDset.next() == false) {
            errorMessage.setText("Error: Given Doctor ID does not exist");
            return;
        }

        patientList = statement.executeQuery(patientQuery + docID);
        if (patientList.next() == false) {
            errorMessage.setText("The given Doctor ID does not have any Patients.");
            return;
        }

        col1.setCellValueFactory(new PropertyValueFactory<>("PID"));
        col2.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        col3.setCellValueFactory(new PropertyValueFactory<>("DOB"));
        col4.setCellValueFactory(new PropertyValueFactory<>("gender"));
        col5.setCellValueFactory(new PropertyValueFactory<>("phoneNo"));
        col6.setCellValueFactory(new PropertyValueFactory<>("address"));
        col7.setCellValueFactory(new PropertyValueFactory<>("admissionDate"));
        col8.setCellValueFactory(new PropertyValueFactory<>("dischargeDate"));
        col9.setCellValueFactory(new PropertyValueFactory<>("diagnosis"));
        col10.setCellValueFactory(new PropertyValueFactory<>("sickRoom"));
        col11.setCellValueFactory(new PropertyValueFactory<>("fee"));
        col12.setCellValueFactory(new PropertyValueFactory<>("caringNurseID"));
        col13.setCellValueFactory(new PropertyValueFactory<>("treatmentID"));
        col14.setCellValueFactory(new PropertyValueFactory<>("treatmentStartDate"));
        col15.setCellValueFactory(new PropertyValueFactory<>("treatmentEndDate"));
        col16.setCellValueFactory(new PropertyValueFactory<>("result"));

        patientList.previous();
        while(patientList.next()) {
            if (patientIDSet.contains(patientList.getString(1))) {
                patients.add(new PatientFunctionThree("-",
                        "-",
                        "-",
                        "-",
                        "-",
                        "-",
                        "-",
                        "-",
                        "-",
                        "-",
                        "-",
                        "-",
                        patientList.getString(18),
                        patientList.getString(19).substring(0, 10),
                        patientList.getString(20).substring(0, 10),
                        patientList.getString(21)));
            }
            else {
                patientIDSet.add(patientList.getString(1));

                patients.add(new PatientFunctionThree(patientList.getString(1),
                        patientList.getString(2) + patientList.getString(3),
                        patientList.getString(4).substring(0, 10),
                        patientList.getString(5),
                        patientList.getString(6),
                        patientList.getString(7),
                        patientList.getString(9).substring(0, 10),
                        patientList.getString(10).substring(0, 10),
                        patientList.getString(11),
                        patientList.getString(12),
                        patientList.getString(13),
                        patientList.getString(15),
                        patientList.getString(18),
                        patientList.getString(19).substring(0, 10),
                        patientList.getString(20).substring(0, 10),
                        patientList.getString(21)));
            }
        }

        InfoTable.setItems(patients);

    }

    @FXML
    public void onMouseClickedTextField() {
        errorMessage.clear();
    }

    @FXML
    public void onBackButtonPressed() throws IOException {

        LoginController.secondStage.setScene(LoginController.menuScene);

    }
}
