/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author T.V.Thimira Isiwara
 */
public class Manage_Time_Slots_DB_Connect {
    private int SEssiONID;
    private String TimeSlots;

    public Manage_Time_Slots_DB_Connect(int SEssiONID, String  MANAGETimeSlots) {
        this.SEssiONID = SEssiONID;
        this.TimeSlots = MANAGETimeSlots;
    }

    public int getSEssiONID() {
        return SEssiONID;
    }

    public String getMANAGETimeSlots() {
        return TimeSlots;
    }

}
