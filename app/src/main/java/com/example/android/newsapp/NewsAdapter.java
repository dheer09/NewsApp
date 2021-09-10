package com.example.android.newsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class NewsAdapter extends ArrayAdapter<News> {

    // View lookup cache
    private static class ViewHolder {
        TextView webTitle;
        TextView sectionName;
    }

    //the hero list that will be displayed
    private List<News> newsList;

    //the context object
    private Context mCtx;

    public NewsAdapter( List<News> news, Context context) {
        super(context,R.layout.list_item,news);
        this.mCtx = context;
        this.newsList = news;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        News news = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag
        if (convertView == null) {
            // If there's no view to re-use, inflate a brand new view for row
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(mCtx);
            convertView = inflater.inflate(R.layout.list_item, parent, false);
            viewHolder.webTitle= (TextView) convertView.findViewById(R.id.webTitle);
            viewHolder.sectionName = (TextView) convertView.findViewById(R.id.sectionName);
            // Cache the viewHolder object inside the fresh view
            convertView.setTag(viewHolder);
        } else {
            // View is being recycled, retrieve the viewHolder object from tag
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Populate the data from the data object via the viewHolder object
        // into the template view.
        viewHolder.sectionName.setText(news.getSectionName());
        viewHolder.webTitle.setText(news.getWebTitle());
        // Return the completed view to render on screen
        return convertView;
    }
}
