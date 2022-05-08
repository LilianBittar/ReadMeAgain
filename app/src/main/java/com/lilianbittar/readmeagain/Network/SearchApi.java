package com.lilianbittar.readmeagain.Network;

import com.lilianbittar.readmeagain.response.SearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SearchApi {

    @GET("search.json?q={name}")
    Call<SearchResponse> getBook(@Path("name") String name);
}
