package sample;

import com.mysql.cj.xdevapi.Session;
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
import member2.lecturer;

import javax.swing.*;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class sessionController implements Initializable {

    @FXML
    private TableColumn<Sessions, Integer> sec_id;

    @FXML
    private TableColumn<Sessions, String> sec_lec;

    @FXML
    private TableColumn<Sessions, String> sub_code;

    @FXML
    private TableColumn<Sessions, String>  sub_name;

    @FXML
    private TableView<Sessions> main_table;

    @FXML
    private TableColumn<Sessions, String> groupID;
    @FXML
    private TableColumn<Sessions, String> tag;
    @FXML
    private TableColumn<Sessions, String> nostudent;
    @FXML
    private TableColumn<Sessions, String> duration;


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
    PreparedStatement pst2 = null;

    @FXML
    public void consecutive (){
        try {
            conn = getConnection();
            String value1 = s1_id.getText();
            String value2 = s2_id.getText();
            String sql = "INSERT INTO consecutive SELECT * FROM seesion_add WHERE id ='"+value1+"' ";
            String sql2 = "INSERT INTO consecutive SELECT * FROM seesion_add WHERE id ='"+value2+"' ";
            pst= conn.prepareStatement(sql);
            pst2= conn.prepareStatement(sql2);
            pst.execute();
            pst2.execute();
            JOptionPane.showMessageDialog(null, "DONE");
            UpdateTable();

        } catch (Exception e) {
            System.out.println("error " + e);
        }

    }
    @FXML
    public void parallel (){
        try {
            conn = getConnection();
            String value1 = s1_id.getText();
            String value2 = s2_id.getText();
            String sql = "INSERT INTO parallel SELECT * FROM seesion_add WHERE id ='"+value1+"' ";
            String sql2 = "INSERT INTO parallel SELECT * FROM seesion_add WHERE id ='"+value2+"' ";
            pst= conn.prepareStatement(sql);
            pst2= conn.prepareStatement(sql2);
            pst.execute();
            pst2.execute();
            JOptionPane.showMessageDialog(null, "DONE");
            UpdateTable();

        } catch (Exception e) {
            System.out.println("error " + e);
        }

    }
    @FXML
    public void notOverlap (){
        try {
            conn = getConnection();
            String value1 = s1_id.getText();
            String value2 = s2_id.getText();
            String sql = "INSERT INTO nonoverlapping SELECT * FROM seesion_add WHERE id ='"+value1+"' ";
            String sql2 = "INSERT INTO nonoverlapping SELECT * FROM seesion_add WHERE id ='"+value2+"' ";
            pst= conn.prepareStatement(sql);
            pst2= conn.prepareStatement(sql2);
            pst.execute();
            pst2.execute();
            JOptionPane.showMessageDialog(null, "DONE");
            UpdateTable();

        } catch (Exception e) {
            System.out.println("error " + e);
        }

    }
/*

    @FXML
    void getSelected (MouseEvent event){
        index = table_stat.getSelectionModel().getSelectedIndex();
        if (index <= -1){

            return;
        }
        off_year.setText(off_col.getCellData(index).toString());
        off_sem.setText(sem_col.getCellData(index).toString());
        sub_name.setText(sub_col.getCellData(index).toString());
        sub_code.setText(code_col.getCellData(index).toString());
        no_lec.setText(lec_col.getCellData(index).toString());
        no_tute.setText(tute_col.getCellData(index).toString());
        no_lab.setText(lab_col.getCellData(index).toString());
        no_evo.setText(evo_col.getCellData(index).toString());

    }

    @FXML
    public void Edit (){
        try {
            conn = getConnection();
            String value1 = off_year.getText();
            String value2 = off_sem.getText();
            String value3 = sub_name.getText();
            String value4 = sub_code.getText();
            String value5 = no_lec.getText();
            String value6 = no_tute.getText();
            String value7 = no_lab.getText();
            String value8 = no_evo.getText();
            String sql = "update subject set OfferedYr= '"+value1+"',OfferedSem= '"+value2+"',SubName= '"+value3+"',LecHrs= '"+value5+"',TuteHrs= '"+value6+"',LabHrs= '"+value7+"',EvalutionHrs= '"+value8+"' where SubCode='"+value4+"' ";
            pst= conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Update");
            UpdateTable();

        } catch (Exception e) {
            System.out.println("error UPDATE" + e);
        }

    }
    @FXML
    public void Delete(){

        conn = getConnection();
        String sql = "delete from subject where SubCode = ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, sub_code.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Delete");
            UpdateTable();

        } catch (Exception e) {
            System.out.println("error DLT" + e);
        }

    }
    */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UpdateTable();
    }
    public Connection getConnection() {
        Connection conn;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root", "shenaljfx");
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public ObservableList<Sessions> getSession(){
        ObservableList<Sessions> SessionList = FXCollections.observableArrayList();
        Connection connection = getConnection();
        String query = "SELECT * FROM seesion_add ";
        Statement st;
        ResultSet rs;


        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);
            Sessions Sessions;
            while(rs.next()) {
                Sessions = new Sessions( rs.getInt("id"), rs.getString("lec"), rs.getString("sub_code"), rs.getString("sub_name"),rs.getString("group_id"),rs.getString("tag"),rs.getString("noOfStudents"),rs.getString("duration"));
               SessionList.add(Sessions);

            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return SessionList;
    }

    public void UpdateTable() {
        try{
            ObservableList<Sessions> listM = getSession();
            sec_id.setCellValueFactory(new PropertyValueFactory<>("id"));
            sec_lec.setCellValueFactory(new PropertyValueFactory<>("lec"));
            sub_code.setCellValueFactory(new PropertyValueFactory<>("sub_code"));
            sub_name.setCellValueFactory(new PropertyValueFactory<>("sub_name"));
            groupID.setCellValueFactory(new PropertyValueFactory<>("group_id"));
            tag.setCellValueFactory(new PropertyValueFactory<>("tag"));
            nostudent.setCellValueFactory(new PropertyValueFactory<>("noOfStudents"));
            duration.setCellValueFactory(new PropertyValueFactory<>("duration"));
            main_table.setItems(listM);

        }catch(Exception e){
            System.out.println(e);
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

