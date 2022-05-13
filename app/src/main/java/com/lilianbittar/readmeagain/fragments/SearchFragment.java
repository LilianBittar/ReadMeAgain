package com.lilianbittar.readmeagain.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_search, container, false);

        viewModel = new ViewModelProvider(this).get(SearchViewModel.class);

        initViews();

        recyclerView = root.findViewById(R.id.rv_search);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        ArrayList<Book> books = new ArrayList<>();


        bookAdapter = new BookAdapter(books);
        recyclerView.setAdapter(bookAdapter);



        viewModel.getSearchedBook().observe(this.getViewLifecycleOwner(), searchedBooks -> {
            books.clear();
            books.addAll(searchedBooks);
            recyclerView.setAdapter(bookAdapter);
        });


        searchButton.setOnClickListener(v -> {
            viewModel.searchForBook(searchInput.getEditText().getText().toString());
        });

        return root;
    }

    private void initViews() {
        searchInput = root.findViewById(R.id.book_search_input);
        searchButton = root.findViewById(R.id.button_search);
    }

}
