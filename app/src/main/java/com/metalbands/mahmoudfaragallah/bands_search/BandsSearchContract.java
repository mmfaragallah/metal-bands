package com.metalbands.mahmoudfaragallah.bands_search;

import com.metalbands.mahmoudfaragallah.model.data_models.MetalBand;

import java.util.List;

/**
 * Created by Mahmoud on 08-12-2017.
 */

public interface BandsSearchContract {

    interface Presenter {

        void searchBands(String query);

        void checkLatestSearchedQuery();
    }

    interface View {

        void onBandClick(String bandId);

        void noSearchResults(String query);

        void updateSearchViewText(String query);

        void setBandsList(List<MetalBand> bands, String forQuery);
    }

    interface Router {
        void goToBandDetailsScreen(String bandId);
    }
}
