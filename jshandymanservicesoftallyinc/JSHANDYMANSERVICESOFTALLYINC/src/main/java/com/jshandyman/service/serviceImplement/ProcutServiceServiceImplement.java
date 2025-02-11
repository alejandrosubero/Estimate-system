
/*
Create on Sun Dec 19 08:48:07 EST 2021
*Copyright (C) 121.
@author alejandro subero
  
@since 11.0
@version1.0.0.0
@version  %I%, %G%
*<p>Description: manejo de facturas y trabajos </p>
*/


package com.jshandyman.service.serviceImplement;

import com.jshandyman.service.entitys.ProcutService;
import com.jshandyman.service.repository.ProcutServiceRepository;
import com.jshandyman.service.service.ProcutServiceService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ProcutServiceServiceImplement implements ProcutServiceService {

    // TODO: BORRAR ESTA ENTIDAD Y TODO LO RELACIONADO A PRODUCTSERVICE

    protected static final Log logger = LogFactory.getLog(ProcutServiceServiceImplement.class);
    @Autowired
    private ProcutServiceRepository procutservicerepository;

    @Override
    public ProcutService findByTypeService(String typeService) {

        logger.info("Starting getProcutService");
        ProcutService procutserviceEntity = new ProcutService();
        Optional<ProcutService> fileOptional1 = procutservicerepository.findByTypeService(typeService);

        if (fileOptional1.isPresent()) {
            try {
                procutserviceEntity = fileOptional1.get();
            } catch (DataAccessException e) {
                logger.error(" ERROR : " + e);
            }
        }
        return procutserviceEntity;
    }


    @Override
    public List<ProcutService> getAllProcutService() {
        logger.info("Get allProyect");
        List<ProcutService> listaProcutService = new ArrayList<ProcutService>();
        procutservicerepository.findAll().forEach(procutservice -> listaProcutService.add(procutservice));
//        for (ProcutService service: procutservicerepository.findAll() ){
//            if(service.getService()){
//                listaProcutService.add(service);
//            }
//        }
        return listaProcutService;
    }


    @Override
    public boolean saveProcutService(ProcutService procutservice) {
        logger.info("Save Proyect");

        try {
            procutservicerepository.save(procutservice);
            return true;
        } catch (DataAccessException e) {
            logger.error(" ERROR : " + e);
            return false;
        }
    }


    @Override
    public boolean deleteProcutService(Long id) {
        logger.info("Delete Proyect");
        boolean clave = false;

        try {
            procutservicerepository.deleteById(id);
            clave = true;
        } catch (DataAccessException e) {
            logger.error(" ERROR : " + e);
            clave = false;
        }
        return clave;
    }


    @Override
    public ProcutService findById(Long id) {
        return procutservicerepository.findById(id).get();
    }


    @Override
    public List<ProcutService> findByTypeServiceContaining(String typeservice) {
        logger.info("Get allProyect");
        List<ProcutService> listaProcutService = new ArrayList<ProcutService>();
        listaProcutService = procutservicerepository.findByTypeServiceContaining(typeservice);
        return listaProcutService;
    }


}



 /*
 Copyright (C) 2008 Google Inc.
* Licensed to the Apache Software Foundation (ASF) under one or more
* contributor license agreements.  See the NOTICE file distributed with
* this work for additional information regarding copyright ownership.
* The ASF licenses this file to You under the Apache License, Version 2.0
* (the "License"); you may not use this file except in compliance with
* the License.  You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/