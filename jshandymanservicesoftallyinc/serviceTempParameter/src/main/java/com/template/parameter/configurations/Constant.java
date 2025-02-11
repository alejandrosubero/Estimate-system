package com.template.parameter.configurations;

public class Constant {

    private static String separador = java.nio.file.FileSystems.getDefault().getSeparator();
    private static String workDir = System.getProperty("user.dir");

    //public static String template = ".//src//main//resources//files//";

   // /Users/alejandro/Documents/tomcat/apache-tomcat-9.0.64/webapps/jshandyman/WEB-INF/classes/templates
//    public static String templateFile = workDir+separador+"WEB-INF"+separador+"classes"+separador+"templates"+separador;
//    public static String templateFile2 = workDir+separador+"WEB-INF"+separador+"classes"+separador+"templates";

        public static String templateFile = workDir+separador+"src"+ separador+"main"+separador+"resources"+separador+"templates"+separador;
    public static String templateFile2 = workDir+separador+"src"+ separador+"main"+separador+"resources"+separador+"templates";

    public static final String TAXES = "taxes";
    public static final String CODETEMPLATE = "code-1";
    public static final String TEMPLATENAME = "templete1.html";
    public static final String FILENAMEHTML = "templeteSending.html";
    public static final String FILENAMEPDF = "TallyHandyManInvoice.pdf";

    public static final String FILENAMEFINAL = "TallyHandyMan.pdf";
    public static final String FILENAMEDOCINVOICE = "TallyHandyManDocInvoice.pdf";

    public static final String FILENAMEHTMLPAY = "templeteSendingPay.html";
    public static final String FILESIGNATURE = "Signature.html";
    public static final String FILESIGNATUREPDF = "Signature.pdf";
    public static final String FILENAMEPDFPAY = "TallyHandyManPay.pdf";
    public static final String FILENAME_PDF_INVOICE = "TallyHandyMan_invoice.pdf";
    public static final String FILENAME_PDF_ESTIMATE = "TallyHandyMan_Estimate.pdf";
    public static final String MAIL_CODE_PORT = "MAIL_CODE_PORT";
    public static final String CREATE ="create";

    public static final String APPROVED ="APPROVED";
    public static final String CANCELED ="CANCELED";
    public static final String IN_PROGRESS ="IN PROGRESS";
    public static final String SEND = "SEND";
    public static final String FINALIZED = "FINALIZED";
    public static final String PAUSE = "PAUSE";
    public static final String ESTIMATE = "ESTIMATE";
    public static final String WORK = "WORK";


}


