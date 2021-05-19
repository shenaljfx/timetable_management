

public class subject {
    String OfferedYr;
    String OfferedSem;

    public String getOfferedYr() {
        return OfferedYr;
    }

    public void setOfferedYr(String offeredYr) {
        OfferedYr = offeredYr;
    }

    public String getOfferedSem() {
        return OfferedSem;
    }

    public void setOfferedSem(String offeredSem) {
        OfferedSem = offeredSem;
    }

    public String getSubName() {
        return SubName;
    }

    public void setSubName(String subName) {
        SubName = subName;
    }

    public String getSubCode() {
        return SubCode;
    }

    public void setSubCode(String subCode) {
        SubCode = subCode;
    }

    public String getLecHrs() {
        return LecHrs;
    }

    public void setLecHrs(String lecHrs) {
        LecHrs = lecHrs;
    }

    public String getTuteHrs() {
        return TuteHrs;
    }

    public void setTuteHrs(String tuteHrs) {
        TuteHrs = tuteHrs;
    }

    public String getLabHrs() {
        return LabHrs;
    }

    public void setLabHrs(String labHrs) {
        LabHrs = labHrs;
    }

    public String getEvalutionHrs() {
        return EvalutionHrs;
    }

    public void setEvalutionHrs(String evalutionHrs) {
        EvalutionHrs = evalutionHrs;
    }

    String SubName;
    String SubCode;
    String LecHrs;
    String TuteHrs;
    String LabHrs;
    String EvalutionHrs;

    public subject(String offeredYr, String offeredSem, String subName, String subCode, String lecHrs, String tuteHrs, String labHrs, String evalutionHrs) {
        OfferedYr = offeredYr;
        OfferedSem = offeredSem;
        SubName = subName;
        SubCode = subCode;
        LecHrs = lecHrs;
        TuteHrs = tuteHrs;
        LabHrs = labHrs;
        EvalutionHrs = evalutionHrs;
    }


}
