package com.metalbands.mahmoudfaragallah.band_details;

import com.metalbands.mahmoudfaragallah.model.data_models.BandAlbum;
import com.metalbands.mahmoudfaragallah.model.data_models.BandInfo;

import java.util.List;

/**
 * Created by Mahmoud on 09-12-2017.
 */

public interface BandDetailsContract {

    interface Presenter {
        void getBandDetails();
    }

    interface View {

        String getBandId();

        void showProgressDialog();

        void dismissProgressDialog();

        void noDetailsResults(String bandId);

        void bindBandPhoto(String bandPhotoUrl);

        void bindBandName(String bandName);

        void bindBandInfo(BandInfo bandInfo);

        void bindBandAlbumsList(List<BandAlbum> albums);
    }
}
