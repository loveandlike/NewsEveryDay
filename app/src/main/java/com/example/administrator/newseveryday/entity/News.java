package com.example.administrator.newseveryday.entity;

/**
 * Created by Administrator on 2016/7/13.
 */
public class News {
    private String summary;
    private String icon;
    private String stamp;
    private String title;
    private int nid;
    private String link;
    private int type;

    public News() {
    }

    @Override
    public String toString() {
        return "News{" +
                "icon='" + icon + '\'' +
                ", summary='" + summary + '\'' +
                ", stamp='" + stamp + '\'' +
                ", title='" + title + '\'' +
                ", nid=" + nid +
                ", link='" + link + '\'' +
                ", type=" + type +
                '}';
    }

    public News(String icon, String link, int nid, String stamp, String summary, String title, int type) {
        this.icon = icon;
        this.link = link;
        this.nid = nid;
        this.stamp = stamp;
        this.summary = summary;
        this.title = title;
        this.type = type;

    }


    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getNid() {
        return nid;
    }

    public void setNid(int nid) {
        this.nid = nid;
    }

    public String getStamp() {
        return stamp;
    }

    public void setStamp(String stamp) {
        this.stamp = stamp;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
