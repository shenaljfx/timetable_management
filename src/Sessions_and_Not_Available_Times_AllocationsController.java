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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author T.V.Thimira Isiwara
 */
public class Sessions_and_Not_Available_Times_AllocationsController implements Initializable {
    //label
    @FXML
    private Label lecturer;
    @FXML
    private Label group;
    @FXML
    private Label subGroup;
    @FXML
    private Label sessionID;
    @FXML
    private Label time;

    //button
    @FXML
    private Button btnSubmit;
    @FXML
    private Button btnView;
    @FXML
    private Button btnClear;

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
    private TableView<Sessions_and_Not_Available_Times_Allocations_DB_Connect> TableView_MangeNot_Available_Times;

    ObservableList<String> LECTURERlist=FXCollections.observableArrayList("Mr. Prasanna Sumathipala","Ms. Hansika Mahaadikara","Mr.Surath Kahandawa","Dr. Windya Rankothge","Dr. Anuradha Jayakody","Ms Shashika Lokuliyana",
                                                                           "Ms. Chathurangika Kahandawarachchi","Ms. Isuri Udara","Mr. Kavinga Yapa Abeywardene","Ms. Mihiri Chethana","Ms. Suranjini Silva","Mr. Adeepa Gunarathna","Mr. Oshadha Seneweer","Mr. Nelum Chathuranga Amarasena","Ms. Archana  Kugathasan",
                                                                           "Ms. Buddhima Attanayake","Ms. Jenny Krishara","Ms. Chathurangika Kahandawarachchi","Ms. Isuri Udara","Prof. Pradeep Abeygunawardhana","Mr. Nalaka Dissanayake","Dr. Janaka Wijekoon","Ms. Lasantha Abesiri");
    ObservableList<String> GROUPlist=FXCollections.observableArrayList("Y1S1.01(IT)","Y1S1.02(IT)","Y1S3.01(IT)","Y1S4.01(IT)","Y1S2.01(IT)","Y1S2.02(IT)","Y1S2.03(IT)","Y2S1.01(IT)",
                                                                        "Y2S1.02(IT)","Y2S1.03(IT)","Y2S1.04(CS)","Y2S2.01(IT)","Y2S2.02(IT)","Y2S2.03(IT)","Y3S1.01(IT)","Y3S1.01(IT)","Y3S1.02(IT)",
                                                                        "Y3S1.01(CSNE)","Y3S1.01(SE)","Y3S1.01(DS)","Y3S1.01(IM)","Y3S1.01(ISE)","Y3S2.01(IT)","Y3S2.01(SE)","Y3S2.01(CSNE)","Y3S2.02(CSNE)",
                                                                        "Y3S2.01(ISE)","Y3S2.01(IM)","Y3S2.01(DS)","Y3S2.01(ISE)","Y4S1.01(IT)","Y4S1.02(IT)","Y4S1.01(SE)","Y4S1.01(IM)","Y4S1.01(DS)","Y4S1.01(CSNE)","Y4S1.01(ISE)",
                                                                        "Y4S2.01(IT)","Y4S2.01(IM)","Y4S2.01(DS)","Y4S2.01(SE)","Y4S2.01(DS)");
    ObservableList<String> SUBGROUPlist=FXCollections.observableArrayList("Y1S1.01.01(IT)","Y1S1.01.02(IT)","Y1S1.02.01(IT)","Y1S1.02.02(IT)","Y1S3.01.01(IT)","Y1S3.01.02(IT)","Y1S2.01.01(IT)","Y1S2.01.02(IT)","Y1S2.02.01(IT)","Y1S2.02.02(IT)","Y2S1.01.01(IT)","Y2S1.01.02(IT)","Y2S1.02.01(IT)","Y2S1.02.02(IT)","Y2S2.02.01(IT)","Y2S2.02.02(IT)","Y2S2.03(IT).01","Y2S2.03.02(IT)","Y3S1.01.01(IT)","Y3S1.01.02(IT)","Y3S1.02.01(IT)","Y3S1.02.02(IT)",
                                                                          "Y3S1.01.01(CSNE)","Y3S1.02(CSNE)","Y3S1.01.01(SE)","Y3S1.01.02(SE)","Y3S1.01.01(DS)","Y3S1.01.02(DS)","Y3S2.01.01(ISE)","Y3S2.01.02(ISE)","Y4S1.01.01(IT)","Y4S1.01.02(IT)","Y4S1.02.01(IT)","Y4S1.02.02(IT)","Y4S1.01.01(SE)","Y4S1.01.02(SE)","Y4S1.01.01(IM)","Y4S1.01.02(IM)","Y4S1.01.01(DS)","Y4S1.01.02(DS)","Y4S1.01.01(CSNE)","Y4S1.01.02(CSNE)","Y4S1.01.01(ISE)","Y4S1.01.02(ISE)","Y4S2.01.01(IT)",
                                                                          "Y4S2.01.02(IT)","Y4S2.01.01(IM)","Y4S2.01.02(IM)","Y4S2.01.01(DS)","Y4S2.01.02(DS)","Y4S2.01.01(SE)","Y4S2.01.02(SE)","Y4S2.01.01(DS)","Y4S2.01.02(DS)");
    ObservableList<String> TIMElist=FXCollections.observableArrayList("Monday, 08.30-10.30","Monday, 10.30-12.30","Monday, 13.30-15.30","Monday, 15.30-17.30","Monday, 17.30-19.30",
                                                                      "Tuesday, 08.30-10.30","Tueday, 10.30-12.30","Tuesday, 13.30-15.30","Tueday, 15.30-17.30","Tueday, 17.30-19.30",
                                                                      "Wednesday, 08.30-10.30","Wednesday, 10.30-12.30","Wednesday, 13.30-15.30","Wednesday, 15.30-17.30","Wednesday, 17.30-19.30",
                                                                      "Thrusday, 08.30-10.30","Thrusday, 10.30-12.30","Thrusday, 13.30-15.30","Thrusday, 15.30-17.30","Thrusday, 17.30-19.30",
                                                                      "Firday, 08.30-10.30","Firday, 10.30-12.30","Firday, 13.30-15.30","Firday, 15.30-17.30","Firday, 17.30-19.30",
                                                                      "Saturday, 08.30-10.30","Saturday, 10.30-12.30", "Saturday, 13.00-15.00","Saturday, 15.00-17.00","Saturday, 17.00-19.00",
                                                                      "Sunday, 08.30-10.30","Sunday, 10.30-12.30","Sunday, 13.00-15.00","Sunday, 15.00-17.00","Sunday, 17.00-19.00");

    //Spinner value factory for lecturer
    SpinnerValueFactory <String> svflecturer = new SpinnerValueFactory.ListSpinnerValueFactory<String>(LECTURERlist);

    //Spinner value factory for group
    SpinnerValueFactory <String> svfgroup = new SpinnerValueFactory.ListSpinnerValueFactory<String>(GROUPlist);

    //Spinner value factory for subgroup
    SpinnerValueFactory <String> svfsubgroup = new SpinnerValueFactory.ListSpinnerValueFactory<String>(SUBGROUPlist);

    //Spinner value factory for time duration
    SpinnerValueFactory <String> svftimeduration = new SpinnerValueFactory.ListSpinnerValueFactory<String>(TIMElist);


    //Spinner value factory for ID
    final int initialValue=1;
    SpinnerValueFactory<Integer> svf= new SpinnerValueFactory.IntegerSpinnerValueFactory(1,20,initialValue);


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {


        // TODO
        sessionIDLevel.setValueFactory(svf);


       LECTURERlist.add("Mr. Prasanna Sumathipala");
       lecturerLevel.setValueFactory(svflecturer);

       GROUPlist.add("Y1S1.01(IT)");
       groupLevel.setValueFactory(svfgroup);

       SUBGROUPlist.add("Y1S1.01.01(IT)");
       subGroupLevel.setValueFactory(svfsubgroup);

       TIMElist.add("Monday, 08.30-10.30");
       TimeDurationLevel.setValueFactory(svftimeduration);

    }

    private void handleBtnLevelAction(ActionEvent event) {
        if (event.getSource() == btnSubmit) {
            insertRecord();
        }
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
            }catch(Exception ex){//SubGroup
            System.out.println("error1"+ ex);;
            }
        return addSessionsAndNotAvailableTimesAllocationsList;
    }



    //Insert Manage Time Slots
    @FXML
      private void insertRecord(){
      try{
      String query ="INSERT INTO sessions_and_not_available_times_allocations_db_connect VALUES(" +sessionIDLevel.getValue()+ ",'"+lecturerLevel.getValue()+ "','"+groupLevel.getValue()+ "','"+subGroupLevel.getValue()+ "','"+TimeDurationLevel.getValue() +"')";
            System.out.println(query);
            executeQuery(query);


    }catch (Exception e){
            System.out.println("Error insert" + e);
        }
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
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("Manage_Not_Available_Times.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);
            Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(tableViewScene);
            window.show();
        }catch(Exception E){
            System.out.println(E);
        }
    }
    @FXML
    public void changeScreen2(javafx.event.ActionEvent event) {
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


    @FXML
    private void clearFields(ActionEvent event) {
        lecturerLevel.getValueFactory().setValue("Mr. Prasanna Sumathipala");
        groupLevel.getValueFactory().setValue("Y1S1.01(IT)");
        subGroupLevel.getValueFactory().setValue("Y1S1.01.01(IT)");
        //sessionIDLevel.getValueFactory().setValue("1");
        TimeDurationLevel.getValueFactory().setValue("Monday, 08.30-10.30");

    }
}
