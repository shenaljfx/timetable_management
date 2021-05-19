/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.JOptionPane;


/**
 * FXML Controller class
 *
 * @author T.V.Thimira Isiwara
 */
public class Add_Working_Days_HoursController implements Initializable {

    @FXML
    private Label NoOfWorkingDays;
    @FXML
    private Label WorkingDays;
    @FXML
    private Label WorkingPerTimeDay;
    @FXML
    private Label ID;
    @FXML
    private TextField txtID;
    @FXML
    private TableView<Add_Working_Days_Hours_DB_Connect> DescWorkingDays_AndHours;
    @FXML
    private TableColumn<Add_Working_Days_Hours_DB_Connect, Integer> CoID;
    @FXML
    private TableColumn<Add_Working_Days_Hours_DB_Connect, Integer> CoNoOfWorkingDays;
    @FXML
    private TableColumn<Add_Working_Days_Hours_DB_Connect, String> CoWorking_Days;
    @FXML
    private TableColumn<Add_Working_Days_Hours_DB_Connect, Integer> CoHours;
    @FXML
    private TableColumn<Add_Working_Days_Hours_DB_Connect, Integer> CoMinutes;

    // check box in monday,tuesday,wednesday,thrusday, firday, saturday , sunday
    @FXML
    private CheckBox chkMonday;
    @FXML
    private CheckBox chkTuesday;
    @FXML
    private CheckBox chkWednesday;
    @FXML
    private CheckBox chkThrusday;
    @FXML
    private CheckBox chkFirday;
    @FXML
    private CheckBox chkStaurday;
    @FXML
    private CheckBox chkSunday;

    // save , update and delete
    @FXML
    private Button btnSave;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private Label WORKD;
    @FXML
    private Label minuts;

    //Spinner
    @FXML
    private Spinner<Integer> ListSPINNERDays;
    @FXML
    private Spinner<Integer> ListSPINNERHours;
    @FXML
    private Spinner<Integer> ListSPINNERMinutes;


    ObservableList<String> checkBoxList = FXCollections.observableArrayList();
    @FXML
    private Label hrs;

    /**
     * Initializes the controller class.
     */

    //NoOfWorkingDays //WorkingDays WorkingPerTimeDay ID
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showAdd_Working_Days_Hours();

        //Spinner No of Working Days
        SpinnerValueFactory<Integer> daysValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,7,00);
        this.ListSPINNERDays.setValueFactory(daysValueFactory);
        ListSPINNERDays.setEditable(true);

        //Spinner Hours of Working Time Per Day
        SpinnerValueFactory<Integer> hoursValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,30,00);
        this.ListSPINNERHours.setValueFactory(hoursValueFactory);
        ListSPINNERHours.setEditable(true);

        //Spinner Minutes of Working Time Per Day
        SpinnerValueFactory<Integer> minutesValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,30,00);
        this.ListSPINNERMinutes.setValueFactory(minutesValueFactory);
        ListSPINNERMinutes.setEditable(true);
    }

    private void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btnSave) {
            insertRecord();
        }/*else if (event.getSource() ==btnUpdate){
            updateRecord();*/
    }


    public Connection getConnection(){
        Connection conn;
        try{
            conn = DriverManager.getConnection("jdbc:sqlite:D:/SLIIT/3rd year 1st sem/ITPM/IT19033938/src/student");
           // conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root","root");
            return conn;
        }
        catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }

    public ObservableList<Add_Working_Days_Hours_DB_Connect> getAdd_Working_Days_HoursList(){
        ObservableList<Add_Working_Days_Hours_DB_Connect> addWorkingdaysHrsList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM add_working_days_hours_db_connect;";
        Statement st;
        ResultSet rs;

        try{
              st = conn.createStatement();
              rs = st.executeQuery(query);
              Add_Working_Days_Hours_DB_Connect add_working_days_hours_db_connect;
              while(rs.next()){
                  add_working_days_hours_db_connect= new Add_Working_Days_Hours_DB_Connect(rs.getInt("SessionID"),rs.getInt("NoWorkingDays"),
                                    rs.getString("WorkingDays"),rs.getInt("hours"),rs.getInt("minutes"));
                  addWorkingdaysHrsList.add(add_working_days_hours_db_connect);
              }

            }catch(Exception ex){
            System.out.println("error1"+ ex);;
            }
        return addWorkingdaysHrsList;
    }

    public void showAdd_Working_Days_Hours(){
        try{
       ObservableList<Add_Working_Days_Hours_DB_Connect> lists = getAdd_Working_Days_HoursList();

       CoID.setCellValueFactory(new PropertyValueFactory<Add_Working_Days_Hours_DB_Connect, Integer>("SessionID"));
       CoNoOfWorkingDays.setCellValueFactory(new PropertyValueFactory<Add_Working_Days_Hours_DB_Connect, Integer>("NoWorkingDays"));
       CoWorking_Days.setCellValueFactory(new PropertyValueFactory<Add_Working_Days_Hours_DB_Connect, String>("WorkingDays"));
       CoHours.setCellValueFactory(new PropertyValueFactory<Add_Working_Days_Hours_DB_Connect, Integer>("hours"));
       CoMinutes.setCellValueFactory(new PropertyValueFactory<Add_Working_Days_Hours_DB_Connect, Integer>("minutes"));

       DescWorkingDays_AndHours.setItems(lists);}catch (Exception e){
            System.out.println("error2 "+ e);
        }
    }
    public void getWorkdays(){

        String m = chkMonday.getText();
        String t = chkTuesday.getText();
        String w = chkWednesday.getText();
        String th= chkThrusday.getText();
        String f= chkFirday.getText();
        String st= chkStaurday.getText();
        String sn= chkSunday.getText();

        String saveDays = m+","+t+","+w+","+th+","+f+","+st+","+sn;
        System.out.println(saveDays);
        WORKD.setText(saveDays);
    }

    //Insert Add working days and Hours interface
    @FXML
    private void insertRecord(){
        try{
      String query ="INSERT INTO add_working_days_hours_db_connect VALUES(" + txtID.getText() + "," +ListSPINNERDays.getValue()+ ","+ListSPINNERMinutes.getValue()+","+ListSPINNERHours.getValue()+",'" + WORKD.getText() +"')";
            System.out.println(query);
            executeQuery(query);
            showAdd_Working_Days_Hours();
            getWorkdays();

            String WorkingDays ="";
            if(chkMonday.isSelected()){
                WorkingDays+=chkMonday.getText()+"";
            }
            if(chkTuesday.isSelected()){
                WorkingDays+=chkTuesday.getText()+"";
            }
            if(chkWednesday.isSelected()){
                WorkingDays+=chkWednesday.getText()+"";
            }
            if(chkThrusday.isSelected()){
                WorkingDays+=chkThrusday.getText()+"";
            }
            if(chkFirday.isSelected()){
                WorkingDays+=chkFirday.getText()+"";
            }
            if(chkStaurday.isSelected()){
                WorkingDays+=chkStaurday.getText()+"";
            }
            if(chkSunday.isSelected()){
                WorkingDays+=chkSunday.getText()+"";
            }

    }catch (Exception e){
            System.out.println("Error insert" + e);
        }
    }

    //Update Add working days and Hours interface
    @FXML
   private void updateRecord(){
      String query= "UPDATE add_working_days_hours_db_connect SET NoWorkingDays=" + ListSPINNERDays.getValue() + ",minutes=" + ListSPINNERMinutes.getValue() + ",hours=" + ListSPINNERHours.getValue() + ",WorkingDays='" + WORKD.getText() + "' WHERE SessionID=" + txtID.getText() + "";
      executeQuery(query);
        System.out.println(query);
      showAdd_Working_Days_Hours();
        getWorkdays();
    }

   ////Delete Add working days and Hours interface
    @FXML
    private void deleteRecord(){
      String query= "DELETE FROM add_working_days_hours_db_connect WHERE SessionID = " + txtID.getText() +" ";
      executeQuery(query);
      showAdd_Working_Days_Hours();
    }

    private void executeQuery(String query) {
        try{
        Connection conn= getConnection();
        Statement st;
        try{
            st = conn.createStatement();
            st.executeUpdate(query);
        }catch(Exception ex){
            System.out.println("error s"+ ex);;
        }
    }catch (Exception e){
            System.out.println("error qu"+e);
        }
    }
    @FXML
    public void changeScreen(javafx.event.ActionEvent event) {
        try{
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("mainPage.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);
            Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(tableViewScene);
            window.show();
        }catch(Exception E){
            System.out.println(E);
        }
    }
}
