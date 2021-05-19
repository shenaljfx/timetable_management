/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author T.V.Thimira Isiwara
 */
public class Add_Locations_DB_Connect {
    private String room;
    private String days;
    private String StartTime;
    private String EndTime;

    public Add_Locations_DB_Connect(String room, String days, String StartTime, String EndTime) {
        this.room = room;
        this.days = days;
        this.StartTime = StartTime;
        this.EndTime = EndTime;
    }

    public String getRoom() {
        return room;
    }

    public String getDays() {
        return days;
    }

    public String getStartTime() {
        return StartTime;
    }

    public String getEndTime() {
        return EndTime;
    }

}
