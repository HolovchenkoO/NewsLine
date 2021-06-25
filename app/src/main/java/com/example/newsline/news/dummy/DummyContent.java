package com.example.newsline.news.dummy;

import android.util.Log;

import com.example.newsline.news.Hit;
import com.example.newsline.news.entity.News;

import java.util.ArrayList;
import java.util.List;

public class DummyContent {
    public static final List<Hit> ITEMS = new ArrayList<>();
    public static final List<News> ENTITYS = new ArrayList<>();

    static {
        try{
            ENTITYS.addAll(News.listAll(News.class));
        } catch (Exception  e) {
            e.printStackTrace();
            Log.e("LE", "Exception: " + e);

        }

    }
}
