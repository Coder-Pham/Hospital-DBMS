package Model;

import javafx.beans.property.SimpleStringProperty;

public class PatientFee {
    private SimpleStringProperty MethodID;
    private int fee;

    public PatientFee(String MethodID, int fee) {
        this.MethodID = new SimpleStringProperty(MethodID);
        this.fee = fee;
    }

    public String getMethodID() {
        return MethodID.get();
    }

    public int getFee() {
        return fee;
    }
}
