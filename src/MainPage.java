import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainPage {
    public void manageStudent(javafx.event.ActionEvent event) {
        try{
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("sample/student.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);
            Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(tableViewScene);
            window.show();
        }catch(Exception E){
            System.out.println(E);
        }
    }
    public void manageTags(javafx.event.ActionEvent event) {
        try{
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("sample/tags.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);
            Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(tableViewScene);
            window.show();
        }catch(Exception E){
            System.out.println(E);
        }
    }
    public void add_working_days(javafx.event.ActionEvent event) {
        try{
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("time_table_management_system_it19171302/Add_Working_Days_Hours.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);
            Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(tableViewScene);
            window.show();
        }catch(Exception E){
            System.out.println(E);
        }
    }
    public void add_location(javafx.event.ActionEvent event) {
        try{
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("timetablemgt/RoomLocation.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);
            Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(tableViewScene);
            window.show();
        }catch(Exception E){
            System.out.println(E);
        }
    }
    @FXML
    public void stat(javafx.event.ActionEvent event) {
        try{
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("timetablemgt/statistics.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);
            Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(tableViewScene);
            window.show();
        }catch(Exception E){
            System.out.println(E);
        }
    }
    @FXML
    public void TimeSlots(javafx.event.ActionEvent event) {
        try{
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("time_table_management_system_it19171302/Manage_Time_Slots.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);
            Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(tableViewScene);
            window.show();
        }catch(Exception E){
            System.out.println(E);
        }
    }
    @FXML
    public void TimeTable(javafx.event.ActionEvent event) {
        try{
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("TIMETABLEGEN/timetable.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);
            Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(tableViewScene);
            window.show();
        }catch(Exception E){
            System.out.println(E);
        }
    }
    @FXML
    public void addLecturer(javafx.event.ActionEvent event) {
        try{
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("member2/lecture.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);
            Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(tableViewScene);
            window.show();
        }catch(Exception E){
            System.out.println(E);
        }
    }
    @FXML
    public void addSubject(javafx.event.ActionEvent event) {
        try{
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("member2/subject.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);
            Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(tableViewScene);
            window.show();
        }catch(Exception E){
            System.out.println(E);
        }
    }
    @FXML
    public void managesession(javafx.event.ActionEvent event) {
        try{
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("sample/sessions.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);
            Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(tableViewScene);
            window.show();
        }catch(Exception E){
            System.out.println(E);
        }
    }

}
