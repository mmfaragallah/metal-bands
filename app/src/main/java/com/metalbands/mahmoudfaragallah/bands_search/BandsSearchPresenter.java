package com.metalbands.mahmoudfaragallah.bands_search;

import com.metalbands.mahmoudfaragallah.backend.BandsService;
import com.metalbands.mahmoudfaragallah.backend.RetrofitHandler;
import com.metalbands.mahmoudfaragallah.model.data_models.BandSearchData;
import com.metalbands.mahmoudfaragallah.model.data_models.MetalBand;
import com.metalbands.mahmoudfaragallah.model.responses.SearchAPIResponse;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Mahmoud on 08-12-2017.
 */

public class BandsSearchPresenter implements BandsSearchContract.Presenter {

    //region objects
    private File cacheDir;
    private BandsSearchContract.View bandsListView;
    //endregion

    //region constructors
    public BandsSearchPresenter(BandsSearchContract.View bandsListView, File cacheDir) {
        this.cacheDir = cacheDir;
        this.bandsListView = bandsListView;
    }
    //endregion

    //region presenter callbacks
    @Override
    public Call<SearchAPIResponse> searchBands(final String query) {

        BandsService bandsService = RetrofitHandler.getInstance(cacheDir).createBandsService();

        Call<SearchAPIResponse> call = bandsService.bandsSearch(query);

        call.enqueue(new Callback<SearchAPIResponse>() {
            @Override
            public void onResponse(Call<SearchAPIResponse> call, Response<SearchAPIResponse> response) {

                boolean hasAResult = false;

                if (response.isSuccessful()) {

                    SearchAPIResponse resultsBody = response.body();
                    if (resultsBody != null) {
                        BandSearchData results = resultsBody.getResponseData();
                        if (results != null) {
                            List<MetalBand> bands = results.getBands();
                            if (bands != null && bands.size() > 0) {
                                hasAResult = true;
                                bandsListView.setBandsList(bands, query);
                            }
                        }
                    }

                    // in case of no results, clear the previous results
                    if (!hasAResult) {
//                        bandsListView.noSearchResults(query);
                        bandsListView.setBandsList(new ArrayList<MetalBand>(), query);
                    }

                } else {
                    bandsListView.noSearchResults(query);
                }
            }

            @Override
            public void onFailure(Call<SearchAPIResponse> call, Throwable t) {
                bandsListView.noSearchResults(query);
            }
        });

        return call;
    }
    //endregion
}
