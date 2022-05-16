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
import com.lilianbittar.readmeagain.viewmodels.ProfileViewModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ToReadBookAdapter extends RecyclerView.Adapter<ToReadBookAdapter.ViewHandler> {
    private ProfileViewModel viewModel;
    private List<BookToRead> ToReadBookList;

    public ToReadBookAdapter() {
        this.ToReadBookList = new ArrayList<>();
    }

    @NonNull
    @Override
    public ToReadBookAdapter.ViewHandler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.fragment_book_items_toread, parent, false);
        return new ToReadBookAdapter.ViewHandler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ToReadBookAdapter.ViewHandler holder, int position) {
        holder.position = holder.getAdapterPosition();
        String coverId = ToReadBookList.get(position).getCoverId();
        if (coverId.isEmpty()) {
            coverId = "https://user-images.githubusercontent.com/24848110/33519396-7e56363c-d79d-11e7-969b-09782f5ccbab.png";
        } else {
            coverId = "https://covers.openlibrary.org/b/id/" + coverId + ".jpg";
        }
        Glide.with(holder.context).load(coverId).into(holder.imageView);
        holder.bookTitle.setText(ToReadBookList.get(position).getTitle());

        String author = "Not found";
        if (ToReadBookList.get(position).getAuthor() != null) {
            author = ToReadBookList.get(position).getAuthor();
        }
        holder.bookAuthor.setText(author);

        String subject = "Not found";
        if (ToReadBookList.get(position).getSubject() != null) {
            subject = ToReadBookList.get(position).getSubject();
        }
        holder.bookGenre.setText(subject);

        String isbn = "Not found";
        if (ToReadBookList.get(position).getIsbn() != null) {
            isbn = ToReadBookList.get(position).getIsbn();
        }
        holder.bookISBN.setText(isbn);
    }



    public void setViewModel(ProfileViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public int getItemCount() {
        if (ToReadBookList != null)
            return ToReadBookList.size();
        return 0;
    }

    public void updateBookList(final List<BookToRead> bookList) {
        clearBookList();
        this.ToReadBookList = bookList;
        notifyDataSetChanged();
    }

    public void clearBookList() {
       // this.ToReadBookList.clear();
    }

public class ViewHandler extends RecyclerView.ViewHolder {

    private final ImageView imageView;
    int position;
    private TextView bookTitle;
    private TextView bookAuthor;
    private TextView bookGenre;
    private TextView bookISBN;
    private Context context;
    private Button addToExchange;

    public ViewHandler(@NonNull View itemView) {
        super(itemView);
        context = itemView.getContext();
        imageView = itemView.findViewById(R.id.book_image);
        bookTitle = itemView.findViewById(R.id.book_title);
        bookAuthor = itemView.findViewById(R.id.book_author);
        bookGenre = itemView.findViewById(R.id.book_genre);
        bookISBN = itemView.findViewById(R.id.book_isbn);
        addToExchange = itemView.findViewById(R.id.button_add_to_exchange);
        addToExchange.setOnClickListener(view -> {
//                viewModel.addBookToRead(books.get(position));
            Toast.makeText(this.context, "added book " + ToReadBookList.get(position).getTitle() + " to read", Toast.LENGTH_LONG).show();
        });
    }
}
}


