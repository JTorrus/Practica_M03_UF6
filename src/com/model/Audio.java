package com.model;

public class Audio extends Product{
    private String type;
    private int soundMode;
    private boolean wireless;
    private boolean microphone;


    public Audio(int audioId, String name, String brand, float price, int stock, String type, int soundMode, boolean wireless, boolean microphone) {
        super(audioId, name, brand, price, stock);
        this.type = type;
        this.soundMode = soundMode;
        this.wireless = wireless;
        this.microphone = microphone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSoundMode() {
        return soundMode;
    }

    public void setSoundMode(int soundMode) {
        this.soundMode = soundMode;
    }

    public boolean isWireless() {
        return wireless;
    }

    public void setWireless(boolean wireless) {
        this.wireless = wireless;
    }

    public boolean isMicrophone() {
        return microphone;
    }

    public void setMicrophone(boolean microphone) {
        this.microphone = microphone;
    }
}
