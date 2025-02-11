package com.jshandyman.service.serviceImplement;

import com.jshandyman.service.entitys.Subcontractor;
import com.jshandyman.service.mapper.SubcontractorMapper;
import com.jshandyman.service.pojo.SubcontractorPojo;
import com.jshandyman.service.repository.SubcontractorRepository;
import com.jshandyman.service.service.SubcontractorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubcontractorServiceImplemt implements SubcontractorService {

   @Autowired
   private SubcontractorRepository SubcontractorRepository;

   @Autowired
   private SubcontractorMapper subcontractorMapper;


    @Override
    public List<SubcontractorPojo> finBySearch(String keyword) {
        return subcontractorMapper.entityToPojoList(SubcontractorRepository.finBySearch(keyword));
    }


    @Override
    public Boolean saveSubContractor(Subcontractor subcontractor) {
        try {
            SubcontractorRepository.save(subcontractor);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<SubcontractorPojo> allSubContractor() {
        List<SubcontractorPojo> lista = new ArrayList<SubcontractorPojo>();
         SubcontractorRepository.findAll().forEach(subcontractor -> lista.add(subcontractorMapper.entityToPojo(subcontractor)));
        return lista;
    }

    @Override
    public SubcontractorPojo findByCompany(String company) {
        SubcontractorPojo pojo = null;
        try {
            pojo = subcontractorMapper.entityToPojo(SubcontractorRepository.findByCompany(company)) ;
        }catch (Exception e){
            e.printStackTrace();
            return pojo;
        }
        return pojo;
    }

    @Override
    public SubcontractorPojo findByCodeClient(String code) {
        SubcontractorPojo pojo = null;
        try {
            pojo = subcontractorMapper.entityToPojo(SubcontractorRepository.findByCodeClient(code)) ;
        }catch (Exception e){
            e.printStackTrace();
            return pojo;
        }
        return pojo;
    }

    @Override
    public SubcontractorPojo findByMail(String mail) {
        SubcontractorPojo pojo = null;
        try {
            pojo = subcontractorMapper.entityToPojo(SubcontractorRepository.findByMail(mail)) ;
        }catch (Exception e){
            e.printStackTrace();
            return pojo;
        }
        return pojo;
    }

    @Override
    public void deleteSubcontractor(Long id) {
        try {
          SubcontractorRepository.deleteById(id);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
