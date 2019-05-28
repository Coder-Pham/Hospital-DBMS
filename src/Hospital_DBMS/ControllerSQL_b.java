package Hospital_DBMS;

import helpers.Info;
import helpers.PatientSQL_b;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ControllerSQL_b {

    private Connection conn;
    private Statement statement;
    private ResultSet resultSet;
    private ObservableList<PatientSQL_b> patientLists = FXCollections.observableArrayList();
    private String query = "SELECT PID,PFNAME || ' ' || PLNAME AS PATIENT_NAME" +
            " FROM (PATIENT JOIN" +
            "((SELECT PID_OUT AS PID_IO,EID_DOC FROM OUTPATIENT) " +
            " UNION (SELECT PID_IN AS PID_IO,EID_DOC FROM INPATIENT)) " +
            " ON PID = PID_IO) " +
            " JOIN EMPLOYEE ON EID = EID_DOC " +
            "WHERE EFNAME || ' ' || ELNAME = 'Nguyen Van A'";

    @FXML
    private TableView InfoTable;

    @FXML
    private TableColumn col1;

    @FXML
    private TableColumn col2;

    @FXML
    public void showTable() throws SQLException {

        conn = Info.connection;

        statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        resultSet = statement.executeQuery(query);


        while(resultSet.next()) {

            patientLists.add(new PatientSQL_b(resultSet.getString(1),
                    resultSet.getString(2)));
        }

        col1.setCellValueFactory(new PropertyValueFactory<>("PID"));
        col2.setCellValueFactory(new PropertyValueFactory<>("Patient_Name"));

        InfoTable.setItems(patientLists);
    }

    @FXML
    public void onBackButtonPressed() {

        LoginController.secondStage.setScene(LoginController.menuScene);

    }

}
