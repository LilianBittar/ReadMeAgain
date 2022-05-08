package com.lilianbittar.readmeagain.Network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    private static SearchApi searchApi;

    // Method for accessing the search API
    public static SearchApi getSearchApi(){
        if (searchApi == null){
            searchApi = new Retrofit.Builder().baseUrl("http://openlibrary.org/").addConverterFactory(GsonConverterFactory.create())
                    .build().create(SearchApi.class);
        }
        return searchApi;
    }

}
