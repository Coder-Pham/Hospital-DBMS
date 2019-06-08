package com.haydenhuynh;

import Model.Info;

public class ControllerLogOutDialog {

    public void logOut() {
        Info.username = "";
        Info.password = "";
        Info.connection = null;

        Main.firstStage.show();

        LoginController.secondStage.close();
    }
}
