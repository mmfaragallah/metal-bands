package com.metalbands.mahmoudfaragallah.storage_utility.search_history;

import android.content.Context;
import android.provider.SearchRecentSuggestions;

import com.metalbands.mahmoudfaragallah.content_provider.SearchHistoryProvider;

/**
 * Created by Mahmoud on 15-12-2017.
 */

public class SearchRecentProviderImpl implements SearchRecentProvider {

    private SearchRecentSuggestions suggestions;

    public SearchRecentProviderImpl(Context context) {
        suggestions = new SearchRecentSuggestions(context, SearchHistoryProvider.AUTHORITY, SearchHistoryProvider.MODE);
    }

    @Override
    public void saveRecentQuery(String query) {
        suggestions.saveRecentQuery(query, null);
    }
}
