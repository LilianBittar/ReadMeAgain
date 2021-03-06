package com.lilianbittar.readmeagain.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.lilianbittar.readmeagain.R;
import com.lilianbittar.readmeagain.dao.ReadBook;
import com.lilianbittar.readmeagain.viewmodels.ProfileViewModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReadBookAdapter extends RecyclerView.Adapter<ReadBookAdapter.ViewHandler> {

    private ProfileViewModel viewModel;
    private List<ReadBook> readBookList;

    public ReadBookAdapter(ProfileViewModel viewModel) {
        this.viewModel = viewModel;
        this.readBookList = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHandler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.rc_item_book_read, parent, false);
        return new ReadBookAdapter.ViewHandler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHandler holder, int position) {
        holder.position = holder.getBindingAdapterPosition();
        String coverId = readBookList.get(position).getCoverId();
        if (coverId.isEmpty()) {
            coverId = "https://user-images.githubusercontent.com/24848110/33519396-7e56363c-d79d-11e7-969b-09782f5ccbab.png";
        } else {
            coverId = "https://covers.openlibrary.org/b/id/" + coverId + ".jpg";
        }
        Glide.with(holder.context).load(coverId).into(holder.imageView);
        holder.bookTitle.setText(readBookList.get(position).getTitle());

        String author = "Not found";
        if (readBookList.get(position).getAuthor() != null) {
            author = readBookList.get(position).getAuthor();
        }
        holder.bookAuthor.setText(author);

        String subject = "Not found";
        if (readBookList.get(position).getSubject() != null) {
            subject = readBookList.get(position).getSubject();
        }
        holder.bookGenre.setText(subject);

        String date = new Date().toString();
        if (readBookList.get(position).getReadDate() != null) {
            date = readBookList.get(position).getReadDate();
        }
        holder.bookDate.setText(date.toString());
    }

    @Override
    public int getItemCount() {
        if (readBookList != null)
            return readBookList.size();
        return 0;
    }

    public void updateBookList(List<ReadBook> bookList) {
        if (bookList != null && !bookList.isEmpty()) {
            Log.i("gh", bookList.toString());
            this.readBookList = bookList;
            notifyDataSetChanged();
        }
    }

    public class ViewHandler extends RecyclerView.ViewHolder {

        int position;
        private final ImageView imageView;
        private final TextView bookTitle;
        private final TextView bookAuthor;
        private final TextView bookGenre;
        private final TextView bookDate;
        private final Context context;
        private final Button addToExchange;

        public ViewHandler(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            imageView = itemView.findViewById(R.id.book_image);
            bookTitle = itemView.findViewById(R.id.book_title);
            bookAuthor = itemView.findViewById(R.id.book_author);
            bookGenre = itemView.findViewById(R.id.book_genre);
            bookDate = itemView.findViewById(R.id.book_date);
            addToExchange = itemView.findViewById(R.id.button_add_to_exchange);
            addToExchange.setOnClickListener(view -> {
              viewModel.markBookAsExchange(readBookList.get(position));
                Toast.makeText(this.context, "added book " + readBookList.get(position).getTitle() + " to exchange", Toast.LENGTH_LONG).show();
            });
        }
    }
}
