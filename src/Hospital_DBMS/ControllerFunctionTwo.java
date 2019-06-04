package Hospital_DBMS;

/* TODO:
    - DropList Doctor, Nurse, MID
    - Dialog box check trùng PID
    - Tách Full name -> First Last
 */

import com.jfoenix.controls.*;
import helpers.Info;
import helpers.Patient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    private JFXTextField name;
    @FXML
    private JFXDatePicker dob;
    @FXML
    private JFXTextField address;
    @FXML
    private JFXTextField pid;
    @FXML
    private JFXTextField phone;
    @FXML
    private JFXTextField tid;
    @FXML
    private JFXTextField Tfee;
    @FXML
    private JFXTextField room;
    @FXML
    private JFXTextField Efee;
    @FXML
    private JFXTextField eid;
    @FXML
    private TableView table;
    @FXML
    private TableColumn id;
    @FXML
    private TableColumn fname;
    @FXML
    private TableColumn lname;
    @FXML
    private TableColumn pdob;
    @FXML
    private TableColumn gender;
    @FXML
    private TableColumn Pphone;
    @FXML
    private TableColumn addr;

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
        fname.setCellValueFactory(new PropertyValueFactory<Patient, String>("PFname"));
        fname.setStyle("-fx-alignment: CENTER;");
        lname.setCellValueFactory(new PropertyValueFactory<Patient, String>("PLname"));
        lname.setStyle("-fx-alignment: CENTER;");
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

        Efee.setDisable(true);
        eid.setDisable(true);

//        DISPLAY ALL PATIENT TABLE
        try {
            this.statement = Info.connection.createStatement();
            showPatient();
        } catch (SQLException e) {}
    }

    @FXML
    private void switchScene(ActionEvent event) throws IOException {
        PatientType = ((JFXToggleButton) event.getSource()).isSelected();
        if (PatientType) {
//            OUTPATIENT choose
            tid.setDisable(true);
            tid.clear();
            room.setDisable(true);
            room.clear();
            Tfee.setDisable(true);
            Tfee.clear();

            Efee.setDisable(false);
            eid.setDisable(false);
        }
        else {
//            INPATIENT choose
            Efee.setDisable(true);
            Efee.clear();
            eid.setDisable(true);
            eid.clear();

            tid.setDisable(false);
            room.setDisable(false);
            Tfee.setDisable(false);
        }
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
        String pidValue = pid.getText();
        String pname = name.getText();
        String[] NamePart = pname.split(" ");
        String PDOB = dob.getValue().toString();
        String PAddress = address.getText();
        String PPhone = phone.getText();
        String PGender = ((JFXRadioButton) GenderGroup.getSelectedToggle()).getText();
        String gender;
        if (PGender == "Female")
            gender = "F";
        else
            gender="M";

        // INSERT PATIENT TABLE
        try {
            PreparedStatement update = Info.connection.prepareStatement(addQuery);
            update.setString(1, pidValue);
            update.setString(2, NamePart[0]);
            update.setString(3, NamePart[1]);
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

        if (PatientType)
        {
//            OUTPATIENT choose
            try {
//              Get RANDOM ID
                ResultSet doctor = statement.executeQuery(DOCTOR_rand);
                doctor.next();
                String DOC_ID = doctor.getString(1);
                ResultSet medication = statement.executeQuery(MID_rand);
                medication.next();
                int MID = medication.getInt(1);

//              Get Examinate Date - TODAY
                Date date = Calendar.getInstance().getTime();
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String EXDATE = dateFormat.format(date);

//              Get form
                int EXID = Integer.parseInt(eid.getText());
                int fee = Integer.parseInt(Efee.getText());

//              add OUTPATIENT
                PreparedStatement outpatient = Info.connection.prepareStatement(addOUTPATIENT);
                outpatient.setString(1, pidValue);
                outpatient.setString(2, DOC_ID);
                outpatient.executeUpdate();

                PreparedStatement exam = Info.connection.prepareStatement(addEXAMINATION);
                exam.setString(1, DOC_ID);
                exam.setString(2, pidValue);
                exam.setInt(3, EXID);
                exam.setString(4, EXDATE);
                exam.setInt(5, fee);
                exam.executeUpdate();

                PreparedStatement uses_exam = Info.connection.prepareStatement(addUSES_EXAM);
                uses_exam.setString(1, DOC_ID);
                uses_exam.setString(2, pidValue);
                uses_exam.setInt(3, EXID);
                uses_exam.setInt(4, MID);
                uses_exam.executeUpdate();

            } catch (SQLException e) {
                showAlert();
                return;
            }
        }
        else {
//            INPATIENT choose
            try{
                ResultSet doctor = statement.executeQuery(DOCTOR_rand);
                doctor.next();
                String DOC_ID = doctor.getString(1);
                ResultSet nurse = statement.executeQuery(NURSE_rand);
                nurse.next();
                String NURSE_ID = nurse.getString(1);
                ResultSet medication = statement.executeQuery(MID_rand);
                medication.next();
                int MID = medication.getInt(1);


                // Admision Date - Today
                Date date = Calendar.getInstance().getTime();
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String admissionDate = dateFormat.format(date);

                // Discharge Date
                Calendar c = Calendar.getInstance();
  		        c.add(Calendar.DATE, 30);
        	    Date d = c.getTime();
        	    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		        String dischargeDate = df.format(d);


                String PRoom = room.getText();
                int fee = Integer.parseInt(Tfee.getText());
                int TRID = Integer.parseInt(tid.getText());

                PreparedStatement inpatient = Info.connection.prepareStatement(addINPATIENT);
                inpatient.setString(1, pidValue);
                inpatient.setString(2, admissionDate);
                inpatient.setString(3, dischargeDate);
                inpatient.setString(4, PRoom);
                inpatient.setInt(5, fee);
                inpatient.setString(6, DOC_ID);
                inpatient.setString(7, NURSE_ID);
                inpatient.executeUpdate();

                PreparedStatement treatment = Info.connection.prepareStatement(addTREATMENT);
                treatment.setString(1, DOC_ID);
                treatment.setString(2, pidValue);
                treatment.setInt(3, TRID);
                treatment.executeUpdate();

                PreparedStatement uses_treatment = Info.connection.prepareStatement(addUSES_TREAT);
                uses_treatment.setString(1, DOC_ID);
                uses_treatment.setString(2, pidValue);
                uses_treatment.setInt(3, TRID);
                uses_treatment.setInt(4, MID);
                uses_treatment.executeUpdate();
            } catch (SQLException e) {
                showAlert();
                return;
            }

        }

        // CLEAR ALL
        pid.clear();
        name.clear();
        address.clear();
        phone.clear();
        Efee.clear();
        eid.clear();
        Tfee.clear();
        tid.clear();
        room.clear();
        try {
            showPatient();
        } catch (SQLException e) {}
    }

    @FXML
    public void onBackButtonPressed() throws IOException {

        LoginController.secondStage.setScene(LoginController.menuScene);

    }
}
