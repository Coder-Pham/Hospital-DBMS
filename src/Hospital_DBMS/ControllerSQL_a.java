package com.haydenhuynh;

import java.io.IOException;
import java.sql.*;
import Model.PatientSQL_a;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import Model.Info;


public class ControllerSQL_a {

    private Connection conn;
    private Statement statement1;
    private Statement statement2;
    private ResultSet resultSet1;
    private ResultSet resultSet2;
    private ObservableList<PatientSQL_a> patientFees;
    private String queryFee1 = "SELECT PFee FROM INPATIENT";
    private String queryFee2 = "UPDATE INPATIENT SET PFee = PFee + PFee * 0.1 WHERE INPATIENT.PAdmissionDate >= TO_DATE('01-SEP-2017', 'DD-MON-YY')";
    private String queryJoin = "SELECT PID_In, PFName || ' ' || PLName, PFee FROM PATIENT, INPATIENT WHERE PATIENT.PID = INPATIENT.PID_In AND INPATIENT.PAdmissionDate >= TO_DATE('01-SEP-2017', 'DD-MON-YY')";

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
    private ScrollPane scrollpane;

    public void initialize() {
        scrollpane.setFitToHeight(true);

        scrollpane.setFitToWidth(true);

        conn = Info.connection;

        patientFees = FXCollections.observableArrayList();
    }

    @FXML
    public void onConfirmButtonPressed() throws SQLException {

        InfoTable.getItems().clear();

        statement1 = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        statement2 = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

        resultSet1 = statement1.executeQuery(queryFee1);

        statement2.execute(queryFee2);

        resultSet2 = statement2.executeQuery(queryJoin);

        while(resultSet2.next() && resultSet1.next()) {

            patientFees.add(new PatientSQL_a(resultSet2.getString(1),
                                            resultSet2.getString(2),
                                            resultSet1.getString(1),
                                            resultSet2.getString(3)));
        }

        col1.setCellValueFactory(new PropertyValueFactory<>("PID_In"));
        col2.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        col3.setCellValueFactory(new PropertyValueFactory<>("initialFee"));
        col4.setCellValueFactory(new PropertyValueFactory<>("afterwardFee"));

        InfoTable.setItems(patientFees);
    }

    @FXML
    public void onBackButtonPressed() throws IOException {

        LoginController.secondStage.setScene(LoginController.menuScene);

    }
}
