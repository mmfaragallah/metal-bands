package com.blacklane.mahmoudfaragallah.blacklane_task.band_details;

import com.blacklane.mahmoudfaragallah.blacklane_task.model.data_models.BandDetailsData;

/**
 * Created by Mahmoud on 09-12-2017.
 */

public interface BandDetailsContract {

    interface Presenter {
        void getBandById(String bandId);
    }

    interface View {

        void noDetailsResults(String bandId);

        void setBandDetailsData(BandDetailsData bandDetailsData);
    }
}
