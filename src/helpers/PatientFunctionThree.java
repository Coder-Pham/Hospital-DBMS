package Model;

public class PatientFunctionThree {

    private String PID;
    private String fullName;
    private String DOB;
    private String gender;
    private String phoneNo;
    private String address;
    private String admissionDate;
    private String dischargeDate;
    private String diagnosis;
    private String sickRoom;
    private String fee;
    private String caringNurseID;
    private String treatmentID;
    private String treatmentStartDate;
    private String treatmentEndDate;
    private String result;

    public PatientFunctionThree(String PID, String fullName, String DOB, String gender, String phoneNo, String address, String admissionDate, String dischargeDate, String diagnosis, String sickRoom, String fee, String caringNurseID, String treatmentID, String treatmentStartDate, String treatmentEndDate, String result) {
        this.PID = PID;
        this.fullName = fullName;
        this.DOB = DOB;
        this.gender = gender;
        this.phoneNo = phoneNo;
        this.address = address;
        this.admissionDate = admissionDate;
        this.dischargeDate = dischargeDate;
        this.diagnosis = diagnosis;
        this.sickRoom = sickRoom;
        this.fee = fee;
        this.caringNurseID = caringNurseID;
        this.treatmentID = treatmentID;
        this.treatmentStartDate = treatmentStartDate;
        this.treatmentEndDate = treatmentEndDate;
        this.result = result;
    }

    public String getPID() {
        return PID;
    }

    public String getFullName() {
        return fullName;
    }

    public String getDOB() {
        return DOB;
    }

    public String getGender() {
        return gender;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getAddress() {
        return address;
    }

    public String getAdmissionDate() {
        return admissionDate;
    }

    public String getDischargeDate() {
        return dischargeDate;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public String getSickRoom() {
        return sickRoom;
    }

    public String getFee() {
        return fee;
    }

    public String getCaringNurseID() {
        return caringNurseID;
    }

    public String getTreatmentID() {
        return treatmentID;
    }

    public String getTreatmentStartDate() {
        return treatmentStartDate;
    }

    public String getTreatmentEndDate() {
        return treatmentEndDate;
    }

    public String getResult() {
        return result;
    }
}
