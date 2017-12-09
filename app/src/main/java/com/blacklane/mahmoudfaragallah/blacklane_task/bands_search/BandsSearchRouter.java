package com.blacklane.mahmoudfaragallah.blacklane_task.bands_search;

import android.content.Context;
import android.content.Intent;

import com.blacklane.mahmoudfaragallah.blacklane_task.band_details.BandDetailsScreen;

/**
 * Created by Mahmoud on 08-12-2017.
 */

public class BandsSearchRouter implements BandsSearchContract.Router {

    Context context;

    BandsSearchRouter(Context context) {
        this.context = context;
    }

    @Override
    public void goToBandDetailsScreen(String bandId) {

        Intent intent = new Intent(context, BandDetailsScreen.class);
        intent.putExtra(BandDetailsScreen.BAND_ID, bandId);
        context.startActivity(intent);
    }
}
