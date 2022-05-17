package com.lilianbittar.readmeagain.adapters;

import android.content.Context;
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
import com.lilianbittar.readmeagain.dao.BookToRead;
import com.lilianbittar.readmeagain.dao.ReadBook;
import com.lilianbittar.readmeagain.model.ExchangeBook;

import java.util.ArrayList;
import java.util.List;

public class ExchangeAdapter extends RecyclerView.Adapter<ExchangeAdapter.ViewHandler> {

    private List<ExchangeBook> toExchange;

    public ExchangeAdapter() {
        this.toExchange = new ArrayList<>();
    }


    @NonNull
    @Override
    public ExchangeAdapter.ViewHandler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.rc_item_book_toexchange, parent, false);
        return new ExchangeAdapter.ViewHandler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExchangeAdapter.ViewHandler holder, int position) {

        String coverId = toExchange.get(position).getCoverId();
        if (coverId.isEmpty()) {
            coverId = "https://user-images.githubusercontent.com/24848110/33519396-7e56363c-d79d-11e7-969b-09782f5ccbab.png";
        } else {
            coverId = "https://covers.openlibrary.org/b/id/" + coverId + ".jpg";
        }
        Glide.with(holder.context).load(coverId).into(holder.imageView);
        holder.bookTitle.setText(toExchange.get(position).getTitle());

        String author = "Not found";
        if (toExchange.get(position).getAuthor() != null) {
            author = toExchange.get(position).getAuthor();
        }
        holder.bookAuthor.setText(author);

        String subject = "Not found";
        if (toExchange.get(position).getSubject() != null) {
            subject = toExchange.get(position).getSubject();
        }
        holder.bookGenre.setText(subject);

        String isbn = "Not found";
        if (toExchange.get(position).getIsbn() != null) {
            isbn = toExchange.get(position).getIsbn();
        }
        holder.bookISBN.setText(isbn);
    }

    @Override
    public int getItemCount() {
        if (toExchange != null)
            return toExchange.size();
        return 0;
    }

    public void updateBookList(List<ExchangeBook> bookList) {
        if (bookList != null) {
            this.toExchange = bookList;
            notifyDataSetChanged();
        }
    }

    public class ViewHandler extends RecyclerView.ViewHolder {

        private final ImageView imageView;
        private TextView bookTitle;
        private TextView bookAuthor;
        private TextView bookGenre;
        private TextView bookISBN;
        private Context context;

        public ViewHandler(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            imageView = itemView.findViewById(R.id.book_image);
            bookTitle = itemView.findViewById(R.id.book_title);
            bookAuthor = itemView.findViewById(R.id.book_author);
            bookGenre = itemView.findViewById(R.id.book_genre);
            bookISBN = itemView.findViewById(R.id.book_isbn);

        }
    }
}
