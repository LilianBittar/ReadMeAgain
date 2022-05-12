package com.lilianbittar.readmeagain.viewmodels;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.google.firebase.auth.FirebaseUser;
import com.lilianbittar.readmeagain.repositories.UserRepository;

public class SignInViewModel extends AndroidViewModel {

    private final UserRepository userRepo;

    public SignInViewModel(Application app) {
        super(app);
        userRepo = UserRepository.getInstance(app);
    }

    public LiveData<FirebaseUser> getCurrentUser(){
        return userRepo.getCurrentUser();
    }
}
