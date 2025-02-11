package com.jshandyman.service.security.logingTrack.mappers;


import com.jshandyman.service.security.logingTrack.entitys.LogingTrack;
import com.jshandyman.service.security.logingTrack.pojos.LogingTrackPojo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class LogingTrackMapper {


    public LogingTrack pojoToEntity(LogingTrackPojo pojo) {
        ModelMapper modelMapper = new ModelMapper();
        LogingTrack entity = null;

        if (pojo != null) {
            entity = modelMapper.map(pojo, LogingTrack.class);
        }
        return entity;
    }

    public LogingTrackPojo entityToPojo(LogingTrack entity) {
        ModelMapper modelMapper = new ModelMapper();
        LogingTrackPojo pojo = null;

        if (entity != null) {
            pojo = modelMapper.map(entity, LogingTrackPojo.class);
        }
        return pojo;
    }


}
