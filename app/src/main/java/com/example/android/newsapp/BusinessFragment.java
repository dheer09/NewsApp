package com.example.android.newsapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class BusinessFragment extends Fragment {

    //Recyclerview object
    private RecyclerView recyclerView;

    //Relative Layout To store empty list view.
    RelativeLayout relativeLayout;

    //Relative Layout To store No Internet Connection Layout.
    RelativeLayout noconnection;
    //Progress Bar for showing status
    ProgressBar progressBar;


    public BusinessFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.news_list, container, false);

        noconnection = rootView.findViewById(R.id.no_internet_view);
        noconnection.setVisibility(View.GONE);
        relativeLayout = rootView.findViewById(R.id.empty_stateview);
        relativeLayout.setVisibility(View.GONE);
        progressBar = rootView.findViewById(R.id.progress_circular);
        progressBar.setVisibility(View.VISIBLE);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,
                false);
        //initializing listview and hero list
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        ConnectivityManager cm =
                (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();


        if (!isConnected) {
            noconnection.setVisibility(View.VISIBLE);
        } else {

            BusinessViewModel model = new BusinessViewModel(getActivity());

            model.getNews().observe(getViewLifecycleOwner(), heroList -> {
                NewsAdapter adapter = new NewsAdapter(heroList, getActivity());
                recyclerView.setAdapter(adapter);
                if (heroList.isEmpty()) {
                    relativeLayout.setVisibility(View.VISIBLE);
                }
            });


        }
        progressBar.setVisibility(View.GONE);
        return rootView;

    }
}