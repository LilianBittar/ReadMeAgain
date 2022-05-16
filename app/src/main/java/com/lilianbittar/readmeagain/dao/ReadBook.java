package com.lilianbittar.readmeagain.dao;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.util.Date;

@Entity(tableName = "read_books")
public class ReadBook {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private Long number_of_pages_median;
    private String isbn;
    private String author;
    private String subject;
    private String coverId;
    private String readDate;

    public ReadBook(String title, Long number_of_pages_median, String isbn, String author, String subject, String coverId, String readDate) {
        this.title = title;
        this.number_of_pages_median = number_of_pages_median;
        this.isbn = isbn;
        this.author = author;
        this.subject = subject;
        this.coverId = coverId;
        this.readDate = readDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getNumber_of_pages_median() {
        return number_of_pages_median;
    }

    public void setNumber_of_pages_median(Long number_of_pages_median) {
        this.number_of_pages_median = number_of_pages_median;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getCoverId() {
        return coverId;
    }

    public void setCoverId(String coverId) {
        this.coverId = coverId;
    }

    public String getReadDate() {
        return readDate;
    }

    public void setReadDate(String readDate) {
        this.readDate = readDate;
    }
}
