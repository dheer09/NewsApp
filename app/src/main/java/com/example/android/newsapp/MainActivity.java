package com.example.android.newsapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static String BASE_URL = "https://content.guardianapis.com/search?api-key=test";
//    private static String BASE_URL = "https://content.guardianapis.com/search?q=aadsfndjfnkjdn&api-key=test"; //To check for Empty State View

    //Recyclerview object
    private RecyclerView recyclerView;

    //the hero list where we will store all the hero objects after parsing json
    List<News> newsList;

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


        //initializing listview and hero list
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        newsList = new ArrayList<>();

        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
                false);

        // in below two lines we are setting layoutmanager and adapter to our recycler view.
        recyclerView.setLayoutManager(linearLayoutManager);

        //this method will fetch and parse the data
        loadNewsList();


    }

    private void loadNewsList() {

        ConnectivityManager cm =
                (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();


        if (!isConnected) {
            noconnection.setVisibility(View.VISIBLE);
        }
        else {
            
            //creating a string request to send request to the url
            StringRequest stringRequest = new StringRequest(Request.Method.GET, BASE_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
//                        //hiding the progressbar after completion
//                        progressBar.setVisibility(View.INVISIBLE);


                            try {
                                //getting the whole json object from the response
                                JSONObject obj1 = new JSONObject(response);

                                JSONObject obj = obj1.getJSONObject("response");

                                //we have the array named hero inside the object
                                //so here we are getting that json array
                                JSONArray newsArray = obj.getJSONArray("results");

                                //now looping through all the elements of the json array
                                for (int i = 0; i < newsArray.length(); i++) {
                                    //getting the json object of the particular index inside the array
                                    JSONObject newsObject = newsArray.getJSONObject(i);

                                    //creating a newsobject and giving them the values from json object
                                    News news = new News(newsObject.getString("webTitle"),
                                            newsObject.getString("sectionName"), newsObject.getString("webUrl"));

                                    //adding the news to newslist
                                    newsList.add(news);
                                }

                                //creating custom adapter object
                                NewsAdapter adapter = new NewsAdapter(newsList, getApplicationContext());

                                //adding the adapter to listview
                                recyclerView.setAdapter(adapter);


                                if (newsList.isEmpty()) {

                                    relativeLayout.setVisibility(View.VISIBLE);
                                    recyclerView.setVisibility(View.GONE);
                                }


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //displaying the error in toast if occurrs
                            Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

            //creating a request queue
            RequestQueue requestQueue = Volley.newRequestQueue(this);

            //adding the string request to request queue
            requestQueue.add(stringRequest);
        }

        progressBar.setVisibility(View.GONE);
    }
}
