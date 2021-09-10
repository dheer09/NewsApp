package com.example.android.newsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.newsapp.News;
import com.example.android.newsapp.R;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.Viewholder> {

    private Context context;
    private List<News> newsList;

    // Constructor
    public NewsAdapter(List<News> newsList, Context context) {
        this.context = context;
        this.newsList = newsList;
    }

    @NonNull
    @Override
    public NewsAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.Viewholder holder, int position) {
        // to set data to textview and imageview of each card layout
        News news = newsList.get(position);
        holder.web_title.setText(news.getWebTitle());
        holder.section_name.setText(news.getSectionName());
    }

    @Override
    public int getItemCount() {
        // this method is used for showing number
        // of card items in recycler view.
        return newsList.size();
    }

    // View holder class for initializing of
    // your views such as TextView and Imageview.
    public class Viewholder extends RecyclerView.ViewHolder {
        private TextView web_title, section_name;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            web_title = itemView.findViewById(R.id.webTitle);
            section_name = itemView.findViewById(R.id.sectionName);
        }
    }
}