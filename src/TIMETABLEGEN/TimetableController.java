package TIMETABLEGEN;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import sample.student;
import timetablemgt.locations;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class TimetableController implements Initializable {
    @FXML
    public Label time1,time2,time3,time4,time5,time6,time7,time8,time9;
    public Label m1,m2,m3,m4,m5,m6,m7,m8,m9;
    public Label tu1,tu2,tu3,tu4,tu5,tu6,tu7,tu8,tu9;
    public Label w1,w2,w3,w4,w5,w6,w7,w8,w9;
    public Label th1,th2,th3,th4,th5,th6,th7,th8,th9;
    public Label fr1,fr2,fr3,fr4,fr5,fr6,fr7,fr8,fr9;
    public Label sat1,sat2,sat3,sat4,sat5,sat6,sat7,sat8,sat9;
    public Label sun1,sun2,sun3,sun4,sun5,sun6,sun7,sun8,sun9;



    static final String DB_URL = "jdbc:mysql://localhost:3306/student";
    static final String USER = "root";
    static final String PASS = "shenaljfx";
    static final String QUERY = "SELECT lec, sub_code, sub_name, group_id, tag, noOfStudents, duration  FROM parallel";
    static final String QUERY2 = "SELECT lec, sub_code, sub_name, group_id, tag, noOfStudents, duration  FROM consecutive";


    @Override
    public void initialize(URL location, ResourceBundle resources) {

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
            System.out.println();


    }
    public void generateStudentGroupTimetable() {

    }
    public void generateLectureTimetable() {
        try {

        }catch (Exception E){
            System.out.println(E);
        }
    }
    public void generateRoomTimetable() {
        try {

        }catch (Exception E){
            System.out.println(E);
        }
    }

@FXML
    public void createParaSlots() throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(QUERY);
            String a = null;
            String b= null;
            String c= null;
            String d= null;
            String e= null;
            String f= null;
            String g= null;
            String paraSlot = null;


            while(rs.next()){
                //Display values
               a =( rs.getString("lec"));
                 b =(rs.getString("sub_code"));
                c =( rs.getString("sub_name"));
                d =( rs.getString("group_id"));
                e =( rs.getString("tag"));
                 f =( rs.getString("noOfStudents"));
                g =( rs.getString("duration"));

                paraSlot = a +"\n"+c+"("+b+")"+e+"\n"+d+","+f+"("+g+")";

            return ;


            }


    System.out.println(paraSlot);
    }


    public void createCons() throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(QUERY2);

        while(rs.next()){
            //Display values
            String a =( rs.getString("lec"));
            String b =(rs.getString("sub_code"));
            String c =( rs.getString("sub_name"));
            String d =( rs.getString("group_id"));
            String e =( rs.getString("tag"));
            String f =( rs.getString("noOfStudents"));
            String g =( rs.getString("duration"));

          String paraSlot = a +"\n"+c+"("+b+")"+e+"\n"+d+","+f+"("+g+")";
            System.out.println(paraSlot);
            genTimeSlotes();
            m1.setText(paraSlot);



        }
    }
    public void createNotOverlapSlotes() {
        try {

        }catch (Exception E){
            System.out.println(E);
        }
    }

}
