package com.lilianbittar.readmeagain.repositories;

import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.lilianbittar.readmeagain.model.Book;
import com.lilianbittar.readmeagain.network.BookApi;
import com.lilianbittar.readmeagain.network.CallbackLoading;
import com.lilianbittar.readmeagain.network.ServiceGenerator;
import com.lilianbittar.readmeagain.network.responses.SearchBookByTitleResponse;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookRepo {

    private static BookRepo instance;
    private final MutableLiveData<List<Book>> searchedBook;

    private BookRepo() {
        searchedBook = new MutableLiveData<>();
    }

    public static synchronized BookRepo getInstance() {
        if (instance == null) {
            instance = new BookRepo();
        }
        return instance;
    }

    public LiveData<List<Book>> getSearchedBook() {
        return searchedBook;
    }

    public void searchForBook(CallbackLoading callback, String bookName){
        BookApi searchApi = ServiceGenerator.getBookApi();
        Call<SearchBookByTitleResponse> call = searchApi.getBookByTitle(bookName);
        call.enqueue(new Callback<SearchBookByTitleResponse>() {
            @Override
            public void onResponse(Call<SearchBookByTitleResponse> call, Response<SearchBookByTitleResponse> response) {
                if (response.isSuccessful()) {
                    searchedBook.setValue(response.body().getBooks());
                }
                callback.call();
            }

            @Override
            public void onFailure(Call<SearchBookByTitleResponse> call, Throwable t) {
                Log.i("Retrofit", "Something went wrong: (");
                callback.call();
            }
        });

    }
}
