package com.blacklane.mahmoudfaragallah.blacklane_task.bands_search;

import com.blacklane.mahmoudfaragallah.blacklane_task.model.data_models.MetalBand;
import com.blacklane.mahmoudfaragallah.blacklane_task.model.responses.SearchAPIResponse;

import java.util.List;

import retrofit2.Call;

/**
 * Created by Mahmoud on 08-12-2017.
 */

public interface BandsSearchContract {

    interface Presenter {
        Call<SearchAPIResponse> searchBands(String query);
    }

    interface View {

        void onBandClick(String bandId);

        void noSearchResults(String query);

        void setBandsList(List<MetalBand> bands);
    }

    interface Router {
        void goToBandDetailsScreen(String bandId);
    }
}
