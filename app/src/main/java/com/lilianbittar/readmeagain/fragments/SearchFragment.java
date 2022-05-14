package com.lilianbittar.readmeagain.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputLayout;
import com.lilianbittar.readmeagain.R;
import com.lilianbittar.readmeagain.adapters.BookAdapter;
import com.lilianbittar.readmeagain.model.Book;
import com.lilianbittar.readmeagain.viewmodels.SearchViewModel;
import com.lilianbittar.readmeagain.viewmodels.MainViewModel;

import java.util.ArrayList;

public class SearchFragment extends Fragment {

    private View root;

    private BookAdapter bookAdapter;
    private RecyclerView recyclerView;
    private SearchViewModel viewModel;

    private TextInputLayout searchInput;
    private Button searchButton;
    private ProgressBar progressBar;
    private TextView loading;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_search, container, false);

        viewModel = new ViewModelProvider(this).get(SearchViewModel.class);
        initViews();

        recyclerView = root.findViewById(R.id.rv_search);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        bookAdapter = new BookAdapter();
        recyclerView.setAdapter(bookAdapter);

        viewModel.getSearchResult().observe(this.getViewLifecycleOwner(), booksResult -> {
            bookAdapter.clearBookList();
            bookAdapter.updateBookList(booksResult);
        });

        searchButton.setOnClickListener(v -> {
            String input = searchInput.getEditText().getText().toString();
            if (input == null) {
                searchInput.setError("type the name of the book");
                searchInput.requestFocus();
            } else {
                viewModel.searchForBook(input);
            }

        });

        viewModel.getLoading().observe(this.getViewLifecycleOwner(), this::setProgressBarVisibility);

        return root;
    }

    private void initViews() {
        searchInput = root.findViewById(R.id.book_search_input);
        searchButton = root.findViewById(R.id.button_search);
        progressBar = root.findViewById(R.id.progressBar);
        loading = root.findViewById(R.id.loading);
    }

    public void setProgressBarVisibility(boolean loading1){
        if (loading1) {
            progressBar.setVisibility(View.VISIBLE);
            loading.setVisibility(View.VISIBLE);
        }
        else {
            progressBar.setVisibility(View.INVISIBLE);
            loading.setVisibility(View.INVISIBLE);
        }
    }
}
