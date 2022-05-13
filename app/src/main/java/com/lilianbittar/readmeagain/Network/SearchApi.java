package com.lilianbittar.readmeagain.Network;

import com.lilianbittar.readmeagain.response.SearchBookByTitleResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SearchApi {

    @GET("search.json")
    Call<SearchBookByTitleResponse> getBookByTitle(@Query("q") String BookTitle);
}
