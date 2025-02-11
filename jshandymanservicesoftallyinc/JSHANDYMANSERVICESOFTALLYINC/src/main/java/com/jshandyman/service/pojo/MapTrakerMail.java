package com.jshandyman.service.pojo;

import java.util.HashMap;

public class MapTrakerMail {

   private HashMap<Long, SendMailTrakerPojo> mapTrakerWork;
   private HashMap<Long, SendMailTrakerPojo> mapTrakerEstimete;


    public MapTrakerMail() {
        this.mapTrakerWork = new HashMap<Long, SendMailTrakerPojo>();
        this.mapTrakerEstimete = new HashMap<Long, SendMailTrakerPojo>();

    }

    public MapTrakerMail(HashMap<Long, SendMailTrakerPojo> newMapTrakerWork, HashMap<Long, SendMailTrakerPojo> newMapTrakerEstimete) {
        this.mapTrakerWork = new HashMap<Long, SendMailTrakerPojo>();
        this.mapTrakerEstimete = new HashMap<Long, SendMailTrakerPojo>();

        this.mapTrakerWork = newMapTrakerWork;
        this.mapTrakerEstimete = newMapTrakerEstimete;
    }


    public void addMapTrakerWork(SendMailTrakerPojo tracker){
        if (tracker.getIdWorkReferene() != null && !this.mapTrakerWork.containsKey(tracker.getIdWorkReferene())) {
            this.mapTrakerWork.put(tracker.getIdWorkReferene(), tracker);
        }
    }


    public void addMapTrakerEstimete(SendMailTrakerPojo tracker){
        if (tracker.getIdEstimateReferene() != null && !this.mapTrakerEstimete.containsKey(tracker.getIdEstimateReferene())) {
            this.mapTrakerEstimete.put(tracker.getIdEstimateReferene(), tracker);
        }
    }


    public HashMap<Long, SendMailTrakerPojo> getMapTrakerWork() {
        return mapTrakerWork;
    }

    public void setMapTrakerWork(HashMap<Long, SendMailTrakerPojo> mapTrakerWork) {
        this.mapTrakerWork = mapTrakerWork;
    }

    public HashMap<Long, SendMailTrakerPojo> getMapTrakerEstimete() {
        return mapTrakerEstimete;
    }

    public void setMapTrakerEstimete(HashMap<Long, SendMailTrakerPojo> mapTrakerEstimete) {
        this.mapTrakerEstimete = mapTrakerEstimete;
    }
}
