package com.inpromos.app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.inpromos.app.R;
import com.inpromos.app.models.ProductModel;

import java.util.List;

public class NewProductAdapter extends RecyclerView.Adapter<NewProductAdapter.ViewHolder> {

    private List<ProductModel> newProducts;
    private Context context;

    public NewProductAdapter(List<ProductModel> newProducts, Context context) {
        this.newProducts = newProducts;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_new_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProductModel product = newProducts.get(position);

        holder.mName.setText(product.getProduct_name());
        holder.mCount.setText(String.valueOf(product.getProduct_quantity()));

    }

    @Override
    public int getItemCount() {
        return newProducts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView mImage;
        private TextView mName, mCount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mImage = itemView.findViewById(R.id.newProductImg);
            mName = itemView.findViewById(R.id.newProductNameTxt);
            mCount = itemView.findViewById(R.id.newProductCountTxt);

        }

    }

}
