/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;

/**
 *
 * @author jayhuynh
 */
public class Patient_Button_Item {
    private SimpleStringProperty PATIENT_ID;
    private SimpleStringProperty PATIENT_FULLNAME;
    private Button choose_button;

    public Patient_Button_Item(String PATIENT_ID, String PATIENT_FULLNAME, Button choose_button) {
        this.PATIENT_ID = new SimpleStringProperty(PATIENT_ID);
        this.PATIENT_FULLNAME = new SimpleStringProperty(PATIENT_FULLNAME);
        this.choose_button = choose_button;
        this.choose_button.setText("Choose");
    }

    public String getPATIENT_ID() {
        return PATIENT_ID.get();
    }

    public void setPATIENT_ID(SimpleStringProperty PATIENT_ID) {
        this.PATIENT_ID = PATIENT_ID;
    }

    public String getPATIENT_FULLNAME() {
        return PATIENT_FULLNAME.get();
    }

    public void setPATIENT_FULLNAME(SimpleStringProperty PATIENT_FULLNAME) {
        this.PATIENT_FULLNAME = PATIENT_FULLNAME;
    }

    public Button getChoose_button() {
        return choose_button;
    }

    public void setChoose_button(Button choose_button) {
        this.choose_button = choose_button;
    }

    
    
    
}
