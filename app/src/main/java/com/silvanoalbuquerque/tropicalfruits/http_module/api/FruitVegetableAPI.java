package com.silvanoalbuquerque.tropicalfruits.http_module.api;

import com.silvanoalbuquerque.tropicalfruits.model.SearchResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FruitVegetableAPI {
    @GET("tfvjsonapi.php")
    Call<SearchResult> searchFruitOrVegetable(@Query("search") String search);

    @GET("tfvjsonapi.php")
    Call<SearchResult> getFruitOrVegetable(@Query("tfvitem") String itemName);
}