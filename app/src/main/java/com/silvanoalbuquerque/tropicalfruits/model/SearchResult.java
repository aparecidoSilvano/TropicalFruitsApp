package com.silvanoalbuquerque.tropicalfruits.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchResult {
    @SerializedName("results")
    @Expose
    private List<FruitVegetable> results = null;

    @SerializedName("tfvcount")
    @Expose
    private int tfvcount;

    public List<FruitVegetable> getResults() {
        return results;
    }

    public void setResults(List<FruitVegetable> results) {
        this.results = results;
    }

    public int getTfvcount() {
        return tfvcount;
    }

    public void setTfvcount(int tfvcount) {
        this.tfvcount = tfvcount;
    }
}
