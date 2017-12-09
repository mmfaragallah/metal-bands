package com.blacklane.mahmoudfaragallah.blacklane_task.model.data_models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Mahmoud on 09-12-2017.
 */

public class BandDetailsData {

    @SerializedName("id")
    private String bandId;

    @SerializedName("band_name")
    private String bandName;

    @SerializedName("logo")
    private String bandLogo;

    @SerializedName("photo")
    private String bandPhoto;

    @SerializedName("details")
    private BandInfo bandInfo;

    @SerializedName("discography")
    private List<BandAlbum> albums;

    public String getBandId() {
        return bandId;
    }

    public void setBandId(String bandId) {
        this.bandId = bandId;
    }

    public String getBandName() {
        return bandName;
    }

    public void setBandName(String bandName) {
        this.bandName = bandName;
    }

    public String getBandLogo() {
        return bandLogo;
    }

    public void setBandLogo(String bandLogo) {
        this.bandLogo = bandLogo;
    }

    public String getBandPhoto() {
        return bandPhoto;
    }

    public void setBandPhoto(String bandPhoto) {
        this.bandPhoto = bandPhoto;
    }

    public List<BandAlbum> getAlbums() {
        return albums;
    }

    public void setAlbums(List<BandAlbum> albums) {
        this.albums = albums;
    }

    public BandInfo getBandInfo() {
        return bandInfo;
    }

    public void setBandInfo(BandInfo bandInfo) {
        this.bandInfo = bandInfo;
    }
}