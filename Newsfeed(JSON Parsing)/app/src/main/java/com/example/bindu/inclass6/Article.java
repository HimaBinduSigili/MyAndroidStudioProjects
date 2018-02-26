package com.example.bindu.inclass6;

/**
 * Assignment: Inclass 06
 * FileName: MainActivity.java
 * Group 15: Hima Bindu Sigili
 *           Bryson Shannon
 */

public class Article {
    String author,title,description,url,urlToImage,publishedAt;
    Source source;

    public Article() {
    }

    @Override
    public String toString() {
        return "Article{" +
                "author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", urlToImage='" + urlToImage + '\'' +
                ", publishedAt='" + publishedAt + '\'' +
                ", source=" + source +
                '}';
    }
}
