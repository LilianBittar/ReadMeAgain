package com.lilianbittar.readmeagain.viewmodels;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.google.firebase.auth.FirebaseUser;
import com.lilianbittar.readmeagain.dao.BookToRead;
import com.lilianbittar.readmeagain.dao.ReadBook;
import com.lilianbittar.readmeagain.model.Book;
import com.lilianbittar.readmeagain.model.BookList;
import com.lilianbittar.readmeagain.repositories.BookRepo;
import com.lilianbittar.readmeagain.repositories.UserRepository;
import java.util.ArrayList;
import java.util.List;

public class ProfileViewModel extends AndroidViewModel {
    
    private final UserRepository userRepository;
    private final BookRepo bookRepo;

    public ProfileViewModel(Application app){
        super(app);
        userRepository = UserRepository.getInstance(app);
        bookRepo = BookRepo.getInstance(app);
    }

    public void init() {
       // String userId = userRepository.getCurrentUser().getValue().getUid();
       // bookRepo.init(userId);
    }

    public LiveData<FirebaseUser> getCurrentUser() {
        return userRepository.getCurrentUser();
    }

    public LiveData<List<BookToRead>> getToReadBooks() {
        return bookRepo.getAllBooksToRead();
    }

    public void signOut() {
        userRepository.signOut();
    }
}
