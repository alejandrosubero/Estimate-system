package com.jshandyman.service.service;


import com.jshandyman.service.pojo.SearchResponsePojo;

import java.util.Date;

public interface SearchService {

    public SearchResponsePojo findByCreateDayMonthYear(Double month, Double year, String clave);

    public SearchResponsePojo finByCreateDayBetween(Date startDate, Date endDay, String clave);

    public SearchResponsePojo finBySearch(String keyword);

    public SearchResponsePojo finBySearchResponsePojos(String keyword);

}
