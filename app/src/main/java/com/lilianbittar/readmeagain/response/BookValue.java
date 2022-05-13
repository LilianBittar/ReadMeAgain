package com.lilianbittar.readmeagain.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BookValue {

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("number_of_pages_median")
    @Expose
    private Long number_of_pages_median;

    @SerializedName("isbn")
    @Expose
    private List<String> isbns;

    @SerializedName("author_name")
    @Expose
    private List<String> author;

    @SerializedName("subject")
    @Expose
    private List<String> subjects;

    @SerializedName("cover_i")
    @Expose
    private String coverId = "";

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

    public List<String> getIsbns() {
        return isbns;
    }

    public void setIsbns(List<String> isbns) {
        this.isbns = isbns;
    }

    public List<String> getAuthor() {
        return author;
    }

    public void setAuthor(List<String> author) {
        this.author = author;
    }

    public List<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }

    public String getCoverId() {
        return coverId;
    }

    public void setCoverId(String coverId) {
        this.coverId = coverId;
    }
}
