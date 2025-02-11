package com.jshandyman.service.serviceImplement;

import com.google.gson.Gson;
import com.itextpdf.html2pdf.HtmlConverter;
import com.jshandyman.service.configurations.Constant;
import com.jshandyman.service.entitys.SendMailTraker;
import com.jshandyman.service.entitys.Template;
import com.jshandyman.service.pojo.*;
import com.jshandyman.service.security.EncryptAES;
import com.jshandyman.service.service.*;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.*;


@Service("EmailService")
public class MailServicesImplment implements MailServices {

    protected static final Log logger = LogFactory.getLog(MailServicesImplment.class);

    @Value("${saltAESKey}")
    private String saltAES;

    @Autowired
    private EncryptAES encryptAES;

    @Autowired(required = true)
    private EmailDataConfigService emailDataConfigService;

    @Autowired(required = true)
    private DataJshandyManServicesConfigServiceImplement dataJshandyMan;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private WorkService workService;

    @Autowired
    private TemplateService templateService;

    @Autowired
    private BuildTemplate buildTemplate;

    @Autowired
    private BuildPdfAndFile buildPdfAndFile;

    @Autowired
    private ParametersServices parametersServices;

    @Autowired
    private SendMailTrakerService sendMailTrakerService;

    @Autowired
    private RestTemplateBuilder restTemplate;

//    @Value("${mailsender}")
//    private String serverUrl;

    private static final String MAIL_SERVER = "smtp";
    private static final String SMTP_HOST_NAME = "smtp.gmail.com";
    private static final int SMTP_HOST_PORT = 587;
    private static String separador = java.nio.file.FileSystems.getDefault().getSeparator();
    private List<String> fileToDelete = new ArrayList<String>();


    @Override
    public Boolean cleanDirectory(){

     try {
            File directory = new File(Constant.templateFile+"FILE");
            FileUtils.cleanDirectory(directory);
        } catch (IOException e) {
         logger.info("the directory is clean of files = "+ false);
            e.printStackTrace();
            return false;
        }
        logger.info("the directory is clean of files = "+ true);
     return true;
    }


    @Override
    public Boolean sendMailOfWork(EmailHandyManTallyPojo handyManTallyPojo) {

        List<String> adjuntos = null;
        String port = null;
        Long idEstimate = null;
        Long idWork = null;
        String tem ="";
        String item ="_";
        String folderAndName="";
        String baseUrl ="";
        String baseUrl2 ="";
        Boolean result = true;
        String fileNamePdf = Constant.FILENAMEPDF;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmm");
        Date date = new Date();

        port = parametersServices.findByClave(Constant.MAIL_CODE_PORT).getValor();

       if (port != null){
           handyManTallyPojo.setEmailconfiguration(emailDataConfigService.findByPortAndCompanys(port, handyManTallyPojo.getCompany()));
//           handyManTallyPojo.setEmailconfiguration(emailDataConfigService.findByPort(port));
       }

        handyManTallyPojo.setTemplate(templateService.findBycodeTemplate(Constant.CODE_TEMPLATE, handyManTallyPojo.getCompany()).getTemplate());

       if(handyManTallyPojo.getEstimatePojo() != null && handyManTallyPojo.getEstimatePojo().getIdEstimate() != null){
           idEstimate =  handyManTallyPojo.getEstimatePojo().getIdEstimate();
       }

        if(handyManTallyPojo.getWorkPojo() != null && handyManTallyPojo.getWorkPojo().getIdWork() != null){
            idWork =  handyManTallyPojo.getWorkPojo().getIdWork();
        }

        DataJshandyManServicesConfigPojo jshandyManServicesConfig = dataJshandyMan.getActive(handyManTallyPojo.getCompany());
        handyManTallyPojo.setDataJshandyManServices(jshandyManServicesConfig);

        logger.info("Save sent Message...");
        this.sendMailTrakerService.save(new SendMailTrakerPojo(handyManTallyPojo.getEmail().getTo(),
                        handyManTallyPojo.getEmail().getCc(), handyManTallyPojo.getEmail().getBcc(),
                        idEstimate,idWork, handyManTallyPojo.getCompany()));

        logger.info("Start to build templete for mail...");

        if (handyManTallyPojo.getWorkPojo() != null) {
            fileNamePdf = Constant.FILENAME_PDF_INVOICE;
            folderAndName = handyManTallyPojo.getWorkPojo().getIdWork().toString() +"_"+simpleDateFormat.format(date);
            item = "Invoice"+ item + folderAndName+"_";
            fileNamePdf = item + fileNamePdf;
            handyManTallyPojo.getEmail().getTo().add(handyManTallyPojo.getWorkPojo().getClient().getEmails().get(0).getEmail());
        } else {
            fileNamePdf = Constant.FILENAME_PDF_ESTIMATE ;
            folderAndName = handyManTallyPojo.getEstimatePojo().getIdEstimate().toString()+"_"+simpleDateFormat.format(date);
            item = "Estimate" + item + folderAndName +"_";
            fileNamePdf = item + fileNamePdf;
            handyManTallyPojo.getEmail().getTo().add(handyManTallyPojo.getEstimatePojo().getClient().getEmails().get(0).getEmail());
        }

//        logger.info("baseUrl-1: "+Constant.templateFile+"FILE"+separador);
//        logger.info("baseUrl-2: "+Constant.templateFile2+separador+"FILE"+separador);

        baseUrl = Constant.templateFile+"FILE"+separador+folderAndName+separador;
        baseUrl2 = Constant.templateFile2+separador+"FILE"+separador+folderAndName;

        logger.info("baseUrl-1: "+baseUrl);
        logger.info("baseUrl-2: "+baseUrl2);

        String fileNameHtml = item + Constant.FILENAMEHTML;
        adjuntos = new ArrayList<String>();

        logger.info("add the list for delete...");

         //parar par obtener el template
        logger.info("get Template...");
        tem = buildTemplate.writeTemplate(handyManTallyPojo);
        logger.info("write Template 'build Template'...");
        tem =  buildTemplate.writeTemplateComentsSignature(tem, handyManTallyPojo.getDataJshandyManServices());

        StringBuilder pdrfTemp = new StringBuilder(tem);
        String temNew = pdrfTemp.toString();
        tem = buildTemplate.setComent(tem,handyManTallyPojo);
        logger.info("set Template in handyManTally...");
        handyManTallyPojo.setTemplate(tem);

            try {
                logger.info("PDF build Template and write Template PDF...");
                temNew = temNew.replace("max-width:600px", "max-width:900px");
                temNew = temNew.replace("@Comments@", " ");
                temNew = temNew.replace("@Payments@", " ");
                temNew = temNew.replace("@TotalPayments@", " ");


                String inmagenBase64Temp = templateService.findBycodeTemplate(Constant.ING_TEMPLATE, handyManTallyPojo.getCompany()).getTemplate();
                if (inmagenBase64Temp != null){
                    this.saveBase64StringToFile(inmagenBase64Temp, Constant.pathTemporalFile, "5a9cda.png");
                    String stringCss = "background-image: url(\"https://i.postimg.cc/jS33pZKh/5a9cda4f-00f8-43fe-bb1c-fc28ebb89a40.png\");";
                    String newstringCss = "background-image: url(\""+Constant.pathTemporalFile +"5a9cda.png"+"\");";
                    temNew = temNew.replace(stringCss, newstringCss);
                    fileToDelete.add(Constant.pathTemporalFile +"5a9cda.png");
                }

                String newtempl =temNew;
                logger.info(" build Pdf And File 'create file html...");
                buildPdfAndFile.crearArchivo(baseUrl2, newtempl, fileNameHtml);
                logger.info("building Pdf File ...");
                buildPdfAndFile.buildingPdf(fileNameHtml, fileNamePdf, baseUrl);

                if(handyManTallyPojo.getWorkPojo() != null && handyManTallyPojo.getWorkPojo().getIdWork() != null){
                      String invoicePainFormateTemp = templateService.findBycodeTemplate(Constant.CAR_ING_TEMPLATE, handyManTallyPojo.getCompany()).getTemplate();
                      logger.info("Add Invoice Pain Formate to Template...");
                      invoicePainFormateTemp =  buildTemplate.writeTemplateInvoicePainFormate(invoicePainFormateTemp, handyManTallyPojo);

                    logger.info("Create html pay file...");
                    String FILENAMEHTMLPAY = item+Constant.FILENAMEHTMLPAY;
                    String FILENAMEPDFPAY = item+Constant.FILENAMEPDFPAY;
                    String FILENAMEFINAL  = item + Constant.FILENAMEFINAL;

                    buildPdfAndFile.crearArchivo(baseUrl, invoicePainFormateTemp, FILENAMEHTMLPAY);
//                    fileToDelete.add(FILENAMEHTMLPAY);
                    logger.info("Create PDF pay  file...");

                    buildPdfAndFile.buildingPdf(FILENAMEHTMLPAY, FILENAMEPDFPAY, baseUrl);
//                    fileToDelete.add(FILENAMEPDFPAY);

                    logger.info("Create PDF invoice file...");
                    buildPdfAndFile.mergePdfFile(fileNamePdf,FILENAMEPDFPAY,FILENAMEFINAL,baseUrl);
//                    fileToDelete.add(FILENAMEFINAL);

                    logger.info("add to adjuntos list the PDF invoice file path...");
                    adjuntos.add(baseUrl + FILENAMEFINAL);

                }else{
                    logger.info("'Estimate'-- Add to adjuntos list the PDF invoice file path...");
                    adjuntos.add(baseUrl + fileNamePdf);
                }


//=============== nueva parte al servicio o micro de mail ====================

                Boolean executeSendMail = null;
                Gson gson = new Gson();
                logger.info(" send Handy Man Tally Mail...");

//                MailSender sendMail = new MailSender( port, handyManTallyPojo.getTemplate(),
//                        gson.toJson(handyManTallyPojo.getEmail()),
//                        gson.toJson(adjuntos)
//                );

                List<AttachmentPojo> listAttachments = this.addAdjunto(adjuntos);
                MailSender sendMail = new MailSender(port, handyManTallyPojo.getTemplate(), gson.toJson(handyManTallyPojo.getEmail()), listAttachments, handyManTallyPojo.getCompany());

                try {
                    String serverUrl = parametersServices.findByClave("mailsender").getValor();
                    ResponseEntity<Boolean> respuesta = restTemplate.build().postForEntity(new URI(serverUrl), sendMail, Boolean.class);
                    executeSendMail = respuesta.getBody();
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }

                logger.info("ExecuteSendMail = "+ executeSendMail);

            } catch (IOException e) {
                e.printStackTrace();
            }
//        });
//       newThread.start();
        this.delete( fileToDelete );
        return result;
    }


    private List<AttachmentPojo> addAdjunto(List<String> adjuntosFilepath) throws IOException {

        List<AttachmentPojo> listAttachments = null;
        if(adjuntosFilepath != null && adjuntosFilepath.size() > 0){
            listAttachments = new ArrayList<AttachmentPojo>();

            for (String filepath: adjuntosFilepath) {
                File file = new File(filepath);
                byte [] fileAttachment = Files.readAllBytes(file.toPath());
                AttachmentPojo pojo = new AttachmentPojo(fileAttachment, file.getName());
                listAttachments.add(pojo);
            }
        }
        return listAttachments;
    }


    private Boolean cleanPdf(boolean delete,  List<String> list,  String base){
        logger.info(" Delete-File is = "+delete);
        if(delete){
            list.stream().forEach(itemToDelete -> {
                buildPdfAndFile.deleteFileOrFolder(base + itemToDelete);
            });
        }
        return true;
    }


    public Boolean delete( List<String> fileToDelete ){
        Boolean response = false;
        if(fileToDelete != null && fileToDelete.size() >0){
            for (String paht : fileToDelete ){
                File file = new File(paht);
                if (file.delete()) {
                    logger.info("File deleted successfully...");
                    response = true;
                }
                else {
                    logger.info("Failed to delete the file");
                    response = false;
                }
            }
        }
        return response;
    }

    public static void saveBase64StringToFile(String base64Str, String filePath, String fileName) throws FileNotFoundException, IOException {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            File dir = new File(filePath);
            if (!dir.exists() && dir.isDirectory()) {
                dir.mkdirs();
            }
            file = new File(filePath, fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);

            byte[] bfile = Base64.getDecoder().decode(base64Str);
            bos.write(bfile);
        } catch (FileNotFoundException e) {
            throw e;
        } catch (IOException e) {
            throw e;
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }


    public void Base64EncodePdf() {
        try {
            File file = new File(Constant.templateFile +"5a9cda.png");
            byte [] bytes = Files.readAllBytes(file.toPath());
            String b64 = Base64.getEncoder().encodeToString(bytes);
            Template templateImagen = new Template(b64, true, "imagen", "png", "5a9cda");
            templateService.save2(templateImagen);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        
}

//    public Boolean sendHandyManTallyMail(String code64, List<String> adjuntos, EmailHandyManTallyPojo handyManTallyPojo) {
//
//        try {
//            //---------------------------------------------STEP 0---------------------------------------------
//            String mailCient = "";
//            String pass = handyManTallyPojo.getEmailconfiguration().getMailpassword().trim();
//            String userName = handyManTallyPojo.getEmailconfiguration().getMailusername().trim();
//            String SMTP_HOST_NAME_s = handyManTallyPojo.getEmailconfiguration().getHost().trim();
//            int SMTP_HOST_PORT_s = Integer.parseInt(handyManTallyPojo.getEmailconfiguration().getPort().trim());
//
//            Properties mailProps = System.getProperties();
//            mailProps.setProperty("mail.transport.protocol", MAIL_SERVER);
//            mailProps.put("mail.smtp.host", SMTP_HOST_NAME_s);
//            mailProps.put("mail.smtp.user", userName);
//            mailProps.put("mail.smtp.auth", "true");
//            mailProps.put("mail.smtp.starttls.enable", "true");
//            mailProps.put("mail.smtp.port", SMTP_HOST_PORT_s);
//
//            //---------------------------------------------STEP 1---------------------------------------------
//
//            // creates a new session with an authenticator
//            Authenticator auth = new Authenticator() {
//                public PasswordAuthentication getPasswordAuthentication() {
//                    return new PasswordAuthentication(userName, pass);
//                }
//            };
//
//            Session session = Session.getInstance(mailProps, auth);
//
//            session.setDebug(false);
//            BodyPart messageBodyPart = new MimeBodyPart();
//            MimeMultipart multiPart = new MimeMultipart();
//            messageBodyPart.setContent(handyManTallyPojo.getTemplate(), "text/html; charset=utf-8");
//            multiPart.addBodyPart(messageBodyPart);
//
//            if (code64 != null) {
//                PreencodedMimeBodyPart pmp = new PreencodedMimeBodyPart("base64");
//                pmp.setHeader("Content-ID", "<012345678>");
//                pmp.setDisposition(MimeBodyPart.INLINE);
//                pmp.setText(code64);
//                multiPart.addBodyPart(pmp);
//                MimeBodyPart filePart = new PreencodedMimeBodyPart("base64");
//                filePart.setContent(code64, "plain/text"); //"imagen/*"
//                multiPart.addBodyPart(filePart);
//            }
//
//            if (adjuntos != null) {
//                MimeBodyPart messageBodyPart2 = null;
//                for (String fn : adjuntos) {
//                    FileDataSource source = new FileDataSource(fn);
//                    File file = new File(fn);
//                    messageBodyPart2 = new MimeBodyPart();
//                    messageBodyPart2.setDataHandler(new DataHandler(source));
//                    messageBodyPart2.setFileName(file.getName());
//                    multiPart.addBodyPart(messageBodyPart2);
//                }
//            }
//
//            //---------------------------------------------STEP 2---------------------------------------------
//
//            MimeMessage message = new MimeMessage(session);
//
//            try {
//                // Set From: header field of the header.
//                message.setFrom(new InternetAddress(userName));
//
//                if (handyManTallyPojo.getWorkPojo() != null) {
//                    mailCient = handyManTallyPojo.getWorkPojo().getClient().getEmails().get(0).getEmail();
//                    handyManTallyPojo.getEmail().getTo().add(mailCient);
//                } else {
//                    mailCient = handyManTallyPojo.getEstimatePojo().getClient().getEmails().get(0).getEmail();
//                    handyManTallyPojo.getEmail().getTo().add(mailCient);
//                }
//
//                if (handyManTallyPojo.getEmail().getTo() != null && handyManTallyPojo.getEmail().getTo().size() > 0) {
//                    InternetAddress[] toAddress = new InternetAddress[handyManTallyPojo.getEmail().getTo().size()];
//                    // To get the array of toaddresses
//                    for (int i = 0; i < handyManTallyPojo.getEmail().getTo().size(); i++) {
//                        toAddress[i] = new InternetAddress(handyManTallyPojo.getEmail().getTo().get(i));
//                    }
//                    // Set To: header field of the header.
//                    for (int i = 0; i < toAddress.length; i++) {
//                        message.addRecipient(Message.RecipientType.TO, toAddress[i]);
//                    }
//                }
//
//                if (handyManTallyPojo.getEmail().getCc() != null && handyManTallyPojo.getEmail().getCc().size() > 0) {
//                    InternetAddress[] ccAddress = new InternetAddress[handyManTallyPojo.getEmail().getCc().size()];
//                    // To get the array of ccaddresses
//                    for (int i = 0; i < handyManTallyPojo.getEmail().getCc().size(); i++) {
//                        ccAddress[i] = new InternetAddress(handyManTallyPojo.getEmail().getCc().get(i));
//                    }
//                    // Set cc: header field of the header.
//                    for (int i = 0; i < ccAddress.length; i++) {
//                        message.addRecipient(Message.RecipientType.CC, ccAddress[i]);
//                    }
//                }
//
//                if (handyManTallyPojo.getEmail().getBcc() != null && handyManTallyPojo.getEmail().getBcc().size() > 0) {
//                    InternetAddress[] bccAddress = new InternetAddress[handyManTallyPojo.getEmail().getBcc().size()];
//                    // To get the array of bccaddresses
//                    for (int i = 0; i < handyManTallyPojo.getEmail().getBcc().size(); i++) {
//                        bccAddress[i] = new InternetAddress(handyManTallyPojo.getEmail().getBcc().get(i));
//                    }
//                    // Set bcc: header field of the header.
//                    for (int i = 0; i < bccAddress.length; i++) {
//                        message.addRecipient(Message.RecipientType.BCC, bccAddress[i]);
//                    }
//                }
//
//                // Set Subject: header field
//                message.setSubject(handyManTallyPojo.getEmail().getSubject());
//
//                // Now set the date to actual message
//                message.setSentDate(new Date());
//
//                // Now set the actual message
//                // message.setContent(body,"text/html");
//                message.setContent(multiPart);
//
//                //---------------------------------------------STEP 3---------------------------------------------
//
//                logger.info("\n\n 3rd ===> Get Session and Send Mail");
//                // Send message
//                Transport transport = session.getTransport(MAIL_SERVER);
//                transport.connect(SMTP_HOST_NAME_s, SMTP_HOST_PORT_s, userName, pass);
//                transport.sendMessage(message, message.getAllRecipients());
//                transport.close();
//                logger.info("Sent Message Successfully...");
//
//
//            } catch (AddressException ae) {
//                ae.printStackTrace();
//            } catch (MessagingException me) {
//                me.printStackTrace();
//            }
//
//            return true;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//
//    public Boolean sendHandyManTallyMail2(String code64, List<String> adjuntos, EmailHandyManTallyPojo handyManTallyPojo) {
//
//        try {
//            String mailCient = "";
//            String pass = handyManTallyPojo.getEmailconfiguration().getMailpassword();
//            String userName = handyManTallyPojo.getEmailconfiguration().getMailusername();
//
//            Properties mailProps = System.getProperties();
//            mailProps.setProperty("mail.transport.protocol", "smtp");
//            mailProps.put("mail.smtp.host", handyManTallyPojo.getEmailconfiguration().getHost());
//            mailProps.put("mail.smtp.user", userName);
//            mailProps.put("mail.smtp.auth", "true");
//            mailProps.put("mail.smtp.starttls.enable", "true");
//            mailProps.put("mail.smtp.port", handyManTallyPojo.getEmailconfiguration().getPort());
//
//            Session session = Session.getDefaultInstance(mailProps, null);
//            session.setDebug(false);
//            BodyPart messageBodyPart = new MimeBodyPart();
//            MimeMultipart multiPart = new MimeMultipart();
//            messageBodyPart.setContent(handyManTallyPojo.getTemplate(), "text/html; charset=utf-8");
//            multiPart.addBodyPart(messageBodyPart);
//
//            if (code64 != null) {
//                PreencodedMimeBodyPart pmp = new PreencodedMimeBodyPart("base64");
//                pmp.setHeader("Content-ID", "<012345678>");
//                pmp.setDisposition(MimeBodyPart.INLINE);
//                pmp.setText(code64);
//                multiPart.addBodyPart(pmp);
//                MimeBodyPart filePart = new PreencodedMimeBodyPart("base64");
//                filePart.setContent(code64, "plain/text"); //"imagen/*"
//                multiPart.addBodyPart(filePart);
//            }
//
//            if (adjuntos != null) {
//                MimeBodyPart messageBodyPart2 = null;
//                for (String fn : adjuntos) {
//                    FileDataSource source = new FileDataSource(fn);
//                    File file = new File(fn);
//                    messageBodyPart2 = new MimeBodyPart();
//                    messageBodyPart2.setDataHandler(new DataHandler(source));
//                    messageBodyPart2.setFileName(file.getName());
//                    multiPart.addBodyPart(messageBodyPart2);
//                }
//            }
//
//            MimeMessage message = new MimeMessage(session);
//            message.setFrom(new InternetAddress(handyManTallyPojo.getEmailconfiguration().getMailusername()));
//
//
//            if (handyManTallyPojo.getWorkPojo() != null) {
//                mailCient = handyManTallyPojo.getWorkPojo().getClient().getEmails().get(0).getEmail();
//            } else {
//                mailCient = handyManTallyPojo.getEstimatePojo().getClient().getEmails().get(0).getEmail();
//            }
//
//            message.addRecipient(Message.RecipientType.TO, new InternetAddress(mailCient));
//            message.setSubject(handyManTallyPojo.getSubject());
//            message.setContent(multiPart);
//
//            // Se envia el correo.
//            Transport t = session.getTransport("smtp");
//            t.connect(handyManTallyPojo.getEmailconfiguration().getHost(), userName, pass);
//            t.sendMessage(message, message.getAllRecipients());
//            t.close();
//            logger.info("Send the mail");
//            return true;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }

