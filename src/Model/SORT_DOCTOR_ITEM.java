/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author jayhuynh
 */
public class SORT_DOCTOR_ITEM extends RecursiveTreeObject<SORT_DOCTOR_ITEM>{
    private StringProperty DOC_ID;
    private StringProperty DOC_FNAME;
    private StringProperty NO_PATIENT;

    public SORT_DOCTOR_ITEM(String DOC_ID, String DOC_FNAME, String NO_PATIENT) {
        this.DOC_ID = new SimpleStringProperty(DOC_ID);
        this.DOC_FNAME = new SimpleStringProperty(DOC_FNAME);
        this.NO_PATIENT = new SimpleStringProperty(NO_PATIENT);
    }

    public StringProperty getDOC_ID() {
        return DOC_ID;
    }

    public void setDOC_ID(StringProperty DOC_ID) {
        this.DOC_ID = DOC_ID;
    }

    public StringProperty getDOC_FNAME() {
        return DOC_FNAME;
    }

    public void setDOC_FNAME(StringProperty DOC_FNAME) {
        this.DOC_FNAME = DOC_FNAME;
    }

    public StringProperty getNO_PATIENT() {
        return NO_PATIENT;
    }

    public void setNO_PATIENT(StringProperty NO_PATIENT) {
        this.NO_PATIENT = NO_PATIENT;
    }
    
}
