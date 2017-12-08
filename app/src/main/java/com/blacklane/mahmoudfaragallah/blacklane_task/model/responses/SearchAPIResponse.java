package com.blacklane.mahmoudfaragallah.blacklane_task.model.responses;

import com.blacklane.mahmoudfaragallah.blacklane_task.model.SearchData;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Mahmoud on 07-12-2017.
 */

public class SearchAPIResponse {

    private String status;
    private int code;
    private String message;
    private String hash;

    @SerializedName("data")
    private SearchData results;

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

    public SearchData getResults() {
        return results;
    }

    public void setResults(SearchData results) {
        this.results = results;
    }
}