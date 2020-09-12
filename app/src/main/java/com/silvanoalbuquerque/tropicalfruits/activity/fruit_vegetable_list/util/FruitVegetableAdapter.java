package com.silvanoalbuquerque.tropicalfruits.activity.fruit_vegetable_list.util;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.silvanoalbuquerque.tropicalfruits.R;
import com.silvanoalbuquerque.tropicalfruits.model.FruitVegetableModel;

public class FruitVegetableAdapter extends RecyclerView.Adapter<FruitVegetableVH> {

    private IFruitVegetableListContext ctx;

    public FruitVegetableAdapter(IFruitVegetableListContext ctx) {
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public FruitVegetableVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fruit_or_vegetable, parent, false);
        return new FruitVegetableVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FruitVegetableVH holder, int position) {
        FruitVegetableModel model = ctx.getFruitAndVegetables().get(position);
        holder.bind(model);
    }

    @Override
    public int getItemCount() {
        return ctx.getFruitAndVegetables().size();
    }
}
