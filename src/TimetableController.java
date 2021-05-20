
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TimetableController implements Initializable {
    @FXML
    public Label time1,time2,time3,time4,time5,time6,time7,time8,time9;
    @FXML
    public Label m1,m2,m3,m4,m5,m6,m7,m8,m9;
    @FXML
    public Label tu1,tu2,tu3,tu4,tu5,tu6,tu7,tu8,tu9;
    @FXML
    public Label w1,w2,w3,w4,w5,w6,w7,w8,w9;
    @FXML
    public Label th1,th2,th3,th4,th5,th6,th7,th8,th9;
    @FXML
    public Label fr1,fr2,fr3,fr4,fr5,fr6,fr7,fr8,fr9;
    @FXML
    public Label sat1,sat2,sat3,sat4,sat5,sat6,sat7,sat8,sat9;
    @FXML
    public Label sun1,sun2,sun3,sun4,sun5,sun6,sun7,sun8,sun9;
    @FXML
    public TextField group;
    @FXML
    public TextField location;
    @FXML
    public ComboBox <String>leclist ;
    @FXML
    public ComboBox <String>grouplist ;
    @FXML
    public ComboBox <String>roomlist ;




        static final String DB_URL = "jdbc:sqlite:D:/SLIIT/3rd year 1st sem/ITPM/IT19033938/src/student";
        static final String QUERY = "SELECT lec, sub_code, sub_name, group_id, tag, noOfStudents, duration,date,room FROM parallel";
        static final String QUERY2 = "SELECT lec, sub_code, sub_name, group_id, tag, noOfStudents, duration,date,room  FROM consecutive";
        static final String QUERY3 = "SELECT lec, sub_code, sub_name, group_id, tag, noOfStudents, duration,date,room  FROM nonoverlapping";
        static final String QUERY4 = "SELECT Name FROM lectrer";
        static final String QUERY5 = "SELECT SGID FROM studentgroup";
        static final String QUERY6 = "SELECT RoomName FROM roomlocations";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            getLecToList();
            getGroupToList();
            getRoomToList();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    @FXML
    public void refresh(ActionEvent event) {
            try{
                Parent tableViewParent = FXMLLoader.load(getClass().getResource("timetable.fxml"));
                Scene tableViewScene = new Scene(tableViewParent);
                Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(tableViewScene);
                window.show();
            }catch(Exception E){
                System.out.println(E);
            }
        }

    @FXML
    public void getLecToList()throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(QUERY4);
        ObservableList<String>  data = FXCollections.observableArrayList();

        while (rs.next()) {
            try {
                data.add(rs.getString("Name"));

            }catch (Exception e){
                System.out.println(e);
            }


        }leclist.setItems(data);


    }
    @FXML
    public void getGroupToList()throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(QUERY5);
        ObservableList<String>  data = FXCollections.observableArrayList();

        while (rs.next()) {
            try {
                data.add(rs.getString("SGID"));

            }catch (Exception e){
                System.out.println(e);
            }


        }grouplist.setItems(data);


    }
    @FXML
    public void getRoomToList()throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(QUERY6);
        ObservableList<String>  data = FXCollections.observableArrayList();

        while (rs.next()) {
            try {
                data.add(rs.getString("RoomName"));

            }catch (Exception e){
                System.out.println(e);
            }


        }roomlist.setItems(data);


    }






    @FXML
    public void genTimeSlotes(){
     String[] iArray = new String[]{"8.30 - 9.30","9.30 - 10.30","10.30 - 11.30","11.30 - 12.30","12.30 - 1.30","1.30 - 2.30","2.30 - 3.30","3.30- 4.30","4.30 - 5.30"};

            time1.setText(iArray[0]);
            time2.setText(iArray[1]);
            time3.setText(iArray[2]);
            time4.setText(iArray[3]);
            time5.setText(iArray[4]);
            time6.setText(iArray[5]);
            time7.setText(iArray[6]);
            time8.setText(iArray[7]);
            time9.setText(iArray[8]);
    }
    public void generateStudentGroupTimetable() throws SQLException {
        createCons();
        createParaSlots();
        createNotOverlapSlotes();
        genTimeSlotes();

    }

    @FXML
    public void createParaSlots() throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(QUERY);
        String [] paraArray = new String[10];
        String [] paraArray2 = new String[10];
        String [] paraArray3 = new String[10];
        String [] paraArray4 = new String[10];
        String [] paraArray5 = new String[10];
        String [] paraArray6 = new String[10];
        String [] paraArray7 = new String[10];
        String h = null;
        for (int J =0; J<10;J++) {

        while (rs.next()) {
            try {
                //get values
                String a = (rs.getString("lec"));
                String b = (rs.getString("sub_code"));
                String c = (rs.getString("sub_name"));
                String d = (rs.getString("group_id"));
                String e = (rs.getString("tag"));
                String f = (rs.getString("noOfStudents"));
                String g = (rs.getString("duration"));
                h = (rs.getString("date"));
                String i = (rs.getString("room"));
                String paraSlot = a + "\n" + c + "(" + b + ")" + e + "\n" + d + "," + f + "(" + g + ")" + "," + i;
                if(h.equals("monday")) {
                    if (J == 0 &&  d.equals(grouplist.getValue()) || J == 0 && a.equals(leclist.getValue())|| J == 0 && i.equals(roomlist.getValue())) {
                        paraArray[0] = paraSlot;
                    }
                    if (J == 1 &&  d.equals(grouplist.getValue()) || J == 1 && a.equals(leclist.getValue()) || J == 1 && i.equals(roomlist.getValue())) {
                        paraArray[1] = paraSlot;
                    }
                    if (J == 2 &&  d.equals(grouplist.getValue()) || J == 2 && a.equals(leclist.getValue()) || J == 2 && i.equals(roomlist.getValue())) {
                        paraArray[2] = paraSlot;
                    }
                    if (J == 3 &&  d.equals(grouplist.getValue()) || J == 3 && a.equals(leclist.getValue()) || J == 3 && i.equals(roomlist.getValue())) {
                        paraArray[3] = paraSlot;
                    }
                    if (J == 4 &&  d.equals(grouplist.getValue()) || J == 4 && a.equals(leclist.getValue()) || J == 4 && i.equals(roomlist.getValue())) {
                        paraArray[4] = paraSlot;
                    }
                    if (J == 5 &&  d.equals(grouplist.getValue()) || J == 5 && a.equals(leclist.getValue()) || J == 5 && i.equals(roomlist.getValue())) {
                        paraArray[5] = paraSlot;
                    }
                    if (J == 6 &&  d.equals(grouplist.getValue()) || J == 6 && a.equals(leclist.getValue()) || J == 6 && i.equals(roomlist.getValue())) {
                        paraArray[6] = paraSlot;
                    }
                    if (J == 7 &&  d.equals(grouplist.getValue()) || J == 7 && a.equals(leclist.getValue()) || J == 7 && i.equals(roomlist.getValue())) {
                        paraArray[7] = paraSlot;
                    }
                    if (J == 8 &&  d.equals(grouplist.getValue()) || J == 8 && a.equals(leclist.getValue()) || J == 8 && i.equals(roomlist.getValue())) {
                        paraArray[8] = paraSlot;
                    }
                    if (J == 8 &&  d.equals(grouplist.getValue()) || J == 9 && a.equals(leclist.getValue()) || J == 9 && i.equals(roomlist.getValue())) {
                        paraArray[9] = paraSlot;
                    }
                    m6.setText((paraArray[0]) + "\n" + (paraArray[1]));
                    m8.setText((paraArray[2]) + "\n" + (paraArray[3]));
                    m9.setText((paraArray[4]) + "\n" + (paraArray[5]));
                }
                //tuesday
                if(h.equals("tuesday")) {
                    if (J == 0 &&  d.equals(grouplist.getValue()) || J == 0 && a.equals(leclist.getValue()) || J == 0 && i.equals(roomlist.getValue())) {
                        paraArray2[0] = paraSlot;
                    }
                    if (J == 1 &&  d.equals(grouplist.getValue()) || J == 1 && a.equals(leclist.getValue()) || J == 1 && i.equals(roomlist.getValue())) {
                        paraArray2[1] = paraSlot;
                    }
                    if (J == 2 &&  d.equals(grouplist.getValue()) || J == 2 && a.equals(leclist.getValue()) || J == 2 && i.equals(roomlist.getValue())) {
                        paraArray2[2] = paraSlot;
                    }
                    if (J == 3 &&  d.equals(grouplist.getValue()) || J == 3 && a.equals(leclist.getValue()) || J == 3 && i.equals(roomlist.getValue())) {
                        paraArray2[3] = paraSlot;
                    }
                    if (J == 4 &&  d.equals(grouplist.getValue()) || J == 4 && a.equals(leclist.getValue()) || J == 4 && i.equals(roomlist.getValue())) {
                        paraArray2[4] = paraSlot;
                    }
                    if (J == 5 &&  d.equals(grouplist.getValue()) || J == 5 && a.equals(leclist.getValue()) || J == 5 && i.equals(roomlist.getValue())) {
                        paraArray2[5] = paraSlot;
                    }
                    if (J == 6 &&  d.equals(grouplist.getValue()) || J == 6 && a.equals(leclist.getValue()) || J == 6 && i.equals(roomlist.getValue())) {
                        paraArray2[6] = paraSlot;
                    }
                    if (J == 7 &&  d.equals(grouplist.getValue()) || J == 7 && a.equals(leclist.getValue()) || J == 7 && i.equals(roomlist.getValue())) {
                        paraArray2[7] = paraSlot;
                    }
                    if (J == 8 &&  d.equals(grouplist.getValue()) || J == 8 && a.equals(leclist.getValue()) || J == 8 && i.equals(roomlist.getValue())) {
                        paraArray2[8] = paraSlot;
                    }
                    if (J == 8 &&  d.equals(grouplist.getValue()) || J == 9 && a.equals(leclist.getValue()) || J == 9 && i.equals(roomlist.getValue())) {
                        paraArray2[9] = paraSlot;
                    }
                    tu6.setText((paraArray2[0]) + "\n" + (paraArray2[1]));
                    tu8.setText((paraArray2[2]) + "\n" + (paraArray2[3]));
                    tu9.setText((paraArray2[4]) + "\n" + (paraArray2[5]));
                }
                //wednesday
                if(h.equals("wednesday")) {
                    if (J == 0 &&  d.equals(grouplist.getValue()) || J == 0 && a.equals(leclist.getValue()) || J == 0 && i.equals(roomlist.getValue())) {
                        paraArray3[0] = paraSlot;
                    }
                    if (J == 1 &&  d.equals(grouplist.getValue()) || J == 1 && a.equals(leclist.getValue()) || J == 1 && i.equals(roomlist.getValue())) {
                        paraArray3[1] = paraSlot;
                    }
                    if (J == 2 &&  d.equals(grouplist.getValue()) || J == 2 && a.equals(leclist.getValue()) || J == 2 && i.equals(roomlist.getValue())) {
                        paraArray3[2] = paraSlot;
                    }
                    if (J == 3 &&  d.equals(grouplist.getValue()) || J == 3 && a.equals(leclist.getValue()) || J == 3 && i.equals(roomlist.getValue())) {
                        paraArray3[3] = paraSlot;
                    }
                    if (J == 4 &&  d.equals(grouplist.getValue()) || J == 4 && a.equals(leclist.getValue()) || J == 4 && i.equals(roomlist.getValue())) {
                        paraArray3[4] = paraSlot;
                    }
                    if (J == 5 &&  d.equals(grouplist.getValue()) || J == 5 && a.equals(leclist.getValue()) || J == 5 && i.equals(roomlist.getValue())) {
                        paraArray3[5] = paraSlot;
                    }
                    if (J == 6 &&  d.equals(grouplist.getValue()) || J == 6 && a.equals(leclist.getValue()) || J == 6 && i.equals(roomlist.getValue())) {
                        paraArray3[6] = paraSlot;
                    }
                    if (J == 7 &&  d.equals(grouplist.getValue()) || J == 7 && a.equals(leclist.getValue()) || J == 7 && i.equals(roomlist.getValue())) {
                        paraArray3[7] = paraSlot;
                    }
                    if (J == 8 &&  d.equals(grouplist.getValue()) || J == 8 && a.equals(leclist.getValue()) || J == 8 && i.equals(roomlist.getValue())) {
                        paraArray3[8] = paraSlot;
                    }
                    if (J == 8 &&  d.equals(grouplist.getValue()) || J == 9 && a.equals(leclist.getValue()) || J == 9 && i.equals(roomlist.getValue())) {
                        paraArray3[9] = paraSlot;
                    }
                    w6.setText((paraArray3[0]) + "\n" + (paraArray3[1]));
                    w8.setText((paraArray3[2]) + "\n" + (paraArray3[3]));
                    w9.setText((paraArray3[4]) + "\n" + (paraArray3[5]));
                }
                //thursday
                if(h.equals("thursday")) {
                    if (J == 0 &&  d.equals(grouplist.getValue()) || J == 0 && a.equals(leclist.getValue()) || J == 0 && i.equals(roomlist.getValue())) {
                        paraArray4[0] = paraSlot;
                    }
                    if (J == 1 &&  d.equals(grouplist.getValue()) || J == 1 && a.equals(leclist.getValue()) || J == 1 && i.equals(roomlist.getValue())) {
                        paraArray4[1] = paraSlot;
                    }
                    if (J == 2 &&  d.equals(grouplist.getValue()) || J == 2 && a.equals(leclist.getValue()) || J == 2 && i.equals(roomlist.getValue())) {
                        paraArray4[2] = paraSlot;
                    }
                    if (J == 3 &&  d.equals(grouplist.getValue()) || J == 3 && a.equals(leclist.getValue()) || J == 3 && i.equals(roomlist.getValue())) {
                        paraArray4[3] = paraSlot;
                    }
                    if (J == 4 &&  d.equals(grouplist.getValue()) || J == 4 && a.equals(leclist.getValue()) || J == 4 && i.equals(roomlist.getValue())) {
                        paraArray4[4] = paraSlot;
                    }
                    if (J == 5 &&  d.equals(grouplist.getValue()) || J == 5 && a.equals(leclist.getValue()) || J == 5 && i.equals(roomlist.getValue())) {
                        paraArray4[5] = paraSlot;
                    }
                    if (J == 6 &&  d.equals(grouplist.getValue()) || J == 6 && a.equals(leclist.getValue()) || J == 6 && i.equals(roomlist.getValue())) {
                        paraArray4[6] = paraSlot;
                    }
                    if (J == 7 &&  d.equals(grouplist.getValue()) || J == 7 && a.equals(leclist.getValue()) || J == 7 && i.equals(roomlist.getValue())) {
                        paraArray4[7] = paraSlot;
                    }
                    if (J == 8 &&  d.equals(grouplist.getValue()) || J == 8 && a.equals(leclist.getValue()) || J == 8 && i.equals(roomlist.getValue())) {
                        paraArray4[8] = paraSlot;
                    }
                    if (J == 8 &&  d.equals(grouplist.getValue()) || J == 9 && a.equals(leclist.getValue()) || J == 9 && i.equals(roomlist.getValue())) {
                        paraArray4[9] = paraSlot;
                    }
                    th6.setText((paraArray4[0]) + "\n" + (paraArray4[1]));
                    th8.setText((paraArray4[2]) + "\n" + (paraArray4[3]));
                    th9.setText((paraArray4[4]) + "\n" + (paraArray4[5]));
                }

                //friday
                if(h.equals("friday")) {
                    if (J == 0 &&  d.equals(grouplist.getValue()) || J == 0 && a.equals(leclist.getValue()) || J == 0 && i.equals(roomlist.getValue())) {
                        paraArray5[0] = paraSlot;
                    }
                    if (J == 1 &&  d.equals(grouplist.getValue()) || J == 1 && a.equals(leclist.getValue()) || J == 1 && i.equals(roomlist.getValue())) {
                        paraArray5[1] = paraSlot;
                    }
                    if (J == 2 &&  d.equals(grouplist.getValue()) || J == 2 && a.equals(leclist.getValue()) || J == 2 && i.equals(roomlist.getValue())) {
                        paraArray5[2] = paraSlot;
                    }
                    if (J == 3 &&  d.equals(grouplist.getValue()) || J == 3 && a.equals(leclist.getValue()) || J == 3 && i.equals(roomlist.getValue())) {
                        paraArray5[3] = paraSlot;
                    }
                    if (J == 4 &&  d.equals(grouplist.getValue()) || J == 4 && a.equals(leclist.getValue()) || J == 4 && i.equals(roomlist.getValue())) {
                        paraArray5[4] = paraSlot;
                    }
                    if (J == 5 &&  d.equals(grouplist.getValue()) || J == 5 && a.equals(leclist.getValue()) || J == 5 && i.equals(roomlist.getValue())) {
                        paraArray5[5] = paraSlot;
                    }
                    if (J == 6 &&  d.equals(grouplist.getValue()) || J == 6 && a.equals(leclist.getValue()) || J == 6 && i.equals(roomlist.getValue())) {
                        paraArray5[6] = paraSlot;
                    }
                    if (J == 7 &&  d.equals(grouplist.getValue()) || J == 7 && a.equals(leclist.getValue()) || J == 7 && i.equals(roomlist.getValue())) {
                        paraArray5[7] = paraSlot;
                    }
                    if (J == 8 &&  d.equals(grouplist.getValue()) || J == 8 && a.equals(leclist.getValue()) || J == 8 && i.equals(roomlist.getValue())) {
                        paraArray5[8] = paraSlot;
                    }
                    if (J == 8 &&  d.equals(grouplist.getValue()) || J == 9 && a.equals(leclist.getValue()) || J == 9 && i.equals(roomlist.getValue())) {
                        paraArray5[9] = paraSlot;
                    }
                    fr6.setText((paraArray5[0]) + "\n" + (paraArray5[1]));
                    fr8.setText((paraArray5[2]) + "\n" + (paraArray5[3]));
                    fr9.setText((paraArray5[4]) + "\n" + (paraArray5[5]));
                }
                //saturday
                if(h.equals("saturday")) {
                if (J == 0 &&  d.equals(grouplist.getValue())  || J == 0 && a.equals(leclist.getValue()) || J == 0 && i.equals(roomlist.getValue())) {
                    paraArray6[0] = paraSlot;
                }
                if (J == 1 &&  d.equals(grouplist.getValue())  || J == 1 && a.equals(leclist.getValue()) || J == 1 && i.equals(roomlist.getValue())) {
                    paraArray6[1] = paraSlot;
                }
                if (J == 2 &&  d.equals(grouplist.getValue())  || J == 2 && a.equals(leclist.getValue()) || J == 2 && i.equals(roomlist.getValue())) {
                    paraArray6[2] = paraSlot;
                }
                if (J == 3 &&  d.equals(grouplist.getValue())  || J == 3 && a.equals(leclist.getValue()) || J == 3 && i.equals(roomlist.getValue())) {
                    paraArray6[3] = paraSlot;
                }
                if (J == 4 &&  d.equals(grouplist.getValue())  || J == 4 && a.equals(leclist.getValue()) || J == 4 && i.equals(roomlist.getValue())) {
                    paraArray6[4] = paraSlot;
                }
                if (J == 5 &&  d.equals(grouplist.getValue())  || J == 5 && a.equals(leclist.getValue()) || J == 5 && i.equals(roomlist.getValue())) {
                    paraArray6[5] = paraSlot;
                }
                if (J == 6 &&  d.equals(grouplist.getValue())  || J == 6 && a.equals(leclist.getValue()) || J == 6 && i.equals(roomlist.getValue())) {
                    paraArray6[6] = paraSlot;
                }
                if (J == 7 &&  d.equals(grouplist.getValue())  || J == 7 && a.equals(leclist.getValue()) || J == 7 && i.equals(roomlist.getValue())) {
                    paraArray6[7] = paraSlot;
                }
                if (J == 8 &&  d.equals(grouplist.getValue())  || J == 8 && a.equals(leclist.getValue()) || J == 8 && i.equals(roomlist.getValue())) {
                    paraArray6[8] = paraSlot;
                }
                if (J == 8 &&  d.equals(grouplist.getValue())  || J == 9 && a.equals(leclist.getValue()) || J == 9 && i.equals(roomlist.getValue())) {
                    paraArray6[9] = paraSlot;
                }
                    sat6.setText((paraArray6[0]) + "\n" + (paraArray6[1]));
                    sat8.setText((paraArray6[2]) + "\n" + (paraArray6[3]));
                    sat9.setText((paraArray6[4]) + "\n" + (paraArray6[5]));
                }
                //sunday
                if(h.equals("sunday")) {
                if (J == 0 &&  d.equals(grouplist.getValue())  || J == 0 && a.equals(leclist.getValue()) || J == 0 && i.equals(roomlist.getValue())) {
                    paraArray7[0] = paraSlot;
                }
                if (J == 1 &&  d.equals(grouplist.getValue())  || J == 1 && a.equals(leclist.getValue()) || J == 1 && i.equals(roomlist.getValue())) {
                    paraArray7[1] = paraSlot;
                }
                if (J == 2 &&  d.equals(grouplist.getValue())  || J == 2 && a.equals(leclist.getValue()) || J == 2 && i.equals(roomlist.getValue())) {
                    paraArray7[2] = paraSlot;
                }
                if (J == 3 &&  d.equals(grouplist.getValue())  || J == 3 && a.equals(leclist.getValue()) || J == 3 && i.equals(roomlist.getValue())) {
                    paraArray7[3] = paraSlot;
                }
                if (J == 4 &&  d.equals(grouplist.getValue())  || J == 4 && a.equals(leclist.getValue()) || J == 4 && i.equals(roomlist.getValue())) {
                    paraArray7[4] = paraSlot;
                }
                if (J == 5 &&  d.equals(grouplist.getValue())  || J == 5 && a.equals(leclist.getValue()) || J == 5 && i.equals(roomlist.getValue())) {
                    paraArray7[5] = paraSlot;
                }
                if (J == 6 &&  d.equals(grouplist.getValue())  || J == 6 && a.equals(leclist.getValue()) || J == 6 && i.equals(roomlist.getValue())) {
                    paraArray7[6] = paraSlot;
                }
                if (J == 7 &&  d.equals(grouplist.getValue())  || J == 7 && a.equals(leclist.getValue()) || J == 7 && i.equals(roomlist.getValue())) {
                    paraArray7[7] = paraSlot;
                }
                if (J == 8 &&  d.equals(grouplist.getValue())  || J == 8 && a.equals(leclist.getValue()) || J == 8 && i.equals(roomlist.getValue())) {
                    paraArray7[8] = paraSlot;
                }
                if (J == 8 &&  d.equals(grouplist.getValue())  || J == 9 && a.equals(leclist.getValue()) || J == 9 && i.equals(roomlist.getValue())) {
                    paraArray7[9] = paraSlot;
                }
                    sun6.setText((paraArray7[0]) + "\n" + (paraArray7[1]));
                    sun8.setText((paraArray7[2]) + "\n" + (paraArray7[3]));
                    sun9.setText((paraArray7[4]) + "\n" + (paraArray7[5]));
                }
                break;
            }catch (Exception e){
                System.out.println(e);
            }
        }
    }
    }


    public void createCons() throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(QUERY2);
        String [] consArray = new String[10];
        String [] consArray2 = new String[10];
        String [] consArray3 = new String[10];
        String [] consArray4 = new String[10];
        String [] consArray5 = new String[10];
        String [] consArray6 = new String[10];
        String [] consArray7 = new String[10];
        String h;
        String i1;

        for (int i =0; i<10;i++) {

            while (rs.next()) {

                    //Display values
                    String a = (rs.getString("lec"));
                    String b = (rs.getString("sub_code"));
                    String c = (rs.getString("sub_name"));
                    String d = (rs.getString("group_id"));
                    String e = (rs.getString("tag"));
                    String f = (rs.getString("noOfStudents"));
                    String g = (rs.getString("duration"));
                    h = (rs.getString("date"));
                    i1 = (rs.getString("room"));

                    String conSlot = a + "\n" + c + "(" + b + ")" + e + "\n" + d + "," + f + "(" + g + ")" + "," + i1;
                    if(h.equals("monday")) {
                        if (i == 0 && d.equals(grouplist.getValue()) || i == 0 && a.equals(leclist.getValue()) || i == 0 && i1.equals(roomlist.getValue())) {
                            consArray[0] = conSlot;
                            System.out.println(i1);
                            System.out.println(a);

                        }
                        if (i == 1 &&  d.equals(grouplist.getValue()) || i == 1 && a.equals(leclist.getValue()) || i == 1 && i1.equals(roomlist.getValue())) {
                            consArray[1] = conSlot;
                            System.out.println(i1);
                            System.out.println(a);
                        }
                        if (i == 2 &&  d.equals(grouplist.getValue()) || i == 2 && a.equals(leclist.getValue()) || i == 2 && i1.equals(roomlist.getValue())) {
                            consArray[2] = conSlot;
                            System.out.println(i1);
                            System.out.println(a);
                        }
                        if (i == 3 &&  d.equals(grouplist.getValue()) || i == 3 && a.equals(leclist.getValue()) || i == 3 && i1.equals(roomlist.getValue())) {
                            consArray[3] = conSlot;
                            System.out.println(i1);
                            System.out.println(a);
                        }
                        if (i == 4 &&  d.equals(grouplist.getValue()) || i == 4 && a.equals(leclist.getValue()) || i == 4 && i1.equals(roomlist.getValue())) {
                            consArray[4] = conSlot;
                        }
                        if (i == 5 &&  d.equals(grouplist.getValue()) || i == 5 && a.equals(leclist.getValue()) || i == 5 && i1.equals(roomlist.getValue())) {
                            consArray[5] = conSlot;
                        }
                        if (i == 6 &&  d.equals(grouplist.getValue()) || i == 6 && a.equals(leclist.getValue()) || i == 6 && i1.equals(roomlist.getValue())) {
                            consArray[6] = conSlot;
                        }
                        if (i == 7 &&  d.equals(grouplist.getValue()) || i == 7 && a.equals(leclist.getValue()) || i == 7 && i1.equals(roomlist.getValue())) {
                            consArray[7] = conSlot;
                        }
                        if (i == 8 &&  d.equals(grouplist.getValue()) || i == 8 && a.equals(leclist.getValue()) || i == 8 && i1.equals(roomlist.getValue())) {
                            consArray[8] = conSlot;
                        }
                        if (i == 8 &&  d.equals(grouplist.getValue()) || i == 9 && a.equals(leclist.getValue()) || i == 9 && i1.equals(roomlist.getValue())) {
                            consArray[9] = conSlot;
                        }

                        m1.setText(consArray[0]);
                        m2.setText(consArray[1]);
                        m3.setText(consArray[2]);
                        m4.setText(consArray[3]);
                        m5.setText(consArray[4]);
                    }
                    //tuesday
                    if(h.equals("tuesday")) {

                        if (i == 0 &&  d.equals(grouplist.getValue()) || i == 0 && a.equals(leclist.getValue()) || i == 0 && i1.equals(roomlist.getValue())) {
                            consArray2[0] = conSlot;
                        }
                        if (i == 1 &&  d.equals(grouplist.getValue()) || i == 1 && a.equals(leclist.getValue()) || i == 1 && i1.equals(roomlist.getValue())) {
                            consArray2[1] = conSlot;
                        }
                        if (i == 2 &&  d.equals(grouplist.getValue()) || i == 2 && a.equals(leclist.getValue()) || i == 2 && i1.equals(roomlist.getValue())) {
                            consArray2[2] = conSlot;
                        }
                        if (i == 3 &&  d.equals(grouplist.getValue()) || i == 3 && a.equals(leclist.getValue()) || i == 3 && i1.equals(roomlist.getValue())) {
                            consArray2[3] = conSlot;
                        }
                        if (i == 4 &&  d.equals(grouplist.getValue()) || i == 4 && a.equals(leclist.getValue()) || i == 4 && i1.equals(roomlist.getValue())) {
                            consArray2[4] = conSlot;
                        }
                        if (i == 5 &&  d.equals(grouplist.getValue()) || i == 5 && a.equals(leclist.getValue()) || i == 5 && i1.equals(roomlist.getValue())) {
                            consArray2[5] = conSlot;
                        }
                        if (i == 6 &&  d.equals(grouplist.getValue()) || i == 6 && a.equals(leclist.getValue()) || i == 6 && i1.equals(roomlist.getValue())) {
                            consArray2[6] = conSlot;
                        }
                        if (i == 7 &&  d.equals(grouplist.getValue()) || i == 7 && a.equals(leclist.getValue()) || i == 7 && i1.equals(roomlist.getValue())) {
                            consArray2[7] = conSlot;
                        }
                        if (i == 8 &&  d.equals(grouplist.getValue()) || i == 8 && a.equals(leclist.getValue()) || i == 8 && i1.equals(roomlist.getValue())) {
                            consArray2[8] = conSlot;
                        }
                        if (i == 8 &&  d.equals(grouplist.getValue()) || i == 9 && a.equals(leclist.getValue()) || i == 9 && i1.equals(roomlist.getValue())) {
                            consArray2[9] = conSlot;
                        }
                        tu1.setText(consArray2[0]);
                        tu2.setText(consArray2[1]);
                        tu3.setText(consArray2[2]);
                        tu4.setText(consArray2[3]);
                    }
                    //wednesday
                    if(h.equals("wednesday")) {
                        if (i == 0 &&  d.equals(grouplist.getValue()) || i == 0 && a.equals(leclist.getValue()) || i == 0 && i1.equals(roomlist.getValue())) {
                            consArray3[0] = conSlot;
                        }
                        if (i == 1 &&  d.equals(grouplist.getValue()) || i == 1 && a.equals(leclist.getValue()) || i == 1 && i1.equals(roomlist.getValue())) {
                            consArray3[1] = conSlot;
                        }
                        if (i == 2 &&  d.equals(grouplist.getValue()) || i == 2 && a.equals(leclist.getValue()) || i == 2 && i1.equals(roomlist.getValue())) {
                            consArray3[2] = conSlot;
                        }
                        if (i == 3 &&  d.equals(grouplist.getValue()) || i == 3 && a.equals(leclist.getValue()) || i == 3 && i1.equals(roomlist.getValue())) {
                            consArray3[3] = conSlot;
                        }
                        if (i == 4 &&  d.equals(grouplist.getValue()) || i == 4 && a.equals(leclist.getValue()) || i == 4 && i1.equals(roomlist.getValue())) {
                            consArray3[4] = conSlot;
                        }
                        if (i == 5 &&  d.equals(grouplist.getValue()) || i == 5 && a.equals(leclist.getValue()) || i == 5 && i1.equals(roomlist.getValue())) {
                            consArray3[5] = conSlot;
                        }
                        if (i == 6 &&  d.equals(grouplist.getValue()) || i == 6 && a.equals(leclist.getValue()) || i == 6 && i1.equals(roomlist.getValue())) {
                            consArray3[6] = conSlot;
                        }
                        if (i == 7 &&  d.equals(grouplist.getValue()) || i == 7 && a.equals(leclist.getValue()) || i == 7 && i1.equals(roomlist.getValue())) {
                            consArray3[7] = conSlot;
                        }
                        if (i == 8 &&  d.equals(grouplist.getValue()) || i == 8 && a.equals(leclist.getValue()) || i == 8 && i1.equals(roomlist.getValue())) {
                            consArray3[8] = conSlot;
                        }
                        if (i == 8 &&  d.equals(grouplist.getValue()) || i == 9 && a.equals(leclist.getValue()) || i == 9 && i1.equals(roomlist.getValue())) {
                            consArray3[9] = conSlot;
                        }
                        w1.setText(consArray3[0]);
                        w2.setText(consArray3[1]);
                        w3.setText(consArray3[2]);
                        w4.setText(consArray3[3]);
                    }
                    //thursday
                    if(h.equals("thursday")) {
                        if (i == 0 &&  d.equals(grouplist.getValue()) || i == 0 && a.equals(leclist.getValue()) || i == 0 && i1.equals(roomlist.getValue())) {
                            consArray[0] = conSlot;
                        }
                        if (i == 1 &&  d.equals(grouplist.getValue()) || i == 1 && a.equals(leclist.getValue())  || i == 1 && i1.equals(roomlist.getValue())) {
                            consArray4[1] = conSlot;
                        }
                        if (i == 2 &&  d.equals(grouplist.getValue()) || i == 2 && a.equals(leclist.getValue())  || i == 2 && i1.equals(roomlist.getValue())) {
                            consArray4[2] = conSlot;
                        }
                        if (i == 3 &&  d.equals(grouplist.getValue()) || i == 3 && a.equals(leclist.getValue())  || i == 3 && i1.equals(roomlist.getValue())) {
                            consArray4[3] = conSlot;
                        }
                        if (i == 4 &&  d.equals(grouplist.getValue()) || i == 4 && a.equals(leclist.getValue())  || i == 4 && i1.equals(roomlist.getValue())) {
                            consArray4[4] = conSlot;
                        }
                        if (i == 5 &&  d.equals(grouplist.getValue()) || i == 5 && a.equals(leclist.getValue())  || i == 5 && i1.equals(roomlist.getValue())) {
                            consArray4[5] = conSlot;
                        }
                        if (i == 6 &&  d.equals(grouplist.getValue()) || i == 6 && a.equals(leclist.getValue())  || i == 6 && i1.equals(roomlist.getValue())) {
                            consArray4[6] = conSlot;
                        }
                        if (i == 7 &&  d.equals(grouplist.getValue()) || i == 7 && a.equals(leclist.getValue())  || i == 7 && i1.equals(roomlist.getValue())) {
                            consArray4[7] = conSlot;
                        }
                        if (i == 8 &&  d.equals(grouplist.getValue()) || i == 8 && a.equals(leclist.getValue())  || i == 8 && i1.equals(roomlist.getValue())) {
                            consArray4[8] = conSlot;
                        }
                        if (i == 8 &&  d.equals(grouplist.getValue()) || i == 9 && a.equals(leclist.getValue())  || i == 9 && i1.equals(roomlist.getValue())) {
                            consArray4[9] = conSlot;
                        }
                        th1.setText(consArray4[0]);
                        th2.setText(consArray4[1]);
                        th3.setText(consArray4[2]);
                        th4.setText(consArray4[3]);
                    }
                    //friday
                    if(h.equals("friday")) {
                        if (i == 0 &&  d.equals(grouplist.getValue()) || i == 0 && a.equals(leclist.getValue())  || i == 0 && i1.equals(roomlist.getValue())) {
                            consArray5[0] = conSlot;
                        }
                        if (i == 1 &&  d.equals(grouplist.getValue()) || i == 1 && a.equals(leclist.getValue())  || i == 1 && i1.equals(roomlist.getValue())) {
                            consArray5[1] = conSlot;
                        }
                        if (i == 2 &&  d.equals(grouplist.getValue()) || i == 2 && a.equals(leclist.getValue())  || i == 2 && i1.equals(roomlist.getValue())) {
                            consArray5[2] = conSlot;
                        }
                        if (i == 3 &&  d.equals(grouplist.getValue()) || i == 3 && a.equals(leclist.getValue())  || i == 3 && i1.equals(roomlist.getValue())) {
                            consArray5[3] = conSlot;
                        }
                        if (i == 4 &&  d.equals(grouplist.getValue()) || i == 4 && a.equals(leclist.getValue())  || i == 4 && i1.equals(roomlist.getValue())) {
                            consArray5[4] = conSlot;
                        }
                        if (i == 5 &&  d.equals(grouplist.getValue()) || i == 5 && a.equals(leclist.getValue())  || i == 5 && i1.equals(roomlist.getValue())) {
                            consArray5[5] = conSlot;
                        }
                        if (i == 6 &&  d.equals(grouplist.getValue()) || i == 6 && a.equals(leclist.getValue())  || i == 6 && i1.equals(roomlist.getValue())) {
                            consArray5[6] = conSlot;
                        }
                        if (i == 7 &&  d.equals(grouplist.getValue()) || i == 7 && a.equals(leclist.getValue())  || i == 7 && i1.equals(roomlist.getValue())) {
                            consArray5[7] = conSlot;
                        }
                        if (i == 8 &&  d.equals(grouplist.getValue()) || i == 8 && a.equals(leclist.getValue())  || i == 8 && i1.equals(roomlist.getValue())) {
                            consArray5[8] = conSlot;
                        }
                        if (i == 8 &&  d.equals(grouplist.getValue()) || i == 9 && a.equals(leclist.getValue())  || i == 9 && i1.equals(roomlist.getValue())) {
                            consArray5[9] = conSlot;
                        }
                        fr1.setText(consArray5[0]);
                        fr2.setText(consArray5[1]);
                        fr3.setText(consArray5[2]);
                        fr4.setText(consArray5[3]);
                    }
                    if(h.equals("saturday")) {
                        //saturday
                        if (i == 0 &&  d.equals(grouplist.getValue()) || i == 0 && a.equals(leclist.getValue())  || i == 0 && i1.equals(roomlist.getValue())) {
                            consArray6[0] = conSlot;
                        }
                        if (i == 1 &&  d.equals(grouplist.getValue()) || i == 1 && a.equals(leclist.getValue())  || i == 1 && i1.equals(roomlist.getValue())) {
                            consArray6[1] = conSlot;
                        }
                        if (i == 2 &&  d.equals(grouplist.getValue()) || i == 2 && a.equals(leclist.getValue())  || i == 2 && i1.equals(roomlist.getValue())) {
                            consArray6[2] = conSlot;
                        }
                        if (i == 3 &&  d.equals(grouplist.getValue()) || i == 3 && a.equals(leclist.getValue())  || i == 3 && i1.equals(roomlist.getValue())) {
                            consArray6[3] = conSlot;
                        }
                        if (i == 4 &&  d.equals(grouplist.getValue()) || i == 4 && a.equals(leclist.getValue())  || i == 4 && i1.equals(roomlist.getValue())) {
                            consArray6[4] = conSlot;
                        }
                        if (i == 5 &&  d.equals(grouplist.getValue()) || i == 5 && a.equals(leclist.getValue())  || i == 5 && i1.equals(roomlist.getValue())) {
                            consArray6[5] = conSlot;
                        }
                        if (i == 6 &&  d.equals(grouplist.getValue()) || i == 6 && a.equals(leclist.getValue())  || i == 6 && i1.equals(roomlist.getValue())) {
                            consArray6[6] = conSlot;
                        }
                        if (i == 7 &&  d.equals(grouplist.getValue()) || i == 7 && a.equals(leclist.getValue())  || i == 7 && i1.equals(roomlist.getValue())) {
                            consArray6[7] = conSlot;
                        }
                        if (i == 8 &&  d.equals(grouplist.getValue()) || i == 8 && a.equals(leclist.getValue())  || i == 8 && i1.equals(roomlist.getValue())) {
                            consArray6[8] = conSlot;
                        }
                        if (i == 8 &&  d.equals(grouplist.getValue()) || i == 9 && a.equals(leclist.getValue())  || i == 9 && i1.equals(roomlist.getValue())) {
                            consArray6[9] = conSlot;
                        }
                        m1.setText(consArray6[0]);
                        m2.setText(consArray6[1]);
                        m3.setText(consArray6[2]);
                        m4.setText(consArray6[3]);
                    }
                    break;
            }
        }


    }
    public void createNotOverlapSlotes() throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(QUERY3);
        String [] nonArray = new String[10];
        String [] nonArray1 = new String[10];
        String [] nonArray2 = new String[10];
        String [] nonArray3 = new String[10];
        String [] nonArray4 = new String[10];
        String [] nonArray5 = new String[10];
        String [] nonArray6 = new String[10];
        String [] nonArray7 = new String[10];
        String h = null;
        for (int z =0; z<10;z++) {
            while (rs.next()) {
                //get values
                String a = (rs.getString("lec"));
                String b = (rs.getString("sub_code"));
                String c = (rs.getString("sub_name"));
                String d = (rs.getString("group_id"));
                String e = (rs.getString("tag"));
                String f = (rs.getString("noOfStudents"));
                String g = (rs.getString("duration"));
                h = (rs.getString("date"));
                String i =(rs.getString("room"));
                String nonSlot = a + "\n" + c + "(" + b + ")" + e + "\n" + d + "," + f + "(" + g + ")"+","+i;
//monday
                if(h.equals("monday")) {
                    if (z == 0 &&  d.equals(grouplist.getValue()) || z == 0 && a.equals(leclist.getValue()) || z == 0 && i.equals(roomlist.getValue())) {
                        nonArray[0] = nonSlot;
                    }
                    if (z == 1 &&  d.equals(grouplist.getValue()) || z == 1 && a.equals(leclist.getValue()) || z == 1 && i.equals(roomlist.getValue())) {
                        nonArray[1] = nonSlot;
                    }
                    if (z == 2 &&  d.equals(grouplist.getValue()) || z == 2 && a.equals(leclist.getValue()) || z == 2 && i.equals(roomlist.getValue())) {
                        nonArray[2] = nonSlot;
                    }
                    if (z == 3 &&  d.equals(grouplist.getValue()) || z == 3 && a.equals(leclist.getValue()) || z == 3 && i.equals(roomlist.getValue())) {
                        nonArray[3] = nonSlot;
                    }
                    if (z == 4 &&  d.equals(grouplist.getValue()) || z == 4 && a.equals(leclist.getValue()) || z == 4 && i.equals(roomlist.getValue())) {
                        nonArray[4] = nonSlot;
                    }
                    if (z == 5 &&  d.equals(grouplist.getValue()) || z == 5 && a.equals(leclist.getValue()) || z == 5 && i.equals(roomlist.getValue())) {
                        nonArray[5] = nonSlot;
                    }
                    if (z == 6 &&  d.equals(grouplist.getValue()) || z == 6 && a.equals(leclist.getValue()) || z == 6 && i.equals(roomlist.getValue())) {
                        nonArray[6] = nonSlot;
                    }
                    if (z == 7 &&  d.equals(grouplist.getValue()) || z == 7 && a.equals(leclist.getValue()) || z == 7 && i.equals(roomlist.getValue())) {
                        nonArray[7] = nonSlot;
                    }
                    if (z == 8 &&  d.equals(grouplist.getValue()) || z == 8 && a.equals(leclist.getValue()) || z == 8 && i.equals(roomlist.getValue())) {
                        nonArray[8] = nonSlot;
                    }
                    if (z == 8 &&  d.equals(grouplist.getValue()) || z == 9 && a.equals(leclist.getValue()) || z == 9 && i.equals(roomlist.getValue())) {
                        nonArray[8] = nonSlot;
                    }
                    m1.setText(nonArray1[0]);
                    m2.setText(nonArray1[1]);
                    m3.setText(nonArray1[2]);
                    m4.setText(nonArray1[3]);
                }
//tuesday

                if(h.equals("tuesday")) {
                    if (z == 0 &&  d.equals(grouplist.getValue()) || z == 0 && a.equals(leclist.getValue()) || z == 0 && i.equals(roomlist.getValue())) {
                        nonArray2[0] = nonSlot;
                    }
                    if (z == 1 &&  d.equals(grouplist.getValue()) || z == 1 && a.equals(leclist.getValue()) || z == 1 && i.equals(roomlist.getValue())) {
                        nonArray2[1] = nonSlot;
                    }
                    if (z == 2 &&  d.equals(grouplist.getValue()) || z == 2 && a.equals(leclist.getValue()) || z == 2 && i.equals(roomlist.getValue())) {
                        nonArray2[2] = nonSlot;
                    }
                    if (z == 3 &&  d.equals(grouplist.getValue()) || z == 3 && a.equals(leclist.getValue()) || z == 3 && i.equals(roomlist.getValue())) {
                        nonArray2[3] = nonSlot;
                    }
                    if (z == 4 &&  d.equals(grouplist.getValue()) || z == 4 && a.equals(leclist.getValue()) || z == 4 && i.equals(roomlist.getValue())) {
                        nonArray2[4] = nonSlot;
                    }
                    if (z == 5 &&  d.equals(grouplist.getValue()) || z == 5 && a.equals(leclist.getValue()) || z == 5 && i.equals(roomlist.getValue())) {
                        nonArray2[5] = nonSlot;
                    }
                    if (z == 6 &&  d.equals(grouplist.getValue()) || z == 6 && a.equals(leclist.getValue()) || z == 6 && i.equals(roomlist.getValue())) {
                        nonArray2[6] = nonSlot;
                    }
                    if (z == 7 &&  d.equals(grouplist.getValue()) || z == 7 && a.equals(leclist.getValue()) || z == 7 && i.equals(roomlist.getValue())) {
                        nonArray2[7] = nonSlot;
                    }
                    if (z == 8 &&  d.equals(grouplist.getValue()) || z == 8 && a.equals(leclist.getValue()) || z == 8 && i.equals(roomlist.getValue())) {
                        nonArray2[8] = nonSlot;
                    }
                    if (z == 8 &&  d.equals(grouplist.getValue()) || z == 9 && a.equals(leclist.getValue()) || z == 9 && i.equals(roomlist.getValue())) {
                        nonArray2[9] = nonSlot;
                    }
                    tu1.setText(nonArray2[0]);
                    tu2.setText(nonArray2[1]);
                    tu3.setText(nonArray2[2]);
                    tu4.setText(nonArray2[3]);
                }
                //wednesday
                if(h.equals("wednesday")) {
                    if (z == 0 &&  d.equals(grouplist.getValue()) || z == 0 && a.equals(leclist.getValue()) || z == 0 && i.equals(roomlist.getValue())) {
                        nonArray3[0] = nonSlot;
                    }
                    if (z == 1 &&  d.equals(grouplist.getValue()) || z == 1 && a.equals(leclist.getValue()) || z == 1 && i.equals(roomlist.getValue())) {
                        nonArray3[1] = nonSlot;
                    }
                    if (z == 2 &&  d.equals(grouplist.getValue()) || z == 2 && a.equals(leclist.getValue()) || z == 2 && i.equals(roomlist.getValue())) {
                        nonArray3[2] = nonSlot;
                    }
                    if (z == 3 &&  d.equals(grouplist.getValue()) || z == 3 && a.equals(leclist.getValue()) || z == 3 && i.equals(roomlist.getValue())) {
                        nonArray3[3] = nonSlot;
                    }
                    if (z == 4 &&  d.equals(grouplist.getValue()) || z == 4 && a.equals(leclist.getValue()) || z == 4 && i.equals(roomlist.getValue())) {
                        nonArray3[4] = nonSlot;
                    }
                    if (z == 5 &&  d.equals(grouplist.getValue()) || z == 5 && a.equals(leclist.getValue()) || z == 5 && i.equals(roomlist.getValue())) {
                        nonArray3[5] = nonSlot;
                    }
                    if (z == 6 &&  d.equals(grouplist.getValue()) || z == 6 && a.equals(leclist.getValue()) || z == 6 && i.equals(roomlist.getValue())) {
                        nonArray3[6] = nonSlot;
                    }
                    if (z == 7 &&  d.equals(grouplist.getValue()) || z == 7 && a.equals(leclist.getValue()) || z == 7 && i.equals(roomlist.getValue())) {
                        nonArray3[7] = nonSlot;
                    }
                    if (z == 8 &&  d.equals(grouplist.getValue()) || z == 8 && a.equals(leclist.getValue()) || z == 8 && i.equals(roomlist.getValue())) {
                        nonArray3[8] = nonSlot;
                    }
                    if (z == 8 &&  d.equals(grouplist.getValue()) || z == 9 && a.equals(leclist.getValue()) || z == 9 && i.equals(roomlist.getValue())) {
                        nonArray3[9] = nonSlot;
                    }
                    w1.setText(nonArray3[0]);
                    w2.setText(nonArray3[1]);
                    w3.setText(nonArray3[2]);
                    w4.setText(nonArray3[3]);
                }
                //thursday
                if(h.equals("thursday")) {
                    if (z == 0 &&  d.equals(grouplist.getValue()) || z == 0 && a.equals(leclist.getValue()) || z == 0 && i.equals(roomlist.getValue())) {
                        nonArray4[0] = nonSlot;
                    }
                    if (z == 1 &&  d.equals(grouplist.getValue()) || z == 1 && a.equals(leclist.getValue()) || z == 1 && i.equals(roomlist.getValue())) {
                        nonArray4[1] = nonSlot;
                    }
                    if (z == 2 &&  d.equals(grouplist.getValue()) || z == 2 && a.equals(leclist.getValue()) || z == 2 && i.equals(roomlist.getValue())) {
                        nonArray4[2] = nonSlot;
                    }
                    if (z == 3 &&  d.equals(grouplist.getValue()) || z == 3 && a.equals(leclist.getValue()) || z == 3 && i.equals(roomlist.getValue())) {
                        nonArray4[3] = nonSlot;
                    }
                    if (z == 4 &&  d.equals(grouplist.getValue()) || z == 4 && a.equals(leclist.getValue()) || z == 4 && i.equals(roomlist.getValue())) {
                        nonArray4[4] = nonSlot;
                    }
                    if (z == 5 &&  d.equals(grouplist.getValue()) || z == 5 && a.equals(leclist.getValue()) || z == 5 && i.equals(roomlist.getValue())) {
                        nonArray4[5] = nonSlot;
                    }
                    if (z == 6 &&  d.equals(grouplist.getValue()) || z == 6 && a.equals(leclist.getValue()) || z == 6 && i.equals(roomlist.getValue())) {
                        nonArray4[6] = nonSlot;
                    }
                    if (z == 7 &&  d.equals(grouplist.getValue()) || z == 7 && a.equals(leclist.getValue()) || z == 7 && i.equals(roomlist.getValue())) {
                        nonArray4[7] = nonSlot;
                    }
                    if (z == 8 &&  d.equals(grouplist.getValue()) || z == 8 && a.equals(leclist.getValue()) || z == 8 && i.equals(roomlist.getValue())) {
                        nonArray4[8] = nonSlot;
                    }
                    if (z == 9 &&  d.equals(grouplist.getValue()) || z == 9 && a.equals(leclist.getValue()) || z == 9 && i.equals(roomlist.getValue())) {
                        nonArray4[9] = nonSlot;
                    }
                    th1.setText(nonArray4[0]);
                    th2.setText(nonArray4[1]);
                    th3.setText(nonArray4[2]);
                    th4.setText(nonArray4[3]);
                }
                //friday
                if(h.equals("friday")) {
                    if (z == 0 &&  d.equals(grouplist.getValue()) || z == 0 && a.equals(leclist.getValue()) || z == 9 && i.equals(roomlist.getValue())) {
                        nonArray5[0] = nonSlot;
                    }
                    if (z == 1 &&  d.equals(grouplist.getValue()) || z == 1 && a.equals(leclist.getValue()) || z == 9 && i.equals(roomlist.getValue())) {
                        nonArray5[1] = nonSlot;
                    }
                    if (z == 2 &&  d.equals(grouplist.getValue()) || z == 2 && a.equals(leclist.getValue()) || z == 9 && i.equals(roomlist.getValue())) {
                        nonArray5[2] = nonSlot;
                    }
                    if (z == 3 &&  d.equals(grouplist.getValue()) || z == 3 && a.equals(leclist.getValue()) || z == 9 && i.equals(roomlist.getValue())) {
                        nonArray5[3] = nonSlot;
                    }
                    if (z == 4 &&  d.equals(grouplist.getValue()) || z == 4 && a.equals(leclist.getValue()) || z == 9 && i.equals(roomlist.getValue())) {
                        nonArray5[4] = nonSlot;
                    }
                    if (z == 5 &&  d.equals(grouplist.getValue()) || z == 5 && a.equals(leclist.getValue()) || z == 9 && i.equals(roomlist.getValue())) {
                        nonArray5[5] = nonSlot;
                    }
                    if (z == 6 &&  d.equals(grouplist.getValue()) || z == 6 && a.equals(leclist.getValue()) || z == 9 && i.equals(roomlist.getValue())) {
                        nonArray5[6] = nonSlot;
                    }
                    if (z == 7 &&  d.equals(grouplist.getValue()) || z == 7 && a.equals(leclist.getValue()) || z == 9 && i.equals(roomlist.getValue())) {
                        nonArray5[7] = nonSlot;
                    }
                    if (z == 8 &&  d.equals(grouplist.getValue()) || z == 8 && a.equals(leclist.getValue()) || z == 9 && i.equals(roomlist.getValue())) {
                        nonArray5[8] = nonSlot;
                    }
                    if (z == 8 &&  d.equals(grouplist.getValue()) || z == 9 && a.equals(leclist.getValue()) || z == 9 && i.equals(roomlist.getValue())) {
                        nonArray5[9] = nonSlot;
                    }
                    fr1.setText(nonArray5[0]);
                    fr2.setText(nonArray5[1]);
                    fr3.setText(nonArray5[2]);
                    fr4.setText(nonArray5[3]);
                }
                if(h.equals("saturday")) {
                    //saturday
                    if (z == 0 &&  d.equals(grouplist.getValue()) || z == 0 && a.equals(leclist.getValue()) || z == 0 && i.equals(roomlist.getValue())) {
                        nonArray6[0] = nonSlot;
                    }
                    if (z == 1 &&  d.equals(grouplist.getValue()) || z == 1 && a.equals(leclist.getValue()) || z == 1 && i.equals(roomlist.getValue())) {
                        nonArray6[1] = nonSlot;
                    }
                    if (z == 2 &&  d.equals(grouplist.getValue()) || z == 2 && a.equals(leclist.getValue()) || z == 2 && i.equals(roomlist.getValue())) {
                        nonArray6[2] = nonSlot;
                    }
                    if (z == 3 &&  d.equals(grouplist.getValue()) || z == 3 && a.equals(leclist.getValue()) || z == 3 && i.equals(roomlist.getValue())) {
                        nonArray6[3] = nonSlot;
                    }
                    if (z == 4 &&  d.equals(grouplist.getValue()) || z == 4 && a.equals(leclist.getValue()) || z == 4 && i.equals(roomlist.getValue())) {
                        nonArray6[4] = nonSlot;
                    }
                    if (z == 5 &&  d.equals(grouplist.getValue()) || z == 5 && a.equals(leclist.getValue()) || z == 5 && i.equals(roomlist.getValue())) {
                        nonArray6[5] = nonSlot;
                    }
                    if (z == 6 &&  d.equals(grouplist.getValue()) || z == 6 && a.equals(leclist.getValue()) || z == 6 && i.equals(roomlist.getValue())) {
                        nonArray6[6] = nonSlot;
                    }
                    if (z == 7 &&  d.equals(grouplist.getValue()) || z == 7 && a.equals(leclist.getValue()) || z == 7 && i.equals(roomlist.getValue())) {
                        nonArray6[7] = nonSlot;
                    }
                    if (z == 8 &&  d.equals(grouplist.getValue()) || z == 8 && a.equals(leclist.getValue()) || z == 8 && i.equals(roomlist.getValue())) {
                        nonArray6[8] = nonSlot;
                    }
                    if (z == 8 &&  d.equals(grouplist.getValue()) || z == 9 && a.equals(leclist.getValue()) || z == 9 && i.equals(roomlist.getValue())) {
                        nonArray6[9] = nonSlot;
                    }
                    m1.setText(nonArray6[0]);
                    m2.setText(nonArray6[1]);
                    m3.setText(nonArray6[2]);
                    m4.setText(nonArray6[3]);
                }
                if(h.equals("sunday")) {
                    //sunday
                    if (z == 0 &&  d.equals(grouplist.getValue()) || z == 0 && a.equals(leclist.getValue())  || z == 0 && i.equals(roomlist.getValue())) {
                        nonArray7[0] = nonSlot;
                    }
                    if (z == 1 &&  d.equals(grouplist.getValue()) || z == 1 && a.equals(leclist.getValue())  || z == 1 && i.equals(roomlist.getValue())) {
                        nonArray7[1] = nonSlot;
                    }
                    if (z == 2 &&  d.equals(grouplist.getValue()) || z == 2 && a.equals(leclist.getValue())  || z == 2 && i.equals(roomlist.getValue())) {
                        nonArray7[2] = nonSlot;
                    }
                    if (z == 3 &&  d.equals(grouplist.getValue()) || z == 3 && a.equals(leclist.getValue())  || z == 3 && i.equals(roomlist.getValue())) {
                        nonArray7[3] = nonSlot;
                    }
                    if (z == 4 &&  d.equals(grouplist.getValue()) || z == 4 && a.equals(leclist.getValue()) || z == 4 && i.equals(roomlist.getValue()) ) {
                        nonArray7[4] = nonSlot;
                    }
                    if (z == 5 &&  d.equals(grouplist.getValue()) || z == 5 && a.equals(leclist.getValue()) || z == 5 && i.equals(roomlist.getValue()) ) {
                        nonArray7[5] = nonSlot;
                    }
                    if (z == 6 &&  d.equals(grouplist.getValue()) || z == 6 && a.equals(leclist.getValue()) || z == 6 && i.equals(roomlist.getValue()) ) {
                        nonArray7[6] = nonSlot;
                    }
                    if (z == 7 &&  d.equals(grouplist.getValue()) || z == 7 && a.equals(leclist.getValue())  || z == 7 && i.equals(roomlist.getValue())) {
                        nonArray7[7] = nonSlot;
                    }
                    if (z == 8 &&  d.equals(grouplist.getValue()) || z == 8 && a.equals(leclist.getValue())  || z == 8 && i.equals(roomlist.getValue())) {
                        nonArray7[8] = nonSlot;
                    }
                    if (z == 8 &&  d.equals(grouplist.getValue()) || z == 9 && a.equals(leclist.getValue())  || z == 9 && i.equals(roomlist.getValue())) {
                        nonArray7[9] = nonSlot;
                    }
                    m1.setText(nonArray7[0]);
                    m2.setText(nonArray7[1]);
                    m3.setText(nonArray7[2]);
                    m4.setText(nonArray7[3]);
                }

                break;

            }
        }


    }
    @FXML
    public void changeScreen(ActionEvent event) {
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
