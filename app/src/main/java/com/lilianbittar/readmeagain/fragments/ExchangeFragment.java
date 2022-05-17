package com.lilianbittar.readmeagain.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lilianbittar.readmeagain.R;
import com.lilianbittar.readmeagain.adapters.ExchangeAdapter;
import com.lilianbittar.readmeagain.adapters.ToReadBookAdapter;
import com.lilianbittar.readmeagain.viewmodels.ExchangeViewModel;

public class ExchangeFragment extends Fragment {

    private View root;
    private RecyclerView recyclerView;
    private ExchangeAdapter adapter;
    private ExchangeViewModel viewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_exchange, container, false);

        viewModel = new ViewModelProvider(this).get(ExchangeViewModel.class);
        viewModel.init();
        initViews();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));


        adapter = new ExchangeAdapter();

        recyclerView.setAdapter(adapter);

        viewModel.getBooksToExchange().observe(this.getViewLifecycleOwner(), bookList -> {
            if (bookList != null) {
                adapter.updateBookList(bookList.getBookList());
            }
        });

        return root;
    }

    private void initViews() {
        recyclerView = root.findViewById(R.id.rc_books_exchange);
    }
}
