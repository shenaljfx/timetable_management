

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




        static final String DB_URL = "jdbc:sqlite:D:/SLIIT/3rd year 1st sem/ITPM/IT19033938/src/student";

        static final String QUERY = "SELECT lec, sub_code, sub_name, group_id, tag, noOfStudents, duration,date,room FROM parallel";
        static final String QUERY2 = "SELECT lec, sub_code, sub_name, group_id, tag, noOfStudents, duration,date,room  FROM consecutive";
        static final String QUERY3 = "SELECT lec, sub_code, sub_name, group_id, tag, noOfStudents, duration,date,room  FROM nonoverlapping";

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

                if (J == 0 && d.equals(group.getText()) && h.equals("monday") || J == 0 && a.equals(lec.getText()) && h.equals("monday") || J == 0 && i.equals(location.getText()) && h.equals("monday")) {
                    paraArray[0] = paraSlot;
                }
                if (J == 1 && d.equals(group.getText()) && h.equals("monday") || J == 1 && a.equals(lec.getText()) && h.equals("monday") || J == 0 && i.equals(location.getText()) && h.equals("monday")) {
                    paraArray[1] = paraSlot;
                }
                if (J == 2 && d.equals(group.getText()) && h.equals("monday") || J == 2 && a.equals(lec.getText()) && h.equals("monday") || J == 0 && i.equals(location.getText()) && h.equals("monday")) {
                    paraArray[2] = paraSlot;
                }
                if (J == 3 && d.equals(group.getText()) && h.equals("monday") || J == 3 && a.equals(lec.getText()) && h.equals("monday") || J == 0 && i.equals(location.getText()) && h.equals("monday")) {
                    paraArray[3] = paraSlot;
                }
                if (J == 4 && d.equals(group.getText()) && h.equals("monday") || J == 4 && a.equals(lec.getText()) && h.equals("monday") || J == 0 && i.equals(location.getText()) && h.equals("monday")) {
                    paraArray[4] = paraSlot;
                }
                if (J == 5 && d.equals(group.getText()) && h.equals("monday") || J == 5 && a.equals(lec.getText()) && h.equals("monday") || J == 0 && i.equals(location.getText()) && h.equals("monday")) {
                    paraArray[5] = paraSlot;
                }
                if (J == 6 && d.equals(group.getText()) && h.equals("monday") || J == 6 && a.equals(lec.getText()) && h.equals("monday") || J == 0 && i.equals(location.getText()) && h.equals("monday")) {
                    paraArray[6] = paraSlot;
                }
                if (J == 7 && d.equals(group.getText()) && h.equals("monday") || J == 7 && a.equals(lec.getText()) && h.equals("monday") || J == 0 && i.equals(location.getText()) && h.equals("monday")) {
                    paraArray[7] = paraSlot;
                }
                if (J == 8 && d.equals(group.getText()) && h.equals("monday") || J == 8 && a.equals(lec.getText()) && h.equals("monday") || J == 0 && i.equals(location.getText()) && h.equals("monday")) {
                    paraArray[8] = paraSlot;
                }
                if (J == 8 && d.equals(group.getText()) && h.equals("monday") || J == 9 && a.equals(lec.getText()) && h.equals("monday") || J == 0 && i.equals(location.getText()) && h.equals("monday")) {
                    paraArray[9] = paraSlot;
                }
                //tuesday

                if (J == 0 && d.equals(group.getText()) && h.equals("tuesday") || J == 0 && a.equals(lec.getText()) && h.equals("tuesday") || J == 0 && i.equals(location.getText()) && h.equals("tuesday")) {
                    paraArray2[0] = paraSlot;
                }
                if (J == 1 && d.equals(group.getText()) && h.equals("tuesday") || J == 1 && a.equals(lec.getText()) && h.equals("tuesday") || J == 0 && i.equals(location.getText()) && h.equals("tuesday")) {
                    paraArray2[1] = paraSlot;
                }
                if (J == 2 && d.equals(group.getText()) && h.equals("tuesday") || J == 2 && a.equals(lec.getText()) && h.equals("tuesday") || J == 0 && i.equals(location.getText()) && h.equals("tuesday")) {
                    paraArray2[2] = paraSlot;
                }
                if (J == 3 && d.equals(group.getText()) && h.equals("tuesday") || J == 3 && a.equals(lec.getText()) && h.equals("tuesday") || J == 0 && i.equals(location.getText()) && h.equals("tuesday")) {
                    paraArray2[3] = paraSlot;
                }
                if (J == 4 && d.equals(group.getText()) && h.equals("tuesday") || J == 4 && a.equals(lec.getText()) && h.equals("tuesday") || J == 0 && i.equals(location.getText()) && h.equals("tuesday")) {
                    paraArray2[4] = paraSlot;
                }
                if (J == 5 && d.equals(group.getText()) && h.equals("tuesday") || J == 5 && a.equals(lec.getText()) && h.equals("tuesday") || J == 0 && i.equals(location.getText()) && h.equals("tuesday")) {
                    paraArray2[5] = paraSlot;
                }
                if (J == 6 && d.equals(group.getText()) && h.equals("tuesday") || J == 6 && a.equals(lec.getText()) && h.equals("tuesday") || J == 0 && i.equals(location.getText()) && h.equals("tuesday")) {
                    paraArray2[6] = paraSlot;
                }
                if (J == 7 && d.equals(group.getText()) && h.equals("tuesday") || J == 7 && a.equals(lec.getText()) && h.equals("tuesday") || J == 0 && i.equals(location.getText()) && h.equals("tuesday")) {
                    paraArray2[7] = paraSlot;
                }
                if (J == 8 && d.equals(group.getText()) && h.equals("tuesday") || J == 8 && a.equals(lec.getText()) && h.equals("tuesday") || J == 0 && i.equals(location.getText()) && h.equals("tuesday")) {
                    paraArray2[8] = paraSlot;
                }
                if (J == 8 && d.equals(group.getText()) && h.equals("tuesday") || J == 9 && a.equals(lec.getText()) && h.equals("tuesday") || J == 0 && i.equals(location.getText()) && h.equals("tuesday")) {
                    paraArray2[9] = paraSlot;
                }
                //wednesday

                if (J == 0 && d.equals(group.getText()) && h.equals("wednesday") || J == 0 && a.equals(lec.getText()) && h.equals("wednesday") || J == 0 && i.equals(location.getText()) && h.equals("wednesday")) {
                    paraArray3[0] = paraSlot;
                }
                if (J == 1 && d.equals(group.getText()) && h.equals("wednesday") || J == 1 && a.equals(lec.getText()) && h.equals("wednesday") || J == 0 && i.equals(location.getText()) && h.equals("wednesday")) {
                    paraArray3[1] = paraSlot;
                }
                if (J == 2 && d.equals(group.getText()) && h.equals("wednesday") || J == 2 && a.equals(lec.getText()) && h.equals("wednesday") || J == 0 && i.equals(location.getText()) && h.equals("wednesday")) {
                    paraArray3[2] = paraSlot;
                }
                if (J == 3 && d.equals(group.getText()) && h.equals("wednesday") || J == 3 && a.equals(lec.getText()) && h.equals("wednesday") || J == 0 && i.equals(location.getText()) && h.equals("wednesday")) {
                    paraArray3[3] = paraSlot;
                }
                if (J == 4 && d.equals(group.getText()) && h.equals("wednesday") || J == 4 && a.equals(lec.getText()) && h.equals("wednesday") || J == 0 && i.equals(location.getText()) && h.equals("wednesday")) {
                    paraArray3[4] = paraSlot;
                }
                if (J == 5 && d.equals(group.getText()) && h.equals("wednesday") || J == 5 && a.equals(lec.getText()) && h.equals("wednesday") || J == 0 && i.equals(location.getText()) && h.equals("wednesday")) {
                    paraArray3[5] = paraSlot;
                }
                if (J == 6 && d.equals(group.getText()) && h.equals("wednesday") || J == 6 && a.equals(lec.getText()) && h.equals("wednesday") || J == 0 && i.equals(location.getText()) && h.equals("wednesday")) {
                    paraArray3[6] = paraSlot;
                }
                if (J == 7 && d.equals(group.getText()) && h.equals("wednesday") || J == 7 && a.equals(lec.getText()) && h.equals("wednesday") || J == 0 && i.equals(location.getText()) && h.equals("wednesday")) {
                    paraArray3[7] = paraSlot;
                }
                if (J == 8 && d.equals(group.getText()) && h.equals("wednesday") || J == 8 && a.equals(lec.getText()) && h.equals("wednesday") || J == 0 && i.equals(location.getText()) && h.equals("wednesday")) {
                    paraArray3[8] = paraSlot;
                }
                if (J == 8 && d.equals(group.getText()) && h.equals("wednesday") || J == 9 && a.equals(lec.getText()) && h.equals("wednesday") || J == 0 && i.equals(location.getText()) && h.equals("wednesday")) {
                    paraArray3[9] = paraSlot;
                }
                //thursday

                if (J == 0 && d.equals(group.getText()) && h.equals("thursday") || J == 0 && a.equals(lec.getText()) && h.equals("thursday") || J == 0 && i.equals(location.getText()) && h.equals("thursday")) {
                    paraArray4[0] = paraSlot;
                }
                if (J == 1 && d.equals(group.getText()) && h.equals("thursday") || J == 1 && a.equals(lec.getText()) && h.equals("thursday") || J == 0 && i.equals(location.getText()) && h.equals("thursday")) {
                    paraArray4[1] = paraSlot;
                }
                if (J == 2 && d.equals(group.getText()) && h.equals("thursday") || J == 2 && a.equals(lec.getText()) && h.equals("thursday") || J == 0 && i.equals(location.getText()) && h.equals("thursday")) {
                    paraArray4[2] = paraSlot;
                }
                if (J == 3 && d.equals(group.getText()) && h.equals("thursday") || J == 3 && a.equals(lec.getText()) && h.equals("thursday") || J == 0 && i.equals(location.getText()) && h.equals("thursday")) {
                    paraArray4[3] = paraSlot;
                }
                if (J == 4 && d.equals(group.getText()) && h.equals("thursday") || J == 4 && a.equals(lec.getText()) && h.equals("thursday") || J == 0 && i.equals(location.getText()) && h.equals("thursday")) {
                    paraArray4[4] = paraSlot;
                }
                if (J == 5 && d.equals(group.getText()) && h.equals("thursday") || J == 5 && a.equals(lec.getText()) && h.equals("thursday") || J == 0 && i.equals(location.getText()) && h.equals("thursday")) {
                    paraArray4[5] = paraSlot;
                }
                if (J == 6 && d.equals(group.getText()) && h.equals("thursday") || J == 6 && a.equals(lec.getText()) && h.equals("thursday") || J == 0 && i.equals(location.getText()) && h.equals("thursday")) {
                    paraArray4[6] = paraSlot;
                }
                if (J == 7 && d.equals(group.getText()) && h.equals("thursday") || J == 7 && a.equals(lec.getText()) && h.equals("thursday") || J == 0 && i.equals(location.getText()) && h.equals("thursday")) {
                    paraArray4[7] = paraSlot;
                }
                if (J == 8 && d.equals(group.getText()) && h.equals("thursday") || J == 8 && a.equals(lec.getText()) && h.equals("thursday") || J == 0 && i.equals(location.getText()) && h.equals("thursday")) {
                    paraArray4[8] = paraSlot;
                }
                if (J == 8 && d.equals(group.getText()) && h.equals("thursday") || J == 9 && a.equals(lec.getText()) && h.equals("thursday") || J == 0 && i.equals(location.getText()) && h.equals("thursday")) {
                    paraArray4[9] = paraSlot;
                }

                //friday

                if (J == 0 && d.equals(group.getText()) && h.equals("friday") || J == 0 && a.equals(lec.getText()) && h.equals("friday") || J == 0 && i.equals(location.getText()) && h.equals("friday")) {
                    paraArray5[0] = paraSlot;
                }
                if (J == 1 && d.equals(group.getText()) && h.equals("friday") || J == 1 && a.equals(lec.getText()) && h.equals("friday") || J == 0 && i.equals(location.getText()) && h.equals("friday")) {
                    paraArray5[1] = paraSlot;
                }
                if (J == 2 && d.equals(group.getText()) && h.equals("friday") || J == 2 && a.equals(lec.getText()) && h.equals("friday") || J == 0 && i.equals(location.getText()) && h.equals("friday")) {
                    paraArray5[2] = paraSlot;
                }
                if (J == 3 && d.equals(group.getText()) && h.equals("friday") || J == 3 && a.equals(lec.getText()) && h.equals("friday") || J == 0 && i.equals(location.getText()) && h.equals("friday")) {
                    paraArray5[3] = paraSlot;
                }
                if (J == 4 && d.equals(group.getText()) && h.equals("friday") || J == 4 && a.equals(lec.getText()) && h.equals("friday") || J == 0 && i.equals(location.getText()) && h.equals("friday")) {
                    paraArray5[4] = paraSlot;
                }
                if (J == 5 && d.equals(group.getText()) && h.equals("friday") || J == 5 && a.equals(lec.getText()) && h.equals("friday") || J == 0 && i.equals(location.getText()) && h.equals("friday")) {
                    paraArray5[5] = paraSlot;
                }
                if (J == 6 && d.equals(group.getText()) && h.equals("friday") || J == 6 && a.equals(lec.getText()) && h.equals("friday") || J == 0 && i.equals(location.getText()) && h.equals("friday")) {
                    paraArray5[6] = paraSlot;
                }
                if (J == 7 && d.equals(group.getText()) && h.equals("friday") || J == 7 && a.equals(lec.getText()) && h.equals("friday") || J == 0 && i.equals(location.getText()) && h.equals("friday")) {
                    paraArray5[7] = paraSlot;
                }
                if (J == 8 && d.equals(group.getText()) && h.equals("friday") || J == 8 && a.equals(lec.getText()) && h.equals("friday") || J == 0 && i.equals(location.getText()) && h.equals("friday")) {
                    paraArray5[8] = paraSlot;
                }
                if (J == 8 && d.equals(group.getText()) && h.equals("friday") || J == 9 && a.equals(lec.getText()) && h.equals("friday") || J == 0 && i.equals(location.getText()) && h.equals("friday")) {
                    paraArray5[9] = paraSlot;
                }
                //saturday
                if (J == 0 && d.equals(group.getText()) && h.equals("saturday") || J == 0 && a.equals(lec.getText()) && h.equals("saturday") || J == 0 && i.equals(location.getText()) && h.equals("saturday")) {
                    paraArray6[0] = paraSlot;
                }
                if (J == 1 && d.equals(group.getText()) && h.equals("saturday") || J == 1 && a.equals(lec.getText()) && h.equals("saturday") || J == 0 && i.equals(location.getText()) && h.equals("saturday")) {
                    paraArray6[1] = paraSlot;
                }
                if (J == 2 && d.equals(group.getText()) && h.equals("saturday") || J == 2 && a.equals(lec.getText()) && h.equals("saturday") || J == 0 && i.equals(location.getText()) && h.equals("saturday")) {
                    paraArray6[2] = paraSlot;
                }
                if (J == 3 && d.equals(group.getText()) && h.equals("saturday") || J == 3 && a.equals(lec.getText()) && h.equals("saturday") || J == 0 && i.equals(location.getText()) && h.equals("saturday")) {
                    paraArray6[3] = paraSlot;
                }
                if (J == 4 && d.equals(group.getText()) && h.equals("saturday") || J == 4 && a.equals(lec.getText()) && h.equals("saturday") || J == 0 && i.equals(location.getText()) && h.equals("saturday")) {
                    paraArray6[4] = paraSlot;
                }
                if (J == 5 && d.equals(group.getText()) && h.equals("saturday") || J == 5 && a.equals(lec.getText()) && h.equals("saturday") || J == 0 && i.equals(location.getText()) && h.equals("saturday")) {
                    paraArray6[5] = paraSlot;
                }
                if (J == 6 && d.equals(group.getText()) && h.equals("saturday") || J == 6 && a.equals(lec.getText()) && h.equals("saturday") || J == 0 && i.equals(location.getText()) && h.equals("saturday")) {
                    paraArray6[6] = paraSlot;
                }
                if (J == 7 && d.equals(group.getText()) && h.equals("saturday") || J == 7 && a.equals(lec.getText()) && h.equals("saturday") || J == 0 && i.equals(location.getText()) && h.equals("saturday")) {
                    paraArray6[7] = paraSlot;
                }
                if (J == 8 && d.equals(group.getText()) && h.equals("saturday") || J == 8 && a.equals(lec.getText()) && h.equals("saturday") || J == 0 && i.equals(location.getText()) && h.equals("saturday")) {
                    paraArray6[8] = paraSlot;
                }
                if (J == 8 && d.equals(group.getText()) && h.equals("saturday") || J == 9 && a.equals(lec.getText()) && h.equals("saturday") || J == 0 && i.equals(location.getText()) && h.equals("saturday")) {
                    paraArray6[9] = paraSlot;
                }
                //sunday
                if (J == 0 && d.equals(group.getText()) && h.equals("sunday") || J == 0 && a.equals(lec.getText()) && h.equals("sunday") || J == 0 && i.equals(location.getText()) && h.equals("sunday")) {
                    paraArray7[0] = paraSlot;
                }
                if (J == 1 && d.equals(group.getText()) && h.equals("sunday") || J == 1 && a.equals(lec.getText()) && h.equals("sunday") || J == 0 && i.equals(location.getText()) && h.equals("sunday")) {
                    paraArray7[1] = paraSlot;
                }
                if (J == 2 && d.equals(group.getText()) && h.equals("sunday") || J == 2 && a.equals(lec.getText()) && h.equals("sunday") || J == 0 && i.equals(location.getText()) && h.equals("sunday")) {
                    paraArray7[2] = paraSlot;
                }
                if (J == 3 && d.equals(group.getText()) && h.equals("sunday") || J == 3 && a.equals(lec.getText()) && h.equals("sunday") || J == 0 && i.equals(location.getText()) && h.equals("sunday")) {
                    paraArray7[3] = paraSlot;
                }
                if (J == 4 && d.equals(group.getText()) && h.equals("sunday") || J == 4 && a.equals(lec.getText()) && h.equals("sunday") || J == 0 && i.equals(location.getText()) && h.equals("sunday")) {
                    paraArray7[4] = paraSlot;
                }
                if (J == 5 && d.equals(group.getText()) && h.equals("sunday") || J == 5 && a.equals(lec.getText()) && h.equals("sunday") || J == 0 && i.equals(location.getText()) && h.equals("sunday")) {
                    paraArray7[5] = paraSlot;
                }
                if (J == 6 && d.equals(group.getText()) && h.equals("sunday") || J == 6 && a.equals(lec.getText()) && h.equals("sunday") || J == 0 && i.equals(location.getText()) && h.equals("sunday")) {
                    paraArray7[6] = paraSlot;
                }
                if (J == 7 && d.equals(group.getText()) && h.equals("sunday") || J == 7 && a.equals(lec.getText()) && h.equals("sunday") || J == 0 && i.equals(location.getText()) && h.equals("sunday")) {
                    paraArray7[7] = paraSlot;
                }
                if (J == 8 && d.equals(group.getText()) && h.equals("sunday") || J == 8 && a.equals(lec.getText()) && h.equals("sunday") || J == 0 && i.equals(location.getText()) && h.equals("sunday")) {
                    paraArray7[8] = paraSlot;
                }
                if (J == 8 && d.equals(group.getText()) && h.equals("sunday") || J == 9 && a.equals(lec.getText()) && h.equals("sunday") || J == 0 && i.equals(location.getText()) && h.equals("sunday")) {
                    paraArray7[9] = paraSlot;
                }
                break;
            }catch (Exception e){
                System.out.println(e);
            }
        }
    }
        assert h != null;
        if(h.equals("monday")) {
            m6.setText((paraArray[0]) + "\n" + (paraArray[1]));
            m8.setText((paraArray[2]) + "\n" + (paraArray[3]));
            m9.setText((paraArray[4]) + "\n" + (paraArray[5]));
        }
        if(h.equals("tuesday")) {
            tu6.setText((paraArray2[0]) + "\n" + (paraArray2[1]));
            tu8.setText((paraArray2[2]) + "\n" + (paraArray2[3]));
            tu9.setText((paraArray2[4]) + "\n" + (paraArray2[5]));
        }
        if(h.equals("wednesday")) {
            w6.setText((paraArray3[0]) + "\n" + (paraArray3[1]));
            w8.setText((paraArray3[2]) + "\n" + (paraArray3[3]));
            w9.setText((paraArray3[4]) + "\n" + (paraArray3[5]));
        }
        if(h.equals("thursday")) {
            th6.setText((paraArray4[0]) + "\n" + (paraArray4[1]));
            th8.setText((paraArray4[2]) + "\n" + (paraArray4[3]));
            th9.setText((paraArray4[4]) + "\n" + (paraArray4[5]));
        }
        if(h.equals("friday")) {
            fr6.setText((paraArray5[0]) + "\n" + (paraArray5[1]));
            fr8.setText((paraArray5[2]) + "\n" + (paraArray5[3]));
            fr9.setText((paraArray5[4]) + "\n" + (paraArray5[5]));
        }    if(h.equals("saturday")) {
            sat6.setText((paraArray6[0]) + "\n" + (paraArray6[1]));
            sat8.setText((paraArray6[2]) + "\n" + (paraArray6[3]));
            sat9.setText((paraArray6[4]) + "\n" + (paraArray6[5]));
        }
        if(h.equals("sunday")) {
            sun6.setText((paraArray7[0]) + "\n" + (paraArray7[1]));
            sun8.setText((paraArray7[2]) + "\n" + (paraArray7[3]));
            sun9.setText((paraArray7[4]) + "\n" + (paraArray7[5]));
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

        for (int i =0; i<10;i++) {

            while (rs.next()) {
                try {
                    //Display values
                    String a = (rs.getString("lec"));
                    String b = (rs.getString("sub_code"));
                    String c = (rs.getString("sub_name"));
                    String d = (rs.getString("group_id"));
                    String e = (rs.getString("tag"));
                    String f = (rs.getString("noOfStudents"));
                    String g = (rs.getString("duration"));
                    h = (rs.getString("date"));
                    String i1 = (rs.getString("room"));
                    String conSlot = a + "\n" + c + "(" + b + ")" + e + "\n" + d + "," + f + "(" + g + ")" + "," + i1;

                    if (i == 0 && d.equals(group.getText()) && h.equals("monday") || i == 0 && a.equals(lec.getText()) && h.equals("monday") || i == 0 && i1.equals(location.getText()) && h.equals("monday")) {
                        consArray[0] = conSlot;
                    }
                    if (i == 1 && d.equals(group.getText()) && h.equals("monday") || i == 1 && a.equals(lec.getText()) && h.equals("monday") || i == 0 && i1.equals(location.getText()) && h.equals("monday")) {
                        consArray[1] = conSlot;
                    }
                    if (i == 2 && d.equals(group.getText()) && h.equals("monday") || i == 2 && a.equals(lec.getText()) && h.equals("monday") || i == 0 && i1.equals(location.getText()) && h.equals("monday")) {
                        consArray[2] = conSlot;
                    }
                    if (i == 3 && d.equals(group.getText()) && h.equals("monday") || i == 3 && a.equals(lec.getText()) && h.equals("monday") || i == 0 && i1.equals(location.getText()) && h.equals("monday")) {
                        consArray[3] = conSlot;
                    }
                    if (i == 4 && d.equals(group.getText()) && h.equals("monday") || i == 4 && a.equals(lec.getText()) && h.equals("monday") || i == 0 && i1.equals(location.getText()) && h.equals("monday")) {
                        consArray[4] = conSlot;
                    }
                    if (i == 5 && d.equals(group.getText()) && h.equals("monday") || i == 5 && a.equals(lec.getText()) && h.equals("monday") || i == 0 && i1.equals(location.getText()) && h.equals("monday")) {
                        consArray[5] = conSlot;
                    }
                    if (i == 6 && d.equals(group.getText()) && h.equals("monday") || i == 6 && a.equals(lec.getText()) && h.equals("monday") || i == 0 && i1.equals(location.getText()) && h.equals("monday")) {
                        consArray[6] = conSlot;
                    }
                    if (i == 7 && d.equals(group.getText()) && h.equals("monday") || i == 7 && a.equals(lec.getText()) && h.equals("monday") || i == 0 && i1.equals(location.getText()) && h.equals("monday")) {
                        consArray[7] = conSlot;
                    }
                    if (i == 8 && d.equals(group.getText()) && h.equals("monday") || i == 8 && a.equals(lec.getText()) && h.equals("monday") || i == 0 && i1.equals(location.getText()) && h.equals("monday")) {
                        consArray[8] = conSlot;
                    }
                    if (i == 8 && d.equals(group.getText()) || i == 9 && a.equals(lec.getText()) && h.equals("monday") || i == 0 && i1.equals(location.getText()) && h.equals("monday")) {
                        consArray[9] = conSlot;
                    }
                    //tuesday

                    if (i == 0 && d.equals(group.getText()) && h.equals("tuesday") || i == 0 && a.equals(lec.getText()) && h.equals("tuesday") || i == 0 && i1.equals(location.getText()) && h.equals("tuesday")) {
                        consArray2[0] = conSlot;
                    }
                    if (i == 1 && d.equals(group.getText()) && h.equals("tuesday") || i == 1 && a.equals(lec.getText()) && h.equals("tuesday") || i == 0 && i1.equals(location.getText()) && h.equals("tuesday")) {
                        consArray2[1] = conSlot;
                    }
                    if (i == 2 && d.equals(group.getText()) && h.equals("tuesday") || i == 2 && a.equals(lec.getText()) && h.equals("tuesday") || i == 0 && i1.equals(location.getText()) && h.equals("tuesday")) {
                        consArray2[2] = conSlot;
                    }
                    if (i == 3 && d.equals(group.getText()) && h.equals("tuesday") || i == 3 && a.equals(lec.getText()) && h.equals("tuesday") || i == 0 && i1.equals(location.getText()) && h.equals("tuesday")) {
                        consArray2[3] = conSlot;
                    }
                    if (i == 4 && d.equals(group.getText()) && h.equals("tuesday") || i == 4 && a.equals(lec.getText()) && h.equals("tuesday") || i == 0 && i1.equals(location.getText()) && h.equals("tuesday")) {
                        consArray2[4] = conSlot;
                    }
                    if (i == 5 && d.equals(group.getText()) && h.equals("tuesday") || i == 5 && a.equals(lec.getText()) && h.equals("tuesday") || i == 0 && i1.equals(location.getText()) && h.equals("tuesday")) {
                        consArray2[5] = conSlot;
                    }
                    if (i == 6 && d.equals(group.getText()) && h.equals("tuesday") || i == 6 && a.equals(lec.getText()) && h.equals("tuesday") || i == 0 && i1.equals(location.getText()) && h.equals("tuesday")) {
                        consArray2[6] = conSlot;
                    }
                    if (i == 7 && d.equals(group.getText()) && h.equals("tuesday") || i == 7 && a.equals(lec.getText()) && h.equals("tuesday") || i == 0 && i1.equals(location.getText()) && h.equals("tuesday")) {
                        consArray2[7] = conSlot;
                    }
                    if (i == 8 && d.equals(group.getText()) && h.equals("tuesday") || i == 8 && a.equals(lec.getText()) && h.equals("tuesday") || i == 0 && i1.equals(location.getText()) && h.equals("tuesday")) {
                        consArray2[8] = conSlot;
                    }
                    if (i == 8 && d.equals(group.getText()) && h.equals("tuesday") || i == 9 && a.equals(lec.getText()) && h.equals("tuesday") || i == 0 && i1.equals(location.getText()) && h.equals("tuesday")) {
                        consArray2[9] = conSlot;
                    }
                    //wednesday

                    if (i == 0 && d.equals(group.getText()) && h.equals("wednesday") || i == 0 && a.equals(lec.getText()) && h.equals("wednesday") || i == 0 && i1.equals(location.getText()) && h.equals("wednesday")) {
                        consArray3[0] = conSlot;
                    }
                    if (i == 1 && d.equals(group.getText()) && h.equals("wednesday") || i == 1 && a.equals(lec.getText()) && h.equals("wednesday") || i == 0 && i1.equals(location.getText()) && h.equals("wednesday")) {
                        consArray3[1] = conSlot;
                    }
                    if (i == 2 && d.equals(group.getText()) && h.equals("wednesday") || i == 2 && a.equals(lec.getText()) && h.equals("wednesday") || i == 0 && i1.equals(location.getText()) && h.equals("wednesday")) {
                        consArray3[2] = conSlot;
                    }
                    if (i == 3 && d.equals(group.getText()) && h.equals("wednesday") || i == 3 && a.equals(lec.getText()) && h.equals("wednesday") || i == 0 && i1.equals(location.getText()) && h.equals("wednesday")) {
                        consArray3[3] = conSlot;
                    }
                    if (i == 4 && d.equals(group.getText()) && h.equals("wednesday") || i == 4 && a.equals(lec.getText()) && h.equals("wednesday") || i == 0 && i1.equals(location.getText()) && h.equals("wednesday")) {
                        consArray3[4] = conSlot;
                    }
                    if (i == 5 && d.equals(group.getText()) && h.equals("wednesday") || i == 5 && a.equals(lec.getText()) && h.equals("wednesday") || i == 0 && i1.equals(location.getText()) && h.equals("wednesday")) {
                        consArray3[5] = conSlot;
                    }
                    if (i == 6 && d.equals(group.getText()) && h.equals("wednesday") || i == 6 && a.equals(lec.getText()) && h.equals("wednesday") || i == 0 && i1.equals(location.getText()) && h.equals("wednesday")) {
                        consArray3[6] = conSlot;
                    }
                    if (i == 7 && d.equals(group.getText()) && h.equals("wednesday") || i == 7 && a.equals(lec.getText()) && h.equals("wednesday") || i == 0 && i1.equals(location.getText()) && h.equals("wednesday")) {
                        consArray3[7] = conSlot;
                    }
                    if (i == 8 && d.equals(group.getText()) && h.equals("wednesday") || i == 8 && a.equals(lec.getText()) && h.equals("wednesday") || i == 0 && i1.equals(location.getText()) && h.equals("wednesday")) {
                        consArray3[8] = conSlot;
                    }
                    if (i == 8 && d.equals(group.getText()) && h.equals("wednesday") || i == 9 && a.equals(lec.getText()) && h.equals("wednesday") || i == 0 && i1.equals(location.getText()) && h.equals("wednesday")) {
                        consArray3[9] = conSlot;
                    }
                    //thursday

                    if (i == 0 && d.equals(group.getText()) && h.equals("thursday") || i == 0 && a.equals(lec.getText()) && h.equals("thursday") || i == 0 && i1.equals(location.getText()) && h.equals("thursday")) {
                        consArray[0] = conSlot;
                    }
                    if (i == 1 && d.equals(group.getText()) && h.equals("thursday") || i == 1 && a.equals(lec.getText()) && h.equals("thursday") || i == 0 && i1.equals(location.getText()) && h.equals("thursday")) {
                        consArray4[1] = conSlot;
                    }
                    if (i == 2 && d.equals(group.getText()) && h.equals("thursday") || i == 2 && a.equals(lec.getText()) && h.equals("thursday") || i == 0 && i1.equals(location.getText()) && h.equals("thursday")) {
                        consArray4[2] = conSlot;
                    }
                    if (i == 3 && d.equals(group.getText()) && h.equals("thursday") || i == 3 && a.equals(lec.getText()) && h.equals("thursday") || i == 0 && i1.equals(location.getText()) && h.equals("thursday")) {
                        consArray4[3] = conSlot;
                    }
                    if (i == 4 && d.equals(group.getText()) && h.equals("thursday") || i == 4 && a.equals(lec.getText()) && h.equals("thursday") || i == 0 && i1.equals(location.getText()) && h.equals("thursday")) {
                        consArray4[4] = conSlot;
                    }
                    if (i == 5 && d.equals(group.getText()) && h.equals("thursday") || i == 5 && a.equals(lec.getText()) && h.equals("thursday") || i == 0 && i1.equals(location.getText()) && h.equals("thursday")) {
                        consArray4[5] = conSlot;
                    }
                    if (i == 6 && d.equals(group.getText()) && h.equals("thursday") || i == 6 && a.equals(lec.getText()) && h.equals("thursday") || i == 0 && i1.equals(location.getText()) && h.equals("thursday")) {
                        consArray4[6] = conSlot;
                    }
                    if (i == 7 && d.equals(group.getText()) && h.equals("thursday") || i == 7 && a.equals(lec.getText()) && h.equals("thursday") || i == 0 && i1.equals(location.getText()) && h.equals("thursday")) {
                        consArray4[7] = conSlot;
                    }
                    if (i == 8 && d.equals(group.getText()) && h.equals("thursday") || i == 8 && a.equals(lec.getText()) && h.equals("thursday") || i == 0 && i1.equals(location.getText()) && h.equals("thursday")) {
                        consArray4[8] = conSlot;
                    }
                    if (i == 8 && d.equals(group.getText()) && h.equals("thursday") || i == 9 && a.equals(lec.getText()) && h.equals("thursday") || i == 0 && i1.equals(location.getText()) && h.equals("thursday")) {
                        consArray4[9] = conSlot;
                    }
                    //friday

                    if (i == 0 && d.equals(group.getText()) && h.equals("friday") || i == 0 && a.equals(lec.getText()) && h.equals("friday") || i == 0 && i1.equals(location.getText()) && h.equals("friday")) {
                        consArray5[0] = conSlot;
                    }
                    if (i == 1 && d.equals(group.getText()) && h.equals("friday") || i == 1 && a.equals(lec.getText()) && h.equals("friday") || i == 0 && i1.equals(location.getText()) && h.equals("friday")) {
                        consArray5[1] = conSlot;
                    }
                    if (i == 2 && d.equals(group.getText()) && h.equals("friday") || i == 2 && a.equals(lec.getText()) && h.equals("friday") || i == 0 && i1.equals(location.getText()) && h.equals("friday")) {
                        consArray5[2] = conSlot;
                    }
                    if (i == 3 && d.equals(group.getText()) && h.equals("friday") || i == 3 && a.equals(lec.getText()) && h.equals("friday") || i == 0 && i1.equals(location.getText()) && h.equals("friday")) {
                        consArray5[3] = conSlot;
                    }
                    if (i == 4 && d.equals(group.getText()) && h.equals("friday") || i == 4 && a.equals(lec.getText()) && h.equals("friday") || i == 0 && i1.equals(location.getText()) && h.equals("friday")) {
                        consArray5[4] = conSlot;
                    }
                    if (i == 5 && d.equals(group.getText()) && h.equals("friday") || i == 5 && a.equals(lec.getText()) && h.equals("friday") || i == 0 && i1.equals(location.getText()) && h.equals("friday")) {
                        consArray5[5] = conSlot;
                    }
                    if (i == 6 && d.equals(group.getText()) && h.equals("friday") || i == 6 && a.equals(lec.getText()) && h.equals("friday") || i == 0 && i1.equals(location.getText()) && h.equals("friday")) {
                        consArray5[6] = conSlot;
                    }
                    if (i == 7 && d.equals(group.getText()) && h.equals("friday") || i == 7 && a.equals(lec.getText()) && h.equals("friday") || i == 0 && i1.equals(location.getText()) && h.equals("friday")) {
                        consArray5[7] = conSlot;
                    }
                    if (i == 8 && d.equals(group.getText()) && h.equals("friday") || i == 8 && a.equals(lec.getText()) && h.equals("friday") || i == 0 && i1.equals(location.getText()) && h.equals("friday")) {
                        consArray5[8] = conSlot;
                    }
                    if (i == 8 && d.equals(group.getText()) && h.equals("friday") || i == 9 && a.equals(lec.getText()) && h.equals("friday") || i == 0 && i1.equals(location.getText()) && h.equals("friday")) {
                        consArray5[9] = conSlot;
                    }
                    //saturday
                    if (i == 0 && d.equals(group.getText()) && h.equals("saturday") || i == 0 && a.equals(lec.getText()) && h.equals("saturday") || i == 0 && i1.equals(location.getText()) && h.equals("saturday")) {
                        consArray6[0] = conSlot;
                    }
                    if (i == 1 && d.equals(group.getText()) && h.equals("saturday") || i == 1 && a.equals(lec.getText()) && h.equals("saturday") || i == 0 && i1.equals(location.getText()) && h.equals("saturday")) {
                        consArray6[1] = conSlot;
                    }
                    if (i == 2 && d.equals(group.getText()) && h.equals("saturday") || i == 2 && a.equals(lec.getText()) && h.equals("saturday") || i == 0 && i1.equals(location.getText()) && h.equals("saturday")) {
                        consArray6[2] = conSlot;
                    }
                    if (i == 3 && d.equals(group.getText()) && h.equals("saturday") || i == 3 && a.equals(lec.getText()) && h.equals("saturday") || i == 0 && i1.equals(location.getText()) && h.equals("saturday")) {
                        consArray6[3] = conSlot;
                    }
                    if (i == 4 && d.equals(group.getText()) && h.equals("saturday") || i == 4 && a.equals(lec.getText()) && h.equals("saturday") || i == 0 && i1.equals(location.getText()) && h.equals("saturday")) {
                        consArray6[4] = conSlot;
                    }
                    if (i == 5 && d.equals(group.getText()) && h.equals("saturday") || i == 5 && a.equals(lec.getText()) && h.equals("saturday") || i == 0 && i1.equals(location.getText()) && h.equals("saturday")) {
                        consArray6[5] = conSlot;
                    }
                    if (i == 6 && d.equals(group.getText()) && h.equals("saturday") || i == 6 && a.equals(lec.getText()) && h.equals("saturday") || i == 0 && i1.equals(location.getText()) && h.equals("saturday")) {
                        consArray6[6] = conSlot;
                    }
                    if (i == 7 && d.equals(group.getText()) && h.equals("saturday") || i == 7 && a.equals(lec.getText()) && h.equals("saturday") || i == 0 && i1.equals(location.getText()) && h.equals("saturday")) {
                        consArray6[7] = conSlot;
                    }
                    if (i == 8 && d.equals(group.getText()) && h.equals("saturday") || i == 8 && a.equals(lec.getText()) && h.equals("saturday") || i == 0 && i1.equals(location.getText()) && h.equals("saturday")) {
                        consArray6[8] = conSlot;
                    }
                    if (i == 8 && d.equals(group.getText()) && h.equals("saturday") || i == 9 && a.equals(lec.getText()) && h.equals("saturday") || i == 0 && i1.equals(location.getText()) && h.equals("saturday")) {
                        consArray6[9] = conSlot;
                    }
                    //sunday
                    if (i == 0 && d.equals(group.getText()) && h.equals("sunday") || i == 0 && a.equals(lec.getText()) && h.equals("sunday") || i == 0 && i1.equals(location.getText()) && h.equals("sunday")) {
                        consArray7[0] = conSlot;
                    }
                    if (i == 1 && d.equals(group.getText()) && h.equals("sunday") || i == 1 && a.equals(lec.getText()) && h.equals("sunday") || i == 0 && i1.equals(location.getText()) && h.equals("sunday")) {
                        consArray7[1] = conSlot;
                    }
                    if (i == 2 && d.equals(group.getText()) && h.equals("sunday") || i == 2 && a.equals(lec.getText()) && h.equals("sunday") || i == 0 && i1.equals(location.getText()) && h.equals("sunday")) {
                        consArray7[2] = conSlot;
                    }
                    if (i == 3 && d.equals(group.getText()) && h.equals("sunday") || i == 3 && a.equals(lec.getText()) && h.equals("sunday") || i == 0 && i1.equals(location.getText()) && h.equals("sunday")) {
                        consArray7[3] = conSlot;
                    }
                    if (i == 4 && d.equals(group.getText()) && h.equals("sunday") || i == 4 && a.equals(lec.getText()) && h.equals("sunday") || i == 0 && i1.equals(location.getText()) && h.equals("sunday")) {
                        consArray7[4] = conSlot;
                    }
                    if (i == 5 && d.equals(group.getText()) && h.equals("sunday") || i == 5 && a.equals(lec.getText()) && h.equals("sunday") || i == 0 && i1.equals(location.getText()) && h.equals("sunday")) {
                        consArray7[5] = conSlot;
                    }
                    if (i == 6 && d.equals(group.getText()) && h.equals("sunday") || i == 6 && a.equals(lec.getText()) && h.equals("sunday") || i == 0 && i1.equals(location.getText()) && h.equals("sunday")) {
                        consArray7[6] = conSlot;
                    }
                    if (i == 7 && d.equals(group.getText()) && h.equals("sunday") || i == 7 && a.equals(lec.getText()) && h.equals("sunday") || i == 0 && i1.equals(location.getText()) && h.equals("sunday")) {
                        consArray7[7] = conSlot;
                    }
                    if (i == 8 && d.equals(group.getText()) && h.equals("sunday") || i == 8 && a.equals(lec.getText()) && h.equals("sunday") || i == 0 && i1.equals(location.getText()) && h.equals("sunday")) {
                        consArray7[8] = conSlot;
                    }
                    if (i == 8 && d.equals(group.getText()) && h.equals("sunday") || i == 9 && a.equals(lec.getText()) && h.equals("sunday") || i == 0 && i1.equals(location.getText()) && h.equals("sunday")) {
                        consArray7[9] = conSlot;
                    }
                    assert h != null;
                    if (h.equals("monday")) {

                        m1.setText(consArray[0]);
                        m2.setText(consArray[1]);
                        m3.setText(consArray[2]);
                        m4.setText(consArray[3]);
                    }
                    if (h.equals("tuesday")) {
                        tu1.setText(consArray2[0]);
                        tu2.setText(consArray2[1]);
                        tu3.setText(consArray2[2]);
                        tu4.setText(consArray2[3]);
                    }
                    if (h.equals("wednesday")) {
                        w1.setText(consArray3[0]);
                        w2.setText(consArray3[1]);
                        w3.setText(consArray3[2]);
                        w4.setText(consArray3[3]);
                    }
                    if (h.equals("thursday")) {
                        th1.setText(consArray4[0]);
                        th2.setText(consArray4[1]);
                        th3.setText(consArray4[2]);
                        th4.setText(consArray4[3]);
                    }
                    if (h.equals("friday")) {
                        fr1.setText(consArray5[0]);
                        fr2.setText(consArray5[1]);
                        fr3.setText(consArray5[2]);
                        fr4.setText(consArray5[3]);
                    }
                    if (h.equals("saturday")) {
                        m1.setText(consArray6[0]);
                        m2.setText(consArray6[1]);
                        m3.setText(consArray6[2]);
                        m4.setText(consArray6[3]);
                    }
                    break;

                }catch (Exception E){
                    System.out.println(E);
                }
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
                try {
                    //Display values
                    String a = (rs.getString("lec"));
                    String b = (rs.getString("sub_code"));
                    String c = (rs.getString("sub_name"));
                    String d = (rs.getString("group_id"));
                    String e = (rs.getString("tag"));
                    String f = (rs.getString("noOfStudents"));
                    String g = (rs.getString("duration"));
                    String i1 = (rs.getString("room"));
                    String nonSlot = a + "\n" + c + "(" + b + ")" + e + "\n" + d + "," + f + "(" + g + ")" + "," + i1;
//monday
                    if ((z != 0 || !d.equals(group.getText()) || !h.equals("monday")) && (z != 0 || !a.equals(lec.getText()) || !h.equals("monday")) && (z != 0 || !i1.equals(location.getText()) || !h.equals("monday"))) {
                    } else {
                        nonArray[0] = nonSlot;
                    }
                    if (z == 1 && d.equals(group.getText()) && h.equals("monday") || z == 1 && a.equals(lec.getText()) && h.equals("monday") || z == 0 && i1.equals(location.getText()) && h.equals("monday")) {
                        nonArray[1] = nonSlot;
                    }
                    if (z == 2 && d.equals(group.getText()) && h.equals("monday") || z == 2 && a.equals(lec.getText()) && h.equals("monday") || z == 0 && i1.equals(location.getText()) && h.equals("monday")) {
                        nonArray[2] = nonSlot;
                    }
                    if (z == 3 && d.equals(group.getText()) && h.equals("monday") || z == 3 && a.equals(lec.getText()) && h.equals("monday") || z == 0 && i1.equals(location.getText()) && h.equals("monday")) {
                        nonArray[3] = nonSlot;
                    }
                    if (z == 4 && d.equals(group.getText()) && h.equals("monday") || z == 4 && a.equals(lec.getText()) && h.equals("monday") || z == 0 && i1.equals(location.getText()) && h.equals("monday")) {
                        nonArray[4] = nonSlot;
                    }
                    if (z == 5 && d.equals(group.getText()) && h.equals("monday") || z == 5 && a.equals(lec.getText()) && h.equals("monday") || z == 0 && i1.equals(location.getText()) && h.equals("monday")) {
                        nonArray[5] = nonSlot;
                    }
                    if (z == 6 && d.equals(group.getText()) && h.equals("monday") || z == 6 && a.equals(lec.getText()) && h.equals("monday") || z == 0 && i1.equals(location.getText()) && h.equals("monday")) {
                        nonArray[6] = nonSlot;
                    }
                    if (z == 7 && d.equals(group.getText()) && h.equals("monday") || z == 7 && a.equals(lec.getText()) && h.equals("monday") || z == 0 && i1.equals(location.getText()) && h.equals("monday")) {
                        nonArray[7] = nonSlot;
                    }
                    if (z == 8 && d.equals(group.getText()) && h.equals("monday") || z == 8 && a.equals(lec.getText()) && h.equals("monday") || z == 0 && i1.equals(location.getText()) && h.equals("monday")) {
                        nonArray[8] = nonSlot;
                    }
                    if (z == 8 && d.equals(group.getText()) && h.equals("monday") || z == 9 && a.equals(lec.getText()) && h.equals("monday") || z == 0 && i1.equals(location.getText()) && h.equals("monday")) {
                        nonArray[8] = nonSlot;
                    }

//tuesday

                    if (z == 0 && d.equals(group.getText()) && h.equals("tuesday") || z == 0 && a.equals(lec.getText()) && h.equals("tuesday") || z == 0 && i1.equals(location.getText()) && h.equals("tuesday")) {
                        nonArray2[0] = nonSlot;
                    }
                    if (z == 1 && d.equals(group.getText()) && h.equals("tuesday") || z == 1 && a.equals(lec.getText()) && h.equals("tuesday") || z == 0 && i1.equals(location.getText()) && h.equals("tuesday")) {
                        nonArray2[1] = nonSlot;
                    }
                    if (z == 2 && d.equals(group.getText()) && h.equals("tuesday") || z == 2 && a.equals(lec.getText()) && h.equals("tuesday") || z == 0 && i1.equals(location.getText()) && h.equals("tuesday")) {
                        nonArray2[2] = nonSlot;
                    }
                    if (z == 3 && d.equals(group.getText()) && h.equals("tuesday") || z == 3 && a.equals(lec.getText()) && h.equals("tuesday") || z == 0 && i1.equals(location.getText()) && h.equals("tuesday")) {
                        nonArray2[3] = nonSlot;
                    }
                    if (z == 4 && d.equals(group.getText()) && h.equals("tuesday") || z == 4 && a.equals(lec.getText()) && h.equals("tuesday") || z == 0 && i1.equals(location.getText()) && h.equals("tuesday")) {
                        nonArray2[4] = nonSlot;
                    }
                    if (z == 5 && d.equals(group.getText()) && h.equals("tuesday") || z == 5 && a.equals(lec.getText()) && h.equals("tuesday") || z == 0 && i1.equals(location.getText()) && h.equals("tuesday")) {
                        nonArray2[5] = nonSlot;
                    }
                    if (z == 6 && d.equals(group.getText()) && h.equals("tuesday") || z == 6 && a.equals(lec.getText()) && h.equals("tuesday") || z == 0 && i1.equals(location.getText()) && h.equals("tuesday")) {
                        nonArray2[6] = nonSlot;
                    }
                    if (z == 7 && d.equals(group.getText()) && h.equals("tuesday") || z == 7 && a.equals(lec.getText()) && h.equals("tuesday") || z == 0 && i1.equals(location.getText()) && h.equals("tuesday")) {
                        nonArray2[7] = nonSlot;
                    }
                    if (z == 8 && d.equals(group.getText()) && h.equals("tuesday") || z == 8 && a.equals(lec.getText()) && h.equals("tuesday") || z == 0 && i1.equals(location.getText()) && h.equals("tuesday")) {
                        nonArray2[8] = nonSlot;
                    }
                    if (z == 8 && d.equals(group.getText()) && h.equals("tuesday") || z == 9 && a.equals(lec.getText()) && h.equals("tuesday") || z == 0 && i1.equals(location.getText()) && h.equals("tuesday")) {
                        nonArray2[9] = nonSlot;
                    }
                    //wednesday

                    if (z == 0 && d.equals(group.getText()) && h.equals("wednesday") || z == 0 && a.equals(lec.getText()) && h.equals("wednesday") || z == 0 && i1.equals(location.getText()) && h.equals("wednesday")) {
                        nonArray3[0] = nonSlot;
                    }
                    if (z == 1 && d.equals(group.getText()) && h.equals("wednesday") || z == 1 && a.equals(lec.getText()) && h.equals("wednesday") || z == 0 && i1.equals(location.getText()) && h.equals("wednesday")) {
                        nonArray3[1] = nonSlot;
                    }
                    if (z == 2 && d.equals(group.getText()) && h.equals("wednesday") || z == 2 && a.equals(lec.getText()) && h.equals("wednesday") || z == 0 && i1.equals(location.getText()) && h.equals("wednesday")) {
                        nonArray3[2] = nonSlot;
                    }
                    if (z == 3 && d.equals(group.getText()) && h.equals("wednesday") || z == 3 && a.equals(lec.getText()) && h.equals("wednesday") || z == 0 && i1.equals(location.getText()) && h.equals("wednesday")) {
                        nonArray3[3] = nonSlot;
                    }
                    if (z == 4 && d.equals(group.getText()) && h.equals("wednesday") || z == 4 && a.equals(lec.getText()) && h.equals("wednesday") || z == 0 && i1.equals(location.getText()) && h.equals("wednesday")) {
                        nonArray3[4] = nonSlot;
                    }
                    if (z == 5 && d.equals(group.getText()) && h.equals("wednesday") || z == 5 && a.equals(lec.getText()) && h.equals("wednesday") || z == 0 && i1.equals(location.getText()) && h.equals("wednesday")) {
                        nonArray3[5] = nonSlot;
                    }
                    if (z == 6 && d.equals(group.getText()) && h.equals("wednesday") || z == 6 && a.equals(lec.getText()) && h.equals("wednesday") || z == 0 && i1.equals(location.getText()) && h.equals("wednesday")) {
                        nonArray3[6] = nonSlot;
                    }
                    if (z == 7 && d.equals(group.getText()) && h.equals("wednesday") || z == 7 && a.equals(lec.getText()) && h.equals("wednesday") || z == 0 && i1.equals(location.getText()) && h.equals("wednesday")) {
                        nonArray3[7] = nonSlot;
                    }
                    if (z == 8 && d.equals(group.getText()) && h.equals("wednesday") || z == 8 && a.equals(lec.getText()) && h.equals("wednesday") || z == 0 && i1.equals(location.getText()) && h.equals("wednesday")) {
                        nonArray3[8] = nonSlot;
                    }
                    if (z == 8 && d.equals(group.getText()) && h.equals("wednesday") || z == 9 && a.equals(lec.getText()) && h.equals("wednesday") || z == 0 && i1.equals(location.getText()) && h.equals("wednesday")) {
                        nonArray3[9] = nonSlot;
                    }
                    //thursday

                    if (z == 0 && d.equals(group.getText()) && h.equals("thursday") || z == 0 && a.equals(lec.getText()) && h.equals("thursday") || z == 0 && i1.equals(location.getText()) && h.equals("thursday")) {
                        nonArray4[0] = nonSlot;
                    }
                    if (z == 1 && d.equals(group.getText()) && h.equals("thursday") || z == 1 && a.equals(lec.getText()) && h.equals("thursday") || z == 0 && i1.equals(location.getText()) && h.equals("thursday")) {
                        nonArray4[1] = nonSlot;
                    }
                    if (z == 2 && d.equals(group.getText()) && h.equals("thursday") || z == 2 && a.equals(lec.getText()) && h.equals("thursday") || z == 0 && i1.equals(location.getText()) && h.equals("thursday")) {
                        nonArray4[2] = nonSlot;
                    }
                    if (z == 3 && d.equals(group.getText()) && h.equals("thursday") || z == 3 && a.equals(lec.getText()) && h.equals("thursday") || z == 0 && i1.equals(location.getText()) && h.equals("thursday")) {
                        nonArray4[3] = nonSlot;
                    }
                    if (z == 4 && d.equals(group.getText()) && h.equals("thursday") || z == 4 && a.equals(lec.getText()) && h.equals("thursday") || z == 0 && i1.equals(location.getText()) && h.equals("thursday")) {
                        nonArray4[4] = nonSlot;
                    }
                    if (z == 5 && d.equals(group.getText()) && h.equals("thursday") || z == 5 && a.equals(lec.getText()) && h.equals("thursday") || z == 0 && i1.equals(location.getText()) && h.equals("thursday")) {
                        nonArray4[5] = nonSlot;
                    }
                    if (z == 6 && d.equals(group.getText()) && h.equals("thursday") || z == 6 && a.equals(lec.getText()) && h.equals("thursday") || z == 0 && i1.equals(location.getText()) && h.equals("thursday")) {
                        nonArray4[6] = nonSlot;
                    }
                    if (z == 7 && d.equals(group.getText()) && h.equals("thursday") || z == 7 && a.equals(lec.getText()) && h.equals("thursday") || z == 0 && i1.equals(location.getText()) && h.equals("thursday")) {
                        nonArray4[7] = nonSlot;
                    }
                    if (z == 8 && d.equals(group.getText()) && h.equals("thursday") || z == 8 && a.equals(lec.getText()) && h.equals("thursday") || z == 0 && i1.equals(location.getText()) && h.equals("thursday")) {
                        nonArray4[8] = nonSlot;
                    }
                    if (z == 8 && d.equals(group.getText()) && h.equals("thursday") || z == 9 && a.equals(lec.getText()) && h.equals("thursday") || z == 0 && i1.equals(location.getText()) && h.equals("thursday")) {
                        nonArray4[9] = nonSlot;
                    }
                    //friday

                    if (z == 0 && d.equals(group.getText()) && h.equals("friday") || z == 0 && a.equals(lec.getText()) && h.equals("friday") || z == 0 && i1.equals(location.getText()) && h.equals("friday")) {
                        nonArray5[0] = nonSlot;
                    }
                    if (z == 1 && d.equals(group.getText()) && h.equals("friday") || z == 1 && a.equals(lec.getText()) && h.equals("friday") || z == 0 && i1.equals(location.getText()) && h.equals("friday")) {
                        nonArray5[1] = nonSlot;
                    }
                    if (z == 2 && d.equals(group.getText()) && h.equals("friday") || z == 2 && a.equals(lec.getText()) && h.equals("friday") || z == 0 && i1.equals(location.getText()) && h.equals("friday")) {
                        nonArray5[2] = nonSlot;
                    }
                    if (z == 3 && d.equals(group.getText()) && h.equals("friday") || z == 3 && a.equals(lec.getText()) && h.equals("friday") || z == 0 && i1.equals(location.getText()) && h.equals("friday")) {
                        nonArray5[3] = nonSlot;
                    }
                    if (z == 4 && d.equals(group.getText()) && h.equals("friday") || z == 4 && a.equals(lec.getText()) && h.equals("friday") || z == 0 && i1.equals(location.getText()) && h.equals("friday")) {
                        nonArray5[4] = nonSlot;
                    }
                    if (z == 5 && d.equals(group.getText()) && h.equals("friday") || z == 5 && a.equals(lec.getText()) && h.equals("friday") || z == 0 && i1.equals(location.getText()) && h.equals("friday")) {
                        nonArray5[5] = nonSlot;
                    }
                    if (z == 6 && d.equals(group.getText()) && h.equals("friday") || z == 6 && a.equals(lec.getText()) && h.equals("friday") || z == 0 && i1.equals(location.getText()) && h.equals("friday")) {
                        nonArray5[6] = nonSlot;
                    }
                    if (z == 7 && d.equals(group.getText()) && h.equals("friday") || z == 7 && a.equals(lec.getText()) && h.equals("friday") || z == 0 && i1.equals(location.getText()) && h.equals("friday")) {
                        nonArray5[7] = nonSlot;
                    }
                    if (z == 8 && d.equals(group.getText()) && h.equals("friday") || z == 8 && a.equals(lec.getText()) && h.equals("friday") || z == 0 && i1.equals(location.getText()) && h.equals("friday")) {
                        nonArray5[8] = nonSlot;
                    }
                    if (z == 8 && d.equals(group.getText()) && h.equals("friday") || z == 9 && a.equals(lec.getText()) && h.equals("friday") || z == 0 && i1.equals(location.getText()) && h.equals("friday")) {
                        nonArray5[9] = nonSlot;
                    }
                    //saturday
                    if (z == 0 && d.equals(group.getText()) && h.equals("saturday") || z == 0 && a.equals(lec.getText()) && h.equals("saturday") || z == 0 && i1.equals(location.getText()) && h.equals("saturday")) {
                        nonArray6[0] = nonSlot;
                    }
                    if (z == 1 && d.equals(group.getText()) && h.equals("saturday") || z == 1 && a.equals(lec.getText()) && h.equals("saturday") || z == 0 && i1.equals(location.getText()) && h.equals("saturday")) {
                        nonArray6[1] = nonSlot;
                    }
                    if (z == 2 && d.equals(group.getText()) && h.equals("saturday") || z == 2 && a.equals(lec.getText()) && h.equals("saturday") || z == 0 && i1.equals(location.getText()) && h.equals("saturday")) {
                        nonArray6[2] = nonSlot;
                    }
                    if (z == 3 && d.equals(group.getText()) && h.equals("saturday") || z == 3 && a.equals(lec.getText()) && h.equals("saturday") || z == 0 && i1.equals(location.getText()) && h.equals("saturday")) {
                        nonArray6[3] = nonSlot;
                    }
                    if (z == 4 && d.equals(group.getText()) && h.equals("saturday") || z == 4 && a.equals(lec.getText()) && h.equals("saturday") || z == 0 && i1.equals(location.getText()) && h.equals("saturday")) {
                        nonArray6[4] = nonSlot;
                    }
                    if (z == 5 && d.equals(group.getText()) && h.equals("saturday") || z == 5 && a.equals(lec.getText()) && h.equals("saturday") || z == 0 && i1.equals(location.getText()) && h.equals("saturday")) {
                        nonArray6[5] = nonSlot;
                    }
                    if (z == 6 && d.equals(group.getText()) && h.equals("saturday") || z == 6 && a.equals(lec.getText()) && h.equals("saturday") || z == 0 && i1.equals(location.getText()) && h.equals("saturday")) {
                        nonArray6[6] = nonSlot;
                    }
                    if (z == 7 && d.equals(group.getText()) && h.equals("saturday") || z == 7 && a.equals(lec.getText()) && h.equals("saturday") || z == 0 && i1.equals(location.getText()) && h.equals("saturday")) {
                        nonArray6[7] = nonSlot;
                    }
                    if (z == 8 && d.equals(group.getText()) && h.equals("saturday") || z == 8 && a.equals(lec.getText()) && h.equals("saturday") || z == 0 && i1.equals(location.getText()) && h.equals("saturday")) {
                        nonArray6[8] = nonSlot;
                    }
                    if (z == 8 && d.equals(group.getText()) && h.equals("saturday") || z == 9 && a.equals(lec.getText()) && h.equals("saturday") || z == 0 && i1.equals(location.getText()) && h.equals("saturday")) {
                        nonArray6[9] = nonSlot;
                    }
                    //sunday
                    if (z == 0 && d.equals(group.getText()) && h.equals("sunday") || z == 0 && a.equals(lec.getText()) && h.equals("sunday") || z == 0 && i1.equals(location.getText()) && h.equals("sunday")) {
                        nonArray7[0] = nonSlot;
                    }
                    if (z == 1 && d.equals(group.getText()) && h.equals("sunday") || z == 1 && a.equals(lec.getText()) && h.equals("sunday") || z == 0 && i1.equals(location.getText()) && h.equals("sunday")) {
                        nonArray7[1] = nonSlot;
                    }
                    if (z == 2 && d.equals(group.getText()) && h.equals("sunday") || z == 2 && a.equals(lec.getText()) && h.equals("sunday") || z == 0 && i1.equals(location.getText()) && h.equals("sunday")) {
                        nonArray7[2] = nonSlot;
                    }
                    if (z == 3 && d.equals(group.getText()) && h.equals("sunday") || z == 3 && a.equals(lec.getText()) && h.equals("sunday") || z == 0 && i1.equals(location.getText()) && h.equals("sunday")) {
                        nonArray7[3] = nonSlot;
                    }
                    if (z == 4 && d.equals(group.getText()) && h.equals("sunday") || z == 4 && a.equals(lec.getText()) && h.equals("sunday") || z == 0 && i1.equals(location.getText()) && h.equals("sunday")) {
                        nonArray7[4] = nonSlot;
                    }
                    if (z == 5 && d.equals(group.getText()) && h.equals("sunday") || z == 5 && a.equals(lec.getText()) && h.equals("sunday") || z == 0 && i1.equals(location.getText()) && h.equals("sunday")) {
                        nonArray7[5] = nonSlot;
                    }
                    if (z == 6 && d.equals(group.getText()) && h.equals("sunday") || z == 6 && a.equals(lec.getText()) && h.equals("sunday") || z == 0 && i1.equals(location.getText()) && h.equals("sunday")) {
                        nonArray7[6] = nonSlot;
                    }
                    if (z == 7 && d.equals(group.getText()) && h.equals("sunday") || z == 7 && a.equals(lec.getText()) && h.equals("sunday") || z == 0 && i1.equals(location.getText()) && h.equals("sunday")) {
                        nonArray7[7] = nonSlot;
                    }
                    if (z == 8 && d.equals(group.getText()) && h.equals("sunday") || z == 8 && a.equals(lec.getText()) && h.equals("sunday") || z == 0 && i1.equals(location.getText()) && h.equals("sunday")) {
                        nonArray7[8] = nonSlot;
                    }
                    if (z == 8 && d.equals(group.getText()) && h.equals("sunday") || z == 9 && a.equals(lec.getText()) && h.equals("sunday") || z == 0 && i1.equals(location.getText()) && h.equals("sunday")) {
                        nonArray7[9] = nonSlot;
                    }
                    assert h != null;
                    if (h.equals("monday")) {

                        m1.setText(nonArray1[0]);
                        m2.setText(nonArray1[1]);
                        m3.setText(nonArray1[2]);
                        m4.setText(nonArray1[3]);
                    }
                    if (h.equals("tuesday")) {
                        tu1.setText(nonArray2[0]);
                        tu2.setText(nonArray2[1]);
                        tu3.setText(nonArray2[2]);
                        tu4.setText(nonArray2[3]);
                    }
                    if (h.equals("wednesday")) {
                        w1.setText(nonArray3[0]);
                        w2.setText(nonArray3[1]);
                        w3.setText(nonArray3[2]);
                        w4.setText(nonArray3[3]);
                    }
                    if (h.equals("thursday")) {
                        th1.setText(nonArray4[0]);
                        th2.setText(nonArray4[1]);
                        th3.setText(nonArray4[2]);
                        th4.setText(nonArray4[3]);
                    }
                    if (h.equals("friday")) {
                        fr1.setText(nonArray5[0]);
                        fr2.setText(nonArray5[1]);
                        fr3.setText(nonArray5[2]);
                        fr4.setText(nonArray5[3]);
                    }
                    if (h.equals("saturday")) {
                        m1.setText(nonArray6[0]);
                        m2.setText(nonArray6[1]);
                        m3.setText(nonArray6[2]);
                        m4.setText(nonArray6[3]);
                    }
                    if (h.equals("sunday")) {
                        m1.setText(nonArray7[0]);
                        m2.setText(nonArray7[1]);
                        m3.setText(nonArray7[2]);
                        m4.setText(nonArray7[3]);
                    }
                    break;
                }catch (Exception e){
                    System.out.println(e);
                }
            }
        }
        m7.setText(nonArray[0]);
        m8.setText(nonArray[1]);
        m9.setText(nonArray[2]);
        tu1.setText(nonArray[3]);

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
