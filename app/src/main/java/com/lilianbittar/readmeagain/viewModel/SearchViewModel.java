package com.lilianbittar.readmeagain.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.lilianbittar.readmeagain.model.Book;
import com.lilianbittar.readmeagain.repo.BookRepo;

public class SearchViewModel extends ViewModel {

    BookRepo repo;

    public SearchViewModel(){
        repo = BookRepo.getInstance();
    }

    LiveData<Book> getSearchedBook(){
        return repo.getSearchedBook();
    }

    public void searchForBook(String s){
        repo.searchForBook(s);
    }


}
