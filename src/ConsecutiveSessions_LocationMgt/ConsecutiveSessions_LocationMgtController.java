/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consecutivesessions_locationmgt;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;

/**
 *
 * @author Damika
 */
public class ConsecutiveSessions_LocationMgtController implements Initializable {
    
    //declaring javafx components
    public TextField lec1txt;
    public TextField lec2txt;
    public TextField scodetxt;
    public TextField snametxt;
    public TextField gidtxt;
    public TextField tagtxt;
    public TextField roomtxt;
    public TextField gettext;
    
    public Button btnsave;
    public Button btnupdate;
    public Button btndelete;
    public Button btnsearch;
    
    public TableView tableview;
    public TableColumn collec1;
    public TableColumn collec2;
    public TableColumn colscode;
    public TableColumn colsname;
    public TableColumn colgid;
    public TableColumn coltag;
    public TableColumn colroom;
    
    //enableing connection(MySQL server)
    public Connection getConnection(){

        Connection connect_object;
        try{

            connect_object = DriverManager.getConnection("jdbc:mysql://localhost:3306/consecutivesessions","root","damikagd1" );
            return connect_object;
        }
        catch(Exception e){
            System.out.println("Error:" + e.getMessage());
            return null;
        }

    }
    
    // using SQL statement to make query to update table
    // param: sql_query:String
    private void establishSQLConnection(String sql_query) throws SQLException {

        Connection connect_object = getConnection();

        try(Statement statement = connect_object.createStatement()){
            statement.executeUpdate(sql_query);
        }
        catch (Exception e){

        }
    }
    
    // implementing update from DB 
    public ObservableList<ConsecutiveSessions_Locations> getConsecutiveSessions_Locations(){


        ObservableList<ConsecutiveSessions_Locations> ConsecutiveSessions_Locations = FXCollections.observableArrayList();
        Connection connect = getConnection();
        String sql_query = "SELECT * FROM consecutivesessions";
        pushConsecutiveSessions_LocationsOntoTable();
        System.out.println(sql_query);

        try(Statement statement = connect.createStatement()){
            ResultSet result_set = statement.executeQuery(sql_query);
            // iterating through resultant tags objects from remote DB
            while(result_set.next()){
                ConsecutiveSessions_Locations vehicles_queried = new ConsecutiveSessions_Locations(result_set.getString("Lecturer1"), result_set.getString("Lecturer2"), result_set.getString("Subject_Code"), result_set.getString("SubjectName"), result_set.getString("GroupID"), result_set.getString("Tag"), result_set.getString("Room"));
                ConsecutiveSessions_Locations.add(vehicles_queried);
            }
        }
        catch(Exception e){
            System.out.println("Error:" + e.getMessage());
        }
        return ConsecutiveSessions_Locations;
    }
    
    public ObservableList<ConsecutiveSessions_Locations> getConsecutiveSessions_LocationsForGetButton(){

        // Oracle documentation to process SQL statements with JDBC: https://docs.oracle.com/javase/tutorial/jdbc/basics/processingsqlstatements.html
        ObservableList<ConsecutiveSessions_Locations> ConsecutiveSessions_Locations = FXCollections.observableArrayList();
        Connection connect = getConnection();
        String sql_query = "SELECT * FROM consecutivesessions WHERE id = " + gettext.getText() + "";


        try(Statement statement = connect.createStatement()){
            ResultSet result_set = statement.executeQuery(sql_query);
            
            while(result_set.next()){
                ConsecutiveSessions_Locations vehicles_queried = new ConsecutiveSessions_Locations(result_set.getString("Lecturer1"), result_set.getString("Lecturer2"), result_set.getString("Subject_Code"), result_set.getString("SubjectName"), result_set.getString("GroupID"), result_set.getString("Tag"), result_set.getString("Room"));
                ConsecutiveSessions_Locations.add(vehicles_queried);
            }
        }
        catch(Exception e){
            System.out.println("Error:" + e.getMessage());
        }
        return ConsecutiveSessions_Locations;
    }
    
    //updating data from MySQL DataBase
    public void pushConsecutiveSessions_LocationsOntoTableForGetButton(){

        // retrieving data from DB
        ObservableList<ConsecutiveSessions_Locations> ConsecutiveSessions_Locations = getConsecutiveSessions_LocationsForGetButton();

        // updating DB
        collec1.setCellValueFactory(new PropertyValueFactory<>("Lecturer1"));
        collec2.setCellValueFactory(new PropertyValueFactory<>("Lecturer2"));
        colscode.setCellValueFactory(new PropertyValueFactory<>("Subject_Code"));
        colsname.setCellValueFactory(new PropertyValueFactory<>("SubjectName"));
        colgid.setCellValueFactory(new PropertyValueFactory<>("GroupID"));
        coltag.setCellValueFactory(new PropertyValueFactory<>("Tag"));
        colroom.setCellValueFactory(new PropertyValueFactory<>("Room"));

        tableview.setItems(ConsecutiveSessions_Locations);
    }
    
    //updating data from MySQL DataBase
    public void pushConsecutiveSessions_LocationsOntoTable(){

        // retrieving data from DB
        ObservableList<ConsecutiveSessions_Locations> ConsecutiveSessions_Locations = getConsecutiveSessions_Locations();

        // updating DB
        collec1.setCellValueFactory(new PropertyValueFactory<>("Lecturer1"));
        collec2.setCellValueFactory(new PropertyValueFactory<>("Lecturer2"));
        colscode.setCellValueFactory(new PropertyValueFactory<>("Subject_Code"));
        colsname.setCellValueFactory(new PropertyValueFactory<>("SubjectName"));
        colgid.setCellValueFactory(new PropertyValueFactory<>("GroupID"));
        coltag.setCellValueFactory(new PropertyValueFactory<>("Tag"));
        colroom.setCellValueFactory(new PropertyValueFactory<>("Room"));

        tableview.setItems(ConsecutiveSessions_Locations);
    }
    
    // creating sessions
    public void createConsecutiveSessions_Locations() throws SQLException {

        if(lec1txt.getText().equals("") || lec2txt.getText().equals("") || scodetxt.getText().equals("") || snametxt.getText().equals("") || gidtxt.getText().equals("") || tagtxt.getText().equals("") || roomtxt.getText().equals("")) {
            // testing invalid user input
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please fill all text fields!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.show();
        }
        else{
            // Creating tags object based on user input
            String sql_query = "INSERT INTO consecutivesessions VALUES(" + lec1txt.getText() + "," +   lec2txt.getText() + ",'" + scodetxt.getText() + "','" + snametxt.getText() + "','" + gidtxt.getText() + "','" + tagtxt.getText() + "','" + roomtxt.getText() + "')";
            establishSQLConnection(sql_query);
            pushConsecutiveSessions_LocationsOntoTable();
        }
    }

    // updating tags object based on ID
    public void updateConsecutiveSessions_Locations() throws SQLException {

        if(lec1txt.getText().equals("") || lec2txt.getText().equals("") || scodetxt.getText().equals("") || snametxt.getText().equals("") || gidtxt.getText().equals("") || tagtxt.getText().equals("") || roomtxt.getText().equals("")){
            // testing invalid user inputs
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please fill all text fields!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.show();

        }
        else {
            // updating tags object based on id
            String sql_query = "UPDATE consecutivesessions SET Lecturer1 = " + lec1txt.getText() + ", Lecturer2 = " + lec2txt.getText() + ", Subject_Code = " + scodetxt.getText() + ", SubjectName = " + snametxt.getText() + ", GroupID = '" + gidtxt.getText() + "', Tag = '" + tagtxt.getText() + "', Room = '" + roomtxt.getText() + "' WHERE id = " + gidtxt.getText() + "";
            establishSQLConnection(sql_query);
            pushConsecutiveSessions_LocationsOntoTable();
        }
    }

    // deleting tags object based on ID
    private void deleteConsecutiveSessions_Locations() throws SQLException {

        // testing invalid user inputs
        if(lec1txt.getText().equals("") || lec2txt.getText().equals("") || scodetxt.getText().equals("") || snametxt.getText().equals("") || gidtxt.getText().equals("") || tagtxt.getText().equals("") || roomtxt.getText().equals("")){

            Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a row in the table or add an ID in the text field to delete!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.show();
        }
        else{
            // deleting row based on ID since it is the primary key
            String sql_query = "DELETE FROM consecutivesessions WHERE id = " + gidtxt.getText() + "";
            establishSQLConnection(sql_query);
            pushConsecutiveSessions_LocationsOntoTable();
        }
    }

    // getting tags objects based on ID
    public void getConsecutiveSessions_LocationsByID() throws SQLException{
        // testing for invalid user input by means of Dialog
        if(gettext.getText().equals("")){

            Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter an ID to retrieve corresponding tags entity!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.show();
        }
        else{
            String sql_query = "SELECT * FROM consecutivesessions WHERE id = " + gettext.getText() + "";
            establishSQLConnection(sql_query);
            pushConsecutiveSessions_LocationsOntoTableForGetButton();
        }
    }

    // event handler for button
    // param: actionEvent: ActionEvent
    public void buttonPressed(javafx.event.ActionEvent actionEvent) throws SQLException {

        // calling relevant methods based on event source
        if (actionEvent.getSource() == btnsave ){
            createConsecutiveSessions_Locations();
        }
        else if(actionEvent.getSource() == btnupdate){
            updateConsecutiveSessions_Locations();
        }

        else if(actionEvent.getSource() == btndelete){
            deleteConsecutiveSessions_Locations();
        }

        else if (actionEvent.getSource() == btnsearch){
            getConsecutiveSessions_LocationsByID();
        }
        
        /*
        else if(actionEvent.getSource() == btnsearch){
            pushsessionMgtOntoTable();
            gettext.clear();
        }*/
    }
    
    // event handler for mouse click on table cell
    // param: mouseEvent: MouseEvent
    public void mouseClicked(MouseEvent mouseEvent) {
        try {


        ConsecutiveSessions_Locations ConsecutiveSessions_Locations = (ConsecutiveSessions_Locations) tableview.getSelectionModel().getSelectedItem();

        // extracting data from selected row to be displayed into text fields
        lec1txt.setText(String.valueOf(ConsecutiveSessions_Locations.getLecturer1()));
        lec2txt.setText(String.valueOf(ConsecutiveSessions_Locations.getLecturer2()));
        scodetxt.setText(ConsecutiveSessions_Locations.getSubject_Code());
        snametxt.setText(ConsecutiveSessions_Locations.getSubjectName());
        gidtxt.setText(ConsecutiveSessions_Locations.getGroupID());
        tagtxt.setText(ConsecutiveSessions_Locations.getTag());
        roomtxt.setText(ConsecutiveSessions_Locations.getRoom());
        
        }catch (NullPointerException E){
            System.out.println("error");
        }
    }

    
    
    
    
    
    
    

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
