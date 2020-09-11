package com.silvanoalbuquerque.tropicalfruits.http_module;

import com.silvanoalbuquerque.tropicalfruits.http_module.api.FruitVegetableAPI;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.silvanoalbuquerque.tropicalfruits.util.Constants.BASE_URL;

public final class ApiClient {
    private static ApiClient instance;
    private Retrofit retrofit;

    private ApiClient() {
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static ApiClient getInstance() {
        if (instance == null) {
            instance = new ApiClient();
        }

        return instance;
    }

    public FruitVegetableAPI getFruitVegetableAPI() {
        return retrofit.create(FruitVegetableAPI.class);
    }
}
