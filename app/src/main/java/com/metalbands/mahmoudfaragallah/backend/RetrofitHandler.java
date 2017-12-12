package com.metalbands.mahmoudfaragallah.backend;

import com.metalbands.mahmoudfaragallah.util.ApplicationConstants;

import java.io.File;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.metalbands.mahmoudfaragallah.util.ApplicationConstants.cacheSize;

/**
 * Created by Mahmoud on 07-12-2017.
 */

public class RetrofitHandler {

    //region Constants
    private static final String className = RetrofitHandler.class.getSimpleName();
    private static final String BASE_URL = "http://em.wemakesites.net/";
    //endregion

    //region Objects
    private Retrofit retrofit;
    private static RetrofitHandler instance;
    //endregion

    //region Constructor
    private RetrofitHandler(File cacheDir) {

        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        if (ApplicationConstants.DEVELOPMENT_MODE) {
            // Add HttpLoggingInterceptor
            if (!okHttpClient.interceptors().contains(loggingInterceptor)) {
                okHttpClient.addInterceptor(loggingInterceptor);
            }
        }

        // add the cache feature to the APIs requests
        Cache cache = new Cache(cacheDir, cacheSize);
        okHttpClient.cache(cache);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient.build())
                .build();
    }
    //endregion

    //region Public Methods

    public static RetrofitHandler getInstance(File cacheDir) {

        if (instance == null) {
            instance = new RetrofitHandler(cacheDir);
        }

        return instance;
    }

    public BandsService createBandsService() {
        return retrofit.create(BandsService.class);
    }

    //endregion
}