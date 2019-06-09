package com.haydenhuynh;

import Model.Info;
import Model.Patient_Button_Item;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;


public class UpdateController implements Initializable {
    @FXML
    private VBox inpatient_vbox;
    @FXML
    private VBox outpatient_vbox;
    @FXML
    private HBox inpatient_hbox;
    @FXML
    private HBox outpatient_hbox;
    @FXML
    private JFXRadioButton inpatient;
    @FXML
    private JFXRadioButton outpatient;
    
    
    @FXML
    private JFXToggleButton update;
    @FXML
    private Button back;
    @FXML
    private JFXComboBox doctor_drop;  
    @FXML
    private TableView tbl_PatientView;
    @FXML
    private JFXRadioButton male;
    @FXML
    private JFXRadioButton female;
    @FXML
    private JFXTextField fname, lname;
    @FXML
    private JFXDatePicker dob;
    @FXML
    private JFXTextField address;
    @FXML
    private JFXTextField pid;
    @FXML
    private JFXTextField phone;
    private ToggleGroup GenderGroup;
    private ToggleGroup PatientGroup;
    
    
    //****************************
    @FXML
    private JFXDatePicker admissiondate;
    @FXML
    private JFXDatePicker dischargedate;
    @FXML
    private JFXTextField diagnosis;
    @FXML
    private JFXTextField sickroom;
    @FXML
    private JFXTextField fee;
    @FXML
    private JFXComboBox jfx_doctor_dropbox;
    @FXML
    private JFXComboBox jfx_nurse_dropbox;
    
    
    //***************************
    @FXML
    private JFXButton update_btn;
    
    private ArrayList<Button> button_list;
    
    private ObservableList<Patient_Button_Item> list_item;
    
    private ObservableList<String> doctor_droplist = FXCollections.observableArrayList(); 
    
    private ObservableList<String> nurse_droplist = FXCollections.observableArrayList(); 
    
    @Override
    public  void initialize(URL url, ResourceBundle rb) {
//        update.setSelected(true);
        this.lockall();
        
        
        GenderGroup = new ToggleGroup();
        this.PatientGroup = new ToggleGroup();
        
        update_btn.setOnAction(this::ButtonAction);
        
        
        getDoctor_droplist(doctor_droplist);
        this.getNurse_droplist(nurse_droplist);
        
        doctor_drop.setItems(doctor_droplist);
        
        this.jfx_doctor_dropbox.setItems(doctor_droplist);
        this.jfx_nurse_dropbox.setItems(nurse_droplist);
        
        list_item =  FXCollections.observableArrayList();  
        getList_Patient(list_item);
       
        
        TableColumn firstCol = new TableColumn("PAITENT ID");
        TableColumn secondCol = new TableColumn("PATIENT FULL NAME");
        TableColumn thirdCol = new TableColumn("ACTION");
        
        tbl_PatientView.getColumns().addAll(firstCol,secondCol,thirdCol);
        
        firstCol.setCellValueFactory(new PropertyValueFactory<Patient_Button_Item,String>("PATIENT_ID"));
        secondCol.setCellValueFactory(new PropertyValueFactory<Patient_Button_Item,String>("PATIENT_FULLNAME"));
        thirdCol.setCellValueFactory(new PropertyValueFactory<Patient_Button_Item,String>("choose_button"));
        
        tbl_PatientView.setItems(list_item);
        
        
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
    
    private void getDoctor_droplist (ObservableList<String> returnList)
    {
        String query = "SELECT EID_DOC,EFNAME || ' ' || ELNAME FROM DOCTOR JOIN EMPLOYEE ON EID_DOC = EID";
        try {
            PreparedStatement ps = Info.connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
               //System.out.println(rs.getString(1) + "-" + rs.getString(2));
                returnList.add(rs.getString(1) + "-" + rs.getString(2));
            }
        } catch (Exception e) {System.out.println(e);
        }
    }
    
    private void getNurse_droplist (ObservableList<String> returnList)
    {
        String query = "SELECT EID_NUR,EFNAME || ' ' || ELNAME FROM NURSE JOIN EMPLOYEE ON EID_NUR = EID";
        try {
            PreparedStatement ps = Info.connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
               //System.out.println(rs.getString(1) + "-" + rs.getString(2));
                returnList.add(rs.getString(1) + "-" + rs.getString(2));
            }
        } catch (Exception e) {System.out.println(e);
        }
    }
    
    private void getList_Patient(ObservableList<Patient_Button_Item> returnList)
    {
        String query = "SELECT PID,PFNAME || ' ' || PLNAME FROM PATIENT";
        button_list = new ArrayList<>();
        //System.out.println(button_list.size());
        try {
            PreparedStatement ps = Info.connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
               //System.out.println(rs.getString(1) + "-" + rs.getString(2));
                button_list.add(new Button());
                button_list.get(button_list.size()-1).setOnAction(this::handleButtonAction);
                
                //System.out.println(rs.getString(1) + "-" + rs.getString(2));
                //System.out.println(button_list.size());
                
                returnList.add(new Patient_Button_Item(rs.getString(1),rs.getString(2),button_list.get(button_list.size()-1)));
            }
        } catch (Exception e) {System.out.println(e);
        }
    }
    
    private void handleButtonAction(ActionEvent event)
    {
        for(int i=0;i<button_list.size();i++)
        {
            if(event.getSource() == button_list.get(i))
            {
                this.lockall();
                this.clearAll();
                this.pid.setText(list_item.get(i).getPATIENT_ID());
                this.showPatientDetail(this.pid.getText());
                if(this.checkCase(this.pid.getText()))
                {
                    this.case1(this.pid.getText());
                }
                else
                {
                    this.case2();
                }
            }
        }
    }
    
    private void showPatientDetail(String PID)
    {
        String query = "SELECT * FROM PATIENT WHERE PID = '" + PID + "'";
        //System.out.println(query);
        try {
            PreparedStatement ps = Info.connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
               this.fname.setText(rs.getString(2));
               this.lname.setText(rs.getString(3));
             
               if(rs.getDate(4) == null)
               {
                   this.dob.setValue(null);
               }
               else
               {
                   this.dob.setValue(getLocalDateFromDate(rs.getDate(4)));
               }
               
               male.setToggleGroup(GenderGroup);
               
               female.setToggleGroup(GenderGroup);
               if(rs.getString(5).equals("M"))
               {
                   male.setSelected(true);
                   female.setSelected(false);
               }
               else
               {
                   male.setSelected(false);
                   female.setSelected(true);          
               }
               this.phone.setText(rs.getString(6));
               this.address.setText(rs.getString(7));
            }
        } catch (Exception e) {System.out.println(e);
        }
    }

    public static LocalDate getLocalDateFromDate(Date date){
        return LocalDate.from(Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()));
    }
    
    private boolean checkCase(String PID)
    {
        String query = "SELECT * FROM INPATIENT WHERE PID_IN = '" + PID + "'";
        String query2 = "SELECT * FROM OUTPATIENT WHERE PID_OUT = '" + PID + "'";
        boolean returnValue = false;
        try {
            PreparedStatement ps = Info.connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                returnValue = true;
            }
        } catch (Exception e) {System.out.println(e);
        }
        
        try {
            PreparedStatement ps = Info.connection.prepareStatement(query2);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                returnValue = true;
            }
        } catch (Exception e) {System.out.println(e);
        }
        
        return returnValue;
    }
    
    private void case1(String PID)
    {
        String query = "SELECT * FROM INPATIENT WHERE PID_IN = '" + PID + "'";
        String query2 = "SELECT * FROM OUTPATIENT WHERE PID_OUT = '" + PID + "'";
        
        try {
            PreparedStatement ps = Info.connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                this.inpatient.setSelected(true);
                this.inpatient_hbox.setDisable(false);
                this.inpatient_vbox.setDisable(false);
                if(rs.getDate(2) == null)
                {
                   this.admissiondate.setValue(null);
                }
                else {
                   this.admissiondate.setValue(getLocalDateFromDate(rs.getDate(2)));
                }
                
               if(rs.getDate(3) == null) {
                   this.dischargedate.setValue(null);
               }
               else {
                   this.dischargedate.setValue(getLocalDateFromDate(rs.getDate(3)));
               }
               this.diagnosis.setText(rs.getString(4));
               this.sickroom.setText(rs.getString(5));
               this.fee.setText(rs.getString(6));
               
               if(rs.getInt(7) != 0) {
                    for(int i=0;i<this.doctor_droplist.size();i++)
                    {
                        if(this.doctor_droplist.get(i).contains(rs.getString(7)))
                        {
                            this.jfx_doctor_dropbox.setValue(this.doctor_droplist.get(i));
                        }
                    }
               }
               
               if(rs.getInt(8) != 0) {
                    for(int i=0;i<this.nurse_droplist.size();i++)
                    {
                        if(this.nurse_droplist.get(i).contains(rs.getString(8)))
                        {
                            this.jfx_nurse_dropbox.setValue(this.nurse_droplist.get(i));
                        }
                    }
               }
            } else {
                try {
                    PreparedStatement ps2 = Info.connection.prepareStatement(query2);
                    ResultSet rs2 = ps2.executeQuery();
                    if(rs2.next())
                    {
                        this.outpatient.setSelected(true);
                        this.outpatient_hbox.setDisable(false);
                        this.outpatient_vbox.setDisable(false);
                        
                        
                        if(rs2.getInt(2) != 0)
                        {
                            for(int i=0;i<this.doctor_droplist.size();i++)
                            {
                                if(this.doctor_droplist.get(i).contains(rs2.getString(2)))
                                {
                                    this.doctor_drop.setValue(this.doctor_droplist.get(i));
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    private void case2()
    {
        this.inpatient_vbox.setDisable(false);     
        this.outpatient_vbox.setDisable(false);
       
        
        this.inpatient.setToggleGroup(this.PatientGroup);
        this.outpatient.setToggleGroup(this.PatientGroup);
        
        this.inpatient.setOnAction(this::inpatientAction);
        this.outpatient.setOnAction(this::outpatientAction);
        
    }
    
    private void inpatientAction(ActionEvent event)
    {
        this.inpatient_hbox.setDisable(false);
        this.outpatient_hbox.setDisable(true);
    }
    
    private void outpatientAction(ActionEvent event)
    {
        this.inpatient_hbox.setDisable(true);
        this.outpatient_hbox.setDisable(false);
    }
    
    private void lockall()
    {
        this.inpatient_vbox.setDisable(true);
        this.outpatient_vbox.setDisable(true);
        this.inpatient_hbox.setDisable(true);
        this.outpatient_hbox.setDisable(true);
    }
    
    private void clearAll()
    {
        this.pid.setText(null);
        this.fname.setText(null);
        this.lname.setText(null);
        this.dob.setValue(null);
        this.phone.setText(null);
        this.address.setText(null);
        this.female.setSelected(false);
        this.male.setSelected(false);
        this.inpatient.setSelected(false);
        this.outpatient.setSelected(false);
        this.doctor_drop.setValue(null);
        this.admissiondate.setValue(null);
        this.dischargedate.setValue(null);
        this.diagnosis.setText(null);
        this.sickroom.setText(null);
        this.fee.setText(null);
        this.jfx_doctor_dropbox.setValue(null);
        this.jfx_nurse_dropbox.setValue(null);
    }
    
    private void CaseInpatient()
    {
        if(this.updateInpatient())
        {
            System.out.println("Success");
        }
        if(this.updatePatient())
        {
            System.out.println("Success");   
        }
        this.refreshTable();
        this.lockall();
        this.clearAll();
    }
    
    private void CaseOutpatient()
    {
        if(this.updateOutpatent())
        {
             System.out.println("Success");
        }
        if(this.updatePatient())
        {
            System.out.println("Success");
            
        }
        this.refreshTable();
        this.lockall();
        this.clearAll();
    }
    
    private void CaseNot()
    {
        if(this.updatePatient())
        {
            System.out.println("Success");
            
        }
        this.refreshTable();
        this.lockall();
        this.clearAll();
    }
    
    public boolean updatePatient()
    {
        String pidValue;
        String FName = "";
        String LName = "";
        String PDOB = "";
        String PAddress = "";
        String PPhone = "";
        String PGender = "";

        pidValue = pid.getText();
        FName = fname.getText();
        LName = lname.getText();
        try {
            PDOB = dob.getValue().toString();
        } catch (NullPointerException e)
        {
            PDOB = "";
        }
        PAddress = address.getText();
        PPhone = phone.getText();
        PGender = ((JFXRadioButton) GenderGroup.getSelectedToggle()).getText();
        String gender;
        if (PGender == "Female")
            gender = "F";
        else
            gender="M";

        String query = "UPDATE PATIENT SET "
                                            + "PFNAME = ?,"
                                            + "PLNAME = ?,"
                                            + "PDOB = TO_DATE(?, 'yyyy-mm-dd'),"
                                            + "PGENDER = ?,"
                                            + "PPHONE = ?,"
                                            + "PADDRESS = ? WHERE PID = ?";                                  
        try {
            PreparedStatement ps = Info.connection.prepareStatement(query);
            
            
            ps.setString(1, FName);
            ps.setString(2, LName);
            ps.setString(3, PDOB);
            ps.setString(4, gender);
            ps.setString(5, PPhone);
            ps.setString(6, PAddress);
            
            ps.setString(7, pidValue);
            
            
           
            return ps.executeUpdate() > 0;
        } catch (Exception e) {System.out.println(e);
        }
        return false;
    }
    
    public boolean updateInpatient()
    {
        String pidValue;
        String Addmisssiondate = "";
        String Dischargedate = "";
        String Diagnosis = "";
        String Sickroom = "";
        String Fee = "";
        String Doctor = "";
        String Nurse = "";

        pidValue = pid.getText();
        
        try {
            Addmisssiondate = this.admissiondate.getValue().toString();
        } catch (NullPointerException e)
        {
            Addmisssiondate = "";
        }
        
         try {
            Dischargedate = this.dischargedate.getValue().toString();
        } catch (NullPointerException e)
        {
            Dischargedate = "";
        }
        
        Diagnosis = this.diagnosis.getText();
        Sickroom = this.sickroom.getText();
        Fee = this.fee.getText();
        
        try {
            Doctor = this.jfx_doctor_dropbox.getValue().toString().substring(0,9);
        } catch (NullPointerException e)
        {
            Doctor = "";
        }
        
         try {
            Nurse = this.jfx_nurse_dropbox.getValue().toString().substring(0,9);
        } catch (NullPointerException e)
        {
            Nurse = "";
        }
        
       
        
       
        
        if(this.isInpatient(pidValue))
        {
            String query = "UPDATE INPATIENT SET "
                                            + "PADMISSIONDATE = TO_DATE(?, 'yyyy-mm-dd'),"
                                            + "PDISCHARGEDATE = TO_DATE(?, 'yyyy-mm-dd'),"
                                            + "PDIAGNOSIS = ?,"
                                            + "PSICKROOM = ?,"
                                            + "PFEE = ?,"
                                            + "EID_DOC = ?,"
                                            + "EID_NUR = ? WHERE PID_IN = ?";                                  
            try {
                PreparedStatement ps = Info.connection.prepareStatement(query);


                ps.setString(1, Addmisssiondate);
                ps.setString(2, Dischargedate);
                ps.setString(3, Diagnosis);
                ps.setString(4, Sickroom);
                ps.setString(5, Fee);
                ps.setString(6, Doctor);         
                ps.setString(7, Nurse);

                ps.setString(8, pidValue);



                return ps.executeUpdate() > 0;
            } catch (Exception e) {System.out.println(e);
            }
        }
        else
        {
            String addQuery = "INSERT INTO INPATIENT VALUES (?, TO_DATE(?, 'yyyy-mm-dd'), TO_DATE(?, 'yyyy-mm-dd'), ?, ?, ?, ?, ?)";
            try {
                PreparedStatement ps = Info.connection.prepareStatement(addQuery);

                ps.setString(1, pidValue);
                ps.setString(2, Addmisssiondate);
                ps.setString(3, Dischargedate);
                ps.setString(4, Diagnosis);
                ps.setString(5, Sickroom);
                ps.setString(6, Fee);
                ps.setString(7, Doctor);         
                ps.setString(8, Nurse);

                return ps.executeUpdate() > 0;
            } catch (Exception e) {System.out.println(e);
            }
        }
        
        return false;
    }
    
    public boolean updateOutpatent()
    {
        String pidValue;
        String Doctor = "";
       
        pidValue = pid.getText();
        
        try {
            Doctor = this.doctor_drop.getValue().toString().substring(0,9);
        } catch (NullPointerException e)
        {
            Doctor = "";
        }
    
        if(this.isOutpatient(pidValue))
        {
            String query = "UPDATE OUTPATIENT SET "                                     
                                            + "EID_DOC = ? WHERE PID_OUT = ?";                                  
            try {
                PreparedStatement ps = Info.connection.prepareStatement(query);


                ps.setString(1, Doctor);               

                ps.setString(2, pidValue);



                return ps.executeUpdate() > 0;
            } catch (Exception e) {System.out.println(e);
            }
        }
        else
        {
            String addQuery = "INSERT INTO OUTPATIENT VALUES (?, ?)";
            try {
                PreparedStatement ps = Info.connection.prepareStatement(addQuery);

                ps.setString(1, pidValue);
                ps.setString(2, Doctor);

                return ps.executeUpdate() > 0;
            } catch (Exception e) {System.out.println(e);
            }
        }
        
        return false;
    }
    
    private void ButtonAction(ActionEvent event)
    {
        if(!this.pid.getText().equals(""))
        {
            if(this.inpatient.isSelected())
            {
                CaseInpatient();
            }
            else if(this.outpatient.isSelected())
            {
                this.CaseOutpatient();
            }
            else
            {
                this.CaseNot();
            }
        }
         
    }
    
    private void refreshTable()
    {
        list_item =  FXCollections.observableArrayList();  
        getList_Patient(list_item);
        tbl_PatientView.setItems(list_item);
    }
    
    private boolean isInpatient(String PID)
    {
        boolean returnValue = false;
        String query = "SELECT * FROM INPATIENT WHERE PID_IN = '" + PID + "'";
        
        try {
            PreparedStatement ps = Info.connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                returnValue = true;
            }
        } catch (Exception e) {System.out.println(e);
        }
        return returnValue;
    }        
    
    private boolean isOutpatient(String PID)
    {
        boolean returnValue = false;
        String query = "SELECT * FROM OUTPATIENT WHERE PID_OUT = '" + PID + "'";
        
        try {
            PreparedStatement ps = Info.connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                returnValue = true;
            }
        } catch (Exception e) {System.out.println(e);
        }
        return returnValue;
    }        
}
