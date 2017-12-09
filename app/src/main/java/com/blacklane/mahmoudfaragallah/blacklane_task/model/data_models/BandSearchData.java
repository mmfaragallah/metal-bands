package com.blacklane.mahmoudfaragallah.blacklane_task.model.data_models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Mahmoud on 08-12-2017.
 */

public class BandSearchData {

    private String query;

    @SerializedName("search_results")
    private List<MetalBand> bands;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public List<MetalBand> getBands() {
        return bands;
    }

    public void setBands(List<MetalBand> bands) {
        this.bands = bands;
    }
}
