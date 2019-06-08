package Model;

public class treatmentReport {

    private String treatmentID;
    private String treatmentStart;
    private String treatmentEnd;
    private String treatmentResult;
    private String admissionDate;
    private String dischargeDate;
    private String treatmentDiagnosis;
    private String treatmentSickroom;
    private String medicationName;
    private String medicationEffect;
    private String treatmentFee;
    private String medicationPrice;
    private String total;

    public treatmentReport(String treatmentID, String treatmentStart, String treatmentEnd, String treatmentResult, String admissionDate, String dischargeDate, String treatmentDiagnosis, String treatmentSickroom, String medicationName, String medicationEffect, String treatmentFee, String medicationPrice, String total){
       this.treatmentID = treatmentID;
       this.treatmentStart = treatmentStart;
       this.treatmentEnd = treatmentEnd;
       this.treatmentResult = treatmentResult;
       this.admissionDate = admissionDate;
       this.dischargeDate = dischargeDate;
       this.treatmentDiagnosis = treatmentDiagnosis;
       this.treatmentSickroom = treatmentSickroom;
       this.medicationName = medicationName;
       this.medicationEffect = medicationEffect;
       this.treatmentFee = treatmentFee;
       this.medicationPrice = medicationPrice;
       this.total = total;
    }

    public String getTreatmentID(){
        return this.treatmentID;
    }

    public String getTreatmentStart(){
        return this.treatmentStart;
    }

    public String getTreatmentEnd(){
        return this.treatmentEnd;
    }

    public String getTreatmentResult(){
        return this.treatmentResult;
    }

    public String getAdmissionDate(){
        return this.admissionDate;
    }

    public String getDischargeDate(){
        return this.dischargeDate;
    }

    public String getTreatmentDiagnosis(){
        return this.treatmentDiagnosis;
    }

    public String getTreatmentSickroom(){
        return this.treatmentSickroom;
    }

    public String getMedicationName(){
        return this.medicationName;
    }

    public String getMedicationEffect(){
        return this.medicationEffect;
    }

    public String getMedicationPrice(){
        return this.medicationPrice;
    }

    public String getTreatmentFee(){
        return this.treatmentFee;
    }

    public String getTotal(){
        return this.total;
    }

}
