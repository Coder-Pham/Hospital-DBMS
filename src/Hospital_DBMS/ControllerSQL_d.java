/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hospital_DBMS;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.fxml.Initializable;
import helpers.Info;
import helpers.SORT_DOCTOR_ITEM;
import helpers.DateValidation;
import java.io.IOException;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.util.Callback;
import java.sql.*;
import oracle.sql.ArrayDescriptor;

/**
 * FXML Controller class
 *
 * @author jayhuynh
 */
public class ControllerSQL_d implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private JFXTextField txtStartDate;
    @FXML
    private JFXTextField txtEndDate;
    @FXML
    private JFXButton btnSort;
    @FXML
    private Label errorStart;
    @FXML
    private Label errorEnd;
    @FXML
    private JFXTreeTableView<SORT_DOCTOR_ITEM> tblSort_Doctor;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        errorStart.setPrefSize(0, 0);
        errorStart.setVisible(false);
        errorEnd.setPrefSize(0, 0);
        errorEnd.setVisible(false);
        
        JFXTreeTableColumn<SORT_DOCTOR_ITEM,String> DOC_ID= new JFXTreeTableColumn<>("DOCTOR ID");
        DOC_ID.setPrefWidth(200);
        DOC_ID.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<SORT_DOCTOR_ITEM, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<SORT_DOCTOR_ITEM, String> param) {
                return param.getValue().getValue().getDOC_ID();
            }
        } );
        
        JFXTreeTableColumn<SORT_DOCTOR_ITEM,String> DOC_NAME= new JFXTreeTableColumn<>("DOCTOR FULL NAME");
        DOC_NAME.setPrefWidth(400);
        DOC_NAME.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<SORT_DOCTOR_ITEM, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<SORT_DOCTOR_ITEM, String> param) {
                return param.getValue().getValue().getDOC_FNAME() ;
            }
        } );
        
        JFXTreeTableColumn<SORT_DOCTOR_ITEM,String> DOC_PATIENT= new JFXTreeTableColumn<>("NUMBER OF PATIENT");
        DOC_PATIENT.setPrefWidth(233);
        DOC_PATIENT.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<SORT_DOCTOR_ITEM, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<SORT_DOCTOR_ITEM, String> param) {
                return param.getValue().getValue().getNO_PATIENT();
            }
        } );
        
        ObservableList<SORT_DOCTOR_ITEM> list_item = FXCollections.observableArrayList(); 
         
        TreeItem<SORT_DOCTOR_ITEM> root = new RecursiveTreeItem<SORT_DOCTOR_ITEM>(list_item, RecursiveTreeObject::getChildren);
        tblSort_Doctor.getColumns().setAll(DOC_ID,DOC_NAME,DOC_PATIENT); 
        tblSort_Doctor.setRoot(root);
        tblSort_Doctor.setShowRoot(false);
    }    
    @FXML
    private void sortDoc(ActionEvent event) throws IOException {
        errorStart.setPrefSize(0, 0);
        errorStart.setVisible(false);
        errorEnd.setPrefSize(0, 0);
        errorEnd.setVisible(false);
        
        String strDate = txtStartDate.getText();
        String endDate = txtEndDate.getText();
        
        if(new DateValidation().checkDate(strDate))
        {
            if(new DateValidation().checkDate(endDate))
            {
                strDate = new DateValidation().convertDate(strDate);
                endDate = new DateValidation().convertDate(endDate);


                ObservableList<SORT_DOCTOR_ITEM> list_item = FXCollections.observableArrayList(); 
                getListDoc(list_item,strDate,endDate);
                TreeItem<SORT_DOCTOR_ITEM> root = new RecursiveTreeItem<SORT_DOCTOR_ITEM>(list_item,RecursiveTreeObject::getChildren);
                tblSort_Doctor.setRoot(root);
                tblSort_Doctor.setShowRoot(false);
            }
            else
            {
               errorEnd.setPrefSize(85, 17);
               errorEnd.setVisible(true); 
            }
        }
        else
        {           
            errorStart.setPrefSize(85, 17);
            errorStart.setVisible(true);  
        }
        
        

    }
    
    private void getListDoc(ObservableList<SORT_DOCTOR_ITEM> list_item,String strDate,String endDate)
    {
        try {       
            String command = "{call LIST_DOC2(to_date('" + 
                    strDate + 
                    "','DD-MON-YY'),to_date('" + 
                    endDate +
                    "','DD-MON-YY'),?,?,?)}";
            CallableStatement cstmt = Info.connection.prepareCall(command);

            @Deprecated
            ArrayDescriptor desc = ArrayDescriptor.createDescriptor("DOCID_ARRAY", Info.connection);
            @Deprecated
            ArrayDescriptor desc2 = ArrayDescriptor.createDescriptor("DOCNAME_ARRAY", Info.connection);
            @Deprecated
            ArrayDescriptor desc3 = ArrayDescriptor.createDescriptor("DOCPATIENT_ARRAY", Info.connection);
            
            cstmt.registerOutParameter(1,Types.ARRAY,"MANAGER.DOCID_ARRAY");
            cstmt.registerOutParameter(2,Types.ARRAY,"MANAGER.DOCNAME_ARRAY");
            cstmt.registerOutParameter(3,Types.ARRAY,"MANAGER.DOCPATIENT_ARRAY");

            cstmt.execute();
        
            Array dNames =  cstmt.getArray(1);
            Array dNames2 =  cstmt.getArray(2);
            Array dNames3 =  cstmt.getArray(3);
            
            ResultSet rs = dNames.getResultSet();
            ResultSet rs2 = dNames2.getResultSet();
            ResultSet rs3 = dNames3.getResultSet();
            while(rs.next() && rs2.next() && rs3.next())
            {
                list_item.add(new SORT_DOCTOR_ITEM(rs.getString(2),rs2.getString(2),String.valueOf(rs3.getInt(2))));
            }
            cstmt.close();
            

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onBackButtonPressed() throws IOException {

        LoginController.secondStage.setScene(LoginController.menuScene);

    }
    
}
