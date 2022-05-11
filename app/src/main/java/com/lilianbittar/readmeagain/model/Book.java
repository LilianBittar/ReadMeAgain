package com.lilianbittar.readmeagain.model;

public class Book {
    private String Title;
    private String ISBN;
    private String Author;
    private String Genre;
    private String Description;
    private String Picture;
    private String Rating;

    public Book(String Title, String ISBN, String author, String genre, String description, String Picture, String Rating) {
        this.Title = Title;
        this.ISBN = ISBN;
        Author = author;
        Genre = genre;
        Description = description;
        this.Picture = Picture;
        this.Rating = Rating;
    }

    public String getRating() {
        return Rating;
    }

    public void setRating(String rating) {
        Rating = rating;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
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

    public String getPicture() {
        return Picture;
    }

    public void setPicture(String picture) {
        this.Picture = picture;
    }
}
