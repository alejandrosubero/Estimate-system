package com.jshandyman.service.security.logingTrack.services;

import com.jshandyman.service.security.logingTrack.entitys.LogingTrack;
import com.jshandyman.service.security.logingTrack.pojos.LogingTrackPojo;


public interface LogingTrackService {

    public LogingTrackPojo findByCodeWorkAndActive(String codeWork, Boolean active);

    public LogingTrackPojo findByToken(String token);

    public LogingTrackPojo findByTokenAndActive(String token,Boolean active);

    public Boolean deleteLogingTrack(Long idLogingTrack);

    public LogingTrackPojo saveLogingTrack(LogingTrack logingTrack);

}
