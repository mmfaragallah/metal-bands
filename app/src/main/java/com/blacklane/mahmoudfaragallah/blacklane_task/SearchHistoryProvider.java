package com.blacklane.mahmoudfaragallah.blacklane_task;

import android.content.SearchRecentSuggestionsProvider;

/**
 * Created by Mahmoud on 04-12-2017.
 */

public class SearchHistoryProvider extends SearchRecentSuggestionsProvider {

    public final static int MODE = DATABASE_MODE_QUERIES;
    public final static String AUTHORITY = "com.blacklane.bands.SearchHistoryProvider";

    public SearchHistoryProvider() {
        setupSuggestions(AUTHORITY, MODE);
    }
}