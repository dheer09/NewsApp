package com.example.android.newsapp;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class ScienceViewModel extends ViewModel{
    private static final String BASE_URL = "https://content.guardianapis.com/search?q=science&api-key=test";
//    private static final String BASE_URL = "https://content.guardianapis.com/search?q=abdhsuh&api-key=test";

    //This is the data to be fetched .
    public MutableLiveData<List<News>> newsList ;
    private Context context;

    public ScienceViewModel(Context context) {
        this.context = context;
    }

    public LiveData<List<News>> getNews() {

        if (newsList == null) {
            newsList = new MutableLiveData<List<News>>();
            //we will load it asynchronously from server in this method
            loadNews();
            boolean checkList = (newsList == null);

            Log.v("NewsViewModel","List state: " + checkList);
        }

        //finally we will return the list
        return newsList;
    }

    //Pass connectivity status as a boolean.//Pass Context from MainActivity
    private void loadNews() {

        List<News> newsList1 = new ArrayList<>();
        // depending on the action, do necessary business logic calls and update the
        // userLiveData.
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
                                newsList1.add(news);
                            }

                            newsList.setValue(newsList1);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        //adding the string request to request queue
        requestQueue.add(stringRequest);
    }
}
