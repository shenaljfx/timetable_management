package TIMETABLEGEN;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class timeM extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("Manage_Time_Slots.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("Add_Working_Days_Hours.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("timetable.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("mainPage.fxml"));
        primaryStage.setTitle("Member3 GROUPS");
        primaryStage.setScene(new Scene(root,1227,1449));
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}


