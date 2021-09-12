package com.example.android.newsapp;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
//import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import org.jetbrains.annotations.NotNull;

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

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        ViewPager2 viewPager2 = findViewById(R.id.viewPager);

        // find the viewPager that will allow the user to swipe between fragments.
        CategoryAdapter categoryAdapter = new CategoryAdapter(this);
        viewPager2.setAdapter(categoryAdapter);

        // creating tabLayout
        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull @NotNull TabLayout.Tab tab, int position) {
                if(position == 0){
                    tab.setText("Trending");
                }
                if(position == 1){
                    tab.setText("Business");
                }
                if(position == 2){
                    tab.setText("Technology");
                }
                if(position == 3){
                    tab.setText("Entertainment");
                }
                if(position == 4){
                    tab.setText("Sports");
                }
                if(position == 5){
                    tab.setText("Science");
                }
                if(position == 6){
                    tab.setText("Health");
                }
            }
        }).attach();
    }
}


