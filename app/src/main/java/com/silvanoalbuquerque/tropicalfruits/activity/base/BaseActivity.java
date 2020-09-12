package com.silvanoalbuquerque.tropicalfruits.activity.base;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity {

    protected P presenter;

    protected abstract P createPresenter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        presenter = createPresenter();
    }
}
