/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;


/**
 * FXML Controller class
 *
 * @author Damika
 */
public class RoomLocationController implements Initializable {

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
    private TextField CAPTXT;

    @FXML
    private TextField roomid;

    @FXML
    private RadioButton LABRB;

    @FXML
    private TextField BNtxt;


    @FXML
    private RadioButton LecRB;

    @FXML
    private TextField RNTxt;


    @FXML
    private TextField filterloc;


    ObservableList<locations> listL;
    ObservableList<locations> dataList;


    int index = -1;

    Connection conn =null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    public void Add_locations (){
        conn = getConnection();
        String sql = "insert into roomlocations (BuildingName,RoomName,Capacity,RoomType,roomID)values(?,?,?,?,? )";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, BNtxt.getText());
            pst.setString(2, RNTxt.getText());
            pst.setString(3, CAPTXT.getText());
            pst.setString(4, LABRB.getText());
            pst.setString(5, roomid.getText());
            pst.execute();


            JOptionPane.showMessageDialog(null, "Locations Add succes");
            UpdateTable();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @FXML
    void getSelected (MouseEvent event){
        index = tvloc.getSelectionModel().getSelectedIndex();
        if (index <= -1){

            return;
        }

        BNtxt.setText(tvlocrname.getCellData(index).toString());
        RNTxt.setText(tvlocbname.getCellData(index).toString());
        CAPTXT.setText(tvcap.getCellData(index).toString());
        roomid.setText(roomID.getCellData(index).toString());

    }
    @FXML
    public void Edit (){
            String query = "UPDATE roomlocations SET BuildingName='" + BNtxt.getText() + "',RoomName='" + RNTxt.getText() +"',Capacity=" + CAPTXT.getText() +",roomtype= '"+ LABRB.getText() + "' WHERE roomIDv=" + roomid.getText() + "";
        executeQuery(query);
        System.out.println(query);
            UpdateTable();
        }


    @FXML
    public void Delete(){

        conn = getConnection();
        String sql = "delete from roomlocations where roomID = ?";
            try {
                pst = conn.prepareStatement(sql);
                pst.setString(1, roomid.getText());
                pst.execute();
                JOptionPane.showMessageDialog(null, "Delete");
                UpdateTable();
                //search_loc();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
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
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UpdateTable();
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

    public void UpdateTable(){
        ObservableList<locations> listL = getRoomList();

        tvlocbname.setCellValueFactory(new PropertyValueFactory<locations,String>("BuildingName"));
        tvlocrname.setCellValueFactory(new PropertyValueFactory<locations,String>("RoomName"));
        tvcap.setCellValueFactory(new PropertyValueFactory<locations,Integer>("capacity"));
        tvrromtype.setCellValueFactory(new PropertyValueFactory<locations,String>("roomtype"));
        roomID.setCellValueFactory(new PropertyValueFactory<locations,Integer>("roomID"));


        tvloc.setItems(listL);
    }




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

    public void getSelected(javafx.scene.input.MouseEvent mouseEvent) {
    }
}
