package com.silvanoalbuquerque.tropicalfruits.activity.fruit_vegetable_list.util;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.silvanoalbuquerque.tropicalfruits.R;
import com.silvanoalbuquerque.tropicalfruits.model.FruitVegetableModel;

import org.jetbrains.annotations.NotNull;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FruitVegetableVH extends RecyclerView.ViewHolder {

    @BindView(R.id.item_title) TextView tvItemTitle;
    @BindView(R.id.item_icon) AppCompatImageView itemIcon;

    public FruitVegetableVH(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(FruitVegetableModel model) {
        String itemName = model.getTfvname();
        String itemUrl = getFixedImageURL(model);

        tvItemTitle.setText(itemName);

        Glide.with(itemIcon.getContext())
                .load(itemUrl)
                .apply(RequestOptions.centerCropTransform())
                .into(itemIcon);
    }

    @NotNull
    private String getFixedImageURL(FruitVegetableModel model) {
        String itemUrl = model.getImageurl();
        return itemUrl.replace("http", "https");
    }
}
