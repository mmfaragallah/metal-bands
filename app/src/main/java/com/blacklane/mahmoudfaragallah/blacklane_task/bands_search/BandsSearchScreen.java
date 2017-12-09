package com.blacklane.mahmoudfaragallah.blacklane_task.bands_search;

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

import com.blacklane.mahmoudfaragallah.blacklane_task.R;
import com.blacklane.mahmoudfaragallah.blacklane_task.base.BaseActivity;
import com.blacklane.mahmoudfaragallah.blacklane_task.content_provider.SearchHistoryProvider;
import com.blacklane.mahmoudfaragallah.blacklane_task.model.MetalBand;
import com.blacklane.mahmoudfaragallah.blacklane_task.util.LogUtil;

import java.util.List;

import butterknife.BindView;

public class BandsSearchScreen extends BaseActivity implements BandsSearchContract.View {

    private final static String CLASS_NAME = BandsSearchScreen.class.getSimpleName();

    //region screen views
    @BindView(R.id.bands_list)
    RecyclerView bandsListView;
    //endregion

    //region objects
    private BandsListAdapter listAdapter;
    private BandsSearchContract.Presenter presenter;
    private BandsSearchContract.Router bandsListRouter;
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        handleIntent(getIntent());
    }

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

    //region view callbacks
    @Override
    public void setBandsList(List<MetalBand> bands) {

        LogUtil.debug(CLASS_NAME, "[updateBandsList] bands: " + bands);

        listAdapter.updateBandsList(bands);
    }

    @Override
    public void noSearchResults(String query) {
        LogUtil.showToast(BandsSearchScreen.this, "There are no search results for query: " + query);
    }

    @Override
    public void onBandClick(String bandId) {
        bandsListRouter.goToBandDetailsScreen(bandId);
    }
    //endregion

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        LogUtil.debug(CLASS_NAME, "onCreateOptionsMenu method");

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
//        searchView.setQueryHint(getString(R.string.search_hint));
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {

                LogUtil.debug(CLASS_NAME, "[onQueryTextSubmit] query: " + query);
                LogUtil.showToast(BandsSearchScreen.this, "query submit: " + query);

                presenter.searchBands(query);

//                SearchRecentSuggestions suggestions = new SearchRecentSuggestions(MainActivity.this, SearchHistoryProvider.AUTHORITY, SearchHistoryProvider.MODE);
//                suggestions.saveRecentQuery(query, null);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {

                LogUtil.debug(CLASS_NAME, "[onQueryTextChange] query: " + query);
//                LogUtil.showToast(MainActivity.this, "query change: " + query);

                return false;
            }
        });


        return true;
    }

    @Override
    protected void onNewIntent(Intent intent) {

//        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {

        LogUtil.debug(CLASS_NAME, "[handleIntent]");

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            //use the query to search your data somehow

            SearchRecentSuggestions suggestions = new SearchRecentSuggestions(this, SearchHistoryProvider.AUTHORITY, SearchHistoryProvider.MODE);
            suggestions.saveRecentQuery(query, null);

            LogUtil.debug(CLASS_NAME, "query : " + query);
            LogUtil.showToast(this, "query : " + query);
        }
    }
}