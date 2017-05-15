package com.example.lorris.tp_retrofit;

import java.util.ArrayList;

/**
 * Created by Lorris on 12/05/2017.
 */

class SingletonArticle {
    private static final SingletonArticle ourInstance = new SingletonArticle();

    static SingletonArticle getInstance() {
        return ourInstance;
    }

    private SingletonArticle() {
    }

    private ArrayList<Article> articles;

    public ArrayList<Article> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<Article> articleArrayList) {
        articles = articleArrayList;
    }
}
