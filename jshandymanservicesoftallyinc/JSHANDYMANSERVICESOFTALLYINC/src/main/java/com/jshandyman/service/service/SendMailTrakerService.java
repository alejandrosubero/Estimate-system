package com.jshandyman.service.service;

import com.jshandyman.service.pojo.SendMailTrakerPojo;

import java.util.List;


public interface SendMailTrakerService {

    public Boolean save(SendMailTrakerPojo sendMailTraker);

    public SendMailTrakerPojo findByIdEstimate(Long id);

    public SendMailTrakerPojo findByIdWork(Long id);

    public List<SendMailTrakerPojo> allSendMailTraker(String company);
}
