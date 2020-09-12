package com.silvanoalbuquerque.tropicalfruits.activity.fruit_vegetable_list.util;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.silvanoalbuquerque.tropicalfruits.R;
import com.silvanoalbuquerque.tropicalfruits.model.FruitVegetableModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FruitVegetableVH extends RecyclerView.ViewHolder {

    @BindView(R.id.item_title) TextView tvItemTitle;

    public FruitVegetableVH(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(FruitVegetableModel model) {
        String itemName = model.getBotname();
        tvItemTitle.setText(itemName);
    }
}
