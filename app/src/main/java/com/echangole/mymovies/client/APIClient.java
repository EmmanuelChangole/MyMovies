package com.echangole.mymovies.client;

import com.echangole.mymovies.interfaces.MovieApi;
import com.echangole.mymovies.utils.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    private static Retrofit retrofit = null;

    public static MovieApi getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                      .baseUrl(Constants.BASE_URL)
                      .addConverterFactory(GsonConverterFactory.create())
                      .build();
        }

        MovieApi apiInterface=retrofit.create(MovieApi.class);

        return apiInterface;
    }


}
