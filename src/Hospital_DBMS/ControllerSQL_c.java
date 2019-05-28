package Hospital_DBMS;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import helpers.Info;
import helpers.PatientFee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ControllerSQL_c implements Initializable {
    @FXML
    private JFXButton submit;
    @FXML
    private JFXTextField pid;
    @FXML
    private Button backButton;
    @FXML
    private TableView table;
    @FXML
    private TableColumn methodID;
    @FXML
    private TableColumn fee;
    @FXML
    private Label type;
    @FXML
    private Label total;

    private Statement statement;
    private String Totalquery = "SELECT * FROM TABLE(TOTALMEDPRICE('%s'))";
    private String Type = "SELECT EXIST('%s') FROM DUAL";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.statement = Info.connection.createStatement();
        } catch (SQLException e) {}
    }

    @FXML
    private void setSubmit(ActionEvent event) throws IOException, SQLException {
        String PID = pid.getText();
        String query = String.format(Totalquery, PID);
        ResultSet AllFee = this.statement.executeQuery(query);
        final ObservableList<PatientFee> data = FXCollections.observableArrayList();
        int sum = 0;

        while (AllFee.next()) {
            PatientFee nxtFee = new PatientFee(AllFee.getString(1), AllFee.getInt(2));
            sum += AllFee.getInt(2);
            data.add(nxtFee);
        }

        methodID.setCellValueFactory(new PropertyValueFactory<PatientFee, String>("methodID"));
        fee.setCellValueFactory(new PropertyValueFactory<PatientFee, Integer>("fee"));
        methodID.setStyle("-fx-alignment: RIGHT;");
        methodID.setStyle("-fx-font-size: 18px;");
        fee.setStyle("-fx-alignment: CENTER;");
        fee.setStyle("-fx-font-size: 18px;");

        table.setItems(data);

        query = String.format(Type, PID);
        ResultSet patientType = this.statement.executeQuery(query);
        patientType.next();
        if (patientType.getInt(1) == 1)
            type.setText("Patient Type: INPATIENT");
        else
            type.setText("Patient Type: OUTPATIENT");

        total.setText(String.format("Total: \t\t$%d", sum));
    }

    @FXML
    public void onBackButtonPressed() throws IOException {

        LoginController.secondStage.setScene(LoginController.menuScene);

    }
}
