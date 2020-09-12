package com.silvanoalbuquerque.tropicalfruits.activity.fruit_vegetable_list.util;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.silvanoalbuquerque.tropicalfruits.R;
import com.silvanoalbuquerque.tropicalfruits.model.FruitVegetableModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FruitVegetableVH extends RecyclerView.ViewHolder {

    @BindView(R.id.item_title) TextView tvItemTitle;
    @BindView(R.id.item_icon) ImageView itemIcon;

    public FruitVegetableVH(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(FruitVegetableModel model, OnFruitVegetableClickListener clickListener) {
        String itemName = model.getTfvname();
        String itemUrl = model.getFixedImageURL();

        tvItemTitle.setText(itemName);

        Glide.with(itemIcon.getContext())
                .load(itemUrl)
                .apply(RequestOptions.centerCropTransform())
                .into(itemIcon);

        itemView.setOnClickListener(view -> clickListener.onClickOnItem(model.getTfvname()));
    }
}
