package com.metalbands.mahmoudfaragallah.util;

import com.metalbands.mahmoudfaragallah.BuildConfig;

/**
 * Created by Mahmoud on 07-12-2017.
 */
public interface ApplicationConstants {

    String PROJECT_TAG = "metalbands_app";

    boolean DEVELOPMENT_MODE = BuildConfig.DEBUG;

    //region Shared Preferences items
    String LATEST_SEARCHED_QUERY = "LATEST_SEARCHED_QUERY";
    //endregion

    // API Cache size
    int cacheSize = 10 * 1024 * 1024; // 10 MB
}
