package com.jshandyman.service.controller;

import com.jshandyman.service.service.ServiceFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.text.ParseException;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/secure")
public class FileServiceController {

    @Autowired
    private ServiceFile serviceFile;

    @PostMapping("file")
    public ResponseEntity<?> subirArchivo(@RequestParam("fileUpload") MultipartFile fileUpload, String objeto) throws IOException, ParseException {
      Object pojoResponse  = serviceFile.upload(fileUpload, objeto);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }



}
