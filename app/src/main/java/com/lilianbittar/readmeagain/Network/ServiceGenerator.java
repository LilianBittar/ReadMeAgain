package com.lilianbittar.readmeagain.Network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    public static Retrofit.Builder retrofitBuilder = new Retrofit.Builder().baseUrl("http://openlibrary.org/").
            addConverterFactory(GsonConverterFactory.create());

        private static Retrofit retrofit = retrofitBuilder.build();

    private static SearchApi searchApi = retrofit.create(SearchApi.class);

    public static SearchApi getSearchApi(){
        return searchApi;
    }


}
