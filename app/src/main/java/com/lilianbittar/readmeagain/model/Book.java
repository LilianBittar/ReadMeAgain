package com.lilianbittar.readmeagain.model;

public class Book {
    private String title;
    private String isbn;
    private String author_name;
    private String subject;
    private String picture;
    private String Rating;

    public Book(String title, String isbn, String author_name, String subject,  String picture, String Rating) {
        this.title = title;
        this.isbn = isbn;
        this.author_name = author_name;
        this.subject = subject;
        this.picture = picture;
        this.Rating = Rating;
    }

    public String getRating() {
        return Rating;
    }

    public void setRating(String rating) {
        Rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getISBN() {
        return isbn;
    }

    public void setISBN(String ISBN) {
        this.isbn = ISBN;
    }

    public String getAuthor() {
        return author_name;
    }

    public void setAuthor(String author) {
        author_name = author;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String genre) {
        subject = genre;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
