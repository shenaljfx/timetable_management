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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.swing.*;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class addSessionController implements Initializable  {


        @FXML
        private TableColumn<addSession, Integer> sec_id;

        @FXML
        private TableColumn<addSession, String> sec_lec;

        @FXML
        private TableColumn<addSession, String> sub_code;

        @FXML
        private TableColumn<addSession, String>  sub_name;

        @FXML
        private TableView<addSession> main_table;

        @FXML
        private TableColumn<addSession, String> groupID;
        @FXML
        private TableColumn<addSession, String> tag;
        @FXML
        private TableColumn<addSession, String> nostudent;
        @FXML
        private TableColumn<addSession, String> duration;
        @FXML
        private TableColumn<addSession, String> sec_date;
        @FXML
        public ComboBox<String> lec ;
        @FXML
        public ComboBox <String>subCode ;
        @FXML
        public ComboBox <String>subName ;
        @FXML
        public ComboBox <String>group_ID ;
        @FXML
        public TextField id;
        @FXML
        public TextField tags;
        @FXML
        public TextField s_cap;
        @FXML
        public TextField durations;
        @FXML
        public TextField date;


        ObservableList<lecturer> listM;
        ObservableList<lecturer> dataList;
        int index = -1;
        Connection conn =null;
        ResultSet rs = null;
        PreparedStatement pst = null;
        static final String DB_URL = "jdbc:sqlite:D:/SLIIT/3rd year 1st sem/ITPM/IT19033938/src/student";
        static final String QUERY1 = "SELECT Name FROM lectrer";
        static final String QUERY2 = "SELECT SubCode FROM subject";
        static final String QUERY3 = "SELECT SubName FROM subject";
        static final String QUERY4 = "SELECT SGID FROM studentgroup";

    @FXML
    public void getLecToList()throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(QUERY1);
        ObservableList<String>  data = FXCollections.observableArrayList();

        while (rs.next()) {
            try {
                data.add(rs.getString("Name"));

            }catch (Exception e){
                System.out.println(e);
            }


        }lec.setItems(data);


    }
    @FXML
    public void getSubjectToList()throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(QUERY2);
        ObservableList<String>  data = FXCollections.observableArrayList();

        while (rs.next()) {
            try {
                data.add(rs.getString("SubCode"));

            }catch (Exception e){
                System.out.println(e);
            }


        }subCode.setItems(data);


    }
    @FXML
    public void getSubjectNameToList()throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(QUERY3);
        ObservableList<String>  data = FXCollections.observableArrayList();

        while (rs.next()) {
            try {
                data.add(rs.getString("SubName"));

            }catch (Exception e){
                System.out.println(e);
            }


        }subName.setItems(data);


    }
    @FXML
    public void getGroupIDToList()throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(QUERY4);
        ObservableList<String>  data = FXCollections.observableArrayList();

        while (rs.next()) {
            try {
                data.add(rs.getString("SGID"));

            }catch (Exception e){
                System.out.println(e);
            }


        }group_ID.setItems(data);

    }
    @FXML
    public void Add_Session (){
        conn = getConnection();
        String sql = "insert into seesion_add (id,lec,sub_code,sub_name,group_id,tag,noOfStudents,duration,date)values(?,?,?,?,?,?,?,?,? )";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, id.getText());
            pst.setString(2, lec.getValue());
            pst.setString(3, subCode.getValue());
            pst.setString(4, subName.getValue());
            pst.setString(5, group_ID.getValue());
            pst.setString(6, tags.getText());
            pst.setString(7, s_cap.getText());
            pst.setString(8, durations.getText());
            pst.setString(9, date.getText());
            pst.execute();


            JOptionPane.showMessageDialog(null, "Session Added successfully");
            UpdateTable();

        } catch (Exception e) {
            System.out.println("Error:"+e);
        }
    }
    @FXML

    public void Edit () throws SQLException {
        try {
            conn = getConnection();
            String query = "UPDATE seesion_add SET lec='" + lec.getValue() + "',sub_code='" + subCode.getValue() + "',sub_name=" + subName.getValue() + ",group_id= '" + group_ID.getValue() + ",tag= '" + tags.getText() + ",noOfStudents= '" + s_cap.getText() + ",duration= '" + durations.getText() + ",date= '" + date.getText() + "' WHERE id=" + id.getText() + "";
            pst.executeQuery();
            System.out.println(query);
            UpdateTable();
        }catch (Exception e){
            System.out.println(e);
        }
        try {
            conn = getConnection();
            String value1 = id.getText();
            String value2 = lec.getValue();
            String value3 = subCode.getValue();
            String value4 = subName.getValue();
            String value5 = group_ID.getValue();
            String value6 = tags.getText();
            String value7 = s_cap.getText();
            String value8 = durations.getText();
            String value9 = date.getText();
            String sql = "update seesion_add set lec= '"+value2+"',sub_code= '"+value3+"',sub_name= '"+value4+"',group_id= '"+value5+"',tag= '"+value6+"',noOfStudents= '"+value7+"',duration= '"+value8+"',date= '"+value9+"' where id='"+value1+"' ";
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
        String sql = "delete from seesion_add where id = ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, id.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Delete");
            UpdateTable();
            //search_loc();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }
    public void mouseClicked(MouseEvent mouseEvent) {

        addSession addSession = (addSession) main_table.getSelectionModel().getSelectedItem();

        // extracting data from selected row to be displayed into text fields
        id.setText(String.valueOf(addSession.getId()));
        lec.setValue(String.valueOf(addSession.getLec()));
        subCode.setValue(addSession.getSub_code());
        subName.setValue(addSession.getSub_name());
        group_ID.setValue(addSession.getGroup_id());
        tags.setText(addSession.getTag());
        s_cap.setText(addSession.getNoOfStudents());
        durations.setText(addSession.getDuration());
        date.setText(addSession.getDate());

    }
        @Override
        public void initialize(URL location, ResourceBundle resources) {

            try {
                getLecToList();
                UpdateTable();
                getSubjectNameToList();
                getSubjectToList();
                getGroupIDToList();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
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
        public ObservableList<addSession> getSessionTable(){
            ObservableList<addSession> addSessionList = FXCollections.observableArrayList();
            Connection connection = getConnection();
            String query = "SELECT * FROM seesion_add";
            Statement st;
            ResultSet rs;


            try {
                st = connection.createStatement();
                rs = st.executeQuery(query);
                addSession addSession;
                while(rs.next()) {
                    addSession = new addSession( rs.getInt("id"), rs.getString("lec"), rs.getString("sub_code"), rs.getString("sub_name"),rs.getString("group_id"),rs.getString("tag"),rs.getString("noOfStudents"),rs.getString("duration"),rs.getString("date"));
                    addSessionList.add(addSession);

                }
            } catch (Exception e) {
                System.out.println(e);
            }
            return addSessionList;
        }

        public void UpdateTable() {
            try{
                ObservableList<addSession> listM = getSessionTable();
                sec_id.setCellValueFactory(new PropertyValueFactory<>("id"));
                sec_lec.setCellValueFactory(new PropertyValueFactory<>("lec"));
                sub_code.setCellValueFactory(new PropertyValueFactory<>("sub_code"));
                sub_name.setCellValueFactory(new PropertyValueFactory<>("sub_name"));
                groupID.setCellValueFactory(new PropertyValueFactory<>("group_id"));
                tag.setCellValueFactory(new PropertyValueFactory<>("tag"));
                nostudent.setCellValueFactory(new PropertyValueFactory<>("noOfStudents"));
                duration.setCellValueFactory(new PropertyValueFactory<>("duration"));
                sec_date.setCellValueFactory(new PropertyValueFactory<>("date"));
                main_table.setItems(listM);

            }catch(Exception e){
                System.out.println(e);
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
    public void refresh(ActionEvent event) {
        try{
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("addSession.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);
            Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(tableViewScene);
            window.show();
        }catch(Exception E){
            System.out.println(E);
        }
    }
}
