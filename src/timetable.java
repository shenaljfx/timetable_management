

public class timetable {
    String lec;
    String sub_code;

    public String getLec() {
        return lec;
    }

    public void setLec(String lec) {
        this.lec = lec;
    }

    public String getSub_code() {
        return sub_code;
    }

    public void setSub_code(String sub_code) {
        this.sub_code = sub_code;
    }

    public String getSub_name() {
        return sub_name;
    }

    public void setSub_name(String sub_name) {
        this.sub_name = sub_name;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getNoOfStudents() {
        return noOfStudents;
    }

    public void setNoOfStudents(String noOfStudents) {
        this.noOfStudents = noOfStudents;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    String sub_name;
    String group_id;
    String tag;
    String noOfStudents;
    String duration;

    public timetable(String name, String lec, String sub_code, String sub_name, String group_id, String tag, String noOfStudents, String duration) {
        this.lec = lec;
        this.sub_code = sub_code;
        this.sub_name = sub_name;
        this.group_id = group_id;
        this.tag = tag;
        this.noOfStudents = noOfStudents;
        this.duration = duration;
    }



}
