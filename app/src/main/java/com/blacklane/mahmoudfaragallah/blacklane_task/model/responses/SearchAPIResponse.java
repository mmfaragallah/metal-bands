package com.blacklane.mahmoudfaragallah.blacklane_task.model.responses;

import com.blacklane.mahmoudfaragallah.blacklane_task.model.data_models.BandSearchData;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Mahmoud on 07-12-2017.
 */

public class SearchAPIResponse extends BaseResponse {

    @SerializedName("data")
    private BandSearchData responseData;

    public BandSearchData getResponseData() {
        return responseData;
    }

    public void setResponseData(BandSearchData responseData) {
        this.responseData = responseData;
    }
}