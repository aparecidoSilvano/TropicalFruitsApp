package com.silvanoalbuquerque.tropicalfruits.activity.fruit_vegetable_details;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Group;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.silvanoalbuquerque.tropicalfruits.R;
import com.silvanoalbuquerque.tropicalfruits.activity.base.BaseActivity;
import com.silvanoalbuquerque.tropicalfruits.model.FruitVegetableModel;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.silvanoalbuquerque.tropicalfruits.util.Constants.FRUIT_VEGETABLE_DATA_KEY;

public class FruitVegetableItemActivity extends BaseActivity<FruitVegetableItemPresenter> implements IFruitVegetableItemView {

    @BindView(R.id.fruit_vegetable_details_name) TextView tvItemName;
    @BindView(R.id.pb_loading_details) ProgressBar pbLoadingDetails;
    @BindView(R.id.fruit_vegetable_details_img) ImageView itemDetailsIcon;
    @BindView(R.id.bot_names_value) TextView tvItemBotNames;
    @BindView(R.id.other_names_value) TextView tvItemOtherNames;
    @BindView(R.id.description_value) TextView tvItemDescription;
    @BindView(R.id.item_view_group) Group itemViewGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit_vegetable_item);

        ButterKnife.bind(this);

        loadData();
    }

    private void loadData() {
        Bundle extras = getIntent().getExtras();
        String tfvitem = (String) extras.get(FRUIT_VEGETABLE_DATA_KEY);

        presenter.requestFruitVegetableDetails(tfvitem);
    }

    @Override
    protected FruitVegetableItemPresenter createPresenter() {
        return new FruitVegetableItemPresenter(this);
    }

    @Override
    public void showErrorLoadingData() {
        // TODO
        hideProgress();
    }

    private void hideProgress() {
        pbLoadingDetails.setVisibility(View.GONE);
        itemViewGroup.setVisibility(View.VISIBLE);
    }

    @Override
    public void loadData(FruitVegetableModel model) {
        tvItemName.setText(model.getTfvname());
        tvItemBotNames.setText(model.getBotname());
        tvItemOtherNames.setText(model.getOthname());
        tvItemDescription.setText(model.getDescription());

        String itemUrl = model.getFixedImageURL();

        Glide.with(this)
                .load(itemUrl)
                .apply(RequestOptions.centerCropTransform())
                .addListener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        hideProgress();
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        hideProgress();
                        return false;
                    }
                })
                .into(itemDetailsIcon);
    }
}