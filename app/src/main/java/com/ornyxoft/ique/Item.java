package com.ornyxoft.ique;

import android.media.Image;

public class Item {

    private String title;
    private String description;
    private boolean correct;

    public Item(String title, String description,boolean correct) {
        super();
        this.title = title;
        this.description = description;

        this.correct = correct;
    }
    // getters and setters...
    public String getTitle(){
        return this.title;
    }

    public String getDescription(){
        return  this.description;
    }

    public boolean getCorrectStatus(){
        return this.correct;
    }
}