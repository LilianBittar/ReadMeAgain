package com.lilianbittar.readmeagain.viewmodels;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.google.firebase.auth.FirebaseUser;
import com.lilianbittar.readmeagain.repositories.UserRepository;

public class MainViewModel extends AndroidViewModel {

    private final UserRepository userRepository;

    public MainViewModel(Application app) {
        super(app);
        userRepository = UserRepository.getInstance(app);
    }

    public LiveData<FirebaseUser> getCurrentUser() {
        return userRepository.getCurrentUser();
    }

    public void signOut() {
        userRepository.signOut();
    }
}
