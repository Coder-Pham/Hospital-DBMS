package com.haydenhuynh;

import com.jfoenix.controls.JFXToggleButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UpdateController implements Initializable {
    @FXML
    private JFXToggleButton update;
    @FXML
    private Button back;

    @Override
    public  void initialize(URL url, ResourceBundle rb) {
        update.setSelected(true);
    }

    @FXML
    private void switchPatient(ActionEvent event) throws  IOException {

    }

    @FXML
    private void switchScene(ActionEvent event) throws  IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FunctionTwo.fxml"));
        LoginController.secondStage.setScene(new Scene(root, 1300, 750));
    }

    @FXML
    private void onBackButtonPressed() throws IOException {
        LoginController.secondStage.setScene(LoginController.menuScene);
    }
}
