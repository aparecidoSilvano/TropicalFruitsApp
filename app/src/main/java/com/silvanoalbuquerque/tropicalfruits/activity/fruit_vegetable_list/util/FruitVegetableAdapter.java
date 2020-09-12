package com.silvanoalbuquerque.tropicalfruits.activity.fruit_vegetable_list.util;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.silvanoalbuquerque.tropicalfruits.R;
import com.silvanoalbuquerque.tropicalfruits.model.FruitVegetableModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class FruitVegetableAdapter extends RecyclerView.Adapter<FruitVegetableVH> implements Filterable {

    private IFruitVegetableListContext ctx;
    private List<FruitVegetableModel> fruitsAndVegetablesList;
    private List<FruitVegetableModel> fruitsAndVegetablesFilteredList;

    public FruitVegetableAdapter(IFruitVegetableListContext ctx) {
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public FruitVegetableVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_fruit_or_vegetable, parent, false);

        return new FruitVegetableVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FruitVegetableVH holder, int position) {
        FruitVegetableModel model = fruitsAndVegetablesFilteredList.get(position);
        holder.bind(model, ctx);
    }

    @Override
    public int getItemCount() {
        if (fruitsAndVegetablesList != null) {
            return fruitsAndVegetablesFilteredList.size();

        } else {
            return 0;
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String searchText = constraint.toString();

                if (searchText.isEmpty()) {
                    fruitsAndVegetablesFilteredList = fruitsAndVegetablesList;

                } else {
                    List<FruitVegetableModel> filteredList = new ArrayList<>();

                    for (FruitVegetableModel item : fruitsAndVegetablesList) {
                        if (item.getTfvname().toUpperCase().contains(searchText.toUpperCase())) {
                            filteredList.add(item);
                        }
                    }

                    fruitsAndVegetablesFilteredList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = fruitsAndVegetablesFilteredList;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                fruitsAndVegetablesFilteredList = (List<FruitVegetableModel>) results.values;

                if (fruitsAndVegetablesFilteredList.isEmpty()) {
                    ctx.showNotFoundItems();
                } else {
                    ctx.hideNotFoundItems();
                }

                notifyDataSetChanged();
            }
        };
    }

    public void setFruitsAndVegetablesList(List<FruitVegetableModel> items) {
        if (fruitsAndVegetablesList == null) {
            fruitsAndVegetablesList = items;
            fruitsAndVegetablesFilteredList = items;
            notifyItemChanged(0, items.size());

        } else {
            DiffUtil.DiffResult result = getDiffResult(items);
            this.fruitsAndVegetablesList = items;
            this.fruitsAndVegetablesFilteredList = items;
            result.dispatchUpdatesTo(this);
        }
    }

    @NotNull
    private DiffUtil.DiffResult getDiffResult(List<FruitVegetableModel> items) {
        return DiffUtil.calculateDiff(new DiffUtil.Callback() {
            @Override
            public int getOldListSize() {
                return FruitVegetableAdapter.this.fruitsAndVegetablesList.size();
            }

            @Override
            public int getNewListSize() {
                return items.size();
            }

            @Override
            public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                return areTheSame(oldItemPosition, newItemPosition, items);
            }

            private boolean areTheSame(int oldItemPosition, int newItemPosition, List<FruitVegetableModel> items1) {
                FruitVegetableModel newItem = FruitVegetableAdapter.this.fruitsAndVegetablesList.get(oldItemPosition);
                FruitVegetableModel oldItem = items1.get(newItemPosition);

                return newItem.getTfvname().equals(oldItem.getTfvname());
            }

            @Override
            public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                return areTheSame(oldItemPosition, newItemPosition, items);
            }
        });
    }
}
