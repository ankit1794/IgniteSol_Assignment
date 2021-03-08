package com.ankit.ignitesolassignment.model;

import java.util.List;

public class Results {
    private int id;

    private List<Authors> authors;

    private List<String> bookshelves;

    private int download_count;

    private Formats formats;

    private List<String> languages;

    private String media_type;

    private List<String> subjects;

    private String title;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setAuthors(List<Authors> authors) {
        this.authors = authors;
    }

    public List<Authors> getAuthors() {
        return this.authors;
    }

    public void setBookshelves(List<String> bookshelves) {
        this.bookshelves = bookshelves;
    }

    public List<String> getBookshelves() {
        return this.bookshelves;
    }

    public void setDownload_count(int download_count) {
        this.download_count = download_count;
    }

    public int getDownload_count() {
        return this.download_count;
    }

    public void setFormats(Formats formats) {
        this.formats = formats;
    }

    public Formats getFormats() {
        return this.formats;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public List<String> getLanguages() {
        return this.languages;
    }

    public void setMedia_type(String media_type) {
        this.media_type = media_type;
    }

    public String getMedia_type() {
        return this.media_type;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }

    public List<String> getSubjects() {
        return this.subjects;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }
}

