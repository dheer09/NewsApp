package com.example.android.newsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class NewsAdapter extends ArrayAdapter<News> {

    //the hero list that will be displayed
    private List<News> newsList;

    //the context object
    private Context mCtx;

    public NewsAdapter( List<News> news, Context context) {
        super(context,R.layout.list_item,news);
        this.mCtx = context;
        this.newsList = news;
    }
    //this method will return the list item
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //getting the layoutinflater
        LayoutInflater inflater = LayoutInflater.from(mCtx);

        //creating a view with our xml layout
        View listViewItem = inflater.inflate(R.layout.list_item, null, true);

        //getting text views
        TextView webTitle = listViewItem.findViewById(R.id.webTitle);
        TextView sectionName = listViewItem.findViewById(R.id.sectionName);

        //Getting the hero for the specified position
        News news = newsList.get(position);

        //setting hero values to textviews
        webTitle.setText(news.getWebTitle());
         sectionName.setText(news.getSectionName());

        //returning the listitem
        return listViewItem;
    }
}
