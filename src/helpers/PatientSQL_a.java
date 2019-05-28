package helpers;

public class PatientSQL_a {
    private String PID_In;
    private String fullName;
    private String initialFee;
    private String afterwardFee;

    public PatientSQL_a(String PID_In, String fullName, String initialFee, String afterwardFee) {
        this.PID_In = PID_In;
        this.fullName = fullName;
        this.initialFee = initialFee;
        this.afterwardFee = afterwardFee;
    }

    public String getPID_In() {
        return PID_In;
    }

    public String getFullName() {
        return fullName;
    }

    public String getInitialFee() {
        return initialFee;
    }

    public String getAfterwardFee() {
        return afterwardFee;
    }
}
