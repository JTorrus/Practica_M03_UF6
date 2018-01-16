package com.model;

public class Audio extends Product{
    private int audioId;
    private String type;
    private int soundMode;
    private boolean wireless;
    private boolean microphone;


    public Audio(String productName, String tag, float price, int stock, String type, int soundMode, boolean wireless, boolean microphone) {
        super(productName, tag, price, stock);
        this.type = type;
        this.soundMode = soundMode;
        this.wireless = wireless;
        this.microphone = microphone;
    }


}
