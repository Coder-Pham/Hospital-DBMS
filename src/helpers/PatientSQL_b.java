package helpers;
public class PatientSQL_b {
    private String PID;
    private String Patient_Name;

    public PatientSQL_b(String PID, String Patient_Name) {
        this.PID = PID;
        this.Patient_Name = Patient_Name;
    }

    public String getPID() {
        return PID;
    }

    public String getPatient_Name() {
        return Patient_Name;
    }
}