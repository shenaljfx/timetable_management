/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import javax.swing.JOptionPane;
import javafx.collections.FXCollections;
import javafx.scene.input.MouseEvent;


public class lecturerControll implements Initializable {


    @FXML
    private TableColumn<lecturer, String> lec_col;

    @FXML
    private TableColumn<lecturer, String> ID_col;

    @FXML
    private TableColumn<lecturer, String> fac_col;

    @FXML
    private TableView<lecturer> table_stat;

    @FXML
    private TableColumn<lecturer, String> dep_col;

    @FXML
    private TableColumn<lecturer, String> cen_col;

    @FXML
    private TableColumn<lecturer, String> bul_col;

    @FXML
    private TableColumn<lecturer, String> lev_col;

    @FXML
    private TableColumn<lecturer, String> rank_col;


    @FXML
    private TextField Lname;

    @FXML
    private TextField EID;

    @FXML
    private TextField fac;

    @FXML
    private TextField dep;

    @FXML
    private TextField center;

    @FXML
    private TextField building;

    @FXML
    private TextField level;

    @FXML
    private TextField rank;


    ObservableList<lecturer> listM;
    ObservableList<lecturer> dataList;

    int index = -1;

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    @FXML
    public void save() {
        conn = getConnection();
        String sql = "insert into lectrer (Name,EMPID,Faculty,Department,Center,buliding,Level,`Rank`)values(?,?,?,?,?,?,?,?)";

        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, Lname.getText());
            pst.setString(2, EID.getText());
            pst.setString(3, fac.getText());
            pst.setString(4, dep.getText());
            pst.setString(5, center.getText());
            pst.setString(6, building.getText());
            pst.setString(7, level.getText());
            pst.setString(8, rank.getText());
            pst.execute();

            JOptionPane.showMessageDialog(null, "Analysis Data Add success");
            UpdateTable();

        } catch (Exception e) {

            System.out.println("error insert" + e);

        }
    }


    @FXML
    void getSelected(MouseEvent event) {
        index = table_stat.getSelectionModel().getSelectedIndex();
        if (index <= -1) {

            return;
        }
        Lname.setText(lec_col.getCellData(index).toString());
        EID.setText(ID_col.getCellData(index).toString());
        fac.setText(fac_col.getCellData(index).toString());
        dep.setText(dep_col.getCellData(index).toString());
        center.setText(cen_col.getCellData(index).toString());
        building.setText(bul_col.getCellData(index).toString());
        level.setText(lev_col.getCellData(index).toString());
        rank.setText(rank_col.getCellData(index).toString());

    }

    @FXML
    public void Edit() {
        try {
            conn = getConnection();
            String value1 = Lname.getText();
            String value2 = EID.getText();
            String value3 = fac.getText();
            String value4 = dep.getText();
            String value5 = center.getText();
            String value6 = building.getText();
            String value7 = level.getText();
            String value8 = rank.getText();
            String sql = "update lectrer set Name= '" + value1 + "',Faculty= '" + value3 + "',Department= '" + value4 + "',Center= '" + value5 + "',buliding= '" + value6 + "',Level= '" + value7 + "',lectrer.`Rank`= '" + value8 + "' where EMPID='" + value2 + "' ";
            pst = conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Update");
            UpdateTable();

        } catch (Exception e) {
            System.out.println("error UPDATE" + e);
        }

    }

    @FXML
    public void Delete() {

        conn = getConnection();
        String sql = "delete from lectrer where EMPID = ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, EID.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Delete");
            UpdateTable();

        } catch (Exception e) {
            System.out.println("error DLT" + e);
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

    public ObservableList<lecturer> getstat() {
        ObservableList<lecturer> analysisdataList = FXCollections.observableArrayList();
        Connection connection = getConnection();
        String query = "SELECT * FROM lectrer ";
        Statement st;
        ResultSet rs;
        System.out.println(query);

        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);
            lecturer lecturer;
            while (rs.next()) {
                lecturer = new lecturer(rs.getString("Name"), rs.getString("EMPID"), rs.getString("Faculty"), rs.getString("Department"), rs.getString("Center"), rs.getString("buliding"), rs.getString("Level"), rs.getString("Rank"));
                analysisdataList.add(lecturer);

            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return analysisdataList;
    }

    public void UpdateTable() {
        ObservableList<lecturer> listM = getstat();
        lec_col.setCellValueFactory(new PropertyValueFactory<lecturer, String>("Name"));
        ID_col.setCellValueFactory(new PropertyValueFactory<lecturer, String>("EMPID"));
        fac_col.setCellValueFactory(new PropertyValueFactory<lecturer, String>("Faculty"));
        dep_col.setCellValueFactory(new PropertyValueFactory<lecturer, String>("Department"));
        cen_col.setCellValueFactory(new PropertyValueFactory<lecturer, String>("Center"));
        bul_col.setCellValueFactory(new PropertyValueFactory<lecturer, String>("buliding"));
        lev_col.setCellValueFactory(new PropertyValueFactory<lecturer, String>("Level"));
        rank_col.setCellValueFactory(new PropertyValueFactory<lecturer, String>("Rank"));
        table_stat.setItems(listM);



    }


    @FXML
    public void changeScreen(javafx.event.ActionEvent event) {
        try {
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("mainPage.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(tableViewScene);
            window.show();
        } catch (Exception E) {
            System.out.println(E);
        }
    }

    @FXML
    private void genRank() {
        String level1 = level.getText();
        if (level1.equals("1")) {
            rank.setText("Professor");
        } else if (level1.equals("2")) {
            rank.setText("Assistant Professor");
        } else if (level1.equals("3")) {
            rank.setText("Senior Lecturer(HG)");
        } else if (level1.equals("4")) {
            rank.setText("Senior Lecturer");
        } else if (level1.equals("5")) {
            rank.setText("Lecturer");
        } else if (level1.equals("6")) {
            rank.setText("Assistant Lecturer");
        } else {
            rank.setText("wrong level enter correct level");
        }
        System.out.println(level1);

    }


}
