package com.metalbands.mahmoudfaragallah.bands_search;

import android.content.Context;
import android.content.Intent;

import com.metalbands.mahmoudfaragallah.band_details.BandDetailsPresenter;
import com.metalbands.mahmoudfaragallah.band_details.BandDetailsScreen;

/**
 * Created by Mahmoud on 08-12-2017.
 */

public class BandsSearchRouter implements BandsSearchContract.Router {

    //region objects
    private Context context;
    //endregion

    //region constructors
    BandsSearchRouter(Context context) {
        this.context = context;
    }
    //endregion

    //region Router callbacks
    @Override
    public void goToBandDetailsScreen(String bandId) {

        Intent intent = new Intent(context, BandDetailsScreen.class);
        intent.putExtra(BandDetailsPresenter.BAND_ID, bandId);
        context.startActivity(intent);
    }
    //endregion
}