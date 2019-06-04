package helpers;

public class employeeReport {

    private String EID;
    private String EPHONE;
    private String ENAME;
    private String EDOB;
    private String EGENDER;
    private String ESPECIALITY;
    private String EADDRESS;
    private String ESTARTDATE;
    private String DID;
    private String DTITLE;

    public employeeReport(String EID, String EPHONE, String ENAME, String EDOB, String EGENDER, String ESPECIALITY, String EADDRESS, String ESTARTDATE, String DID, String DTITLE) {
        this.EID = EID;
        this.EPHONE = EPHONE;
        this.ENAME = ENAME;
        this.EDOB = EDOB;
        this.EGENDER = EGENDER;
        this.ESPECIALITY = ESPECIALITY;
        this.EADDRESS = EADDRESS;
        this.ESTARTDATE = ESTARTDATE;
        this.DID = DID;
        this.DTITLE = DTITLE;
    }

    public String getEID() {
        return EID;
    }

    public String getEPHONE() {
        return EPHONE;
    }

    public String getEFNAME() {
        return ENAME;
    }

    public String getEDOB() {
        return EDOB;
    }

    public String getEGENDER() {
        return EGENDER;
    }

    public String getESPECIALITY() {
        return ESPECIALITY;
    }

    public String getEADDRESS() {
        return EADDRESS;
    }

    public String getESTARTDATE() {
        return ESTARTDATE;
    }

    public String getDID() {
        return DID;
    }

    public String getDTITLE() {
        return DTITLE;
    }
}
