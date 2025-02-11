package com.jshandyman.service.service;

import com.jshandyman.service.pojo.DashboardDataPojo;
import com.jshandyman.service.pojo.MapTrakerMail;

public interface DashboardDataService {

    public DashboardDataPojo getStatistics(String company);
    public MapTrakerMail trakerSendMails(String company);

}
