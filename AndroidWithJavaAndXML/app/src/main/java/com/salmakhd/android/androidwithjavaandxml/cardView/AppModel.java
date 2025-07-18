package com.salmakhd.android.androidwithjavaandxml.cardView;

public class AppModel {
    private String title;
    private int numOfDownloads;
    private int thumbnail;

    public AppModel(String title, int numOfDownloads, int thumbnail) {
        this.title = title;
        this.numOfDownloads = numOfDownloads;
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumOfDownloads() {
        return numOfDownloads;
    }

    public void setNumOfDownloads(int numOfDownloads) {
        this.numOfDownloads = numOfDownloads;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}
