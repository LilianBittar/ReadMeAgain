package com.lilianbittar.readmeagain.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    public static Retrofit.Builder retrofitBuilder = new Retrofit.Builder().baseUrl("http://openlibrary.org/").addConverterFactory(GsonConverterFactory.create());
    private static Retrofit retrofit = retrofitBuilder.build();
    private static BookApi searchApi = retrofit.create(BookApi.class);

    public static BookApi getBookApi() {
        return searchApi;
    }
}
