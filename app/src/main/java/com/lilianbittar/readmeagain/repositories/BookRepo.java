package com.lilianbittar.readmeagain.repositories;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.lilianbittar.readmeagain.model.Book;
import com.lilianbittar.readmeagain.model.BookList;
import com.lilianbittar.readmeagain.network.BookApi;
import com.lilianbittar.readmeagain.network.CallbackLoading;
import com.lilianbittar.readmeagain.network.ServiceGenerator;
import com.lilianbittar.readmeagain.network.responses.SearchBookByTitleResponse;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookRepo {

    private static BookRepo instance;
    private DatabaseReference myRef;
    private BookListLiveData booksToRead;

    private BookRepo() {}

    public static synchronized BookRepo getInstance() {
        if (instance == null) {
            instance = new BookRepo();
        }
        return instance;
    }

    public void init(String userId) {
        myRef = FirebaseDatabase.getInstance().getReference().child(userId).child("ToReadBooks");
        booksToRead = new BookListLiveData(myRef);
    }

    public void addBookToRead(Book book) {
        ArrayList<Book> tmp;
        if (booksToRead.getValue() == null) {
            tmp = new ArrayList<>();
        } else {
            tmp = booksToRead.getValue().getBookList();
        }
        tmp.add(book);
        myRef.setValue(new BookList(tmp));

    }

    public BookListLiveData getBooks() {
        return booksToRead;
    }

    public void searchForBook(String bookName, CallbackLoading callback){
        BookApi searchApi = ServiceGenerator.getBookApi();
        Call<SearchBookByTitleResponse> call = searchApi.getBookByTitle(bookName, 25);
        call.enqueue(new Callback<SearchBookByTitleResponse>() {
            @Override
            public void onResponse(Call<SearchBookByTitleResponse> call, Response<SearchBookByTitleResponse> response) {
                List<Book> searchResult = new ArrayList<>();
                if (response.isSuccessful()) {
                    searchResult = response.body().getBooks();
                }
                callback.call(searchResult);
            }

            @Override
            public void onFailure(Call<SearchBookByTitleResponse> call, Throwable t) {
                Log.i("Retrofit", "Something went wrong: (");
                callback.call(new ArrayList<>());
            }
        });

    }
}
