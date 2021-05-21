/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author Damika
 */
public class statisticsController implements Initializable {


    @FXML
    private TableColumn<analysisdata, String> tvpract;

    @FXML
    private TableColumn<analysisdata, String> tvlostpract;

    @FXML
    private TableColumn<analysisdata, String> tvklec;

    @FXML
    private TableView<analysisdata> table_stat;

    @FXML
    private TableColumn<analysisdata, String> tvlostlec;

    @FXML
    private TableColumn<analysisdata, String> tvgid;

    @FXML
    private TableColumn<analysisdata, String> tvsubj;

    @FXML
    private TableColumn<analysisdata, String> tvmf;

    @FXML
    private TableColumn<analysisdata, String> tvmt;



    @FXML
    private TextField txtpract;

    @FXML
    private TextField txtlostpract;

    @FXML
    private TextField asmf;

    @FXML
    private TextField asmt;

    @FXML
    private TextField txtgrpid;

    @FXML
    private TextField txtlec;

    @FXML
    private TextField txtsubj;

    @FXML
    private TextField txtlostlect;

    @FXML
    private TextField filterfield;



    ObservableList<analysisdata> listM;
    ObservableList<analysisdata> dataList;

    int index = -1;

    Connection conn =null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    @FXML
    public void save_stat (){
        conn =getConnection();
        String sql = "insert into analysisdata (GroupID,MonthFrom,MonthTo,Subject,Nooflectures,Nooflostlectures,Noofpracticals,Nooflostpracticals)values(?,?,?,?,?,?,?,? )";

        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, txtgrpid.getText());
            pst.setString(2, asmf.getText());
            pst.setString(3, asmt.getText());
            pst.setString(4, txtsubj.getText());
            pst.setString(5, txtlec.getText());
            pst.setString(6, txtlostlect.getText());
            pst.setString(7, txtpract.getText());
            pst.setString(8, txtlostpract.getText());
            pst.execute();

            JOptionPane.showMessageDialog(null, "Analysis Data Add succes");
            UpdateTable();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e);

        }
    }


    @FXML
    void getSelected (MouseEvent event){
        index = table_stat.getSelectionModel().getSelectedIndex();
        if (index <= -1){

            return;
    }
        txtgrpid.setText(tvgid.getCellData(index).toString());
        asmf.setText(tvmf.getCellData(index).toString());
        asmt.setText(tvmt.getCellData(index).toString());
        txtsubj.setText(tvsubj.getCellData(index).toString());
        txtlec.setText(tvklec.getCellData(index).toString());
        txtlostlect.setText(tvlostlec.getCellData(index).toString());
        txtpract.setText(tvpract.getCellData(index).toString());
        txtlostpract.setText(tvlostpract.getCellData(index).toString());

    }

    @FXML
    public void Edit (){
        try {
            conn = getConnection();
            String value1 = txtgrpid.getText();
            String value2 = asmf.getText();
            String value3 = asmt.getText();
            String value4 = txtsubj.getText();
            String value5 = txtlec.getText();
            String value6 = txtlostlect.getText();
            String value7 = txtpract.getText();
            String value8 = txtlostpract.getText();
            String sql = "update analysisdata set GroupID= '"+value1+"',MonthFrom= '"+value2+"',MonthTo= '"+value3+"',Subject= '"+value4+"',Nooflectures= '"+value5+"',Nooflostlectures= '"+value6+"',Noofpracticals= '"+value7+"',Nooflostpracticals= '"+value8+"' where GroupID='"+value1+"' ";
            pst= conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Update");
            UpdateTable();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }
    @FXML
    public void Delete(){

        conn = getConnection();
        String sql = "delete from analysisdata where GroupID = ?";
            try {
                pst = conn.prepareStatement(sql);
                pst.setString(1, txtgrpid.getText());
                pst.execute();
                JOptionPane.showMessageDialog(null, "Delete");
                UpdateTable();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
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
    public ObservableList<analysisdata> getstat(){
        ObservableList<analysisdata> analysisdataList = FXCollections.observableArrayList();
        Connection connection = getConnection();
        String query = "SELECT * FROM analysisdata ";
        Statement st;
        ResultSet rs;
        System.out.println(query);

        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);
            analysisdata analysisdata;
            while(rs.next()) {
                analysisdata = new analysisdata( rs.getString("GroupID"), rs.getString("MonthFrom"), rs.getString("MonthTo"), rs.getString("Subject"),rs.getString("Nooflectures"),rs.getString("Nooflostlectures"),rs.getString("Noofpracticals"),rs.getString("Nooflostpracticals"));
                analysisdataList.add(analysisdata);

            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return analysisdataList;
    }

    public void UpdateTable(){
        ObservableList<analysisdata> listM = getstat();
        tvgid.setCellValueFactory(new PropertyValueFactory<analysisdata,String>("GroupID"));
        tvmf.setCellValueFactory(new PropertyValueFactory<analysisdata,String>("MonthFrom"));
        tvmt.setCellValueFactory(new PropertyValueFactory<analysisdata,String>("MonthTo"));
        tvsubj.setCellValueFactory(new PropertyValueFactory<analysisdata,String>("Subject"));
        tvklec.setCellValueFactory(new PropertyValueFactory<analysisdata,String>("Nooflectures"));
        tvlostlec.setCellValueFactory(new PropertyValueFactory<analysisdata,String>("Nooflostlectures"));
        tvpract.setCellValueFactory(new PropertyValueFactory<analysisdata,String>("Noofpracticals"));
        tvlostpract.setCellValueFactory(new PropertyValueFactory<analysisdata,String>("Nooflostpracticals"));
        table_stat.setItems(listM);


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
