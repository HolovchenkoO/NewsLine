package com.example.newsline;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.newsline.news.DataNewsResponse;
import com.example.newsline.news.Hit;
import com.example.newsline.news.adapters.NewsAdapter;
import com.example.newsline.news.dummy.DummyContent;
import com.example.newsline.news.entity.News;
import com.example.newsline.news.fragments.NewsFragment;
import com.example.newsline.news.fragments.View_NewsFragment;
import com.example.newsline.news.service.DataNewsService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.orm.SugarContext;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity
    implements View_NewsFragment.OnView_NewsFragmentListener,
            NewsFragment.OnNewsFragmentListener{


        @Override
        protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        SugarContext.init(this);
        setContentView(R.layout.activity_main);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.datanews.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        DataNewsService dataNewsService = retrofit.create(DataNewsService.class);

        dataNewsService.getNews().enqueue(new Callback<DataNewsResponse>() {
            @Override
            public void onResponse(Call<DataNewsResponse> call, Response<DataNewsResponse> response) {
                DataNewsResponse body = response.body();
                List<Hit> news = body.getHits();

                if (DummyContent.ENTITYS.size() != 0) {
                    for (Hit p : news) {
                        try {
                            News n = News.find(News.class, "title=?", p.getTitle()).get(0);
                            p.setViewing(n.getViewing());
                        } catch (Exception n) {
                            n.printStackTrace();
                        }
                    }

                }

                Collections.sort(news);
                DummyContent.ITEMS.addAll(news);
                NewsFragment nf = (NewsFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container_view);
                nf.adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<DataNewsResponse> call, Throwable t) {

            }
        });

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container_view, new NewsFragment(), "LIST_TAG")
                .commit();
    }

        @Override
        public void onBtnBackClick () {
        Collections.sort(DummyContent.ITEMS);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container_view, new NewsFragment(), "LIST_TAG")
                .commit();
    }

        @Override
        public void onItemSelect (Hit item){
        View_NewsFragment f = new View_NewsFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container_view, f, "INFO_TAG")
                .commit();
        f.setData(item);
    }
    }

