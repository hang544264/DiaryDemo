package com.example.diarydemo;

public class Diary {

    private int id;
    private String headline;
    private String content;
    public Diary(int id,String headline,String content){
        this.id = id;
        this.headline = headline;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
