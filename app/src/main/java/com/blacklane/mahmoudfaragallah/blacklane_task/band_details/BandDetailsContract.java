package com.blacklane.mahmoudfaragallah.blacklane_task.band_details;

import com.blacklane.mahmoudfaragallah.blacklane_task.model.data_models.BandAlbum;
import com.blacklane.mahmoudfaragallah.blacklane_task.model.data_models.BandInfo;

import java.util.List;

/**
 * Created by Mahmoud on 09-12-2017.
 */

public interface BandDetailsContract {

    interface Presenter {
        void getBandById(String bandId);
    }

    interface View {

        void noDetailsResults(String bandId);

        void bindBandPhoto(String bandPhotoUrl);

        void bindBandName(String bandName);

        void bindBandInfo(BandInfo bandInfo);

        void bindBandAlbumsList(List<BandAlbum> albums);
    }
}
