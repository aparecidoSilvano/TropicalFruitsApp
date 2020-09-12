package com.silvanoalbuquerque.tropicalfruits.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FruitVegetableModel implements Comparable<FruitVegetableModel> {
    @SerializedName("tfvname")
    @Expose
    private String tfvname;

    @SerializedName("botname")
    @Expose
    private String botname;

    @SerializedName("othname")
    @Expose
    private String othname;

    @SerializedName("imageurl")
    @Expose
    private String imageurl;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("uses")
    @Expose
    private String uses;

    @SerializedName("propagation")
    @Expose
    private String propagation;

    @SerializedName("soil")
    @Expose
    private String soil;

    @SerializedName("climate")
    @Expose
    private String climate;

    @SerializedName("health")
    @Expose
    private String health;

    public String getTfvname() {
        return tfvname;
    }

    public void setTfvname(String tfvname) {
        this.tfvname = tfvname;
    }

    public String getBotname() {
        return botname;
    }

    public void setBotname(String botname) {
        this.botname = botname;
    }

    public String getOthname() {
        return othname;
    }

    public void setOthname(String othname) {
        this.othname = othname;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUses() {
        return uses;
    }

    public void setUses(String uses) {
        this.uses = uses;
    }

    public String getPropagation() {
        return propagation;
    }

    public void setPropagation(String propagation) {
        this.propagation = propagation;
    }

    public String getSoil() {
        return soil;
    }

    public void setSoil(String soil) {
        this.soil = soil;
    }

    public String getClimate() {
        return climate;
    }

    public void setClimate(String climate) {
        this.climate = climate;
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    @Override
    public int compareTo(FruitVegetableModel anotherModel) {
        return getBotname().toUpperCase().compareTo(anotherModel.getBotname().toUpperCase());
    }
}
