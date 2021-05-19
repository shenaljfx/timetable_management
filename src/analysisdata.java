/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Damika
 */
public class analysisdata {

    public String getGroupID() {
        return GroupID;
    }

    public void setGroupID(String groupID) {
        GroupID = groupID;
    }

    public String getMonthFrom() {
        return MonthFrom;
    }

    public void setMonthFrom(String monthFrom) {
        MonthFrom = monthFrom;
    }

    public String getMonthTo() {
        return MonthTo;
    }

    public void setMonthTo(String monthTo) {
        MonthTo = monthTo;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public String getNooflectures() {
        return Nooflectures;
    }

    public void setNooflectures(String nooflectures) {
        Nooflectures = nooflectures;
    }

    public String getNooflostlectures() {
        return Nooflostlectures;
    }

    public void setNooflostlectures(String nooflostlectures) {
        Nooflostlectures = nooflostlectures;
    }

    public String getNoofpracticals() {
        return Noofpracticals;
    }

    public void setNoofpracticals(String noofpracticals) {
        Noofpracticals = noofpracticals;
    }

    public String getNooflostpracticals() {
        return Nooflostpracticals;
    }

    public void setNooflostpracticals(String nooflostpracticals) {
        Nooflostpracticals = nooflostpracticals;
    }

    String GroupID;
    String MonthFrom;
    String MonthTo;
    String Subject;
    String Nooflectures;
    String Nooflostlectures;
    String Noofpracticals;

    public analysisdata(String groupID, String monthFrom, String monthTo, String subject, String nooflectures, String nooflostlectures, String noofpracticals, String nooflostpracticals) {
        GroupID = groupID;
        MonthFrom = monthFrom;
        MonthTo = monthTo;
        Subject = subject;
        Nooflectures = nooflectures;
        Nooflostlectures = nooflostlectures;
        Noofpracticals = noofpracticals;
        Nooflostpracticals = nooflostpracticals;
    }

    String Nooflostpracticals;




}
