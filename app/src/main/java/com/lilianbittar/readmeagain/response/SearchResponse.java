package com.lilianbittar.readmeagain.response;

import android.media.Rating;

import com.lilianbittar.readmeagain.model.Book;

public class SearchResponse {

    private String Title;
    private String ISBN;
    private String Author;
    private String Genre;
    private String Description;
    private Medium medium;
    private String Rating;


    public Book getBook(){
        return new Book(Title, ISBN, Author, Genre, Description, medium.front_default, Rating);
    }


    private static class Medium {
        private String front_default;
    }

}
