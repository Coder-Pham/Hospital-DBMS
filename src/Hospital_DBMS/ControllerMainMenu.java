package Hospital_DBMS;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import java.io.IOException;
import java.util.Optional;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.layout.VBox;

public class ControllerMainMenu {

    @FXML
    private VBox mainVBox;

    @FXML
    private JFXButton SQL_a;

    @FXML
    private JFXButton SQL_b;

    @FXML
    private JFXButton SQL_c;

    @FXML
    private JFXButton SQL_d;

    @FXML
    private JFXButton function1;

    @FXML
    private JFXButton function2;

    @FXML
    private JFXButton function3;

    @FXML
    private  JFXButton function4;


    @FXML
    public void showLogOutDialog() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainVBox.getScene().getWindow());
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("LogOutDialog.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());

        } catch(IOException e) {
            System.out.println("Couldn't load the dialog");
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Button OkButton = (Button) dialog.getDialogPane().lookupButton(ButtonType.OK);
        Button cancelButton = (Button) dialog.getDialogPane().lookupButton(ButtonType.CANCEL);
        cancelButton.setDefaultButton(true);
        OkButton.setDefaultButton(false);

        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
            ControllerLogOutDialog controller = fxmlLoader.getController();

            controller.logOut();
        }
    }

    @FXML
    public void onFirstFeaturePressed() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("SQL_a.fxml"));

        LoginController.secondStage.setScene(new Scene(root, 1300, 750));

        SQL_a.setDisableVisualFocus(true);

    }

    @FXML
    public void onSecondFeaturePressed() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("SQL_b.fxml"));

        LoginController.secondStage.setScene(new Scene(root, 1300, 750));

        SQL_b.setDisableVisualFocus(true);

    }

    @FXML
    public void onThirdFeaturePressed() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("SQL_c.fxml"));

        LoginController.secondStage.setScene(new Scene(root, 1300, 750));

        SQL_c.setDisableVisualFocus(true);

    }

    @FXML
    public void onFourthFeaturePressed() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("SQL_d.fxml"));

        LoginController.secondStage.setScene(new Scene(root, 1300, 750));

        SQL_d.setDisableVisualFocus(true);

    }

    @FXML
    public void onFifthFeaturePressed() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("FunctionOne.fxml"));

        LoginController.secondStage.setScene(new Scene(root, 1300, 750));

        function1.setDisableVisualFocus(true);

    }

    @FXML
    public void onSixthFeaturePressed() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("FunctionTwo.fxml"));

        LoginController.secondStage.setScene(new Scene(root, 1300, 750));

        function2.setDisableVisualFocus(true);

    }

    @FXML
    public void onSeventhFeaturePressed() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("FunctionThree.fxml"));

        LoginController.secondStage.setScene(new Scene(root, 1300, 750));

        function3.setDisableVisualFocus(true);

    }

    @FXML
    public void onEighthFeaturePressed() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("FunctionFour.fxml"));

        LoginController.secondStage.setScene(new Scene(root, 1300, 750));

        function4.setDisableVisualFocus(true);

    }
}
