package com.lilianbittar.readmeagain.viewmodels;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.google.firebase.auth.FirebaseUser;
import com.lilianbittar.readmeagain.model.Book;
import com.lilianbittar.readmeagain.model.BookList;
import com.lilianbittar.readmeagain.repositories.BookRepo;
import com.lilianbittar.readmeagain.repositories.UserRepository;
import java.util.ArrayList;

public class ProfileViewModel extends AndroidViewModel {
    
    private final UserRepository userRepository;
    private final BookRepo bookRepo;

    public ProfileViewModel(Application app){
        super(app);
        userRepository = UserRepository.getInstance(app);
        bookRepo = BookRepo.getInstance();
    }

    public void init() {
        String userId = userRepository.getCurrentUser().getValue().getUid();
        bookRepo.init(userId);
    }

    public LiveData<FirebaseUser> getCurrentUser() {
        return userRepository.getCurrentUser();
    }


    public LiveData<BookList> getToReadBooks() {
        return bookRepo.getBooks();
    }

    public void signOut() {
        userRepository.signOut();
    }
}
