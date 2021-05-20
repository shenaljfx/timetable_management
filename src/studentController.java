
// javafx libraries
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

// java libraries
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class studentController  implements Initializable {
    // declaring javafx components as defined in .fxml\
    @FXML
    private Label SUBID;
    @FXML
    private TextField sid_text;
    @FXML
    private TextField syear_text;
    @FXML
    private TextField program_text;
    @FXML
    private TextField groupNo;
    @FXML
    private TextField sgroupNo;
    @FXML
    private TextField SGID;
    @FXML
    private TableView <student> main_table;
    @FXML
    private TableColumn<student, Integer> sid_column;
    @FXML
    private TableColumn<student, String> syear_column;
    @FXML
    private TableColumn <student, String>program_column;
    @FXML
    private TableColumn <student, String>groupNo_column;
    @FXML
    private TableColumn<student, String> sgroupNo_column;
    @FXML
    private TableColumn <student, String>SGID_column;
    @FXML
    private Button Screate_btn;
    @FXML
    private Button update_btn;
    @FXML
    private Button delete_btn;
    @FXML
    private TextField get_text;
    @FXML
    private Button get_button;
    @FXML
    private Button revert_button;
    // getting student objects based on ID
    public void getStudentByID() throws SQLException{
        // testing for invalid user input by means of Dialog
        if(get_text.getText().equals("")){

            Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter an ID to retrieve corresponding student entity!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.show();
        }
        else{
            String sql_query = "SELECT * FROM studentgroup WHERE sid = " + get_text.getText() + "";
            executeQuery(sql_query);
            pushStudentOntoTableForGetButton();
        }
    }
    public ObservableList<student> getStudent(){


        ObservableList<student> student = FXCollections.observableArrayList();
        Connection connect = getConnection();
        String sql_query = "SELECT * FROM studentgroup";

        try(Statement statement = connect.createStatement()){
            ResultSet result_set = statement.executeQuery(sql_query);
            // iterating through resultant student objects from remote DB
            while(result_set.next()){
                student student_queried = new student(result_set.getInt("sid"), result_set.getString("syear"), result_set.getString("program"), result_set.getString("groupNo"), result_set.getString("sgroupNo"), result_set.getString("SGID"));
                student.add(student_queried);
            }
        }
        catch(Exception e){
            System.out.println("Error:" + e.getMessage());
        }
        return student;
    }


    public ObservableList<student> getStudentForGetButton(){

        // Oracle documentation to process SQL statements with JDBC: https://docs.oracle.com/javase/tutorial/jdbc/basics/processingsqlstatements.html
        ObservableList<student> student = FXCollections.observableArrayList();
        Connection connect = getConnection();
        String sql_query = "SELECT * FROM studentgroup WHERE sid = " + get_text.getText() + "";


        try(Statement statement = connect.createStatement()){
            ResultSet result_set = statement.executeQuery(sql_query);
            // iterating through resultant student objects from remote DB
            while(result_set.next()){
                student student_queried = new student(result_set.getInt("sid"), result_set.getString("syear"), result_set.getString("program"), result_set.getString("groupNo"), result_set.getString("sgroupNo"), result_set.getString("SGID"));
                student.add(student_queried);
            }
        }
        catch(Exception e){
            System.out.println("Error:" + e.getMessage());
        }
        return student;
    }
    public void buttonPressed(javafx.event.ActionEvent actionEvent) throws SQLException {
       if (actionEvent.getSource() == get_button){
            getStudentByID();
        }

        else if(actionEvent.getSource() == revert_button){
            pushStudentOntoTable();
            get_text.clear();
        }
    }
    // updating data from MySQL DataBase into Desktop GUI application
    public void pushStudentOntoTableForGetButton(){

        // retrieving data from remote DB
        ObservableList<student> student = getStudentForGetButton();

        // updating DB into GUI application
        sid_column.setCellValueFactory(new PropertyValueFactory<>("sid"));
        syear_column.setCellValueFactory(new PropertyValueFactory<>("syear"));
        program_column.setCellValueFactory(new PropertyValueFactory<>("program"));
        groupNo_column.setCellValueFactory(new PropertyValueFactory<>("groupNo"));
        sgroupNo_column.setCellValueFactory(new PropertyValueFactory<>("sgroupNo"));
        SGID_column.setCellValueFactory(new PropertyValueFactory<>("SGID"));

        main_table.setItems(student);
    }
    // updating data from MySQL DataBase into Desktop GUI application

    public void pushStudentOntoTable(){
        try{
            // retrieving data from remote DB
            ObservableList<student> student = getStudent();

            // updating DB into GUI application
            sid_column.setCellValueFactory(new PropertyValueFactory<>("sid"));
            syear_column.setCellValueFactory(new PropertyValueFactory<>("syear"));
            program_column.setCellValueFactory(new PropertyValueFactory<>("program"));
            groupNo_column.setCellValueFactory(new PropertyValueFactory<>("groupNo"));
            sgroupNo_column.setCellValueFactory(new PropertyValueFactory<>("sgroupNo"));
            SGID_column.setCellValueFactory(new PropertyValueFactory<>("SGID"));

            main_table.setItems(student);
        }catch(Exception e1){

            System.out.println("error");
        }}

    @FXML
    private void createStudent() {
        try {
            String query = "insert into studentgroup values(" + sid_text.getText() + ",'" + syear_text.getText() + "','" + SGID.getText() + "','" + groupNo.getText() + "','" + sgroupNo.getText() + "','" + program_text.getText() + "')";
            executeQuery(query);
            showStudent();
        }catch (Exception e){
            System.out.println(e);
        }

    }

    @FXML
    private void updateStudent() {
        String query = "UPDATE studentgroup SET syear='" + syear_text.getText() + "',SGID='" + SGID.getText() + "',groupNo=" + groupNo.getText() + ",sgroupNo=" + sgroupNo.getText() +",program= '"+ program_text.getText() + "' WHERE sid=" + sid_text.getText() + "";
        executeQuery(query);
        showStudent();
    }



    @FXML
    private void deleteStudent() {
        String query = "DELETE FROM studentgroup WHERE sid=" + sid_text.getText() + "";
        executeQuery(query);
        showStudent();
    }
    @FXML
    private void GenerateIDS() {
        genSgroupID();
    }

    public void executeQuery(String query) {
        Connection conn = getConnection();
        Statement st;
        try {
            st = conn.createStatement();
            st.executeUpdate(query);
        } catch (Exception e) {

            System.out.println(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showStudent();
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
    public ObservableList<student> getStudentList(){
        ObservableList<student> studentList = FXCollections.observableArrayList();
        Connection connection = getConnection();
        String query = "SELECT * FROM studentgroup ";
        Statement st;
        ResultSet rs;

        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);
            student student;
            while(rs.next()) {
                student = new student(rs.getInt("sid"),rs.getString("syear"),rs.getString("program"),rs.getString("groupNo"),rs.getString("sgroupNo"),rs.getString("SGID"));
                studentList.add(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return studentList;
    }

  public void showStudent(){
      ObservableList<student> list = getStudentList();
      sid_column.setCellValueFactory(new PropertyValueFactory<student,Integer>("sid"));
      syear_column.setCellValueFactory(new PropertyValueFactory<student,String>("syear"));
      program_column.setCellValueFactory(new PropertyValueFactory<student,String>("program"));
      groupNo_column.setCellValueFactory(new PropertyValueFactory<student,String>("groupNo"));
      sgroupNo_column.setCellValueFactory(new PropertyValueFactory<student,String>("sgroupNo"));
      SGID_column.setCellValueFactory(new PropertyValueFactory<student,String>("SGID"));

      main_table.setItems(list);

  }
    public void mouseClicked(MouseEvent mouseEvent) {

        student student = (student) main_table.getSelectionModel().getSelectedItem();

        // extracting data from selected row to be displayed into text fields
        sid_text.setText(String.valueOf(student.getSid()));
        syear_text.setText(String.valueOf(student.getSyear()));
        program_text.setText(student.getProgram());
        groupNo.setText(student.getGroupNo());
        sgroupNo.setText(student.getSgroupNo());
        SGID.setText(student.getSGID());
    }

    //Generate group ID

    private void genSgroupID() {
        String x = syear_text.getText();
        String y = program_text.getText();
        String z = groupNo.getText();
        String z2= sgroupNo.getText();

        String genID = x+y+z+"."+z2;
        System.out.println(genID);
        SGID.setText(genID);
    }
    //change screen method

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

