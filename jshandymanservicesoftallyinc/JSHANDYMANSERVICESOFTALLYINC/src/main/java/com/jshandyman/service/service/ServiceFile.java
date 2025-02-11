package com.jshandyman.service.service;

import org.springframework.web.multipart.MultipartFile;

public interface ServiceFile {

    public Object upload(MultipartFile fileUpload, String objeto);
}
