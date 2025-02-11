package com.jshandyman.service.serviceImplement;

import com.jshandyman.service.entitys.SendMailTraker;
import com.jshandyman.service.mapper.SendMailTrakerMapper;
import com.jshandyman.service.pojo.SendMailTrakerPojo;
import com.jshandyman.service.repository.SendMailTrakerRepository;
import com.jshandyman.service.service.SendMailTrakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SendMailTrakerServiceImplemet implements SendMailTrakerService {

    @Autowired
    private SendMailTrakerRepository repository;

    @Autowired
    private SendMailTrakerMapper sendMailTrakerMapper;


    @Override
    public Boolean save(SendMailTrakerPojo sendMailTrakerPojo) {
        try{
            if (sendMailTrakerPojo != null){
                SendMailTraker entity = sendMailTrakerMapper.pojoToEntity(sendMailTrakerPojo);
                repository.save(entity);
                return true;
            }
            return false;
        }catch (Exception e){
           e.printStackTrace();
            return false;
        }
    }

    @Override
    public SendMailTrakerPojo findByIdEstimate(Long id) {
        return sendMailTrakerMapper.entityToPojo(repository.findByIdEstimateReferene(id));
    }


    @Override
    public SendMailTrakerPojo findByIdWork(Long id) {
        return sendMailTrakerMapper.entityToPojo(repository.findByIdWorkReferene(id));
    }

    @Override
    public List<SendMailTrakerPojo> allSendMailTraker(String company) {
        List<SendMailTrakerPojo> list = new ArrayList<>();
        repository.findByCompany(company).forEach(sendMailTraker ->list.add( sendMailTrakerMapper.entityToPojo(sendMailTraker)));
//        repository.findAll().forEach(sendMailTraker ->list.add( sendMailTrakerMapper.entityToPojo(sendMailTraker)));
        return list;
    }


}
