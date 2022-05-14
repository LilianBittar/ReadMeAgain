package com.lilianbittar.readmeagain.viewmodels;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.lilianbittar.readmeagain.model.Book;
import com.lilianbittar.readmeagain.repositories.BookRepo;

import java.util.ArrayList;
import java.util.List;

public class SearchViewModel extends AndroidViewModel {

    private final BookRepo bookRepo;

    private MutableLiveData<List<Book>> searchResult;
    private final MutableLiveData<Boolean> loading;

    public SearchViewModel(Application app) {
        super(app);
        bookRepo = BookRepo.getInstance();
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
}
