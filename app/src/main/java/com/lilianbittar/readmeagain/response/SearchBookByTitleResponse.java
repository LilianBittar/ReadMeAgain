package com.lilianbittar.readmeagain.response;

import com.google.gson.annotations.SerializedName;


import java.util.List;

public class SearchBookByTitleResponse {

   @SerializedName("docs")
   private List<BookValue> books;

   public void setBooks(List<BookValue> books) {
      this.books = books;
   }

   public List<BookValue> getBooks() {
      return books;
   }
}
