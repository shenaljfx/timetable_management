public class addSession {
    public addSession(int id, String lec, String sub_code, String sub_name, String group_id, String tag, String noOfStudents, String duration, String date) {
        this.id = id;
        this.lec = lec;
        this.sub_code = sub_code;
        this.sub_name = sub_name;
        Group_id = group_id;
        this.tag = tag;
        this.noOfStudents = noOfStudents;
        this.duration = duration;
        this.date = date;
    }

    int id;
    String lec;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
        return Group_id;
    }

    public void setGroup_id(String group_id) {
        Group_id = group_id;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    String sub_code;
    String sub_name;
    String Group_id;
    String tag;
    String noOfStudents;
    String duration;
    String date;
}
