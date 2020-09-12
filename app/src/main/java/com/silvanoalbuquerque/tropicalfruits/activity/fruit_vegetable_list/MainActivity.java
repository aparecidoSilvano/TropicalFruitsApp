package com.silvanoalbuquerque.tropicalfruits.activity.fruit_vegetable_list;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.silvanoalbuquerque.tropicalfruits.R;
import com.silvanoalbuquerque.tropicalfruits.activity.base.BaseActivity;
import com.silvanoalbuquerque.tropicalfruits.activity.fruit_vegetable_list.util.FruitVegetableAdapter;
import com.silvanoalbuquerque.tropicalfruits.activity.fruit_vegetable_list.util.IFruitVegetableListContext;
import com.silvanoalbuquerque.tropicalfruits.model.FruitVegetableModel;

import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity<MainPresenter> implements IFruitVegetableListView, IFruitVegetableListContext {

    @BindView(R.id.pb_loading_fruits) ProgressBar pbLoadingFruits;
    @BindView(R.id.tv_error_msg) TextView tvErrorMsg;
    @BindView(R.id.recycler_fruit_veg_list) RecyclerView fruitVegetablesList;

    private FruitVegetableAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);

        loadData();
        initUi();
    }

    private void initUi() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        fruitVegetablesList.setLayoutManager(layoutManager);

        adapter = new FruitVegetableAdapter(this);
        fruitVegetablesList.setAdapter(adapter);
    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter(this);
    }

    @Override
    public void showResults() {
        setLoadingVisibility(false);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showErrorLoading() {
        setLoadingVisibility(false);
        tvErrorMsg.setVisibility(View.VISIBLE);
    }

    private void loadData() {
        presenter.requestFruitVegetableList();
    }

    private void setLoadingVisibility(boolean visible) {
        int visibility = visible? View.VISIBLE : View.GONE;
        pbLoadingFruits.setVisibility(visibility);
    }

    @Override
    public List<FruitVegetableModel> getFruitAndVegetables() {
        return presenter.getFruitVegetableModelList();
    }
}