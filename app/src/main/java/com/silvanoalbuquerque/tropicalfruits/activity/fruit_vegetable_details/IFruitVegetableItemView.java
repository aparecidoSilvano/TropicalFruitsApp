package com.silvanoalbuquerque.tropicalfruits.activity.fruit_vegetable_details;

import com.silvanoalbuquerque.tropicalfruits.model.FruitVegetableModel;

public interface IFruitVegetableItemView {
    void showErrorLoadingData();
    void loadData(FruitVegetableModel model);
}
