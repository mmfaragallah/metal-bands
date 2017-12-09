package com.blacklane.mahmoudfaragallah.blacklane_task.model.responses;

import com.blacklane.mahmoudfaragallah.blacklane_task.model.data_models.BandDetailsData;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Mahmoud on 09-12-2017.
 */

public class DetailsAPIResponse extends BaseResponse {

    @SerializedName("data")
    private BandDetailsData responseData;

    public BandDetailsData getResponseData() {
        return responseData;
    }

    public void setResponseData(BandDetailsData responseData) {
        this.responseData = responseData;
    }
}