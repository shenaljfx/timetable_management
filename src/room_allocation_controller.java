import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.swing.*;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class room_allocation_controller implements Initializable {
    @FXML
    private TableColumn<locations, String> tvlocrname;

    @FXML
    private TableColumn<locations, String> tvlocbname;

    @FXML
    private TableColumn<locations, String> tvrromtype;

    @FXML
    private TableView<locations> tvloc;

    @FXML
    private TableColumn<locations, Integer> tvcap;

    @FXML
    private TableColumn<locations, Integer> roomID;
    @FXML
    private TableColumn<Sessions, Integer> sec_id;

    @FXML
    private TableColumn<Sessions, String> sec_lec;

    @FXML
    private TableColumn<Sessions, String> sub_code;

    @FXML
    private TableColumn<Sessions, String>  sub_name;

    @FXML
    private TableView<addSession> main_table;

    @FXML
    private TableColumn<Sessions, String> groupID;
    @FXML
    private TableColumn<Sessions, String> tag;
    @FXML
    private TableColumn<Sessions, String> nostudent;
    @FXML
    private TableColumn<Sessions, String> duration;
    @FXML
    private TableColumn<Sessions, String> sec_date;


    @FXML
    private TextField s1_id;

    @FXML
    private TextField s2_id;


    ObservableList<lecturer> listM;
    ObservableList<lecturer> dataList;

    int index = -1;

    Connection conn =null;

    ResultSet rs = null;
    PreparedStatement pst = null;


    @FXML
    public void allocateRoom (){
        try {
            conn = getConnection();
            String value1 = s1_id.getText();
            String value2 = s2_id.getText();
            String sql = "INSERT INTO allocate_room SELECT id,lec,sub_code,sub_name,group_id,tag,noOfStudents,duration,date,roomName FROM seesion_add,roomlocations WHERE id ='"+value1+"'and roomName ='"+value2+"'";
            pst= conn.prepareStatement(sql);
            pst.execute();

            JOptionPane.showMessageDialog(null, "DONE");
            UpdateTable();

        } catch (Exception e) {
            System.out.println("error " + e);
        }

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UpdateTable();
        UpdateTable1();
    }
    public Connection getConnection() {
        Connection conn;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:D:/SLIIT/3rd year 1st sem/ITPM/IT19033938/src/student");
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public ObservableList<addSession> getSession(){
        ObservableList<addSession> SessionList = FXCollections.observableArrayList();
        Connection connection = getConnection();
        String query = "SELECT * FROM seesion_add ";
        Statement st;
        ResultSet rs;


        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);
            addSession addSession;
            while(rs.next()) {
                addSession = new addSession( rs.getInt("id"), rs.getString("lec"), rs.getString("sub_code"), rs.getString("sub_name"),rs.getString("group_id"),rs.getString("tag"),rs.getString("noOfStudents"),rs.getString("duration"),rs.getString("date"));
                SessionList.add(addSession);

            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return SessionList;
    }

    public void UpdateTable() {
        try{
            ObservableList<addSession> listM = getSession();
            sec_id.setCellValueFactory(new PropertyValueFactory<Sessions,Integer>("id"));
            sec_lec.setCellValueFactory(new PropertyValueFactory<>("lec"));
            sub_code.setCellValueFactory(new PropertyValueFactory<>("sub_code"));
            sub_name.setCellValueFactory(new PropertyValueFactory<>("sub_name"));
            groupID.setCellValueFactory(new PropertyValueFactory<>("group_id"));
            tag.setCellValueFactory(new PropertyValueFactory<>("tag"));
            nostudent.setCellValueFactory(new PropertyValueFactory<>("noOfStudents"));
            duration.setCellValueFactory(new PropertyValueFactory<>("duration"));
            sec_date.setCellValueFactory(new PropertyValueFactory<>("date") );
            main_table.setItems(listM);

        }catch(Exception e){
            System.out.println(e);
        }
    }
    public ObservableList<locations> getRoomList(){
        ObservableList<locations> locations = FXCollections.observableArrayList();
        Connection connection = getConnection();
        String query = "SELECT * FROM roomlocations ";
        Statement st;
        ResultSet rs;
        System.out.println(query);

        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);

            while(rs.next()) {
                locations locations1 = new locations( rs.getString("BuildingName"), rs.getString("RoomName"), rs.getInt("Capacity"), rs.getString("roomtype"),rs.getInt("roomID"));
                locations.add(locations1);

            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return locations;
    }

    public void UpdateTable1(){
        ObservableList<locations> listL = getRoomList();
        tvlocbname.setCellValueFactory(new PropertyValueFactory<locations,String>("BuildingName"));
        tvlocrname.setCellValueFactory(new PropertyValueFactory<locations,String>("RoomName"));
        tvcap.setCellValueFactory(new PropertyValueFactory<locations,Integer>("capacity"));
        tvrromtype.setCellValueFactory(new PropertyValueFactory<locations,String>("roomtype"));
        roomID.setCellValueFactory(new PropertyValueFactory<locations,Integer>("roomID"));
        tvloc.setItems(listL);
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
