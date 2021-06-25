package com.example.newsline.news.service;

import com.example.newsline.news.DataNewsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface DataNewsService {
    @Headers("x-api-key: 0jlbz90yzy7dby43yx4pdl94f")
    @GET("v1/news")
  //  Call<DataNewsResponse> getNews(@Query("q") String query, @Query("from") String dateFrom);
    Call<DataNewsResponse> getNews();
}
