package com.silvanoalbuquerque.tropicalfruits.activity.fruit_vegetable_details;

import com.silvanoalbuquerque.tropicalfruits.activity.base.BasePresenter;
import com.silvanoalbuquerque.tropicalfruits.http_module.ApiClient;
import com.silvanoalbuquerque.tropicalfruits.model.FruitVegetableModel;
import com.silvanoalbuquerque.tropicalfruits.model.SearchResult;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class FruitVegetableItemPresenter implements BasePresenter {

    private IFruitVegetableItemView view;

    public FruitVegetableItemPresenter(IFruitVegetableItemView view) {
        this.view = view;
    }

    public void requestFruitVegetableDetails(String tfvitem) {
        ApiClient.getInstance().getFruitVegetableAPI().getFruitOrVegetable(tfvitem).enqueue(new Callback<SearchResult>() {
            @Override
            public void onResponse(@NotNull Call<SearchResult> call, @NotNull Response<SearchResult> response) {
                SearchResult result = response.body();

                if (result == null) {
                    Timber.d("The search returned null");
                    view.showErrorLoadingData();
                    return;
                }

                FruitVegetableModel model = result.getResults().get(0);
                view.loadData(model);
            }

            @Override
            public void onFailure(@NotNull Call<SearchResult> call, @NotNull Throwable throwable) {
                view.showErrorLoadingData();

                Timber.d("Error loading the fruits and vegetables list");
            }
        });
    }
}
