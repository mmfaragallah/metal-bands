package com.metalbands.mahmoudfaragallah.bands_search;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.SearchRecentSuggestions;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;

import com.metalbands.mahmoudfaragallah.R;
import com.metalbands.mahmoudfaragallah.base.BaseActivity;
import com.metalbands.mahmoudfaragallah.content_provider.SearchHistoryProvider;
import com.metalbands.mahmoudfaragallah.model.data_models.MetalBand;
import com.metalbands.mahmoudfaragallah.model.responses.SearchAPIResponse;
import com.metalbands.mahmoudfaragallah.util.ApplicationConstants;
import com.metalbands.mahmoudfaragallah.util.LogUtil;
import com.metalbands.mahmoudfaragallah.util.SharedPrefUtility;

import java.util.List;

import butterknife.BindView;
import retrofit2.Call;

public class BandsSearchScreen extends BaseActivity implements BandsSearchContract.View {

    //region constants
    private final static String CLASS_NAME = BandsSearchScreen.class.getSimpleName();
    //endregion

    //region screen views
    private SearchView searchView;

    @BindView(R.id.bands_list)
    RecyclerView bandsListView;
    //endregion

    //region objects
    private Call<SearchAPIResponse> currentSearchAPICall;
    private BandsListAdapter listAdapter;
    private BandsSearchContract.Presenter presenter;
    private BandsSearchContract.Router bandsListRouter;
    //endregion

    //region AppCompatActivity callback
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        handleIntent(getIntent());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        LogUtil.debug(CLASS_NAME, "onCreateOptionsMenu method");

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.search).getActionView();
//        searchView.setQueryHint(getString(R.string.search_hint));
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {

                LogUtil.debug(CLASS_NAME, "[onQueryTextSubmit] query: " + query);
                LogUtil.showToast(BandsSearchScreen.this, "[onQueryTextSubmit] query: " + query);

//                SearchRecentSuggestions suggestions = new SearchRecentSuggestions(MainActivity.this, SearchHistoryProvider.AUTHORITY, SearchHistoryProvider.MODE);
//                suggestions.saveRecentQuery(query, null);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {

                LogUtil.debug(CLASS_NAME, "[onQueryTextChange] query: " + query);
//                LogUtil.showToast(MainActivity.this, "query change: " + query);

                //use the query to perform the search
                performSearch(query);

                return false;
            }
        });

        checkLatestSearchedQuery();

        return true;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    //endregion

    //region BaseActivity methods
    @Override
    protected int getLayout() {
        return R.layout.activity_bands_search_screen;
    }

    @Override
    protected void initializeObjects() {
        presenter = new BandsSearchPresenter(this);
        listAdapter = new BandsListAdapter(this);
        bandsListRouter = new BandsSearchRouter(this);
    }

    @Override
    protected void initializeViews() {

        // use this setting to improve performance if you know that changes in content do not change the layout size of the RecyclerView
        bandsListView.setHasFixedSize(true);
        bandsListView.setLayoutManager(new LinearLayoutManager(this));
        bandsListView.setAdapter(listAdapter);
    }
    //endregion

    //region view callbacks
    @Override
    public void setBandsList(List<MetalBand> bands, String forQuery) {

        LogUtil.debug(CLASS_NAME, "[updateBandsList] bands: " + bands);
        listAdapter.updateBandsList(bands);

        if (bands.size() > 0) {
            SearchRecentSuggestions suggestions = new SearchRecentSuggestions(this, SearchHistoryProvider.AUTHORITY, SearchHistoryProvider.MODE);
            suggestions.saveRecentQuery(forQuery, null);

            SharedPrefUtility.getInstance().saveSetting(this, ApplicationConstants.LATEST_SEARCHED_QUERY, forQuery);
        }
    }

    @Override
    public void noSearchResults(String query) {

        LogUtil.showToast(this, "There are no search results for query: " + query);
    }

    @Override
    public void onBandClick(String bandId) {
        bandsListRouter.goToBandDetailsScreen(bandId);
    }
    //endregion

    //region private method

    /**
     * @param query
     */
    private void performSearch(String query) {

        if (currentSearchAPICall != null) {
            currentSearchAPICall.cancel();
        }

        currentSearchAPICall = presenter.searchBands(query);
    }

    /**
     * @param intent
     */
    private void handleIntent(Intent intent) {

        LogUtil.debug(CLASS_NAME, "[handleIntent]");

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {

            String query = intent.getStringExtra(SearchManager.QUERY);
            LogUtil.debug(CLASS_NAME, "[handleIntent] query: " + query);
            LogUtil.showToast(this, "[handleIntent] query: " + query);

            searchView.setQuery(query, false);
        }
    }

    /**
     *
     */
    private void checkLatestSearchedQuery() {

        String latestSearchedQuery = SharedPrefUtility.getInstance().getSettingString(this, ApplicationConstants.LATEST_SEARCHED_QUERY);
        if (latestSearchedQuery != null) {
            searchView.setQuery(latestSearchedQuery, false);
        }
    }
    //endregion
}