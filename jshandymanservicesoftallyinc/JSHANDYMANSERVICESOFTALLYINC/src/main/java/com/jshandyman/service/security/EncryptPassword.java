package com.jshandyman.service.security;


import org.apache.commons.codec.digest.DigestUtils;

import org.springframework.stereotype.Component;

@Component
public class EncryptPassword {

    public EncryptPassword() {
    }

    public  String md5Hex(String password) {
        String EncriptadoConMD5 = DigestUtils.md5Hex(password);
        return EncriptadoConMD5;
    }

    public String sha1Hex(String password) {
        String textoEncriptadoConSHA = DigestUtils.sha1Hex(password);
        return textoEncriptadoConSHA;
    }

    public  String encriptaPassword(String password) {
        return sha1Hex(md5Hex(password));
    }

}
