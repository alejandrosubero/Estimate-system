package com.service.licenses.pojo;

import java.io.Serializable;

public class KeyPojo implements Serializable {

    private static final long serialVersionUID = -2276295262673021632L;

    private String key;

    public KeyPojo() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

}
