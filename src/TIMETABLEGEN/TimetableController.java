package TIMETABLEGEN;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
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
    public TextField lec;
    @FXML
    public TextField group;
    @FXML
    public TextField location;




        static final String DB_URL = "jdbc:mysql://localhost:3306/student";
        static final String USER = "root";
        static final String PASS = "shenaljfx";
        static final String QUERY = "SELECT lec, sub_code, sub_name, group_id, tag, noOfStudents, duration  FROM parallel";
        static final String QUERY2 = "SELECT lec, sub_code, sub_name, group_id, tag, noOfStudents, duration  FROM consecutive";
        static final String QUERY3 = "SELECT lec, sub_code, sub_name, group_id, tag, noOfStudents, duration  FROM nonoverlapping";

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
    }
    public void generateStudentGroupTimetable() throws SQLException {
        createCons();
        createParaSlots();
        genTimeSlotes();

    }

    @FXML
    public void createParaSlots() throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(QUERY);
        String [] paraArray = new String[10];

        for (int J =0; J<10;J++) {

        while (rs.next()) {
            //get values
            String  a = (rs.getString("lec"));
            String b = (rs.getString("sub_code"));
            String c = (rs.getString("sub_name"));
            String d = (rs.getString("group_id"));
            String e = (rs.getString("tag"));
            String f = (rs.getString("noOfStudents"));
            String g = (rs.getString("duration"));
            String paraSlot = a + "\n" + c + "(" + b + ")" + e + "\n" + d + "," + f + "(" + g + ")";
            if(J == 0 && d.equals(group.getText())||J == 0 && a.equals(lec.getText())) {
                paraArray[0] = paraSlot;
            }
            if(J == 1  && d.equals(group.getText())||J == 1 && a.equals(lec.getText())) {
                paraArray[1] = paraSlot;
            }
            if(J == 2  && d.equals(group.getText())||J == 2 && a.equals(lec.getText())) {
                paraArray[2] = paraSlot;
            }
            if(J == 3  && d.equals(group.getText())||J == 3 && a.equals(lec.getText())) {
                paraArray[3] = paraSlot;
            }
            if(J == 4  && d.equals(group.getText())||J == 4 && a.equals(lec.getText())) {
                paraArray[4] = paraSlot;
            }
            if(J == 5  && d.equals(group.getText())||J == 5 && a.equals(lec.getText())) {
                paraArray[5] = paraSlot;
            }
            if(J == 6  && d.equals(group.getText())||J == 6 && a.equals(lec.getText())) {
                paraArray[6] = paraSlot;
            }
            if(J == 7  && d.equals(group.getText())||J == 7 && a.equals(lec.getText())) {
                paraArray[7] = paraSlot;
            }
            if(J == 8  && d.equals(group.getText())||J == 8 && a.equals(lec.getText())) {
                paraArray[8] = paraSlot;
            }
            if(J == 9  && d.equals(group.getText())||J == 9 && a.equals(lec.getText())) {
                paraArray[9] = paraSlot;
            }
            break;
        }
    }
            m5.setText((paraArray[0])+"\n"+(paraArray[1]));
            m6.setText((paraArray[2])+"\n"+(paraArray[3]));
    }


    public void createCons() throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(QUERY2);
        String [] consArray = new String[10];
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
                String conSlot = a + "\n" + c + "(" + b + ")" + e + "\n" + d + "," + f + "(" + g + ")";

                if (i == 0 && d.equals(group.getText())||i == 0 && a.equals(lec.getText())) {
                    consArray[0] = conSlot;
                }
                if (i == 1 && d.equals(group.getText())||i == 1 && a.equals(lec.getText())) {
                    consArray[1] = conSlot;
                }
                if (i == 2 && d.equals(group.getText())||i == 2 && a.equals(lec.getText())) {
                    consArray[2] = conSlot;
                }
                if (i == 3 && d.equals(group.getText())||i == 3 && a.equals(lec.getText())) {
                    consArray[3] = conSlot;
                }
                if (i == 4 && d.equals(group.getText())||i == 4 && a.equals(lec.getText())) {
                    consArray[4] = conSlot;
                }
                if (i == 5 && d.equals(group.getText())||i == 5 && a.equals(lec.getText())) {
                    consArray[5] = conSlot;
                }
                if (i == 6 && d.equals(group.getText())||i == 6 && a.equals(lec.getText())) {
                    consArray[6] = conSlot;
                }
                if (i == 7 && d.equals(group.getText())||i == 7 && a.equals(lec.getText())) {
                    consArray[7] = conSlot;
                }
                if (i == 8 && d.equals(group.getText())||i == 8 && a.equals(lec.getText())) {
                    consArray[8] = conSlot;
                }
                if (i == 8 && d.equals(group.getText())||i == 9 && a.equals(lec.getText())) {
                    consArray[8] = conSlot;
                }

                break;

            }
        }

                m1.setText(consArray[0]);
                m2.setText(consArray[1]);
                m3.setText(consArray[2]);
                m4.setText(consArray[3]);

    }
    public void createNotOverlapSlotes() {
        try {

        }catch (Exception E){
            System.out.println(E);
        }
    }
    @FXML
    public void changeScreen(javafx.event.ActionEvent event) {
        try{
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("../mainPage.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);
            Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(tableViewScene);
            window.show();
        }catch(Exception E){
            System.out.println(E);
        }
    }
}
