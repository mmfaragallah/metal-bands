package com.blacklane.mahmoudfaragallah.blacklane_task.bands_search;

import com.blacklane.mahmoudfaragallah.blacklane_task.model.MetalBand;

import java.util.List;

/**
 * Created by Mahmoud on 08-12-2017.
 */

public class BandsSearchContract {

    interface Presenter {
        void searchBands(String query);
    }

    interface View {
        void setBandsList(List<MetalBand> bands);
    }
}
