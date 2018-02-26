package com.example.bindu.homework04;

/**
 * Created by bindu on 2/23/2018.
 */

import java.util.ArrayList;

/**
 * Assignment: Homework04
 * FileName: MainActivity.java
 * Group 15: Hima Bindu Sigili
 *           Bryson Shannon
 */

public class Article {
    String title,description,link,publishedDate,mediaImage;
    public Article() {

    }

    @Override
    public String toString() {
        return "Article{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", link='" + link + '\'' +
                ", publishedDate='" + publishedDate + '\'' +
                ", mediaImage='" + mediaImage + '\'' +
                '}';
    }
}
