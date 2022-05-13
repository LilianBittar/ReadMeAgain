package com.lilianbittar.readmeagain.network;

import com.lilianbittar.readmeagain.network.responses.SearchBookByTitleResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BookApi {

    @GET("search.json")
    Call<SearchBookByTitleResponse> getBookByTitle(@Query("q") String BookTitle);
}
