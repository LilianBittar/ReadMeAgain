package com.lilianbittar.readmeagain.repo;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.lilianbittar.readmeagain.Network.SearchApi;
import com.lilianbittar.readmeagain.Network.ServiceGenerator;
import com.lilianbittar.readmeagain.model.Book;
import com.lilianbittar.readmeagain.response.SearchResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class BookRepo {

    private static BookRepo instance;
    //here we going to subscribe to the data that is retrieved
    private final MutableLiveData<Book> searchedBook;

    private BookRepo(){
        searchedBook = new MutableLiveData<>();
    }


    public static synchronized BookRepo getInstance(){
        if (instance == null){
            instance = new BookRepo();
        }
        return instance;
    }

    public LiveData<Book> getSearchedBook(){
        return searchedBook;
    }

    public void searchForBook(String bookName){
        SearchApi searchApi = ServiceGenerator.getSearchApi();
        Call<SearchResponse> call = searchApi.getBook(bookName);
        call.enqueue(new Callback<SearchResponse>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                if (response.isSuccessful()) {
                    searchedBook.setValue(response.body().getBook());
                }
            }
            @EverythingIsNonNull
            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                Log.i("Retrofit", "Something went wrong: (");
            }
        });
    }
}
