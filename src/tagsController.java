


// javafx libraries
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

// java libraries
import java.net.URL;
import java.sql.*;
import java.util.Properties;
import java.util.ResourceBundle;

// controller initialization interface
public class tagsController implements Initializable {

    // declaring javafx components as defined in .fxml
    public TextField id_text;
    public TextField Tname_text;
    public TextField Rtag_text;
    public TextField Tcode_text;
    public TableView main_table;
    public TableColumn id_column;
    public TableColumn Tname_column;
    public TableColumn Rtag_column;
    public TableColumn Tcode_column;
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

            connect_object = DriverManager.getConnection("jdbc:sqlite:D:/SLIIT/3rd year 1st sem/ITPM/IT19033938/src/student" );
            return connect_object;
        }
        catch(Exception e){
            System.out.println("Error:" + e.getMessage());
            return null;
        }

    }
    // implementing update from remote DB to Desktop GUI application
    public ObservableList<tags> getTags(){


        ObservableList<tags> tags = FXCollections.observableArrayList();
        Connection connect = getConnection();
        String sql_query = "SELECT * FROM tags";

        try(Statement statement = connect.createStatement()){
            ResultSet result_set = statement.executeQuery(sql_query);
            // iterating through resultant tags objects from remote DB
            while(result_set.next()){
                tags vehicles_queried = new tags(result_set.getInt("id"), result_set.getString("Tname"), result_set.getString("Rtag"), result_set.getString("Tcode"));
                tags.add(vehicles_queried);
            }
        }
        catch(Exception e){
            System.out.println("Error:" + e.getMessage());
        }
        return tags;
    }


    public ObservableList<tags> getTagsForGetButton(){

        // Oracle documentation to process SQL statements with JDBC: https://docs.oracle.com/javase/tutorial/jdbc/basics/processingsqlstatements.html
        ObservableList<tags> tags = FXCollections.observableArrayList();
        Connection connect = getConnection();
        String sql_query = "SELECT * FROM tags WHERE id = " + get_text.getText() + "";


        try(Statement statement = connect.createStatement()){
            ResultSet result_set = statement.executeQuery(sql_query);
            // iterating through resultant tags objects from remote DB
            while(result_set.next()){
                tags vehicles_queried = new tags(result_set.getInt("id"), result_set.getString("Tname"), result_set.getString("Rtag"), result_set.getString("Tcode"));
                tags.add(vehicles_queried);
            }
        }
        catch(Exception e){
            System.out.println("Error:" + e.getMessage());
        }
        return tags;
    }

    // updating data from MySQL DataBase into Desktop GUI application
    public void pushTagsOntoTableForGetButton(){

        // retrieving data from remote DB
        ObservableList<tags> tags = getTagsForGetButton();

        // updating DB into GUI application
        id_column.setCellValueFactory(new PropertyValueFactory<>("id"));
        Tname_column.setCellValueFactory(new PropertyValueFactory<>("Tname"));
        Rtag_column.setCellValueFactory(new PropertyValueFactory<>("Rtag"));
        Tcode_column.setCellValueFactory(new PropertyValueFactory<>("Tcode"));

        main_table.setItems(tags);
    }

    // updating data from MySQL DataBase into Desktop GUI application
    public void pushTagsOntoTable(){

        // retrieving data from remote DB
        ObservableList<tags> tags = getTags();

        // updating DB into GUI application
        id_column.setCellValueFactory(new PropertyValueFactory<>("id"));
        Tname_column.setCellValueFactory(new PropertyValueFactory<>("Tname"));
        Rtag_column.setCellValueFactory(new PropertyValueFactory<>("Rtag"));
        Tcode_column.setCellValueFactory(new PropertyValueFactory<>("Tcode"));

        main_table.setItems(tags);
    }

    // creating tags object based on user input
    public void createTags() throws SQLException {

        if(id_text.getText().equals("") || Tname_text.getText().equals("") || Rtag_text.getText().equals("") || Tcode_text.getText().equals("")) {
            // testing for invalid user input by means of Dialog
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please fill all text fields!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.show();
        }
        else{
            // Creating tags object based on user input
            String sql_query = "INSERT INTO tags VALUES(" + id_text.getText() + "," +   Tcode_text.getText() + ",'" + Rtag_text.getText() + "','" + Tname_text.getText() + "')";
            establishSQLConnection(sql_query);
            pushTagsOntoTable();
        }
    }

    // updating tags object based on ID
    public void updateTags() throws SQLException {

        if(id_text.getText().equals("") || Tname_text.getText().equals("") || Rtag_text.getText().equals("") || Tcode_text.getText().equals("")) {
            // testing for invalid user input by means of Dialog
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please fill all text fields!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.show();

        }
        else {
            // updating tags object based on id
            String sql_query = "UPDATE tags SET Tcode = " + Tcode_text.getText() + ", Rtag = '" + Rtag_text.getText() + "', Tname = '" + Tname_text.getText() + "' WHERE id = " + id_text.getText() + "";
            establishSQLConnection(sql_query);
            pushTagsOntoTable();
        }
    }

    // deleting tags object based on ID
    private void deleteTags() throws SQLException {

        // testing for invalid user input by means of Dialog
        if(id_text.getText().equals("") || Tname_text.getText().equals("") || Rtag_text.getText().equals("") || Tcode_text.getText().equals("")){

            Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a row in the table or add an ID in the text field to delete!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.show();
        }
        else{
            // deleting row based on ID since it is the primary key
            String sql_query = "DELETE FROM tags WHERE id = " + id_text.getText() + "";
            establishSQLConnection(sql_query);
            pushTagsOntoTable();
        }
    }

    // getting tags objects based on ID
    public void getTagsByID() throws SQLException{
        // testing for invalid user input by means of Dialog
        if(get_text.getText().equals("")){

            Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter an ID to retrieve corresponding tags entity!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.show();
        }
        else{
            String sql_query = "SELECT * FROM tags WHERE id = " + get_text.getText() + "";
            establishSQLConnection(sql_query);
            pushTagsOntoTableForGetButton();
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
            createTags();
        }
        else if(actionEvent.getSource() == update_btn){
            updateTags();
        }

        else if(actionEvent.getSource() == delete_btn){
            deleteTags();
        }

        else if (actionEvent.getSource() == get_button){
            getTagsByID();
        }

        else if(actionEvent.getSource() == revert_button){
            pushTagsOntoTable();
            get_text.clear();
        }
    }

    // event handler for mouse click on table cell
    // param: mouseEvent: MouseEvent
    public void mouseClicked(MouseEvent mouseEvent) {
try {


        tags tags = (tags) main_table.getSelectionModel().getSelectedItem();

        // extracting data from selected row to be displayed into text fields
        id_text.setText(String.valueOf(tags.getId()));
        Tname_text.setText(String.valueOf(tags.getTname()));
        Rtag_text.setText(tags.getRtag());
        Tcode_text.setText(tags.getTcode());
}catch (NullPointerException E){
    System.out.println("error");
}
    }

    // delegate function for Initializable class
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pushTagsOntoTable();
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
