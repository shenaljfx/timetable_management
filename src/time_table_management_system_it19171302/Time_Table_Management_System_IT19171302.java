/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package time_table_management_system_it19171302;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author T.V.Thimira Isiwara
 */
public class Time_Table_Management_System_IT19171302 extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("Manage_Time_Slots.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("Add_Working_Days_Hours.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("Add_Locations.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("mainPage.fxml"));
        primaryStage.setTitle("Member3 GROUPS");
        primaryStage.setScene(new Scene(root,800,755));
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
