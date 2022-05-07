package com.lilianbittar.readmeagain;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lilianbittar.readmeagain.model.Book;

import java.util.List;

public class bookAdapter extends RecyclerView.Adapter<bookAdapter.ViewHolder> {

    private List<Book> bookList;
    private OnClickListener onClickListener;

    public bookAdapter(List<Book> bookList, OnClickListener onClickListener){
        this.bookList = bookList;
        this.onClickListener = onClickListener;
    }

    public void setOnClickListener(OnClickListener listener){
        this.onClickListener = listener;
    }


    @NonNull
    public bookAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.fragment_book_items, parent, false);
        return new ViewHolder(view);
    }


    public void onBindViewHolder(@NonNull bookAdapter.ViewHolder holder, int position) {
       holder.name.setText(bookList.get(position).getTitle());

    }


    public int getItemCount() {
        return bookList.size();
    }

     class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView image;
        public ViewHolder( View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.book_title);
            image = itemView.findViewById(R.id.book_image);
            itemView.setOnClickListener(v -> {
                onClickListener.onClick(getAdapterPosition());
            });
        }
    }
    public interface OnClickListener {
        void onClick(int position);
    }
}
