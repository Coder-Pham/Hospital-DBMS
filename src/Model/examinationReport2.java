package Model;

public class examinationReport2 {

    private String examinationID;
    private String examinationDate;
    private String examinationSecondDate;
    private String examinationDiagnosis;
    private String medicationName;
    private String medicationEffect;
    private String examinationFee;
    private String medicationPrice;
    private String total;

    public examinationReport2(String examinationID, String examinationDate, String examinationSecondDate, String examinationDiagnosis, String medicationName, String medicationEffect, String examinationFee, String medicationPrice, String total){
       this.examinationID = examinationID;
       this.examinationDate = examinationDate;
       this.examinationSecondDate = examinationSecondDate;
       this.examinationDiagnosis = examinationDiagnosis;
       this.medicationName = medicationName;
       this.medicationEffect = medicationEffect;
       this.examinationFee = examinationFee;
       this.medicationPrice = medicationPrice;
       this.total = total;
    }

    public String getExaminationID(){
        return this.examinationID;
    }

    public String getExaminationDate(){
        return this.examinationDate;
    }

    public String getExaminationSecondDate(){
        return this.examinationSecondDate;
    }

    public String getExaminationDiagnosis(){
        return this.examinationDiagnosis;
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

    public String getExaminationFee(){
        return this.examinationFee;
    }

    public String getTotal(){
        return this.total;
    }

}

