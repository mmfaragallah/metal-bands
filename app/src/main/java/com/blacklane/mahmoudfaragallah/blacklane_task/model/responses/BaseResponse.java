package com.blacklane.mahmoudfaragallah.blacklane_task.model.responses;

/**
 * Created by Mahmoud on 09-12-2017.
 */

public class BaseResponse {

    private String status;
    private int code;
    private String message;
    private String hash;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}