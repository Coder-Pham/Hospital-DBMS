package com.haydenhuynh;

/* TODO:
    - DropList Doctor, Nurse, MID
    - Dialog box check trùng PID
    - Tách Full name -> First Last
 */

import com.jfoenix.controls.*;
import Model.Info;
import Model.Patient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

public class ControllerFunctionTwo implements Initializable {
    @FXML
    private Button back;
    @FXML
    private JFXToggleButton type;
    @FXML
    private JFXButton btnSubmit;
    @FXML
    private JFXRadioButton male;
    @FXML
    private JFXRadioButton female;
    @FXML
    private JFXTextField fname, lname;
    @FXML
    private JFXDatePicker dob;
    @FXML
    private JFXTextField address;
    @FXML
    private JFXTextField pid;
    @FXML
    private JFXTextField phone;
    @FXML
    private TableView table;
    @FXML
    private TableColumn First, Last, id, pdob, gender, Pphone, addr;

    private ToggleGroup GenderGroup;

    private boolean PatientType = false;
    private Statement statement;
    private String addQuery = "INSERT INTO PATIENT VALUES (?, ?, ?, TO_DATE(?, 'yyyy-mm-dd'), ?, ?, ?)";

    // Show initial Patient Table
    private String SHOW_ALL = "SELECT * FROM PATIENT";
    // Random ID
    private String DOCTOR_rand = "SELECT * FROM (SELECT * FROM DOCTOR ORDER BY DBMS_RANDOM.VALUE) WHERE ROWNUM = 1";
    private String NURSE_rand = "SELECT * FROM (SELECT * FROM NURSE ORDER BY DBMS_RANDOM.VALUE) WHERE ROWNUM = 1";
    private String MID_rand = "SELECT MID FROM (SELECT * FROM MEDICATION ORDER BY DBMS_RANDOM.VALUE) WHERE ROWNUM = 1";

    // New INpatient
    private String addINPATIENT = "INSERT INTO INPATIENT (PID_IN, PADMISSIONDATE, PDISCHARGEDATE, PSICKROOM, PFEE, EID_DOC, EID_NUR) VALUES (?, TO_DATE(?, 'dd/mm/yyyy'), TO_DATE(?, 'dd/mm/yyyy'), ?, ?, ?, ?)";
    private String addTREATMENT = "INSERT INTO TREATMENT (EID_DOC, PID_IN, TRID) VALUES (?, ?, ?)";
    private String addUSES_TREAT = "INSERT INTO USES_TREAT VALUES (?, ?, ?, ?)";
    // New OUTpatient
    private String addOUTPATIENT = "INSERT INTO OUTPATIENT (PID_OUT, EID_DOC) VALUES (?, ?)";
    private String addEXAMINATION = "INSERT INTO EXAMINATION (EID_DOC, PID_OUT, EXID, EXDATE, EXFEE) VALUES (?, ?, ?, TO_DATE(?, 'dd/mm/yyyy'), ?)";
    private String addUSES_EXAM = "INSERT INTO USES_EXAM VALUES (?, ?, ?, ?)";

    private void showPatient() throws  SQLException
    {
        ResultSet ALL_PATIENT = statement.executeQuery(SHOW_ALL);
        final ObservableList<Patient> data = FXCollections.observableArrayList();
        while (ALL_PATIENT.next())
        {
            Patient nxtPatient = new Patient(ALL_PATIENT.getString(1), ALL_PATIENT.getString(2),
                    ALL_PATIENT.getString(3), ALL_PATIENT.getString(4),
                    ALL_PATIENT.getString(5), ALL_PATIENT.getString(6), ALL_PATIENT.getString(7));
            data.add(nxtPatient);
        }

        id.setCellValueFactory(new PropertyValueFactory<Patient, String>("PID"));
        id.setStyle("-fx-alignment: CENTER;");
        First.setCellValueFactory(new PropertyValueFactory<Patient, String>("PFname"));
        First.setStyle("-fx-alignment: CENTER;");
        Last.setCellValueFactory(new PropertyValueFactory<Patient, String>("PLname"));
        Last.setStyle("-fx-alignment: CENTER;");
        pdob.setCellValueFactory(new PropertyValueFactory<Patient, String>("PDOB"));
        pdob.setStyle("-fx-alignment: CENTER;");
        gender.setCellValueFactory(new PropertyValueFactory<Patient, String>("PGender"));
        gender.setStyle("-fx-alignment: CENTER;");
        Pphone.setCellValueFactory(new PropertyValueFactory<Patient, String>("PPhone"));
        addr.setCellValueFactory(new PropertyValueFactory<Patient, String>("PAddress"));

        table.setItems(data);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        GenderGroup = new ToggleGroup();
        male.setToggleGroup(GenderGroup);
        male.setSelected(true);
        female.setToggleGroup(GenderGroup);


//        DISPLAY ALL PATIENT TABLE
        try {
            this.statement = Info.connection.createStatement();
            showPatient();
        } catch (SQLException e) {}
    }

    private void showAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Add new patient");
        alert.setHeaderText(null);
        alert.setContentText("Errors occur when adding new data");
        alert.showAndWait();
    }

    @FXML
    private void submit(ActionEvent event) throws  IOException {
        String pidValue;
        String FName = "";
        String LName = "";
        String PDOB = "";
        String PAddress = "";
        String PPhone = "";
        String PGender = "";

        pidValue = pid.getText();
        FName = fname.getText();
        LName = lname.getText();
        try {
            PDOB = dob.getValue().toString();
        } catch (NullPointerException e)
        {
            PDOB = "";
        }
        PAddress = address.getText();
        PPhone = phone.getText();
        PGender = ((JFXRadioButton) GenderGroup.getSelectedToggle()).getText();
        String gender;
        if (PGender == "Female")
            gender = "F";
        else
            gender="M";

        // INSERT PATIENT TABLE
        try {
            PreparedStatement update = Info.connection.prepareStatement(addQuery);
            update.setString(1, pidValue);
            update.setString(2, FName);
            update.setString(3, LName);
            update.setString(4, PDOB);
            update.setString(5, gender);
            update.setString(6, PPhone);
            update.setString(7, PAddress);
            int result = update.executeUpdate();
            update.close();
        } catch (SQLException e) {
            showAlert();
            return;
        }

        // CLEAR ALL
        pid.clear();
        fname.clear();
        lname.clear();
        address.clear();
        phone.clear();

        try {
            showPatient();
        } catch (SQLException e) {}
    }

    // Action for UPDATE TOGGLE BUTTON
    @FXML
    private void switchScene(ActionEvent event) throws  IOException {
        Parent root = FXMLLoader.load(getClass().getResource("UpdateView.fxml"));
        LoginController.secondStage.setScene(new Scene(root, 1300, 750));
    }

    @FXML
    public void onBackButtonPressed() throws IOException {

        LoginController.secondStage.setScene(LoginController.menuScene);

    }
}
