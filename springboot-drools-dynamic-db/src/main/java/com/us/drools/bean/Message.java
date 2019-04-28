package com.us.drools.bean;

import java.io.Serializable;

/**
 * Created by yangyibo on 16/12/8.
 */
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    private String status;
    private String content;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}