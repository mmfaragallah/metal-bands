package com.metalbands.mahmoudfaragallah.model.data_models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mahmoud on 09-12-2017.
 */

public class BandInfo {

    private String genre;
    private String status;
    private String location;

    @SerializedName("formed in")
    private String formedIn;

    @SerializedName("years active")
    private String yearsActive;

    @SerializedName("lyrical themes")
    private String lyricalThemes;

    @SerializedName("country of origin")
    private String countryOfOrigin;
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getFormedIn() {
        return formedIn;
    }

    public void setFormedIn(String formedIn) {
        this.formedIn = formedIn;
    }

    public String getYearsActive() {
        return yearsActive;
    }

    public void setYearsActive(String yearsActive) {
        this.yearsActive = yearsActive;
    }

    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

    public String getLyricalThemes() {
        return lyricalThemes;
    }

    public void setLyricalThemes(String lyricalThemes) {
        this.lyricalThemes = lyricalThemes;
    }
}
