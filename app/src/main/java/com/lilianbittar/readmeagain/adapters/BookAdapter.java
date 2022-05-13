package com.lilianbittar.readmeagain.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.lilianbittar.readmeagain.R;
import com.lilianbittar.readmeagain.model.Book;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHandler> {

    private List<Book> books;
    private OnClickListener listener;

    public BookAdapter(List<Book> books){
        this.books = books;
    }

    @NonNull
    @Override
    public ViewHandler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.fragment_book_items, parent, false);
        return new ViewHandler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHandler holder, int position) {
        String coverId = books.get(position).getCoverId();
        if (coverId.isEmpty()) {
            coverId = "https://user-images.githubusercontent.com/24848110/33519396-7e56363c-d79d-11e7-969b-09782f5ccbab.png";
        } else {
            coverId = "https://covers.openlibrary.org/b/id/" + coverId + ".jpg";
        }
        Glide.with(holder.context).load(coverId).into(holder.imageView);
        holder.bookTitle.setText(books.get(position).getTitle());
        holder.bookAuthor.setText(books.get(position).getAuthor().get(0));
        holder.bookGenre.setText(books.get(position).getSubjects().get(0));
        holder.bookISBN.setText(books.get(position).getIsbns().get(0));
        holder.bookDescription.setText("sally");
    }

    @Override
    public int getItemCount() {
        if (books != null) {
            return books.size();
        }
        return 0;
    }

    public void setListener(OnClickListener listener){
        this.listener = listener;
    }

    public class ViewHandler extends RecyclerView.ViewHolder {

        private final ImageView imageView;
        private TextView bookTitle;
        private TextView bookAuthor;
        private TextView bookGenre;
        private TextView bookISBN;
        private TextView bookDescription;
        private TextView bookRating;
        private Context context;

        public ViewHandler(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            imageView = itemView.findViewById(R.id.book_image);
            bookTitle = itemView.findViewById(R.id.book_title);
            bookAuthor = itemView.findViewById(R.id.book_author);
            bookGenre = itemView.findViewById(R.id.book_genre);
            bookISBN = itemView.findViewById(R.id.book_isbn);
            bookDescription = itemView.findViewById(R.id.book_description);
            bookRating = itemView.findViewById(R.id.book_rating);

//            itemView.setOnClickListener(view -> {
//                listener.onClick(books.get(getBindingAdapterPosition()));
//            });
        }
    }

    public interface OnClickListener
    {
        void onClick(Book book);
    }
}
