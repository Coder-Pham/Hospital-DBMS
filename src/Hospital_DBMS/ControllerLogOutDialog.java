package Hospital_DBMS;

import helpers.Info;

public class ControllerLogOutDialog {

    public void logOut() {
        Info.username = "";
        Info.password = "";
        Info.connection = null;

        Main.firstStage.show();

        LoginController.secondStage.close();
    }
}
