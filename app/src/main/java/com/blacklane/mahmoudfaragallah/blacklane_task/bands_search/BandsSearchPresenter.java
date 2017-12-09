package com.blacklane.mahmoudfaragallah.blacklane_task.bands_search;

import com.blacklane.mahmoudfaragallah.blacklane_task.backend.BandsService;
import com.blacklane.mahmoudfaragallah.blacklane_task.backend.RetrofitHandler;
import com.blacklane.mahmoudfaragallah.blacklane_task.model.data_models.BandSearchData;
import com.blacklane.mahmoudfaragallah.blacklane_task.model.data_models.MetalBand;
import com.blacklane.mahmoudfaragallah.blacklane_task.model.responses.SearchAPIResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Mahmoud on 08-12-2017.
 */

public class BandsSearchPresenter implements BandsSearchContract.Presenter {

    //region objects
    private BandsSearchContract.View bandsListView;
    //endregion

    //region constructors
    public BandsSearchPresenter(BandsSearchContract.View bandsListView) {
        this.bandsListView = bandsListView;
    }
    //endregion

    //region presenter callbacks
    @Override
    public void searchBands(final String query) {

        BandsService bandsService = RetrofitHandler.getInstance().createBandsService();

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
                                bandsListView.setBandsList(bands);
                            }
                        }
                    }

                    if (!hasAResult) {
                        bandsListView.noSearchResults(query);
                    }
                }
            }

            @Override
            public void onFailure(Call<SearchAPIResponse> call, Throwable t) {
                bandsListView.noSearchResults(query);
            }
        });
    }
    //endregion
}
