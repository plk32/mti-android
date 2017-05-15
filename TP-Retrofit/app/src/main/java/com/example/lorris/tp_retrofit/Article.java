package com.example.lorris.tp_retrofit;

/**
 * Created by Lorris on 12/05/2017.
 */

public class Article {
    private final String uri;
    private final String title;
    private final String auteur;
    private final String type;

    public String getUri() {
        return uri;
    }

    public String getAuthor() {
        return auteur;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public Article(String uri, String title, String auteur, String type) {
        this.uri = uri;
        this.title = title;
        this.auteur = auteur;
        this.type = type;
    }
}
