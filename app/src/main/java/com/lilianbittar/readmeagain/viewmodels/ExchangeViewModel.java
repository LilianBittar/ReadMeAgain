package com.lilianbittar.readmeagain.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import com.lilianbittar.readmeagain.repositories.BookListLiveData;
import com.lilianbittar.readmeagain.repositories.BookRepo;

public class ExchangeViewModel extends AndroidViewModel {

    private final BookRepo bookRepo;

    public ExchangeViewModel(@NonNull Application application) {
        super(application);
        bookRepo = BookRepo.getInstance(application);
    }

    public void init() {
        bookRepo.init();
    }

    public BookListLiveData getBooksToExchange(){
        return bookRepo.getBooksToExchange();
    }
}
