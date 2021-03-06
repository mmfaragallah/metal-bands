package com.metalbands.mahmoudfaragallah.bands_search;

import com.metalbands.mahmoudfaragallah.backend.BandsService;
import com.metalbands.mahmoudfaragallah.base.BasePresenter;
import com.metalbands.mahmoudfaragallah.model.data_models.BandSearchData;
import com.metalbands.mahmoudfaragallah.model.data_models.MetalBand;
import com.metalbands.mahmoudfaragallah.model.responses.SearchAPIResponse;
import com.metalbands.mahmoudfaragallah.storage_utility.search_history.SearchRecentProvider;
import com.metalbands.mahmoudfaragallah.storage_utility.shared_prefs.SharedPrefUtility;
import com.metalbands.mahmoudfaragallah.util.ApplicationConstants;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Mahmoud on 08-12-2017.
 */

public class BandsSearchPresenter extends BasePresenter implements BandsSearchContract.Presenter {

    //region objects
    private BandsService bandsService;
    private SharedPrefUtility sharedPrefUtility;
    private SearchRecentProvider searchRecentProvider;
    private BandsSearchContract.View bandsListView;
    private Call<SearchAPIResponse> currentSearchAPICall;
    //endregion

    //region constructors
    BandsSearchPresenter(BandsSearchContract.View bandsListView, BandsService bandsService, SearchRecentProvider searchRecentProvider, SharedPrefUtility sharedPrefUtility) {

        this.searchRecentProvider = searchRecentProvider;
        this.bandsService = bandsService;
        this.bandsListView = bandsListView;
        this.sharedPrefUtility = sharedPrefUtility;
    }
    //endregion

    //region presenter callbacks
    @Override
    public void searchBands(String query) {

        checkCurrentSearchAPICall();
        performSearch(query);
    }

    @Override
    public void checkLatestSearchedQuery() {

        String latestSearchedQuery = sharedPrefUtility.getSettingString(ApplicationConstants.LATEST_SEARCHED_QUERY);

        if (latestSearchedQuery != null) {
            bandsListView.updateSearchViewText(latestSearchedQuery);
        }
    }
    //endregion

    //region private methods

    /**
     *
     */
    private void checkCurrentSearchAPICall() {

        if (currentSearchAPICall != null) {
            currentSearchAPICall.cancel();
        }
    }

    /**
     * @param query
     */
    private void performSearch(final String query) {

        currentSearchAPICall = bandsService.bandsSearch(query);

        currentSearchAPICall.enqueue(new Callback<SearchAPIResponse>() {
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

                                storeQueryLocally(query);
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
    }

    /**
     * @param query
     */
    private void storeQueryLocally(String query) {

        searchRecentProvider.saveRecentQuery(query);
        sharedPrefUtility.saveSetting(ApplicationConstants.LATEST_SEARCHED_QUERY, query);
    }
    //endregion
}