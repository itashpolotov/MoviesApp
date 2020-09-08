package com.example.moviesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity implements
        CompoundButton.OnCheckedChangeListener {
    RecyclerView recyclerView;
    private SwipeRefreshLayout refreshLayout;
    String sort_by;
    SwitchCompat switchCompat;
    TextView popular;
    TextView topRated;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        popular=(TextView) findViewById(R.id.popular);
       topRated=(TextView) findViewById(R.id.topRated);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        refreshLayout=findViewById(R.id.swipe_refresh);
        refreshLayout.setColorSchemeResources(R.color.colorAccent);

        sort_by = "popularity.desc";
        popular.setTextColor(Color.RED);
        loadMovies(sort_by);

        switchCompat=findViewById(R.id.switch1);

        switchCompat.setOnCheckedChangeListener(this);




        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadMovies(sort_by);
            }
        });

    }

    public void loadMovies(String sort_by) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Utils.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Json api = retrofit.create(Json.class);

        Call<Pages> request;


        request = api.getMovies(Utils.api_key,sort_by);

        request.enqueue(new Callback<Pages>() {
            @Override
            public void onResponse(Call<Pages> call, Response<Pages> response) {
                if (response.isSuccessful()) {
                    Pages pages = response.body();
                    List<Results> results = pages.getResults();
                    refreshLayout.setRefreshing(false);

                    Adapter adapter = new Adapter(MainActivity.this, results);
                    recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this,2));
                    recyclerView.setAdapter(adapter);
                }


            }

            @Override
            public void onFailure(Call<Pages> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }



    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean ischecked) {
      if (ischecked) {
          sort_by = "vote_average.desc";
          loadMovies(sort_by);
          topRated.setTextColor(Color.RED);
          popular.setTextColor(Color.WHITE);
      }else{
          sort_by = "popularity.desc";
          loadMovies(sort_by);
          topRated.setTextColor(Color.WHITE);
          popular.setTextColor(Color.RED);

      }

    }


}