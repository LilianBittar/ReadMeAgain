package com.lilianbittar.readmeagain.response;

import com.lilianbittar.readmeagain.model.Book;

public class SearchResponse {

    private String Title;
    private long ISBN;
    private String Author;
    private String Genre;
    private String Description;
    private Medium medium;


    public Book getBook(){
        return new Book(Title, ISBN, Author, Genre, Description, medium.front_default);
    }


    private static class Medium {
        private String front_default;
    }

}
