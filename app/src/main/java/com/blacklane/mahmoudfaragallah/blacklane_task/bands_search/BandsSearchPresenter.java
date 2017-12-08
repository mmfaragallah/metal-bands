package com.blacklane.mahmoudfaragallah.blacklane_task.bands_search;

import com.blacklane.mahmoudfaragallah.blacklane_task.backend.BandsService;
import com.blacklane.mahmoudfaragallah.blacklane_task.backend.RetrofitHandler;
import com.blacklane.mahmoudfaragallah.blacklane_task.model.MetalBand;
import com.blacklane.mahmoudfaragallah.blacklane_task.model.SearchData;
import com.blacklane.mahmoudfaragallah.blacklane_task.model.responses.SearchAPIResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Mahmoud on 08-12-2017.
 */

public class BandsSearchPresenter implements BandsSearchContract.Presenter {

    private BandsSearchContract.View bandsListView;

    public BandsSearchPresenter(BandsSearchContract.View bandsListView) {
        this.bandsListView = bandsListView;
    }

    @Override
    public void searchBands(String query) {

        BandsService bandsService = RetrofitHandler.getInstance().createBandsService();

        Call<SearchAPIResponse> call = bandsService.bandsSearch(query);

        call.enqueue(new Callback<SearchAPIResponse>() {
            @Override
            public void onResponse(Call<SearchAPIResponse> call, Response<SearchAPIResponse> response) {

                if (response.isSuccessful()) {

                    SearchAPIResponse resultsBody = response.body();
                    if (resultsBody != null) {
                        SearchData results = resultsBody.getResults();
                        if (results != null) {
                            List<MetalBand> bands = results.getBands();
                            if (bands != null && bands.size() > 0) {
                                bandsListView.setBandsList(bands);
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<SearchAPIResponse> call, Throwable t) {

            }
        });
    }
}
