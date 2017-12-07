package com.blacklane.mahmoudfaragallah.blacklane_task;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.SearchRecentSuggestions;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = "Blacklane_Task";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        handleIntent(getIntent());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        Log.d(TAG, "onCreateOptionsMenu method");

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
//        searchView.setQueryHint(getString(R.string.search_hint));
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {

                Log.d(TAG, "query : " + query);
                Toast.makeText(MainActivity.this, "query submit: " + query, Toast.LENGTH_SHORT).show();


                SearchRecentSuggestions suggestions = new SearchRecentSuggestions(MainActivity.this, SearchHistoryProvider.AUTHORITY, SearchHistoryProvider.MODE);
                suggestions.saveRecentQuery(query, null);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {

                Log.d(TAG, "query : " + query);
//                Toast.makeText(MainActivity.this, "query change: " + query, Toast.LENGTH_SHORT).show();

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

        Log.d(TAG, "handleIntent method");

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            //use the query to search your data somehow

            SearchRecentSuggestions suggestions = new SearchRecentSuggestions(this, SearchHistoryProvider.AUTHORITY, SearchHistoryProvider.MODE);
            suggestions.saveRecentQuery(query, null);

            Log.d(TAG, "query : " + query);
            Toast.makeText(this, "query : " + query, Toast.LENGTH_SHORT).show();
        }
    }
}
