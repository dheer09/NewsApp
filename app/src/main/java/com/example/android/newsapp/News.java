package com.example.android.newsapp;

public class News {

    private String webTitle;
    private String sectionName;

    public News (String webTitle, String sectionName)
    {
        this.webTitle = webTitle;
        this.sectionName = sectionName;
    }

    public String getWebTitle()
    {
        return webTitle;
    }

    public String getSectionName() {
        return sectionName;
    }
}
