package com.silvanoalbuquerque.tropicalfruits.activity.fruit_vegetable_list;

import com.silvanoalbuquerque.tropicalfruits.activity.base.BasePresenter;
import com.silvanoalbuquerque.tropicalfruits.http_module.ApiClient;
import com.silvanoalbuquerque.tropicalfruits.model.FruitVegetableModel;
import com.silvanoalbuquerque.tropicalfruits.model.SearchResult;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class MainPresenter implements BasePresenter {
    private IFruitVegetableListView view;
    private List<FruitVegetableModel> fruitVegetableModelList;

    public MainPresenter(IFruitVegetableListView view) {
        this.view = view;
        fruitVegetableModelList = new ArrayList<>();
    }

    public void requestFruitVegetableList() {
        ApiClient.getInstance().getFruitVegetableAPI().searchFruitOrVegetable("all").enqueue(handleFruitVegetableRequest());
    }

    @NotNull
    private Callback<SearchResult> handleFruitVegetableRequest() {
        return new Callback<SearchResult>() {
            @Override
            public void onResponse(@NotNull Call<SearchResult> call, @NotNull Response<SearchResult> response) {
                SearchResult result = response.body();

                if (result == null) {
                    Timber.d("The search returned null");
                    view.showErrorLoading();
                    return;
                }

                fruitVegetableModelList = result.getResults();
                Collections.sort(fruitVegetableModelList);

                view.showResults();
            }

            @Override
            public void onFailure(@NotNull Call<SearchResult> call, @NotNull Throwable throwable) {
                view.showErrorLoading();

                Timber.d("Error loading the fruit and vegetable list");
            }
        };
    }

    public List<FruitVegetableModel> getFruitVegetableModelList() {
        return fruitVegetableModelList;
    }
}
