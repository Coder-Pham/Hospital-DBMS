package helpers;

import javafx.beans.property.SimpleStringProperty;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Patient {
    private SimpleStringProperty PID;
    private SimpleStringProperty PFname;
    private SimpleStringProperty PLname;
    private SimpleStringProperty PDOB;
    private SimpleStringProperty PGender;
    private SimpleStringProperty PPhone;
    private SimpleStringProperty PAddress;

    public Patient (String pid, String fname, String lname, String dob, String gender, String phone, String addr)
    {
        this.PID = new SimpleStringProperty(pid);
        this.PFname = new SimpleStringProperty(fname);
        this.PLname = new SimpleStringProperty(lname);

        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dob);
            String Newdob = new SimpleDateFormat("dd-MM-yyyy").format(date);
            this.PDOB = new SimpleStringProperty(Newdob);
        } catch (ParseException e) {}
        this.PGender = new SimpleStringProperty(gender);
        this.PPhone = new SimpleStringProperty(phone);
        this.PAddress = new SimpleStringProperty(addr);
    }

    public String getPID() {
        return PID.get();
    }

    public String getPFname() {
        return PFname.get();
    }

    public String getPLname() {
        return PLname.get();
    }

    public String getPDOB() {
        return PDOB.get();
    }

    public String getPGender() {
        return PGender.get();
    }

    public String getPPhone() {
        return PPhone.get();
    }

    public String getPAddress() {
        return PAddress.get();
    }
}
