package com.lilianbittar.readmeagain.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.lilianbittar.readmeagain.Network.SearchApi;
import com.lilianbittar.readmeagain.Network.ServiceGenerator;
import com.lilianbittar.readmeagain.model.Book;
import com.lilianbittar.readmeagain.response.BookValue;
import com.lilianbittar.readmeagain.response.SearchBookByTitleResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookRepo {

    private static BookRepo instance;
    //here we going to subscribe to the data that is retrieved
    private final MutableLiveData<List<BookValue>> searchedBook;

    private BookRepo(){
        searchedBook = new MutableLiveData<>();
    }


    public static synchronized BookRepo getInstance(){
        if (instance == null){
            instance = new BookRepo();
        }
        return instance;
    }

    public LiveData<List<BookValue>> getSearchedBook(){
        return searchedBook;
    }

    public void searchForBook(String bookName){
        SearchApi searchApi = ServiceGenerator.getSearchApi();
        Call<SearchBookByTitleResponse> call = searchApi.getBookByTitle(bookName);
        call.enqueue(new Callback<SearchBookByTitleResponse>() {
            @Override
            public void onResponse(Call<SearchBookByTitleResponse> call, Response<SearchBookByTitleResponse> response) {
                if (response.isSuccessful()) {
                    searchedBook.setValue(response.body().getBooks());
                }
            }

            @Override
            public void onFailure(Call<SearchBookByTitleResponse> call, Throwable t) {
                Log.i("Retrofit", "Something went wrong: (");
            }
        });
    }
}
