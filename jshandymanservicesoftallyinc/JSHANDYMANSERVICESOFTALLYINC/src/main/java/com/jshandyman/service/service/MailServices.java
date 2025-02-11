package com.jshandyman.service.service;



import com.jshandyman.service.pojo.EmailHandyManTallyPojo;


public interface MailServices {
    public Boolean sendMailOfWork( EmailHandyManTallyPojo handyManTallyPojo);
    public Boolean cleanDirectory();
//    public Boolean sendMail( EmailHandyManTallyPojo handyManTallyPojo);
//    public Boolean sendMail(EmailPojo email, String portMail);
//    public Boolean sendMails(List<EmailPojo> email, String portMail);
//    public Boolean sendEmailWork(String codeWork);

}
