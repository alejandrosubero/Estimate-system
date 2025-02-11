package com.jshandyman.service.service;

import com.jshandyman.service.entitys.Subcontractor;
import com.jshandyman.service.pojo.SubcontractorPojo;

import java.util.List;

public interface SubcontractorService {

    public List<SubcontractorPojo> finBySearch(String keyword);
    public Boolean saveSubContractor(Subcontractor subcontractor);
    public List<SubcontractorPojo> allSubContractor();
    public SubcontractorPojo findByCompany(String company);
    public SubcontractorPojo findByCodeClient(String code);
    public SubcontractorPojo findByMail(String mail);
    public void deleteSubcontractor(Long id);
}
