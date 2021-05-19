

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
public class Manage_Time_SlotsController implements Initializable {

    @FXML
    private Label time;
    @FXML
    private Label id;

    //Spinner
    @FXML
    private Spinner<String> timeLevel;
    @FXML
    private Spinner<Integer> idLevel;



    ObservableList<String> timeList=FXCollections.observableArrayList("8.30-9.30A.M /9.30-13.00P.M","8.30-10.30A.M/10.30-12.30A.M/15.30-17.30P.M","8.30-10.30A.M/13.30-15.30P.M/15.30-17.30P.M","10.30-11.30A.M/11.30-13.30A.M","11.30-12.30A.M","10.30-12.30A.M/17.30-18.30P.M",
                             "9.30-12.30P.M/12.30-13.30P.M/15.30-17.30P.M/17.30-19.30P.M","13.30-14.30P.M/15.30-17.30P.M","8.30-10.30A.M/14.30-15.30P.M","11.30-13.30P.M/13.30-15.30P.M","8.30-10.30A.M/15.30-16.30P.M","16.30-17.30P.M","15.30-17.30P.M","17.30-18.30P.M","18.30-19.30P.M","17.30-19.30P.M");

    //Spinner value factory for time slots
    SpinnerValueFactory <String> svftime = new SpinnerValueFactory.ListSpinnerValueFactory<String>(timeList);

    //Spinner value factory for ID
    final int initialValue=1;
    SpinnerValueFactory<Integer> svf= new SpinnerValueFactory.IntegerSpinnerValueFactory(1,20,initialValue);

    // insert , delete and update
    @FXML
    private Button saveLevel;
    @FXML
    private Button deleteLevel;
    @FXML
    private Button updateLevel;

    //Table Column
    @FXML
    private TableColumn<Manage_Time_Slots_DB_Connect, Integer> ID_Description;
    @FXML
    private TableColumn<Manage_Time_Slots_DB_Connect, String> Time_Description;

    //Table View Time Slots
    @FXML
    private TableView<Manage_Time_Slots_DB_Connect> TableView_MangeTime_Slots;

    /**
     * Initializes the controller class.
     */


    @Override
    public void initialize(URL url, ResourceBundle rb) {

        showAdd_Manage_Time_Slots();
        // TODO
       idLevel.setValueFactory(svf);

       timeList.add("8.30-9.30A.M");
       //timeList.add("9.30-10.30A.M");
       timeLevel.setValueFactory(svftime);
      // txtLevel.setValueFactory();
    }

    @FXML
    private void handleBtnLevelAction(ActionEvent event) {
        if (event.getSource() == saveLevel) {
            insertRecord();
        }
    }

     public Connection getConnection(){
        Connection conn;
        try{
            conn = DriverManager.getConnection("jdbc:sqlite:D:/SLIIT/3rd year 1st sem/ITPM/IT19033938/src/student");
            //conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root","root");
            return conn;
        }
        catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }

     public ObservableList<Manage_Time_Slots_DB_Connect> getAdd_Manage_Time_SlotsList(){
        ObservableList<Manage_Time_Slots_DB_Connect> addMangeTimeSloTsList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM add_manage_time_slots_db_connect;";
        Statement st;
        ResultSet rs;

        try{
              st = conn.createStatement();
              rs = st.executeQuery(query);
              Manage_Time_Slots_DB_Connect add_manage_time_slots_db_connect;
              while(rs.next()){
                  add_manage_time_slots_db_connect= new Manage_Time_Slots_DB_Connect(rs.getInt("SEssiONID"),rs.getString("TimeSlots"));
                  addMangeTimeSloTsList.add(add_manage_time_slots_db_connect);
              }

            }catch(Exception ex){
            System.out.println("error1"+ ex);;
            }
        return addMangeTimeSloTsList;
    }

    public void showAdd_Manage_Time_Slots(){
        try{
       ObservableList<Manage_Time_Slots_DB_Connect> lists = getAdd_Manage_Time_SlotsList();

       ID_Description.setCellValueFactory(new PropertyValueFactory<Manage_Time_Slots_DB_Connect, Integer>("SEssiONID"));
       Time_Description.setCellValueFactory(new PropertyValueFactory<Manage_Time_Slots_DB_Connect, String>("MANAGETimeSlots"));


       TableView_MangeTime_Slots.setItems(lists);}catch (Exception e){
            System.out.println("error2 "+ e);
        }
    }

    //Insert Manage Time Slots
     @FXML
      private void insertRecord(){
      try{
      String query ="INSERT INTO add_Manage_Time_Slots_db_connect VALUES(" +idLevel.getValue()+ ",'"+timeLevel.getValue() +"')";
            System.out.println(query);
            executeQuery(query);
            showAdd_Manage_Time_Slots();

    }catch (Exception e){
            System.out.println("Error insert" + e);
        }
    }

       //Update Manage Time Slots
      @FXML
       private void updateRecord(){
      String query= "UPDATE add_manage_time_slots_db_connect SET TimeSlots='" + timeLevel.getValue() + "' WHERE SEssiONID=" + idLevel.getValue() + "";
      executeQuery(query);
      System.out.println(query);
      showAdd_Manage_Time_Slots();

    }

      //Delete Manage Time Slots
      @FXML
      private void deleteRecord(){
      String query= "DELETE FROM add_manage_time_slots_db_connect WHERE SEssiONID = " + idLevel.getValue() +" ";
      executeQuery(query);
      showAdd_Manage_Time_Slots();
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
