package com.blacklane.mahmoudfaragallah.blacklane_task.band_details;

import com.blacklane.mahmoudfaragallah.blacklane_task.backend.BandsService;
import com.blacklane.mahmoudfaragallah.blacklane_task.backend.RetrofitHandler;
import com.blacklane.mahmoudfaragallah.blacklane_task.model.data_models.BandDetailsData;
import com.blacklane.mahmoudfaragallah.blacklane_task.model.responses.DetailsAPIResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Mahmoud on 09-12-2017.
 */

public class BandDetailsPresenter implements BandDetailsContract.Presenter {

    //region objects
    private BandDetailsContract.View bandDetailsView;
    //endregion

    //region constructors
    public BandDetailsPresenter(BandDetailsContract.View bandDetailsView) {
        this.bandDetailsView = bandDetailsView;
    }
    //endregion

    //region presenter callbacks
    @Override
    public void getBandById(final String bandId) {

        BandsService bandsService = RetrofitHandler.getInstance().createBandsService();

        Call<DetailsAPIResponse> call = bandsService.getBandDetails(bandId);

        call.enqueue(new Callback<DetailsAPIResponse>() {
            @Override
            public void onResponse(Call<DetailsAPIResponse> call, Response<DetailsAPIResponse> response) {

                boolean hasAResult = false;

                if (response.isSuccessful()) {

                    DetailsAPIResponse resultsBody = response.body();
                    if (resultsBody != null) {

                        BandDetailsData results = resultsBody.getResponseData();

                        hasAResult = true;
                        bandDetailsView.setBandDetailsData(results);
                    }

                    if (!hasAResult) {
                        bandDetailsView.noDetailsResults(bandId);
                    }
                }
            }

            @Override
            public void onFailure(Call<DetailsAPIResponse> call, Throwable t) {
                bandDetailsView.noDetailsResults(bandId);
            }
        });
    }
    //endregion
}
