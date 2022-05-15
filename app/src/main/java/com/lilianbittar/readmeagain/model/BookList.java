package com.lilianbittar.readmeagain.model;

import java.util.ArrayList;

public class BookList {

    private ArrayList<Book> bookList;

    public BookList() {
        this.bookList = new ArrayList<>();
    }

    public BookList(ArrayList<Book> bookList) {
        setBookList(bookList);
    }

    public void setBookList(ArrayList<Book> bookList) {
        this.bookList = bookList;
    }

    public ArrayList<Book> getBookList() {
        return this.bookList;
    }
}
