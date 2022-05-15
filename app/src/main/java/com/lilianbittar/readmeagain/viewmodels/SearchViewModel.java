package com.lilianbittar.readmeagain.viewmodels;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.google.firebase.auth.FirebaseUser;
import com.lilianbittar.readmeagain.model.Book;
import com.lilianbittar.readmeagain.repositories.BookRepo;
import com.lilianbittar.readmeagain.repositories.UserRepository;
import java.util.List;

public class SearchViewModel extends AndroidViewModel {

    private final UserRepository userRepository;
    private final BookRepo bookRepo;

    private MutableLiveData<List<Book>> searchResult;
    private final MutableLiveData<Boolean> loading;

    public SearchViewModel(Application app) {
        super(app);
        userRepository = UserRepository.getInstance(app);
        bookRepo = BookRepo.getInstance();
        searchResult = new MutableLiveData<>();
        loading = new MutableLiveData<>(false);
    }

    public void init() {
        String userId = userRepository.getCurrentUser().getValue().getUid();
        bookRepo.init(userId);
    }

    public LiveData<FirebaseUser> getCurrentUser() {
        return userRepository.getCurrentUser();
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
        bookRepo.addBookToRead(bookToRead);
    }
}
