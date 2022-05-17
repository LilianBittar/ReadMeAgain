package com.lilianbittar.readmeagain.viewmodels;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.lilianbittar.readmeagain.dao.BookToRead;
import com.lilianbittar.readmeagain.model.Book;
import com.lilianbittar.readmeagain.repositories.BookRepo;
import com.lilianbittar.readmeagain.repositories.UserRepository;
import java.util.List;

public class SearchViewModel extends AndroidViewModel {

    private final BookRepo bookRepo;

    private MutableLiveData<List<Book>> searchResult;
    private final MutableLiveData<Boolean> loading;

    public SearchViewModel(Application app) {
        super(app);
        bookRepo = BookRepo.getInstance(app);
        searchResult = new MutableLiveData<>();
        loading = new MutableLiveData<>(false);
    }

    public LiveData<List<Book>> getSearchResult() {
        return searchResult;
    }

    public void searchForBook(String s) {
        loading.setValue(true);
        bookRepo.searchForBook(s, (result) -> {
            searchResult.setValue(result);
            loading.setValue(false);
        });
    }

    public LiveData<Boolean> getLoading() {
        return loading;
    }

    public void addBookToRead(Book bookToRead) {

        String isbn = "Not found";
        if (bookToRead.getIsbns() != null) isbn = bookToRead.getIsbns().get(0);

        String author = "Not found";
        if (bookToRead.getAuthor() != null) author = bookToRead.getAuthor().get(0);

        String subject = "Not found";
        if (bookToRead.getSubjects() != null) subject = bookToRead.getSubjects().get(0);

        BookToRead tmp = new BookToRead(
                bookToRead.getTitle(),
                bookToRead.getNumber_of_pages_median(),
                isbn,
                author,
                subject,
                bookToRead.getCoverId()
        );
        bookRepo.insert(tmp);
    }
}
