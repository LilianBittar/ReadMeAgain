package com.lilianbittar.readmeagain.model;

import com.lilianbittar.readmeagain.dao.ReadBook;
import java.util.ArrayList;

public class BookList {

    private ArrayList<ExchangeBook> bookList;

    public BookList() {
        this.bookList = new ArrayList<>();
    }

    public BookList(ArrayList<ExchangeBook> bookList) {
        setBookList(bookList);
    }

    public void setBookList(ArrayList<ExchangeBook> bookList) {
        this.bookList = bookList;
    }

    public ArrayList<ExchangeBook> getBookList() {
        return this.bookList;
    }
}
