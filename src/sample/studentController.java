package sample;
// javafx libraries
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;

// java libraries
import java.net.URL;
import java.sql.*;
import java.util.Properties;
import java.util.ResourceBundle;

public class studentController  implements Initializable {
    // declaring javafx components as defined in .fxml
    public TextField sid_text;
    public TextField syear_text;
    public TextField program_text;
    public TextField groupNo;
    public TextField sgroupNo;
    public TextField sgroupID;

    public TableView main_table;
    public TableColumn sid_column;
    public TableColumn syear_column;
    public TableColumn program_column;
    public TableColumn groupNo_column;
    public TableColumn sgroupNo_column;
    public TableColumn SGID_column;
    public Button create_btn;
    public Button update_btn;
    public Button delete_btn;
    public TextField get_text;
    public Button get_button;
    public Button revert_button;
    private Properties user;
    private Properties password;

    // establishing initial connection with MySQL server
    public Connection getConnection(){

        Connection connect_object;
        try{
            Class.forName("org.sqlite.JDBC");
            connect_object = DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","shenaljfx" );
            return connect_object;
        }
        catch(Exception e){
            System.out.println("Error:" + e.getMessage());
            return null;
        }

    }
    // implementing update from remote DB to Desktop GUI application
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

    // updating data from MySQL DataBase into Desktop GUI application
    public void pushStudentOntoTableForGetButton(){

        // retrieving data from remote DB
        ObservableList<student> student = getStudentForGetButton();

        // updating DB into GUI application
        sid_column.setCellValueFactory(new PropertyValueFactory<>("sid"));
        syear_column.setCellValueFactory(new PropertyValueFactory<>("year"));
        program_column.setCellValueFactory(new PropertyValueFactory<>("program"));
        groupNo_column.setCellValueFactory(new PropertyValueFactory<>(" groupNo"));
        sgroupNo_column.setCellValueFactory(new PropertyValueFactory<>("sgroupNo"));
        SGID_column.setCellValueFactory(new PropertyValueFactory<>("SGID"));

        main_table.setItems(student);
    }

    // updating data from MySQL DataBase into Desktop GUI application
    public void pushStudentOntoTable(){

        // retrieving data from remote DB
        ObservableList<student> student = getStudent();

        // updating DB into GUI application
        sid_column.setCellValueFactory(new PropertyValueFactory<>("sid"));
        syear_column.setCellValueFactory(new PropertyValueFactory<>("syear"));
        program_column.setCellValueFactory(new PropertyValueFactory<>("program"));
        groupNo_column.setCellValueFactory(new PropertyValueFactory<>("groupNo"));
        sgroupNo_column.setCellValueFactory(new PropertyValueFactory<>("sgroupNo"));


        main_table.setItems(student);
    }

    // creating student object based on user input
    public void createStudent() throws SQLException {

        if(sid_text.getText().equals("") || syear_text.getText().equals("") || program_text.getText().equals("") || groupNo.getText().equals("")|| sgroupNo.getText().equals("")) {
            // testing for invalid user input by means of Dialog
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please fill all text fields!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.show();
        }
        else{
            // Creating student object based on user input
            String sql_query = "INSERT INTO studentgroup VALUES(" + sid_text.getText() + "," + syear_text.getText() + ",'" + program_text.getText() + "','" + groupNo.getText()+ ",'"+ sgroupNo.getText() + "','"  + "')";
            establishSQLConnection(sql_query);
            pushStudentOntoTable();
        }

}

    // updating student object based on ID
    public void updateStudent() throws SQLException {

        if(sid_text.getText().equals("") || syear_text.getText().equals("") || program_text.getText().equals("") || groupNo.getText().equals("")) {
            // testing for invalid user input by means of Dialog
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please fill all text fields!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.show();

        }
        else {
            // updating student object based on id
            String sql_query = "UPDATE studentgroup SET syear = " + syear_text.getText() + ",program = '" + program_text.getText() + "', groupNo = '" + groupNo.getText() + "', groupNo = '" + groupNo.getText() + "' WHERE id = " + sid_text.getText() + "";
            establishSQLConnection(sql_query);
           pushStudentOntoTable();
        }
    }

    // deleting student object based on ID
    private void deleteStudent() throws SQLException {

        // testing for invalid user input by means of Dialog
        if(sid_text.getText().equals("") || syear_text.getText().equals("") || program_text.getText().equals("") || groupNo.getText().equals("")){

            Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a row in the table or add an ID in the text field to delete!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.show();
        }
        else{
            // deleting row based on ID since it is the primary key
            String sql_query = "DELETE FROM studentgroup WHERE sid = " + sid_text.getText() + "";
            establishSQLConnection(sql_query);
            pushStudentOntoTable();
        }
    }

    // getting student objects based on ID
    public void getStudentByID() throws SQLException{
        // testing for invalid user input by means of Dialog
        if(get_text.getText().equals("")){

            Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter an ID to retrieve corresponding student entity!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.show();
        }
        else{
            String sql_query = "SELECT * FROM vehicles WHERE id = " + get_text.getText() + "";
            establishSQLConnection(sql_query);
            pushStudentOntoTableForGetButton();
        }
    }

    // using SQL statement to make relevant query to update table accordingly
    // param: sql_query:String
    private void establishSQLConnection(String sql_query) throws SQLException {

        Connection connect_object = getConnection();

        try(Statement statement = connect_object.createStatement()){
            statement.executeUpdate(sql_query);
        }
        catch (Exception e){

        }
    }

    // event handler for button press
    // param: actionEvent: ActionEvent
    public void buttonPressed(javafx.event.ActionEvent actionEvent) throws SQLException {

        // calling relevant methods based on event source
        if (actionEvent.getSource() == create_btn ){
            createStudent();
        }
        else if(actionEvent.getSource() == update_btn){
            updateStudent();
        }

        else if(actionEvent.getSource() == delete_btn){
            deleteStudent();
        }

        else if (actionEvent.getSource() == get_button){
            getStudentByID();
        }

        else if(actionEvent.getSource() == revert_button){
            pushStudentOntoTable();
            get_text.clear();
        }
    }

    // event handler for mouse click on table cell
    // param: mouseEvent: MouseEvent
    public void mouseClicked(MouseEvent mouseEvent) {

        student student = (student) main_table.getSelectionModel().getSelectedItem();

        // extracting data from selected row to be displayed into text fields
        sid_text.setText(String.valueOf(student.getSid()));
        syear_text.setText(String.valueOf(student.getSyear()));
        program_text.setText(student.getProgram());
        groupNo.setText(student.getGroupNo());
        sgroupNo.setText(student.getSgroupNo());
    }

    // delegate function for Initializable class
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pushStudentOntoTable();
    }


}
