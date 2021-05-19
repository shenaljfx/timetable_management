/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consecutivesessions_locationmgt;

/**
 *
 * @author Damika
 */
public class ConsecutiveSessions_Locations {
    
    String Lecturer1;
    String Lecturer2;
    String Subject_Code;
    String SubjectName;
    String GroupID;
    String Tag;
    String Room;

    public ConsecutiveSessions_Locations(String Lecturer1, String Lecturer2, String Subject_Code, String SubjectName, String GroupID, String Tag, String Room) {
        this.Lecturer1 = Lecturer1;
        this.Lecturer2 = Lecturer2;
        this.Subject_Code = Subject_Code;
        this.SubjectName = SubjectName;
        this.GroupID = GroupID;
        this.Tag = Tag;
        this.Room = Room;
    }

    public String getLecturer1() {
        return Lecturer1;
    }

    public String getLecturer2() {
        return Lecturer2;
    }

    public String getSubject_Code() {
        return Subject_Code;
    }

    public String getSubjectName() {
        return SubjectName;
    }

    public String getGroupID() {
        return GroupID;
    }

    public String getTag() {
        return Tag;
    }

    public String getRoom() {
        return Room;
    }
    
   
    
}
