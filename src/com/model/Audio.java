package com.model;

public class Audio extends Product{
    private int headphonesId;
    private String type;
    private int frequency;
    private boolean wireless;
    private boolean microphone;


    public Audio(String productName, String tag, float price, int stock, String type, int frequency, boolean wireless, boolean microphone) {
        super(productName, tag, price, stock);
        this.type = type;
        this.frequency = frequency;
        this.wireless = wireless;
        this.microphone = microphone;
    }


}
