package com.silvanoalbuquerque.tropicalfruits.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.silvanoalbuquerque.tropicalfruits.R;
import com.silvanoalbuquerque.tropicalfruits.http_module.ApiClient;
import com.silvanoalbuquerque.tropicalfruits.model.SearchResult;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.pb_loading_fruits) ProgressBar pbLoadingFruits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        ApiClient.getInstance().getFruitVegetableAPI().searchFruitOrVegetable("all").enqueue(new Callback<SearchResult>() {
            @Override
            public void onResponse(Call<SearchResult> call, Response<SearchResult> response) {
                Log.d("TESTE", "searchFruitOrVegetable.onResponse: ");

                pbLoadingFruits.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<SearchResult> call, Throwable t) {
                Log.d("TESTE", "searchFruitOrVegetable.onFailure: ");
            }
        });

        ApiClient.getInstance().getFruitVegetableAPI().getFruitOrVegetable("banana").enqueue(new Callback<SearchResult>() {
            @Override
            public void onResponse(Call<SearchResult> call, Response<SearchResult> response) {
                Log.d("TESTE", "getFruitOrVegetable.onResponse: ");
            }

            @Override
            public void onFailure(Call<SearchResult> call, Throwable t) {
                Log.d("TESTE", "getFruitOrVegetable.onFailure: ");
            }
        });
    }
}