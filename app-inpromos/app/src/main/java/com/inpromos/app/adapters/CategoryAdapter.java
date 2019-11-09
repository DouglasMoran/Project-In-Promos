package com.inpromos.app.adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.inpromos.app.R;
import com.inpromos.app.models.CategoryModel;
import com.inpromos.app.utils.ApplicationKeys;

import java.util.List;
import java.util.Objects;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private List<CategoryModel> categories;
    private Context context;
    private Bundle bundle = new Bundle();

    public CategoryAdapter(List<CategoryModel> categories, Context context) {
        this.categories = categories;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final CategoryModel category = categories.get(position);

        holder.mName.setText(category.getCategoryName());

        //Image
        Glide.with(context)
                .load(category.getCategoryImgUrl())
                .into(holder.mImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get category
                bundle.putInt(ApplicationKeys.CATEGORY_BUNDLE_KEY, category.getCategoryId());

                NavHostFragment.findNavController(Objects.requireNonNull(((FragmentActivity) context)
                        .getSupportFragmentManager().findFragmentById(R.id.customizationNavHostFragment)))
                .navigate(R.id.productsFragment, bundle);
            }
        });

    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView mImage;
        private TextView mName;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            mName = itemView.findViewById(R.id.categoryNameTxt);
            mImage = itemView.findViewById(R.id.categoryImg);

        }

    }

}
