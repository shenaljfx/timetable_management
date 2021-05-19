

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author T.V.Thimira Isiwara
 */
public class Add_Working_Days_Hours_DB_Connect {
    private int SessionID;
    private int NoWorkingDays;
    private String WorkingDays;
    private int hours;
    private int minutes;

    public Add_Working_Days_Hours_DB_Connect(int SessionID, int NoWorkingDays, String WorkingDays, int hours, int minutes) {
        this.SessionID = SessionID;
        this.NoWorkingDays = NoWorkingDays;
        this.WorkingDays = WorkingDays;
        this.hours = hours;
        this.minutes = minutes;
    }

    public int getSessionID() {
        return SessionID;
    }

    public int getNoWorkingDays() {
        return NoWorkingDays;
    }

    public String getWorkingDays() {
        return WorkingDays;
    }

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }
}

