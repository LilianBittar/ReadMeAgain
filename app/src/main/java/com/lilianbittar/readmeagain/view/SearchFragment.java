package com.lilianbittar.readmeagain.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lilianbittar.readmeagain.R;
import com.lilianbittar.readmeagain.model.Book;
import com.lilianbittar.readmeagain.model.BookAdapter;
import com.lilianbittar.readmeagain.viewModel.SearchViewModel;

public class SearchFragment extends Fragment {

    private BookAdapter bookAdapter;
    private RecyclerView recyclerView;
    private SearchViewModel searchViewModel;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_search, container, false);
        recyclerView = root.findViewById(R.id.rv_search);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));


        return root;


    }


}
