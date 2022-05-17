package com.lilianbittar.readmeagain.repositories;

import android.app.Application;
import android.util.Log;
import androidx.lifecycle.LiveData;
import com.google.firebase.database.DatabaseReference;
import com.lilianbittar.readmeagain.dao.BookDatabase;
import com.lilianbittar.readmeagain.dao.BookToRead;
import com.lilianbittar.readmeagain.dao.BooksToReadDao;
import com.lilianbittar.readmeagain.dao.ReadBook;
import com.lilianbittar.readmeagain.dao.ReadBooksDao;
import com.lilianbittar.readmeagain.model.Book;
import com.lilianbittar.readmeagain.model.BookList;
import com.lilianbittar.readmeagain.network.BookApi;
import com.lilianbittar.readmeagain.network.CallbackLoading;
import com.lilianbittar.readmeagain.network.ServiceGenerator;
import com.lilianbittar.readmeagain.network.responses.SearchBookByTitleResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookRepo {

    private static BookRepo instance;
    private DatabaseReference myRef;
    private BookListLiveData booksToRead;

    private final BooksToReadDao booksToReadDao;
    private final ReadBooksDao readBooksDao;

    private LiveData<List<BookToRead>> allBooksToRead;
    private LiveData<List<ReadBook>> allReadBooks;

    private final ExecutorService executorService;

    private BookRepo(Application app) {
        BookDatabase database = BookDatabase.getInstance(app);
        booksToReadDao = database.booksToReadDao();
        readBooksDao = database.readBooksDao();
        allBooksToRead = booksToReadDao.getAllToReadBooks();
        allReadBooks = readBooksDao.getAllReadBooks();
        executorService = Executors.newFixedThreadPool(2);
    }

    public static synchronized BookRepo getInstance(Application app) {
        if (instance == null) {
            instance = new BookRepo(app);
        }
        return instance;
    }

    public LiveData<List<BookToRead>> getAllBooksToRead() {
        return allBooksToRead;
    }

    public void insert(BookToRead bookToRead) {
        executorService.execute(() -> {
            booksToReadDao.insert(bookToRead);
        });
    }

    public void delete(BookToRead bookToRead) {
        executorService.execute(() -> {
            booksToReadDao.delete(bookToRead);
        });
    }

    public LiveData<List<ReadBook>> getAllReadBooks() {
        return allReadBooks;
    }

    public void insert(ReadBook readBook) {
        executorService.execute(() -> readBooksDao.insert(readBook));
    }

    public void addBookToExchange(ReadBook book) {
        ArrayList<ReadBook> tmp;
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
