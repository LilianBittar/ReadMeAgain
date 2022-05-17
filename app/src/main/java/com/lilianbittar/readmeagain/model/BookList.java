package com.lilianbittar.readmeagain.model;

import com.lilianbittar.readmeagain.dao.ReadBook;
import java.util.ArrayList;

public class BookList {

    private ArrayList<ReadBook> bookList;

    public BookList() {
        this.bookList = new ArrayList<>();
    }

    public BookList(ArrayList<ReadBook> bookList) {
        setBookList(bookList);
    }

    public void setBookList(ArrayList<ReadBook> bookList) {
        this.bookList = bookList;
    }

    public ArrayList<ReadBook> getBookList() {
        return this.bookList;
    }
}
