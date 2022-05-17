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
import com.lilianbittar.readmeagain.dao.BookToRead;
import com.lilianbittar.readmeagain.viewmodels.ProfileViewModel;
import java.util.ArrayList;
import java.util.List;

public class ToReadBookAdapter extends RecyclerView.Adapter<ToReadBookAdapter.ViewHandler> {

    private final ProfileViewModel viewModel;
    private List<BookToRead> toReadBookList;

    public ToReadBookAdapter(ProfileViewModel viewModel) {
        this.viewModel = viewModel;
        this.toReadBookList = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHandler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.rc_item_book_toread, parent, false);
        return new ToReadBookAdapter.ViewHandler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHandler holder, int position) {
        holder.position = holder.getBindingAdapterPosition();
        String coverId = toReadBookList.get(position).getCoverId();
        if (coverId.isEmpty()) {
            coverId = "https://user-images.githubusercontent.com/24848110/33519396-7e56363c-d79d-11e7-969b-09782f5ccbab.png";
        } else {
            coverId = "https://covers.openlibrary.org/b/id/" + coverId + ".jpg";
        }
        Glide.with(holder.context).load(coverId).into(holder.imageView);
        holder.bookTitle.setText(toReadBookList.get(position).getTitle());

        String author = "Not found";
        if (toReadBookList.get(position).getAuthor() != null) {
            author = toReadBookList.get(position).getAuthor();
        }
        holder.bookAuthor.setText(author);

        String subject = "Not found";
        if (toReadBookList.get(position).getSubject() != null) {
            subject = toReadBookList.get(position).getSubject();
        }
        holder.bookGenre.setText(subject);

        String isbn = "Not found";
        if (toReadBookList.get(position).getIsbn() != null) {
            isbn = toReadBookList.get(position).getIsbn();
        }
        holder.bookISBN.setText(isbn);
    }

    @Override
    public int getItemCount() {
        if (toReadBookList != null)
            return toReadBookList.size();
        return 0;
    }

    public void updateBookList(List<BookToRead> bookList) {
        if (bookList != null) {
            this.toReadBookList = bookList;
            notifyDataSetChanged();
        }
    }

    public class ViewHandler extends RecyclerView.ViewHolder {

        int position;
        private final ImageView imageView;
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
                viewModel.markBookAsRead(toReadBookList.get(position));
                Toast.makeText(this.context, "Marked read", Toast.LENGTH_LONG).show();
            });
        }
    }
}


