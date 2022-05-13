package com.lilianbittar.readmeagain.network.responses;

import com.google.gson.annotations.SerializedName;
import com.lilianbittar.readmeagain.model.Book;
import java.util.List;

public class SearchBookByTitleResponse {

   @SerializedName("docs")
   private List<Book> books;

   public void setBooks(List<Book> books) {
      this.books = books;
   }

   public List<Book> getBooks() {
      return books;
   }
}
