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
public class Add_LocationsController implements Initializable {

    @FXML
    private Label room;
    @FXML
    private Label day;
    @FXML
    private Label startTime;
    @FXML
    private Label endTime;

    //button
    @FXML
    private Button btnAddSession;
    @FXML
    private Button btnClear;

    //Table view
    @FXML
    private TableView <Add_Locations_DB_Connect> DescAddLocationViews;

    //Table Column
    @FXML
    private TableColumn<Add_Locations_DB_Connect, String> CoRoom;
    @FXML
    private TableColumn<Add_Locations_DB_Connect, String> CoDays;
    @FXML
    private TableColumn<Add_Locations_DB_Connect, Integer> CoStartTime;
    @FXML
    private TableColumn<Add_Locations_DB_Connect, String> CoEndTime;

    //Spinner
    @FXML
    private Spinner<String> RoomLevel;
    @FXML
    private Spinner<String> DayLevel;
    @FXML
    private Spinner<String> StartTimeLevel;
    @FXML
    private Spinner<String> EndTimeLevel;


     ObservableList<String> roomList = FXCollections.observableArrayList("New-CyberSecLab","Auditorium","A506","New-DClab","13L-E1+13L-E2-pclab","N3C","N3D-Lecture Hall","N3E- Lecture Hall",
                                                     "410A+410B","13L-C-pclab","N3A-lab","New-Emblab","A406-Pclab","13H-B","13H-A","602+603","A509","A405-Pclab","605-Pclab","13H-C","MMlab",
                                                     "13L-A+13L-B-pclab","B501","B502","A505","N3B-lab","601-Pclab","B403-Pclab","Mini Auditorium","F501","13L-E1+13L-E2-pclab");
     ObservableList<String> daysList = FXCollections.observableArrayList("Weakdays","Weekend");
     ObservableList<String> startTimeList = FXCollections.observableArrayList("08:30A.M","09:30A.M","10:30A.M","11:30A.M","12:30P.M","13:30P.M","14:30P.M","15:30P.M","16:30P.M",
                                                                                  "17:30P.M","18:30P.M","19:30P.M","14:00P.M","15:00P.M","16:00P.M","17:00P.M","18:00P.M","19:00P.M");
     ObservableList<String> endTimeList = FXCollections.observableArrayList("09:30A.M","10:30A.M","11:30A.M","12:30P.M","13:30P.M","14:30P.M","15:30P.M","16:30P.M","17:30P.M","18:30P.M",
                                                                                 "19:30P.M","14:00P.M","15:00P.M", "16:00P.M","17:00P.M","18:00P.M","19:00P.M");

      //Spinner value factory for rooms
    SpinnerValueFactory <String> svfroom = new SpinnerValueFactory.ListSpinnerValueFactory<String>(roomList);

      //Spinner value factory for days
    SpinnerValueFactory <String> svfdays = new SpinnerValueFactory.ListSpinnerValueFactory<String>(daysList);

      //Spinner value factory for start times
    SpinnerValueFactory <String> svfstartTime = new SpinnerValueFactory.ListSpinnerValueFactory<String>(startTimeList);

      //Spinner value factory for end times
    SpinnerValueFactory <String> svfendTime = new SpinnerValueFactory.ListSpinnerValueFactory<String>(endTimeList);

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showAdd_Locations();

        //rooms
       roomList.add("New-CyberSecLab");
       RoomLevel.setValueFactory(svfroom);

       //days
       daysList.add("Weakdays");
       DayLevel.setValueFactory(svfdays);

       //start time
       startTimeList.add("08:30A.M");
       StartTimeLevel.setValueFactory(svfstartTime);

       //end time
       endTimeList.add("09:30A.M");
       EndTimeLevel.setValueFactory(svfendTime);

    }

       @FXML
    private void handleBtnLevelAction(ActionEvent event) {
        if (event.getSource() == btnAddSession) {
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

    public ObservableList<Add_Locations_DB_Connect> getAdd_LocationsList(){
        ObservableList<Add_Locations_DB_Connect> addLocationsList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM add_locations_db_connect;";
        Statement st;
        ResultSet rs;

        try{
              st = conn.createStatement();
              rs = st.executeQuery(query);
              Add_Locations_DB_Connect add_locations_db_connect;
              while(rs.next()){
                  add_locations_db_connect= new Add_Locations_DB_Connect(rs.getString("days"),rs.getString("room"),rs.getString("StartTime"),rs.getString("EndTime"));
                  addLocationsList.add(add_locations_db_connect);
              }

            }catch(Exception ex){
            System.out.println("error1"+ ex);;
            }
        return addLocationsList;
    }

     public void showAdd_Locations(){
        try{
       ObservableList<Add_Locations_DB_Connect> lists = getAdd_LocationsList();


       CoStartTime.setCellValueFactory(new PropertyValueFactory<Add_Locations_DB_Connect, Integer>("StartTime"));
       CoEndTime.setCellValueFactory(new PropertyValueFactory<Add_Locations_DB_Connect, String>("EndTime"));
            CoRoom.setCellValueFactory(new PropertyValueFactory<Add_Locations_DB_Connect, String>("room"));
            CoDays.setCellValueFactory(new PropertyValueFactory<Add_Locations_DB_Connect, String>("days"));

       DescAddLocationViews.setItems(lists);
       }catch (Exception e)
        {
            System.out.println("error2 "+ e);
        }
    }

     //Insert Add locations interface
    @FXML
    private void insertRecord(){
        try{
      String query ="INSERT INTO add_locations_db_connect VALUES(" +"'"+StartTimeLevel.getValue()+"','" + EndTimeLevel.getValue() +"','" + RoomLevel.getValue() + "','" +DayLevel.getValue()+ "')";
            System.out.println(query);
            executeQuery(query);
            showAdd_Locations();

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
    public void clearFields(){

        RoomLevel.getValueFactory().setValue("New-CyberSecLab");
        DayLevel.getValueFactory().setValue("Weakdays");
        StartTimeLevel.getValueFactory().setValue("08:30A.M");
        EndTimeLevel.getValueFactory().setValue("09:30A.M");
    }
}
