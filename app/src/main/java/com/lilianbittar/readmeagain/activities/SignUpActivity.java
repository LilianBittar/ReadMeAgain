package com.lilianbittar.readmeagain.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.lilianbittar.readmeagain.R;

public class SignUpActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private TextInputLayout emailInput;
    private TextInputLayout passwordInput;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initViews();

        firebaseAuth = FirebaseAuth.getInstance();

        emailInput.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    emailInput.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        passwordInput.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    passwordInput.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        registerButton.setOnClickListener(view -> {
            String email = emailInput.getEditText().getText().toString();
            String password = passwordInput.getEditText().getText().toString();

            if (email.isEmpty()) {
                emailInput.setError("Please provide email");
                emailInput.requestFocus();
            } else if (password.isEmpty()) {
                passwordInput.setError("Please provide password");
                passwordInput.requestFocus();
            } else {
                firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, task -> {
                    if (!task.isSuccessful()) {
                        Toast.makeText(this, "Registering failed", Toast.LENGTH_SHORT).show();
                    }
                    if (task.isSuccessful()) {
                        startActivity(new Intent(this, SignInActivity.class));
                        finish();
                    }
                });
            }
        });
    }

    private void initViews() {
        emailInput = findViewById(R.id.sign_up_input_email);
        passwordInput = findViewById(R.id.sign_up_input_password);
        registerButton = findViewById(R.id.sign_up_button_register);
    }
}