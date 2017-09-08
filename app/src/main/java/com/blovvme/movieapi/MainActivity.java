package com.blovvme.movieapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.blovvme.movieapi.model.MovieApi;
import com.blovvme.movieapi.model.Result;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    public static final String URL = "https://api.themoviedb.org/3/search/movie?api_key=6d8c542844bdb85bf0e282aa1a67abe2&query=Jack";
    //public static final String URL = "https://api.themoviedb.org/3/movie/550?api_key=6d8c542844bdb85bf0e282aa1a67abe2";
    List<Result> results;
    RecyclerView revyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.ItemAnimator itemAnimator;
    MovieAdapter movieAdapter;
    MovieApi movieApi;

    EditText etSearch;
    Button btnSearch;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //etSearch = (EditText)findViewById(R.id.etSearch);
        //btnSearch = (Button)findViewById(R.id.btnSearch);

        revyclerView = (RecyclerView) findViewById(R.id.rvLayout);
        layoutManager = new LinearLayoutManager(this);
        itemAnimator = new DefaultItemAnimator();
        revyclerView.setLayoutManager(layoutManager);
        revyclerView.setItemAnimator(itemAnimator);


        //Enqueue
        OkHttpClient okHttpClient;
        Request request;
        okHttpClient = new OkHttpClient();
        request = new Request.Builder().url(URL).build();


        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Gson gson = new Gson();
                MovieApi movieApi;
                movieApi = gson.fromJson(response.body().string(), MovieApi.class);
                results = movieApi.getResults();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        movieAdapter = new MovieAdapter(results, getApplicationContext());
                        revyclerView.setAdapter(movieAdapter);
                        movieAdapter.notifyDataSetChanged();

                    }
                });
            }
        });

    }

    public void isClick(View view) {

    }
}//
