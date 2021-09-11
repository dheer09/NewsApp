package com.example.android.newsapp;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    //Recyclerview object
    private RecyclerView recyclerView;

    //Relative Layout To store empty list view.
    RelativeLayout relativeLayout;

    //Relative Layout To store No Internet Connection Layout.
    RelativeLayout noconnection;
    //Progress Bar for showing status
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        noconnection = findViewById(R.id.no_internet_view);
        noconnection.setVisibility(View.GONE);
        relativeLayout = findViewById(R.id.empty_stateview);
        relativeLayout.setVisibility(View.GONE);
        progressBar = findViewById(R.id.progress_circular);
        progressBar.setVisibility(View.VISIBLE);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
                false);
        //initializing listview and hero list
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        ConnectivityManager cm =
                (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();


        if (!isConnected) {
            noconnection.setVisibility(View.VISIBLE);
        } else {

            NewsViewModel model = new NewsViewModel(getApplicationContext());

            model.getNews().observe(this, heroList -> {
                NewsAdapter adapter = new NewsAdapter(heroList, MainActivity.this);
                recyclerView.setAdapter(adapter);
                if (heroList.isEmpty()) {
                    relativeLayout.setVisibility(View.VISIBLE);
                }
            });


        }
        progressBar.setVisibility(View.GONE);
    }
}


