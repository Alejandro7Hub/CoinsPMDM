package com.example.myapplication;

import android.graphics.drawable.Drawable;

public class ItemsRV {
    private String buttonText;
    private int imagen;
    private String text;

    public ItemsRV(String buttonText, String text, int imagen){
        this.buttonText=buttonText;
        this.text=text;
        this.imagen=imagen;
    }

    public String getButtonText(){
        return buttonText;
    }public String getText(){
        return text;
    }public int getImagen(){
        return imagen;
    }
}
