

public class student {


    public student(int sid, String syear, String program, String groupNo, String sgroupNo, String SGID) {
        this.sid = sid;
        this.syear = syear;
        this.program = program;
        this.groupNo = groupNo;
        this.sgroupNo = sgroupNo;
        this.SGID = SGID;
    }

    public int getSid() {
        return sid;
    }

    public String getSyear() {
        return syear;
    }

    public String getProgram() {
        return program;
    }

    public String getGroupNo() {
        return groupNo;
    }

    public String getSgroupNo() {
        return sgroupNo;
    }

    public String getSGID() {
        return SGID;
    }
    public int sid;
    public String syear;
    public String program;
    public String groupNo;
    public String sgroupNo;
    public String SGID;

}
