

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
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
import javax.swing.JOptionPane;
import javafx.collections.FXCollections;
import javafx.scene.input.MouseEvent;


public class SubjectController implements Initializable {


    @FXML
    private TableColumn<subject, String> off_col;

    @FXML
    private TableColumn<subject, String> sem_col;

    @FXML
    private TableColumn<subject, String> sub_col;

    @FXML
    private TableView<subject> table_stat;

    @FXML
    private TableColumn<subject, String> code_col;

    @FXML
    private TableColumn<subject, String> lec_col;

    @FXML
    private TableColumn<subject, String> tute_col;

    @FXML
    private TableColumn<subject, String> lab_col;

    @FXML
    private TableColumn<subject, String> evo_col;



    @FXML
    private TextField off_year;

    @FXML
    private TextField off_sem;

    @FXML
    private TextField sub_name;

    @FXML
    private TextField sub_code;

    @FXML
    private TextField no_lec;

    @FXML
    private TextField no_tute;

    @FXML
    private TextField no_lab;
    @FXML
    private TextField no_evo;


    ObservableList<lecturer> listM;
    ObservableList<lecturer> dataList;

    int index = -1;

    Connection conn =null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    @FXML
    public void save(){
        conn =getConnection();
        String sql = "insert into subject (OfferedYr,OfferedSem,SubName,SubCode,LecHrs,TuteHrs,LabHrs,`EvalutionHrs`)values(?,?,?,?,?,?,?,?)";

        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, off_year.getText());
            pst.setString(2, off_sem.getText());
            pst.setString(3, sub_name.getText());
            pst.setString(4, sub_code.getText());
            pst.setString(5, no_lec.getText());
            pst.setString(6, no_tute.getText());
            pst.setString(7, no_lab.getText());
            pst.setString(8, no_evo.getText());
            pst.execute();

            JOptionPane.showMessageDialog(null, "subject Data Add success");
            UpdateTable();

        } catch (Exception e) {

            System.out.println("error insert" + e);

        }
    }


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
    public ObservableList<subject> getSubject(){
        ObservableList<subject> subjectList = FXCollections.observableArrayList();
        Connection connection = getConnection();
        String query = "SELECT * FROM subject ";
        Statement st;
        ResultSet rs;
        System.out.println(query);

        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);
            subject subject;
            while(rs.next()) {
                subject = new subject( rs.getString("OfferedYr"), rs.getString("OfferedSem"), rs.getString("SubName"), rs.getString("SubCode"),rs.getString("LecHrs"),rs.getString("TuteHrs"),rs.getString("LabHrs"),rs.getString("EvalutionHrs"));
               subjectList.add(subject);

            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return subjectList;
    }

    public void UpdateTable(){
        ObservableList<subject> listM = getSubject();
        off_col.setCellValueFactory(new PropertyValueFactory<subject,String>("OfferedYr"));
        sem_col.setCellValueFactory(new PropertyValueFactory<subject,String>("OfferedSem"));
        sub_col.setCellValueFactory(new PropertyValueFactory<subject,String>("SubName"));
        code_col.setCellValueFactory(new PropertyValueFactory<subject,String>("SubCode"));
        lec_col.setCellValueFactory(new PropertyValueFactory<subject,String>("LecHrs"));
        tute_col.setCellValueFactory(new PropertyValueFactory<subject,String>("TuteHrs"));
        lab_col.setCellValueFactory(new PropertyValueFactory<subject,String>("LabHrs"));
        evo_col.setCellValueFactory(new PropertyValueFactory<subject,String>("EvalutionHrs"));
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
