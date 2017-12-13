package com.metalbands.mahmoudfaragallah.band_details;

import com.metalbands.mahmoudfaragallah.backend.BandsService;
import com.metalbands.mahmoudfaragallah.backend.RetrofitHandler;
import com.metalbands.mahmoudfaragallah.model.data_models.BandDetailsData;
import com.metalbands.mahmoudfaragallah.model.responses.DetailsAPIResponse;

import java.io.File;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Mahmoud on 09-12-2017.
 */

public class BandDetailsPresenter implements BandDetailsContract.Presenter {

    //region objects
    private File cacheDir;
    private BandDetailsContract.View bandDetailsView;
    //endregion

    //region constructors
    BandDetailsPresenter(BandDetailsContract.View bandDetailsView, File cacheDir) {
        this.cacheDir = cacheDir;
        this.bandDetailsView = bandDetailsView;
    }
    //endregion

    //region presenter callbacks
    @Override
    public void getBandById(final String bandId) {

        bandDetailsView.showProgressDialog();

        BandsService bandsService = RetrofitHandler.getInstance(cacheDir).createBandsService();

        Call<DetailsAPIResponse> call = bandsService.getBandDetails(bandId);

        call.enqueue(new Callback<DetailsAPIResponse>() {
            @Override
            public void onResponse(Call<DetailsAPIResponse> call, Response<DetailsAPIResponse> response) {

                bandDetailsView.dismissProgressDialog();

                boolean hasAResult = false;

                if (response.isSuccessful()) {

                    DetailsAPIResponse resultsBody = response.body();
                    if (resultsBody != null) {

                        BandDetailsData results = resultsBody.getResponseData();

                        hasAResult = true;

                        bandDetailsView.bindBandName(results.getBandName());

                        if (results.getBandInfo() != null) {
                            bandDetailsView.bindBandInfo(results.getBandInfo());
                        }

                        // load the band photo if exists, if not load the band logo if exists
                        if (results.getBandPhoto() != null) {
                            bandDetailsView.bindBandPhoto(results.getBandPhoto());
                        } else if (results.getBandLogo() != null) {
                            bandDetailsView.bindBandPhoto(results.getBandLogo());
                        }

                        if (results.getAlbums() != null && results.getAlbums().size() > 0) {
                            bandDetailsView.bindBandAlbumsList(results.getAlbums());
                        }
                    }

                    if (!hasAResult) {
                        bandDetailsView.noDetailsResults(bandId);
                    }

                } else {
                    bandDetailsView.noDetailsResults(bandId);
                }
            }

            @Override
            public void onFailure(Call<DetailsAPIResponse> call, Throwable t) {

                bandDetailsView.dismissProgressDialog();

                bandDetailsView.noDetailsResults(bandId);
            }
        });
    }
    //endregion
}