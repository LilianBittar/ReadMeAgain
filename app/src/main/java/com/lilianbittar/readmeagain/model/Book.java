package com.lilianbittar.readmeagain.model;

public class Book {
    private String title;
    private long ISBN;
    private String Author;
    private String Genre;
    private String Description;

    public Book(String title, long ISBN, String author, String genre, String description) {
        this.title = title;
        this.ISBN = ISBN;
        Author = author;
        Genre = genre;
        Description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getISBN() {
        return ISBN;
    }

    public void setISBN(long ISBN) {
        this.ISBN = ISBN;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
