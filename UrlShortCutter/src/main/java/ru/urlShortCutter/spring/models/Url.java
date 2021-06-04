package ru.urlShortCutter.spring.models;

public class Url {

    private int id;
    private String sourceUrl;
    private String cutUrl;

    public Url() {
    }

    public Url(int id, String sourceUrl, String cutUrl) {
        this.id = id;
        this.sourceUrl = sourceUrl;
        this.cutUrl = cutUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public String getCutUrl() {
        return cutUrl;
    }

    public void setCutUrl(String cutUrl) {
        this.cutUrl = cutUrl;
    }
}


