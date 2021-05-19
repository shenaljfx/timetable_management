/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author T.V.Thimira Isiwara
 */
public class Manage_Not_Available_TimesController implements Initializable {


    @FXML
    private TableColumn<Sessions_and_Not_Available_Times_Allocations_DB_Connect, Integer> IDdesc;
    @FXML
    private TableColumn<Sessions_and_Not_Available_Times_Allocations_DB_Connect, String> Timedesc;
    @FXML
    private TableColumn<Sessions_and_Not_Available_Times_Allocations_DB_Connect, String> Lecturerdesc;
    @FXML
    private TableColumn<Sessions_and_Not_Available_Times_Allocations_DB_Connect, String> GroupIddesc;
    @FXML
    private TableColumn<Sessions_and_Not_Available_Times_Allocations_DB_Connect, String> subGroupdesc;

    //button
    @FXML
     public TextField del;

     //spinner
    @FXML
    private Spinner<String> lecturerLevel;
    @FXML
    private Spinner<String> groupLevel;
    @FXML
    private Spinner<String> subGroupLevel;
    @FXML
    private Spinner<Integer> sessionIDLevel;
    @FXML
    private Spinner<String> TimeDurationLevel;

    //Table View Manage not available times
    @FXML
    private TableView<Sessions_and_Not_Available_Times_Allocations_DB_Connect> TableView_MangeNot_Available_Times;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showAdd_Sessions_and_Not_Available_Times_Allocations();
    }

    public Connection getConnection(){
        Connection conn;
        try{
            conn = DriverManager.getConnection("jdbc:sqlite:D:/SLIIT/3rd year 1st sem/ITPM/IT19033938/src/student");
            return conn;
        }
        catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }
    @FXML
    public ObservableList<Sessions_and_Not_Available_Times_Allocations_DB_Connect> getAdd_Sessions_and_Not_Available_Times_AllocationsList(){
        ObservableList<Sessions_and_Not_Available_Times_Allocations_DB_Connect> addSessionsAndNotAvailableTimesAllocationsList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM sessions_and_not_available_times_allocations_db_connect;";
        Statement st;
        ResultSet rs;

         try{
              st = conn.createStatement();
              rs = st.executeQuery(query);
              Sessions_and_Not_Available_Times_Allocations_DB_Connect sessions_and_not_available_times_allocations_db_connect;
              while(rs.next()){
                  sessions_and_not_available_times_allocations_db_connect= new Sessions_and_Not_Available_Times_Allocations_DB_Connect(rs.getString("Lecturer"),rs.getString("Group"),rs.getString("SubGroup"),rs.getInt("SessionID"),rs.getString("TimeDuration"));
                  addSessionsAndNotAvailableTimesAllocationsList.add(sessions_and_not_available_times_allocations_db_connect);                     //Lecturer  Group SubGroup  TimeDuration
              }
            }catch(Exception ex){
            System.out.println("error1"+ ex);;
            }
        return addSessionsAndNotAvailableTimesAllocationsList;
    }

    public void showAdd_Sessions_and_Not_Available_Times_Allocations(){
        try{
       ObservableList<Sessions_and_Not_Available_Times_Allocations_DB_Connect> lists = getAdd_Sessions_and_Not_Available_Times_AllocationsList();

       IDdesc.setCellValueFactory(new PropertyValueFactory<Sessions_and_Not_Available_Times_Allocations_DB_Connect, Integer>("SessionID"));
       Lecturerdesc.setCellValueFactory(new PropertyValueFactory<Sessions_and_Not_Available_Times_Allocations_DB_Connect, String>("Lecturer"));
       GroupIddesc.setCellValueFactory(new PropertyValueFactory<Sessions_and_Not_Available_Times_Allocations_DB_Connect, String>("Group"));
       subGroupdesc.setCellValueFactory(new PropertyValueFactory<Sessions_and_Not_Available_Times_Allocations_DB_Connect, String>("SubGroup"));
       Timedesc.setCellValueFactory(new PropertyValueFactory<Sessions_and_Not_Available_Times_Allocations_DB_Connect, String>("TimeDuration"));



       TableView_MangeNot_Available_Times.setItems(lists);}catch (Exception e){
            System.out.println("error2 "+ e);
        }
    }

     //Delete Manage Time Slots
    @FXML
      private void deleteRecord(){
      String query= "DELETE FROM sessions_and_not_available_times_allocations_db_connect WHERE sessionID = " + del.getText() +" ";
      executeQuery(query);
      showAdd_Sessions_and_Not_Available_Times_Allocations();
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
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("Sessions_and_Not_Available_Times_Allocations.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);
            Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(tableViewScene);
            window.show();
        }catch(Exception E){
            System.out.println(E);
        }
    }
}
