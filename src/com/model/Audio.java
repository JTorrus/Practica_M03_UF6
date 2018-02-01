package com.model;

public class Audio extends Product {
    private String type;
    private String soundMode;
    private boolean wireless;
    private boolean microphone;


    public Audio(int audioId, String name, String brand, float price, int stock, String type, String soundMode, boolean wireless, boolean microphone) {
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

    public String getSoundMode() {
        return soundMode;
    }

    public void setSoundMode(String soundMode) {
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

    @Override
    public String toString() {
        String isWireless;
        String hasMicro;

        if (wireless){
            isWireless = "yes";
        }else{
            isWireless = "no";
        }
        if (microphone){
            hasMicro = "yes";
        }else{
            hasMicro = "no";
        }

        return super.toString() + " Type: " + type + ", Sound Mode: " + soundMode + ", Wireless: " + isWireless + ", Microphone: " + hasMicro;
    }
}
