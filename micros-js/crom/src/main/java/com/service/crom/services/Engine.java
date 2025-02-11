package com.service.crom.services;

import com.service.crom.pojo.EntityRespone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.Date;
import java.util.logging.Logger;

@Service
public class Engine {

    @Autowired
    private RestTemplateBuilder restTemplate;

    @Autowired
    private ParametersServices parametersServices;

//    @Value("${mailsender}")
//    private String serverUrl;



    static final Logger LOGGER = Logger.getLogger(Engine.class.getName());

    @Scheduled(fixedDelay = 60000)
    public void sendMail() throws InterruptedException {
        LOGGER.info("start to set Headers...");

        String serverUrl = parametersServices.findByClave("mailsendersend").getValor();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);
        LOGGER.info("send Mail "+ LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
        ResponseEntity<EntityRespone> result = restTemplate.build().exchange(serverUrl, HttpMethod.GET, requestEntity, EntityRespone.class);
        LOGGER.info("send Mail Status ="+ result.getStatusCodeValue());
        if (result.getStatusCodeValue() == 200){
            EntityRespone response = result.getBody();
            LOGGER.info("send Mail response: "+ response.getEntidades());
        }
    }

    @Scheduled(cron = "${interval-in-cron}")
    public void backup(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmm");
        Date date = new Date();
        String dateCreate = simpleDateFormat.format(date);
        try {
            LOGGER.info("star backup...");

            String nameLicensesservice = "js_licensesservice_"+dateCreate+"_dump.SQL";
            String nameJshandyman = "jshandyman_"+dateCreate+"_dump.SQL";

            String baseLicensesserviceName ="jshandyman";
            String baseJshandymanName = "licensesservice";

            String commandoJshandyman = "mysqldump --user=root --password=admin jshandyman > C:\\Users\\aleja\\Desktop\\base2\\" + nameJshandyman;
            String commandoLicensesservice = "mysqldump --user=root --password=admin licensesservice > C:\\Users\\aleja\\Desktop\\base2\\" + nameLicensesservice;

            String command1 = "cmd /c  start "+ commandoJshandyman;
            Process child = Runtime.getRuntime().exec(command1);

            String command = "cmd /c  start "+ commandoLicensesservice;
            Process child2 = Runtime.getRuntime().exec(command);

            LOGGER.info("End backup..");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
