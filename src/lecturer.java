/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


public class lecturer {
    public lecturer(String name, String EMPID, String faculty, String department, String center, String buliding, String level, String rank) {
        Name = name;
        this.EMPID = EMPID;
        Faculty = faculty;
        Department = department;
        Center = center;
        this.buliding = buliding;
        Level = level;
        Rank = rank;
    }

    String Name;
String EMPID;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEMPID() {
        return EMPID;
    }

    public void setEMPID(String EMPID) {
        this.EMPID = EMPID;
    }

    public String getFaculty() {
        return Faculty;
    }

    public void setFaculty(String faculty) {
        Faculty = faculty;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        Department = department;
    }

    public String getCenter() {
        return Center;
    }

    public void setCenter(String center) {
        Center = center;
    }

    public String getBuliding() {
        return buliding;
    }

    public void setBuliding(String buliding) {
        this.buliding = buliding;
    }

    public String getLevel() {
        return Level;
    }

    public void setLevel(String level) {
        Level = level;
    }

    public String getRank() {
        return Rank;
    }

    public void setRank(String rank) {
        Rank = rank;
    }

    String Faculty;
String Department;
String Center;
String buliding;
String Level;
String Rank;
}
