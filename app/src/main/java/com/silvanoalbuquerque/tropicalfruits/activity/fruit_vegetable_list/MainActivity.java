package com.silvanoalbuquerque.tropicalfruits.activity.fruit_vegetable_list;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
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

    private SearchView searchView;
    private FruitVegetableAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);

        loadData();
        initUi();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        setupSearchView(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (!searchView.isIconified()) {
            searchView.setIconified(true);
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter(this);
    }

    @Override
    public void showResults() {
        setLoadingVisibility(false);

        List<FruitVegetableModel> data = presenter.getFruitVegetableModelList();
        adapter.setFruitsAndVegetablesList(data);
    }

    @Override
    public void showErrorLoading() {
        setLoadingVisibility(false);
        setNotFoundItemsVisibility(true);
    }

    private void initUi() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        fruitVegetablesList.setLayoutManager(layoutManager);

        adapter = new FruitVegetableAdapter(this);
        fruitVegetablesList.setAdapter(adapter);
    }

    private void loadData() {
        presenter.requestFruitVegetableList();
    }

    private void setupSearchView(Menu menu) {
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                adapter.getFilter().filter(query);
                return false;
            }
        });
    }

    private void setLoadingVisibility(boolean visible) {
        int visibility = getVisibilityValue(visible);
        pbLoadingFruits.setVisibility(visibility);
    }

    @Override
    public void showNotFoundItems() {
        setNotFoundItemsVisibility(true);
    }

    @Override
    public void hideNotFoundItems() {
        setNotFoundItemsVisibility(false);
    }

    private void setNotFoundItemsVisibility(boolean visible) {
        int visibility = getVisibilityValue(visible);
        tvErrorMsg.setVisibility(visibility);
    }

    private int getVisibilityValue(boolean visible) {
        return visible ? View.VISIBLE : View.GONE;
    }
}