package com.service.mail.serviceImplement;


import com.google.gson.Gson;
import com.service.mail.configurations.Constant;
import com.service.mail.pojo.Adjuntos;
import com.service.mail.pojo.AttachmentPojo;
import com.service.mail.pojo.EmailsPojo;
import com.service.mail.pojo.MailSenderPojo;
import com.service.mail.repository.MailSenderRepository;
import com.service.mail.security.EncryptAES;
import com.service.mail.service.EmailDataConfigService;
import com.service.mail.service.MailServices;
import com.service.mail.service.ParametersServices;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.TimeUnit;


@Service("EmailService")
public class MailServicesImplment implements MailServices {

    protected static final Log logger = LogFactory.getLog(MailServicesImplment.class);

    @Value("${saltAESKey}")
    private String saltAES;

    @Autowired
    private EncryptAES encryptAES;

    @Autowired(required = true)
    private EmailDataConfigService emailDataConfigService;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private ParametersServices parametersServices;

    @Autowired
    private MailSenderRepository mailsenderrepository;


    private static final String MAIL_SERVER = "smtp";
    private static final String SMTP_HOST_NAME = "smtp.gmail.com";
    private static final int SMTP_HOST_PORT = 587;
    private List<String> fileToDelete = new ArrayList<String>();
    private static String separador = java.nio.file.FileSystems.getDefault().getSeparator();


    @Override
    public void eMail(List<MailSenderPojo> listMailSender) {
        for (MailSenderPojo mailSender: listMailSender) {
            this.sendMail(mailSender);
            this.mailsenderrepository.updateWasSend("S", mailSender.getIdMailSender());
        }
    }


    @Override
    public Boolean sendMail(MailSenderPojo mailSenderPojo) {
        String port = null;
        Boolean executeSendMail = false;
        Gson gson = new Gson();
        port = parametersServices.findByClave(Constant.MAIL_CODE_PORT).getValor();

       if (port != null){
//           mailSenderPojo.setEmailconfiguration(emailDataConfigService.findByPort(port));
           mailSenderPojo.setEmailconfiguration(emailDataConfigService.findByPortAndCompanys(port, mailSenderPojo.getCompany()));
       }

       try {
           EmailsPojo emailsPojo = gson.fromJson(mailSenderPojo.getEmail(), EmailsPojo.class);
           mailSenderPojo.setEmailsPojo(emailsPojo);
           String[] adjuntosArray = gson.fromJson(mailSenderPojo.getAttachments(), String[].class);
           mailSenderPojo.addAdjuntos(adjuntosArray);
           executeSendMail = sendHandyManTallyMail(null, mailSenderPojo);
           logger.info("ExecuteSendMail = "+ executeSendMail);
       } catch (Exception e) {
           e.printStackTrace();
           return executeSendMail;
       }
        return executeSendMail;
    }



    public Boolean sendHandyManTallyMail(String code64, MailSenderPojo handyManTallyPojo) {
         List<String> fileToDelete = new ArrayList<String>();
        long startTime = System.nanoTime();

        try {
            //---------------------------------------------STEP 0---------------------------------------------
            String mailCient = "";
            String pass = handyManTallyPojo.getEmailconfiguration().getMailpassword().trim();
            String userName = handyManTallyPojo.getEmailconfiguration().getMailusername().trim();
            String SMTP_HOST_NAME_s = handyManTallyPojo.getEmailconfiguration().getHost().trim();
            int SMTP_HOST_PORT_s = Integer.parseInt(handyManTallyPojo.getEmailconfiguration().getPort().trim());

            Properties mailProps = System.getProperties();
            mailProps.setProperty("mail.transport.protocol", MAIL_SERVER);
            mailProps.put("mail.smtp.host", SMTP_HOST_NAME_s);
            mailProps.put("mail.smtp.user", userName);
            mailProps.put("mail.smtp.auth", "true");
            mailProps.put("mail.smtp.starttls.enable", "true");
            mailProps.put("mail.smtp.port", SMTP_HOST_PORT_s);

            //---------------------------------------------STEP 1---------------------------------------------

            // creates a new session with an authenticator
            Authenticator auth = new Authenticator() {
                public PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(userName, pass);
                }
            };

            Session session = Session.getInstance(mailProps, auth);

            session.setDebug(false);
            BodyPart messageBodyPart = new MimeBodyPart();
            MimeMultipart multiPart = new MimeMultipart();
            messageBodyPart.setContent(handyManTallyPojo.getTemplate(), "text/html; charset=utf-8");
            multiPart.addBodyPart(messageBodyPart);

            if (code64 != null) {
                PreencodedMimeBodyPart pmp = new PreencodedMimeBodyPart("base64");
                pmp.setHeader("Content-ID", "<012345678>");
                pmp.setDisposition(MimeBodyPart.INLINE);
                pmp.setText(code64);
                multiPart.addBodyPart(pmp);
                MimeBodyPart filePart = new PreencodedMimeBodyPart("base64");
                filePart.setContent(code64, "plain/text"); //"imagen/*"
                multiPart.addBodyPart(filePart);
            }

            if (handyManTallyPojo.getListAttachments() != null && handyManTallyPojo.getListAttachments().size() > 0) {
                MimeBodyPart messageBodyPart2 = null;
                for (AttachmentPojo archivo : handyManTallyPojo.getListAttachments()) {
//                    InputStream targetStream = new ByteArrayInputStream(archivo.getFileAttachment());
//                    messageBodyPart2 = new MimeBodyPart();
//                    messageBodyPart2.setDataHandler(new DataHandler(new InputStreamDataSource(targetStream)));
//                    messageBodyPart2.setFileName(archivo.getFileName());
//                    multiPart.addBodyPart(messageBodyPart2);

                    byte[] bytes = archivo.getFileAttachment();
                    String archivoName = archivo.getFileName();
                    Path filePath = Paths.get(archivoName);
                    Files.write(filePath, bytes);

                    FileDataSource source = new FileDataSource(archivoName);
                    File file = new File(archivoName);
                    messageBodyPart2 = new MimeBodyPart();
                    messageBodyPart2.setDataHandler(new DataHandler(source));
                    messageBodyPart2.setFileName(file.getName());
                    multiPart.addBodyPart(messageBodyPart2);
                    fileToDelete.add(archivoName);
                }
            }

//            if (handyManTallyPojo.getAdjuntos() != null && handyManTallyPojo.getAdjuntos().size() > 0) {
//                MimeBodyPart messageBodyPart2 = null;
//                for (String fn : handyManTallyPojo.getAdjuntos()) {
//                    FileDataSource source = new FileDataSource(fn);
//                    File file = new File(fn);
//                    messageBodyPart2 = new MimeBodyPart();
//                    messageBodyPart2.setDataHandler(new DataHandler(source));
//                    messageBodyPart2.setFileName(file.getName());
//                    multiPart.addBodyPart(messageBodyPart2);
//                }
//            }

            //---------------------------------------------STEP 2---------------------------------------------

            MimeMessage message = new MimeMessage(session);

            try {
                // Set From: header field of the header.
                message.setFrom(new InternetAddress(userName));

                if (handyManTallyPojo.getEmailsPojo().getTo() != null && handyManTallyPojo.getEmailsPojo().getTo().size() > 0) {
                    InternetAddress[] toAddress = new InternetAddress[handyManTallyPojo.getEmailsPojo().getTo().size()];
                    // To get the array of toaddresses
                    for (int i = 0; i < handyManTallyPojo.getEmailsPojo().getTo().size(); i++) {
                        toAddress[i] = new InternetAddress(handyManTallyPojo.getEmailsPojo().getTo().get(i));
                    }
                    // Set To: header field of the header.
                    for (int i = 0; i < toAddress.length; i++) {
                        message.addRecipient(Message.RecipientType.TO, toAddress[i]);
                    }
                }

                if (handyManTallyPojo.getEmailsPojo().getCc() != null && handyManTallyPojo.getEmailsPojo().getCc().size() > 0) {
                    InternetAddress[] ccAddress = new InternetAddress[handyManTallyPojo.getEmailsPojo().getCc().size()];
                    // To get the array of ccaddresses
                    for (int i = 0; i < handyManTallyPojo.getEmailsPojo().getCc().size(); i++) {
                        ccAddress[i] = new InternetAddress(handyManTallyPojo.getEmailsPojo().getCc().get(i));
                    }
                    // Set cc: header field of the header.
                    for (int i = 0; i < ccAddress.length; i++) {
                        message.addRecipient(Message.RecipientType.CC, ccAddress[i]);
                    }
                }

                if (handyManTallyPojo.getEmailsPojo().getBcc() != null && handyManTallyPojo.getEmailsPojo().getBcc().size() > 0) {
                    InternetAddress[] bccAddress = new InternetAddress[handyManTallyPojo.getEmailsPojo().getBcc().size()];
                    // To get the array of bccaddresses
                    for (int i = 0; i < handyManTallyPojo.getEmailsPojo().getBcc().size(); i++) {
                        bccAddress[i] = new InternetAddress(handyManTallyPojo.getEmailsPojo().getBcc().get(i));
                    }
                    // Set bcc: header field of the header.
                    for (int i = 0; i < bccAddress.length; i++) {
                        message.addRecipient(Message.RecipientType.BCC, bccAddress[i]);
                    }
                }

                // Set Subject: header field
                message.setSubject(handyManTallyPojo.getEmailsPojo().getSubject());
                // Now set the date to actual message
                message.setSentDate(new Date());
                // Now set the actual message
                // message.setContent(body,"text/html");
                message.setContent(multiPart);

                //---------------------------------------------STEP 3---------------------------------------------

                logger.info("\n\n 3rd ===> Get Session and Send Mail");
                // Send message
                Transport transport = session.getTransport(MAIL_SERVER);
                transport.connect(SMTP_HOST_NAME_s, SMTP_HOST_PORT_s, userName, pass);
                transport.sendMessage(message, message.getAllRecipients());
                transport.close();
                long endTime = System.nanoTime() - startTime;
                long convert = TimeUnit.SECONDS.convert(endTime, TimeUnit.NANOSECONDS);
                Boolean isdelete = delete( fileToDelete );
                logger.info("Sent Message Successfully...");
                logger.info("In: nanoTime = "+ convert+" seconds...");
                logger.info("File is deleted = "+isdelete);
            } catch (AddressException ae) {
                ae.printStackTrace();
                logger.error(ae);
                return false;
            } catch (MessagingException me) {
                me.printStackTrace();
                logger.error(me);
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
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
//            //TODO: AK ESTAN DONDE SE SETEA LOS MAIL A LOS CUALES SE ENVIAN LOS CORREOS
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


//    public void crearArchivo(String direccion, String escrito, String nombreArchivo) {
//
//        logger.info(" start build html template");
//
//        String carpetas = direccion;
//        String archivos = java.nio.file.FileSystems.getDefault().getSeparator() + nombreArchivo;
//        String contenido1 = escrito;
//
//        File create_carpeta = new File(carpetas);
//        File create_archivo = new File(carpetas + archivos);
//
//        if (create_archivo.exists()) {
//            logger.info("THE File EXISTS");
//            deleteFileOrFolder(carpetas + archivos);
//            try {
//                if (create_archivo.createNewFile()) {
//                    FileWriter fw = new FileWriter(create_archivo);
//                    BufferedWriter bw = new BufferedWriter(fw);
//                    bw.write(contenido1);
//                    bw.close();
//                    logger.info("THE FILE WAS CREATED");
//                } else {
//                    logger.info("THE FILE WAS NOT CREATED");
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        } else {
//            logger.info("THE File DOES NOT EXIST IT WILL BE CREATED");
//            create_carpeta.mkdirs();
//            try {
//                if (create_archivo.createNewFile()) {
//                    FileWriter fw = new FileWriter(create_archivo);
//                    BufferedWriter bw = new BufferedWriter(fw);
//                    bw.write(contenido1);
//                    bw.close();
//                    logger.info("THE FILE WAS CREATED");
//                } else {
//                    logger.info("THE FILE WAS NOT CREATED");
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }


//    public void deleteFileOrFolder(String directorio) {
//        File f = new File(directorio);
//        this.delete(f);
//    }

//    private void delete(File fileDel) {
//        if (fileDel.isDirectory()) {
//            if (fileDel.list().length == 0) {
//                fileDel.delete();
//            } else {
//                for (String temp : fileDel.list()) {
//                    File fileDelete = new File(fileDel, temp);
//                    delete(fileDelete);
//                }
//                if (fileDel.list().length == 0) {
//                    fileDel.delete();
//                }
//            }
//        } else {
//            fileDel.delete();
//        }
//    }


//    public static void buildingPdf(String imput, String Ouput) throws FileNotFoundException, IOException {
//        FileInputStream html = new FileInputStream(Constant.templateFile + imput);
//        FileOutputStream pdf = new FileOutputStream(Constant.templateFile + Ouput);
//        HtmlConverter.convertToPdf(html, pdf);
//        html.close();
//        pdf.close();
//        System.out.println("PDF Created!");
//    }


//    public String base64EncodePdf(String archivo) {
//        String b64 = "";
//        try {
//            File file = new File(Constant.templateFile + archivo);
//            byte[] bytes = Files.readAllBytes(file.toPath());
//            b64 = Base64.getEncoder().encodeToString(bytes);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//        return b64;
//    }


}

//    @Override
//    public Boolean sendMail(EmailHandyManTallyPojo handyManTallyPojo) {
//
//        String[] to = { "xxxx@gmail.com" }; // list of recipient email addresses
//        String[] cc={ "xxxxt@gmail.com" };
//        String[] bcc={ "sxxxx@gmail.com" };
//        String subject = "Java Send Mail Attachement Example";
//        String body = "Welcome to Java Mail!<h1>Hello</h1>";
//
//        try {
//            sendFromGMail(to, cc, bcc, subject, body);
//            System.out.println("Email Sent....!");
//        } catch (Exception ex) {
//            System.out.println("Could not send email....!");
//            ex.printStackTrace();
//        }
//
//        return null;
//    }
//
//    private static void sendFromGMail(String[] to, String[] cc, String[] bcc, String subject, String body) {
//
////---------------------------------------------STEP 1---------------------------------------------
//        String USER_NAME ="test";
//        String PASSWORD ="test";
//
//        System.out.println("\n 1st ===> Setup SMTP Mail Server Properties..!");
//
//        // Get system properties
//        Properties properties = System.getProperties();
//
//        // Setup mail server
//        properties.put("mail.smtp.starttls.enable", "true");
//        properties.put("mail.smtp.host", SMTP_HOST_NAME);
//        properties.put("mail.smtp.user", USER_NAME);
//        properties.put("mail.smtp.password", PASSWORD);
//        properties.put("mail.smtp.port", SMTP_HOST_PORT);
//        properties.put("mail.smtp.auth", "true");
//
//        //---------------------------------------------STEP 2---------------------------------------------
//
//
//        System.out.println("\n\n 2nd ===> Get Mail Session..");
//        // Get the Session object.
//
//        // creates a new session with an authenticator
//        Authenticator auth = new Authenticator() {
//            public PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(USER_NAME, PASSWORD);
//            }
//        };
//
//        Session session = Session.getInstance(properties, auth);
//
//        // Create a default MimeMessage object.
//        MimeMessage message = new MimeMessage(session);
//
//        try {
//
//            //---------------------------------------------
//
//            // Set From: header field of the header.
//            message.setFrom(new InternetAddress(USER_NAME));
//
//            //---------------------------------------------
//
//            InternetAddress[] toAddress = new InternetAddress[to.length];
//
//            // To get the array of toaddresses
//            for( int i = 0; i < to.length; i++ ) {
//                toAddress[i] = new InternetAddress(to[i]);
//            }
//
//            // Set To: header field of the header.
//            for( int i = 0; i < toAddress.length; i++) {
//                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
//            }
//
//            InternetAddress[] ccAddress = new InternetAddress[cc.length];
//
//            // To get the array of ccaddresses
//            for( int i = 0; i < cc.length; i++ ) {
//                ccAddress[i] = new InternetAddress(cc[i]);
//            }
//
//            // Set cc: header field of the header.
//            for( int i = 0; i < ccAddress.length; i++) {
//                message.addRecipient(Message.RecipientType.CC, ccAddress[i]);
//            }
//
//            InternetAddress[] bccAddress = new InternetAddress[bcc.length];
//
//            // To get the array of bccaddresses
//            for( int i = 0; i < bcc.length; i++ ) {
//                bccAddress[i] = new InternetAddress(bcc[i]);
//            }
//
//            // Set bcc: header field of the header.
//            for( int i = 0; i < bccAddress.length; i++) {
//                message.addRecipient(Message.RecipientType.BCC, bccAddress[i]);
//            }
//
//            // Set Subject: header field
//            message.setSubject(subject);
//
//            // Now set the date to actual message
//            message.setSentDate(new Date());
//
//            // Now set the actual message
//            message.setContent(body,"text/html");
//
//
//            //---------------------------------------------STEP 3---------------------------------------------
//
//            System.out.println("\n\n 3rd ===> Get Session and Send Mail");
//            // Send message
//            Transport transport = session.getTransport(MAIL_SERVER);
//            transport.connect(SMTP_HOST_NAME, SMTP_HOST_PORT, USER_NAME, PASSWORD);
//            transport.sendMessage(message, message.getAllRecipients());
//            transport.close();
//            System.out.println("Sent Message Successfully....");
//        }
//        catch (AddressException ae) {
//            ae.printStackTrace();
//        }
//        catch (MessagingException me) {
//            me.printStackTrace();
//        }
//
//        //---------------------------------------------------------------------------------------------------
//
//    }


//    @Override
//    public Boolean sendEmailWork(String codeWork) {
//        Boolean result= false;
//        EmailPojo email = new EmailPojo();
//        String portMail = "25";
//
//        try{
//            WorkPojo work = workService.findByCodeWork(codeWork);
//
//                EmailDataConfigPojo config = null;
//                config = emailDataConfigService.findByPort(portMail);
//                email.setTo(work.getClient().getEmails().get(0).getEmail());
//                email.setFrom(config.getMailusername());
//                email.setSubject("Email of test of handyMan");
//                email.setTemplate("code-1");
//
//
//                String text = templateService.findBycodeTemplate(email.getTemplate()).getTemplate();
//                send(null, null, email.getTo(), email.getSubject(), email.getFrom(), text);
//
//            result = true;
//            System.out.println("Fin del envio del correo ......");
//        }catch (Exception e){
//            e.printStackTrace();
//            return result;
//        }
//        return result;
//    }


//    public void send(String code64, List<String> adjuntos, String to, String subject, String from, String contenido) {
//
//        try {
////            envia = "novedadesbpm";
////            pass = "kkk987ha!";
////            String email = emailRepository.findByCodigo();
////            Properties mailProps = new Properties();
////            mailProps.setProperty("mail.transport.protocol", "smtp");
////            mailProps.setProperty("mail.smtp.host", "172.16.3.61");
////            mailProps.setProperty("mail.smtp.starttls.enable", "true");
////            mailProps.setProperty("mail.smtp.port", "25");
////            mailProps.setProperty("mail.smtp.user", "novedadesbpm");
////            mailProps.setProperty("mail.smtp.auth", "false");
////            mailProps.setProperty("mail.debug", "false");
//
//
//            EmailDataConfigPojo config = emailDataConfigService.findByPort("25");
//            String pass = config.getMailpassword();
//            String userName = config.getMailusername();
//
//            Properties mailProps = System.getProperties();
//            mailProps.setProperty("mail.transport.protocol", "smtp");
//            mailProps.put("mail.smtp.host", config.getHost());
//            mailProps.put("mail.smtp.user", userName);
//            mailProps.put("mail.smtp.auth", "true");
//            mailProps.put("mail.smtp.starttls.enable", "true");
//            mailProps.put("mail.smtp.port", config.getPort());
//
//            Session session = Session.getDefaultInstance(mailProps, null);
//            session.setDebug(false);
//
//            // Se compone la parte del texto ////
//
//            BodyPart messageBodyPart = new MimeBodyPart();
//            MimeMultipart multiPart = new MimeMultipart();
//            messageBodyPart.setContent(contenido, "text/html; charset=utf-8");
//            multiPart.addBodyPart(messageBodyPart);
//
//            if (code64 != null) {
//                PreencodedMimeBodyPart pmp = new PreencodedMimeBodyPart("base64");
//                pmp.setHeader("Content-ID", "<012345678>");
//                pmp.setDisposition(MimeBodyPart.INLINE);
//                pmp.setText(code64);
//                multiPart.addBodyPart(pmp);
//                MimeBodyPart filePart = new PreencodedMimeBodyPart("base64");
//                filePart.setContent(code64, "image/*");
//                multiPart.addBodyPart(filePart);
//            }
//
//            if ("notificacion".equals(code64)){
//
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
//            // Se compone el correo, dando to, from, subject y el contenido.
//            MimeMessage message = new MimeMessage(session);
//            message.setFrom(new InternetAddress(from));
//            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
//            message.setSubject(subject);
//            message.setContent(multiPart);
//
//            // Se envia el correo.
//            Transport t = session.getTransport("smtp");
//            t.connect(config.getHost(), userName, pass);
//            // t.connect();
//            t.sendMessage(message, message.getAllRecipients());
//            t.close();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }


//    @Override
//    public Boolean sendMails(List<EmailPojo> emails, String portMail) {
//        int countSendMails = 0;
//        for (EmailPojo emailPojo : emails) {
//            this.sendMail(emailPojo, portMail);
//            countSendMails++;
//        }
//        if (emails.size() == countSendMails) {
//            return true;
//        }
//        return false;
//    }

//    @Override
//    public Boolean sendMail(EmailPojo email, String portMail) {
//        boolean valor = false;
//        EmailDataConfigPojo config = null;
//        config = emailDataConfigService.findByPort(portMail);
//
//        String pass = encryptAES.decrypt(config.getMailpassword(), saltAES);
//        String userName = encryptAES.decrypt(config.getMailusername(), saltAES);
//
//        Properties props = System.getProperties();
//        props.put("mail.smtp.host", config.getHost());
//        props.put("mail.smtp.user", userName);
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.smtp.port", config.getPort());
//        Session session = Session.getDefaultInstance(props);
//        MimeMessage message = new MimeMessage(session);
//
//        try {
//            message.setFrom(new InternetAddress(email.getFrom()));
//            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email.getTo()));
//            message.setSubject(email.getSubject());
//            message.setText(email.getContent());
//            Transport transport = session.getTransport("smtp");
//            transport.connect(config.getHost(), userName, pass);
//            transport.sendMessage(message, message.getAllRecipients());
//            transport.close();
//            valor = true;
//        } catch (MessagingException me) {
//            me.printStackTrace();
//        }
//        return valor;
//    }