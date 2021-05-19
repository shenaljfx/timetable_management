

// Primary object to load data to table view
public class tags {

    public int id;
    public String Tname;
    public String Rtag;
    public String Tcode;

    // constructor
    public tags(int id, String Tname, String Rtag, String Tcode) {

        this.id = id;
        this.Tname = Tname;
        this.Rtag = Rtag;
        this.Tcode = Tcode;
    }
    // defining accessors and mutators
    public int getId() {
        return id;
    }
    public String getTname() {
        return Tname;
    }

    public String getRtag() {
        return Rtag;
    }

    public String getTcode() {
        return Tcode;
    }


}
