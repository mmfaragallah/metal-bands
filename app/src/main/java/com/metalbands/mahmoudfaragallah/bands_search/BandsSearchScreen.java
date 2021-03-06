package com.metalbands.mahmoudfaragallah.bands_search;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;

import com.metalbands.mahmoudfaragallah.idling_resources.SimpleIdlingResource;
import com.metalbands.mahmoudfaragallah.R;
import com.metalbands.mahmoudfaragallah.backend.BandsService;
import com.metalbands.mahmoudfaragallah.backend.RetrofitHandler;
import com.metalbands.mahmoudfaragallah.base.BaseActivity;
import com.metalbands.mahmoudfaragallah.model.data_models.MetalBand;
import com.metalbands.mahmoudfaragallah.storage_utility.search_history.SearchRecentProvider;
import com.metalbands.mahmoudfaragallah.storage_utility.search_history.SearchRecentProviderImpl;
import com.metalbands.mahmoudfaragallah.storage_utility.shared_prefs.SharedPrefImpl;
import com.metalbands.mahmoudfaragallah.storage_utility.shared_prefs.SharedPrefUtility;
import com.metalbands.mahmoudfaragallah.util.LogUtil;

import java.util.List;

import butterknife.BindView;

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
    private BandsService bandsService;
    private BandsListAdapter listAdapter;
    private SharedPrefUtility sharedPrefUtility;
    private SearchRecentProvider searchRecentProvider;
    private BandsSearchContract.Presenter presenter;
    private BandsSearchContract.Router bandsListRouter;

    // The Idling Resource which will be null in production.
    @Nullable
    private SimpleIdlingResource idlingResource;
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
        searchView = (SearchView) menu.findItem(R.id.search_view).getActionView();
//        searchView.setQueryHint(getString(R.string.search_hint));
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {

                LogUtil.debug(CLASS_NAME, "[onQueryTextSubmit] query: " + query);
                LogUtil.showToast(BandsSearchScreen.this, "[onQueryTextSubmit] query: " + query);

                performSearch(query);

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

        presenter.checkLatestSearchedQuery();

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
        listAdapter = new BandsListAdapter(this);
        bandsListRouter = new BandsSearchRouter(this);

        sharedPrefUtility = SharedPrefImpl.getInstance(this);
        searchRecentProvider = new SearchRecentProviderImpl(this);
        bandsService = RetrofitHandler.getInstance(this.getCacheDir()).createBandsService();
        presenter = new BandsSearchPresenter(this, bandsService, searchRecentProvider, sharedPrefUtility);
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

        if (idlingResource != null) {
            idlingResource.setIdleState(true);
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

    /**
     * @param query
     */
    @Override
    public void updateSearchViewText(String query) {
        searchView.setQuery(query, false);
    }
    //endregion

    //region private method

    /**
     * @param query
     */
    private void performSearch(String query) {

        if (idlingResource != null) {
            idlingResource.setIdleState(false);
        }

        presenter.searchBands(query);
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

            updateSearchViewText(query);
        }
    }
    //endregion

    //region helping methods for testing

    /**
     * Only called from test, creates and returns a new {@link SimpleIdlingResource}.
     */
    @VisibleForTesting
    @NonNull
    public IdlingResource getIdlingResource() {
        if (idlingResource == null) {
            idlingResource = new SimpleIdlingResource();
        }
        return idlingResource;
    }
    //endregion
}