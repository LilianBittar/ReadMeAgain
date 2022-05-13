package com.lilianbittar.readmeagain.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.lilianbittar.readmeagain.model.Book;
import com.lilianbittar.readmeagain.repositories.BookRepo;
import com.lilianbittar.readmeagain.response.BookValue;

import java.util.List;

public class SearchViewModel extends ViewModel {

    BookRepo repo;

    public SearchViewModel(){
        repo = BookRepo.getInstance();
    }

    public LiveData<List<BookValue>> getSearchedBook(){
        return repo.getSearchedBook();
    }

    public void searchForBook(String s){
        repo.searchForBook(s);
    }


}
