/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package time_table_management_system_it19171302;

/**
 *
 * @author T.V.Thimira Isiwara
 */
public class Sessions_and_Not_Available_Times_Allocations_DB_Connect {
    private String Lecturer;
    private String Group;
    private String SubGroup;
    private int SessionID;
    private int TimeDuration;

    public Sessions_and_Not_Available_Times_Allocations_DB_Connect(String Lecturer, String Group, String SubGroup, int SessionID, int TimeDuration) {
        this.Lecturer = Lecturer;
        this.Group = Group;
        this.SubGroup = SubGroup;
        this.SessionID = SessionID;
        this.TimeDuration = TimeDuration;
    }

    public String getLecturer() {
        return Lecturer;
    }

    public String getGroup() {
        return Group;
    }

    public String getSubGroup() {
        return SubGroup;
    }

    public int getSessionID() {
        return SessionID;
    }

    public int getTimeDuration() {
        return TimeDuration;
    }
    
}
