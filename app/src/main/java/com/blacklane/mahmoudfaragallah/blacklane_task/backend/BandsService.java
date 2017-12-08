package com.blacklane.mahmoudfaragallah.blacklane_task.backend;

import com.blacklane.mahmoudfaragallah.blacklane_task.model.responses.SearchAPIResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Mahmoud on 07-12-2017.
 */

public interface BandsService {

    String BANDS_API_KEY = "1cc1672b-7ad7-48de-a585-ccd93d9e2925";

    @GET("search/band_name/{search_query}?api_key=" + BANDS_API_KEY)
    Call<SearchAPIResponse> bandsSearch(@Path("search_query") String searchQuery);
}
