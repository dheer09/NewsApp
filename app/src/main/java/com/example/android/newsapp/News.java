package com.example.android.newsapp;

public class News {

    private String webTitle;
    private String sectionName;
    private String web_URL ;

    public News (String webTitle, String sectionName,String web_URL)
    {
        this.webTitle = webTitle;
        this.sectionName = sectionName;
        this.web_URL = web_URL;
    }

    public String getWebTitle()
    {
        return webTitle;
    }

    public String getSectionName() {
        return sectionName;
    }

    public String getWeb_URL() {
        return web_URL;
    }
}
