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
import com.lilianbittar.readmeagain.adapters.BookAdapter;
import com.lilianbittar.readmeagain.adapters.ToReadBookAdapter;
import com.lilianbittar.readmeagain.viewmodels.ProfileViewModel;
import com.lilianbittar.readmeagain.viewmodels.SearchViewModel;

public class ToReadFragment extends Fragment {

    private ProfileViewModel viewModel;

    private View root;
    private RecyclerView recyclerView;
    private ToReadBookAdapter toReadBookAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_book_toread, container, false);

        viewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        initViews();

        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        toReadBookAdapter = new ToReadBookAdapter();
        recyclerView.setAdapter(toReadBookAdapter);
        toReadBookAdapter.updateBookList(viewModel.getToReadBooks().getValue());

        viewModel.getToReadBooks().observe(this.getViewLifecycleOwner(), bookList -> {
            toReadBookAdapter.updateBookList(bookList);
        });

        return root;
    }

    private void initViews() {
        recyclerView = root.findViewById(R.id.frag_book_toread);
    }


}
