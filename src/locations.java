/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javafx.scene.control.RadioButton;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Damika
 */
public class locations {

    public String BuildingName;
    public String RoomName;



    public String getBuildingName() {
        return BuildingName;
    }

    public String getRoomName() {
        return RoomName;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getRoomtype() {
        return roomtype;
    }
    public int getRoomID() {
        return roomID;
    }

    public int capacity;
    public String roomtype;
    public int roomID;

    public locations( String BuildingName, String RoomName, int capacity, String roomtype,int roomID) {

        this.BuildingName = BuildingName;
        this.RoomName = RoomName;
        this.capacity = capacity;
        this.roomtype = roomtype;
        this.roomID = roomID;
    }



}
