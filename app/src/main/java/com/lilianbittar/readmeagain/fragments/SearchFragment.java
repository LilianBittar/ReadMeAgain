package com.lilianbittar.readmeagain.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.textfield.TextInputLayout;
import com.lilianbittar.readmeagain.R;
import com.lilianbittar.readmeagain.adapters.SearchAdapter;
import com.lilianbittar.readmeagain.model.Book;
import com.lilianbittar.readmeagain.viewmodels.SearchViewModel;

import java.util.ArrayList;

public class SearchFragment extends Fragment {

    private View root;

    private SearchAdapter searchAdapter;
    private RecyclerView recyclerView;
    private SearchViewModel viewModel;

    private TextInputLayout searchInput;
    private Button searchButton;
    private ProgressBar progressBar;
    private TextView loading;
    private ImageView searchLogo;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_search, container, false);

        viewModel = new ViewModelProvider(this).get(SearchViewModel.class);
        initViews();

        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        searchAdapter = new SearchAdapter(viewModel);
        recyclerView.setAdapter(searchAdapter);

        viewModel.getSearchResult().observe(this.getViewLifecycleOwner(), booksResult -> searchAdapter.updateBookList(booksResult));

        searchButton.setOnClickListener(v -> {
            String input = searchInput.getEditText().getText().toString();
            if (input == null) {
                searchInput.setError("Type the name of the book");
                searchInput.requestFocus();
            } else {
                searchLogo.setVisibility(View.INVISIBLE);
                searchAdapter.clearBookList();
                viewModel.searchForBook(input);
            }

        });

        viewModel.getLoading().observe(this.getViewLifecycleOwner(), this::setProgressBarVisibility);

        return root;
    }

    private void initViews() {
        recyclerView = root.findViewById(R.id.rv_search);
        searchInput = root.findViewById(R.id.book_search_input);
        searchButton = root.findViewById(R.id.button_search);
        progressBar = root.findViewById(R.id.progressBar);
        loading = root.findViewById(R.id.loading);
        searchLogo = root.findViewById(R.id.frag_search_logo);
    }

    public void setProgressBarVisibility(boolean isLoading){
        if (isLoading) {
            progressBar.setVisibility(View.VISIBLE);
            loading.setVisibility(View.VISIBLE);
        }
        else {
            progressBar.setVisibility(View.INVISIBLE);
            loading.setVisibility(View.INVISIBLE);
        }
    }
}
