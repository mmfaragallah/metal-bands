package com.blacklane.mahmoudfaragallah.blacklane_task;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

public class SearchResultsActivity extends AppCompatActivity {

    private final static String TAG = "Blacklane_Task";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_results_activity);

        Log.d(TAG, "SearchResultsActivity onCreate method");

        handleIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {

        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {

        Log.d(TAG, "handleIntent method");

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            //use the query to search your data somehow

            Log.d(TAG, "query : " + query);
            Toast.makeText(this, "query : " + query, Toast.LENGTH_SHORT).show();
        }
    }
}
