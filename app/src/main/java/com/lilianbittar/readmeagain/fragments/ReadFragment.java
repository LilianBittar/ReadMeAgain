package com.lilianbittar.readmeagain.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lilianbittar.readmeagain.R;
import com.lilianbittar.readmeagain.adapters.ReadBookAdapter;
import com.lilianbittar.readmeagain.adapters.ToReadBookAdapter;
import com.lilianbittar.readmeagain.viewmodels.ProfileViewModel;

public class ReadFragment extends Fragment {

    private ProfileViewModel viewModel;

    private View root;
    private RecyclerView recyclerView;
    private ReadBookAdapter readBookAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_book_read, container, false);
        viewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        initViews();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        readBookAdapter = new ReadBookAdapter(viewModel);

        recyclerView.setAdapter(readBookAdapter);
        readBookAdapter.updateBookList(viewModel.getReadBooks().getValue());

        viewModel.getReadBooks().observe(this.getViewLifecycleOwner(), bookList -> {
            readBookAdapter.updateBookList(bookList);
        });

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        root.requestLayout();
    }

    private void initViews() {
        recyclerView = root.findViewById(R.id.rc_books_read);
    }
}
