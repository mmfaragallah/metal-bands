package com.metalbands.mahmoudfaragallah.bands_search;

import android.app.Activity;
import android.provider.SearchRecentSuggestions;

import com.metalbands.mahmoudfaragallah.backend.BandsService;
import com.metalbands.mahmoudfaragallah.backend.RetrofitHandler;
import com.metalbands.mahmoudfaragallah.base.BasePresenter;
import com.metalbands.mahmoudfaragallah.content_provider.SearchHistoryProvider;
import com.metalbands.mahmoudfaragallah.model.data_models.BandSearchData;
import com.metalbands.mahmoudfaragallah.model.data_models.MetalBand;
import com.metalbands.mahmoudfaragallah.model.responses.SearchAPIResponse;
import com.metalbands.mahmoudfaragallah.util.ApplicationConstants;
import com.metalbands.mahmoudfaragallah.util.SharedPrefUtility;

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
    private BandsSearchContract.View bandsListView;
    private Call<SearchAPIResponse> currentSearchAPICall;
    //endregion

    //region constructors
    BandsSearchPresenter(Activity context, BandsSearchContract.View bandsListView) {
        super(context);
        this.bandsListView = bandsListView;
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

        String latestSearchedQuery = SharedPrefUtility.getInstance().getSettingString(getContext(), ApplicationConstants.LATEST_SEARCHED_QUERY);

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

        BandsService bandsService = RetrofitHandler.getInstance(getContext().getCacheDir()).createBandsService();

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

        SearchRecentSuggestions suggestions = new SearchRecentSuggestions(getContext(), SearchHistoryProvider.AUTHORITY, SearchHistoryProvider.MODE);
        suggestions.saveRecentQuery(query, null);

        SharedPrefUtility.getInstance().saveSetting(getContext(), ApplicationConstants.LATEST_SEARCHED_QUERY, query);
    }
    //endregion
}