/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mgt_locations;

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
public class Mgt_SessionsController implements Initializable {
    
    //declaring javafx components
    public TextField txtlec1;
    public TextField txtlec2;
    public TextField txtsbjcode;
    public TextField txtsbjname;
    public TextField txtgrpid;
    public TextField txttag;
    public TextField txtroom;
    public TextField gettext;
    
    public Button btnsave;
    public Button btnupdate;
    public Button btndelete;
    public Button btnsearch;
    
    public TableView tbsession;
    public TableColumn tbl1;
    public TableColumn tbl2;
    public TableColumn tbscode;
    public TableColumn tbsname;
    public TableColumn tbgid;
    public TableColumn tbtag;
    public TableColumn tbroom;
    
    //enableing connection(MySQL server)
    public Connection getConnection(){

        Connection connect_object;
        try{

            connect_object = DriverManager.getConnection("jdbc:mysql://localhost:3306/session","root","damikagd1" );
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
    public ObservableList<sessionMgt> getsessionMgt(){


        ObservableList<sessionMgt> sessionMgt = FXCollections.observableArrayList();
        Connection connect = getConnection();
        String sql_query = "SELECT * FROM sessionmgt";
        pushsessionMgtOntoTable();
        System.out.println(sql_query);

        try(Statement statement = connect.createStatement()){
            ResultSet result_set = statement.executeQuery(sql_query);
            // iterating through resultant tags objects from remote DB
            while(result_set.next()){
                sessionMgt vehicles_queried = new sessionMgt(result_set.getString("Lecturer1"), result_set.getString("Lecturer2"), result_set.getString("Subject_Code"), result_set.getString("SubjectName"), result_set.getString("GroupID"), result_set.getString("Tag"), result_set.getString("Room"));
                sessionMgt.add(vehicles_queried);
            }
        }
        catch(Exception e){
            System.out.println("Error:" + e.getMessage());
        }
        return sessionMgt;
    }
    
    public ObservableList<sessionMgt> getsessionMgtForGetButton(){

        // Oracle documentation to process SQL statements with JDBC: https://docs.oracle.com/javase/tutorial/jdbc/basics/processingsqlstatements.html
        ObservableList<sessionMgt> sessionMgt = FXCollections.observableArrayList();
        Connection connect = getConnection();
        String sql_query = "SELECT * FROM sessionmgt WHERE id = " + gettext.getText() + "";


        try(Statement statement = connect.createStatement()){
            ResultSet result_set = statement.executeQuery(sql_query);
            
            while(result_set.next()){
                sessionMgt vehicles_queried = new sessionMgt(result_set.getString("Lecturer1"), result_set.getString("Lecturer2"), result_set.getString("Subject_Code"), result_set.getString("SubjectName"), result_set.getString("GroupID"), result_set.getString("Tag"), result_set.getString("Room"));
                sessionMgt.add(vehicles_queried);
            }
        }
        catch(Exception e){
            System.out.println("Error:" + e.getMessage());
        }
        return sessionMgt;
    }
    
    //updating data from MySQL DataBase
    public void pushsessionMgtOntoTableForGetButton(){

        // retrieving data from DB
        ObservableList<sessionMgt> sessionMgt = getsessionMgtForGetButton();

        // updating DB
        tbl1.setCellValueFactory(new PropertyValueFactory<>("Lecturer1"));
        tbl2.setCellValueFactory(new PropertyValueFactory<>("Lecturer2"));
        tbscode.setCellValueFactory(new PropertyValueFactory<>("Subject_Code"));
        tbsname.setCellValueFactory(new PropertyValueFactory<>("SubjectName"));
        tbgid.setCellValueFactory(new PropertyValueFactory<>("GroupID"));
        tbtag.setCellValueFactory(new PropertyValueFactory<>("Tag"));
        tbroom.setCellValueFactory(new PropertyValueFactory<>("Room"));

        tbsession.setItems(sessionMgt);
    }
    
    //updating data from MySQL DataBase
    public void pushsessionMgtOntoTable(){

        // retrieving data from DB
        ObservableList<sessionMgt> sessionMgt = getsessionMgt();

        // updating DB
        tbl1.setCellValueFactory(new PropertyValueFactory<>("Lecturer1"));
        tbl2.setCellValueFactory(new PropertyValueFactory<>("Lecturer2"));
        tbscode.setCellValueFactory(new PropertyValueFactory<>("Subject_Code"));
        tbsname.setCellValueFactory(new PropertyValueFactory<>("SubjectName"));
        tbgid.setCellValueFactory(new PropertyValueFactory<>("GroupID"));
        tbtag.setCellValueFactory(new PropertyValueFactory<>("Tag"));
        tbroom.setCellValueFactory(new PropertyValueFactory<>("Room"));

        tbsession.setItems(sessionMgt);
    }
    
    // creating sessions
    public void createSessions() throws SQLException {

        if(txtlec1.getText().equals("") || txtlec2.getText().equals("") || txtsbjcode.getText().equals("") || txtsbjname.getText().equals("") || txtgrpid.getText().equals("") || txttag.getText().equals("") || txtroom.getText().equals("")) {
            // testing invalid user input
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please fill all text fields!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.show();
        }
        else{
            // Creating tags object based on user input
            String sql_query = "INSERT INTO sessionmgt VALUES(" + txtlec1.getText() + "," +   txtlec2.getText() + ",'" + txtsbjcode.getText() + "','" + txtsbjname.getText() + "','" + txtgrpid.getText() + "','" + txttag.getText() + "','" + txtroom.getText() + "')";
            establishSQLConnection(sql_query);
            pushsessionMgtOntoTable();
        }
    }

    // updating tags object based on ID
    public void updateSessions() throws SQLException {

        if(txtlec1.getText().equals("") || txtlec2.getText().equals("") || txtsbjcode.getText().equals("") || txtsbjname.getText().equals("") || txtgrpid.getText().equals("") || txttag.getText().equals("") || txtroom.getText().equals("")){
            // testing invalid user inputs
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please fill all text fields!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.show();

        }
        else {
            // updating tags object based on id
            String sql_query = "UPDATE sessionmgt SET Lecturer1 = " + txtlec1.getText() + ", Lecturer2 = " + txtlec2.getText() + ", Subject_Code = " + txtsbjcode.getText() + ", SubjectName = " + txtsbjname.getText() + ", GroupID = '" + txtgrpid.getText() + "', Tag = '" + txttag.getText() + "', Room = '" + txtroom.getText() + "' WHERE id = " + txtgrpid.getText() + "";
            establishSQLConnection(sql_query);
            pushsessionMgtOntoTable();
        }
    }

    // deleting tags object based on ID
    private void deleteSessions() throws SQLException {

        // testing invalid user inputs
        if(txtlec1.getText().equals("") || txtlec2.getText().equals("") || txtsbjcode.getText().equals("") || txtsbjname.getText().equals("") || txtgrpid.getText().equals("") || txttag.getText().equals("") || txtroom.getText().equals("")){

            Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a row in the table or add an ID in the text field to delete!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.show();
        }
        else{
            // deleting row based on ID since it is the primary key
            String sql_query = "DELETE FROM sessionmgt WHERE id = " + txtgrpid.getText() + "";
            establishSQLConnection(sql_query);
            pushsessionMgtOntoTable();
        }
    }

    // getting tags objects based on ID
    public void getSessionsByID() throws SQLException{
        // testing for invalid user input by means of Dialog
        if(gettext.getText().equals("")){

            Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter an ID to retrieve corresponding tags entity!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.show();
        }
        else{
            String sql_query = "SELECT * FROM sessionmgt WHERE id = " + gettext.getText() + "";
            establishSQLConnection(sql_query);
            pushsessionMgtOntoTableForGetButton();
        }
    }

    // event handler for button
    // param: actionEvent: ActionEvent
    public void buttonPressed(javafx.event.ActionEvent actionEvent) throws SQLException {

        // calling relevant methods based on event source
        if (actionEvent.getSource() == btnsave ){
            createSessions();
        }
        else if(actionEvent.getSource() == btnupdate){
            updateSessions();
        }

        else if(actionEvent.getSource() == btndelete){
            deleteSessions();
        }

        else if (actionEvent.getSource() == btnsearch){
            getSessionsByID();
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


        sessionMgt sessionMgt = (sessionMgt) tbsession.getSelectionModel().getSelectedItem();

        // extracting data from selected row to be displayed into text fields
        txtlec1.setText(String.valueOf(sessionMgt.getLecturer1()));
        txtlec2.setText(String.valueOf(sessionMgt.getLecturer2()));
        txtsbjcode.setText(sessionMgt.getSubject_Code());
        txtsbjname.setText(sessionMgt.getSubjectName());
        txtgrpid.setText(sessionMgt.getGroupID());
        txttag.setText(sessionMgt.getTag());
        txtroom.setText(sessionMgt.getRoom());
        
        }catch (NullPointerException E){
            System.out.println("error");
        }
    }

          
         
           
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
