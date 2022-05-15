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
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.lilianbittar.readmeagain.R;
import com.lilianbittar.readmeagain.model.Book;
import com.lilianbittar.readmeagain.viewmodels.SearchViewModel;

import java.util.ArrayList;
import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHandler> {

    private SearchViewModel viewModel;
    private List<Book> books;
    private OnClickListener listener;

    public BookAdapter() {
        this.books = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHandler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.fragment_book_items, parent, false);
        return new ViewHandler(view);
    }

    public void setViewModel(SearchViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHandler holder, int position) {
        holder.position = holder.getAdapterPosition();
        String coverId = books.get(position).getCoverId();
        if (coverId.isEmpty()) {
            coverId = "https://user-images.githubusercontent.com/24848110/33519396-7e56363c-d79d-11e7-969b-09782f5ccbab.png";
        } else {
            coverId = "https://covers.openlibrary.org/b/id/" + coverId + ".jpg";
        }
        Glide.with(holder.context).load(coverId).into(holder.imageView);
        holder.bookTitle.setText(books.get(position).getTitle());

        String author = "Not found";
        if (books.get(position).getAuthor() != null) {
            author = books.get(position).getAuthor().get(0);
        }
        holder.bookAuthor.setText(author);

        String subject = "Not found";
        if (books.get(position).getSubjects() != null) {
            subject = books.get(position).getSubjects().get(0);
        }
        holder.bookGenre.setText(subject);

        String isbn = "Not found";
        if (books.get(position).getIsbns() != null) {
            isbn = books.get(position).getIsbns().get(0);
        }
        holder.bookISBN.setText(isbn);
    }

    @Override
    public int getItemCount() {
        if (books != null)
            return books.size();
        return 0;
    }

    public void updateBookList(final List<Book> bookList) {
        clearBookList();
        this.books = bookList;
        notifyDataSetChanged();
    }

    public void clearBookList() {
        this.books.clear();
    }

    public void setListener(OnClickListener listener){
        this.listener = listener;
    }

    public class ViewHandler extends RecyclerView.ViewHolder {

        private final ImageView imageView;
        int position;
        private TextView bookTitle;
        private TextView bookAuthor;
        private TextView bookGenre;
        private TextView bookISBN;
        private Context context;
        private Button addToRead;

        public ViewHandler(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            imageView = itemView.findViewById(R.id.book_image);
            bookTitle = itemView.findViewById(R.id.book_title);
            bookAuthor = itemView.findViewById(R.id.book_author);
            bookGenre = itemView.findViewById(R.id.book_genre);
            bookISBN = itemView.findViewById(R.id.book_isbn);
            addToRead = itemView.findViewById(R.id.button_add_to_read);
            addToRead.setOnClickListener(view -> {
                viewModel.addBookToRead(books.get(position));
                Toast.makeText(this.context, "added book " + books.get(position).getTitle() + " to read", Toast.LENGTH_LONG).show();
            });
        }
    }

    public interface OnClickListener {
        void onClick(Book book);
    }
}
