package com.haydenhuynh;

import com.jfoenix.controls.JFXDecorator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Border;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Main extends Application {

    public static Stage firstStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("LoginView.fxml"));

//        JFXDecorator decorator = new JFXDecorator(primaryStage, root, false, false, true);
//        decorator.setCustomMaximize(false);
//        decorator.setBorder(Border.EMPTY);
//        primaryStage.initStyle(StageStyle.TRANSPARENT);

        Scene scene = new Scene(root, 514, 242);

        primaryStage.setTitle("HOSPITAL UTILITIES");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);

        firstStage = primaryStage;

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
