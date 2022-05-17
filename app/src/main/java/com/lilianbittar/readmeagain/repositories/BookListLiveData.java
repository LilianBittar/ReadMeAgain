package com.lilianbittar.readmeagain.repositories;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.lilianbittar.readmeagain.dao.ReadBook;
import com.lilianbittar.readmeagain.model.BookList;

public class BookListLiveData extends LiveData<BookList> {

    private final ValueEventListener listener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            BookList bookList = snapshot.getValue(BookList.class);
            setValue(bookList);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {}
    };

    DatabaseReference databaseReference;

    public BookListLiveData(DatabaseReference ref) {
        databaseReference = ref;
        ref.get().addOnCompleteListener(d -> {
            setValue(d.getResult().getValue(BookList.class));
        });
    }

    @Override
    protected void onActive() {
        super.onActive();
        databaseReference.addValueEventListener(listener);
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        databaseReference.removeEventListener(listener);
    }
}