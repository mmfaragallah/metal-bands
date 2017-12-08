package com.blacklane.mahmoudfaragallah.blacklane_task.backend;

import com.blacklane.mahmoudfaragallah.blacklane_task.util.ApplicationConstants;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
    private RetrofitHandler() {

        OkHttpClient.Builder client = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        if (ApplicationConstants.DEVELOPMENT_MODE) {
            // Add HttpLoggingInterceptor
            if (!client.interceptors().contains(loggingInterceptor)) {
                client.addInterceptor(loggingInterceptor);
            }
        }
        retrofit = new Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build())
                .build();
    }
    //endregion

    //region Public Methods

    public static RetrofitHandler getInstance() {

        if (instance == null) {
            instance = new RetrofitHandler();
        }

        return instance;
    }

    public BandsService createBandsService() {
        return retrofit.create(BandsService.class);
    }

    //endregion
}