package com.service.mail.service;


import com.service.mail.entitys.MailSender;
import com.service.mail.pojo.MailSenderPojo;

import java.util.List;

public interface MailServices {
    public Boolean sendMail( MailSenderPojo mailSender);
    public void eMail(List<MailSenderPojo> listMailSender);

//    public Boolean sendMail( EmailHandyManTallyPojo handyManTallyPojo);
//    public Boolean sendMail(EmailPojo email, String portMail);
//    public Boolean sendMails(List<EmailPojo> email, String portMail);
//    public Boolean sendEmailWork(String codeWork);

}
