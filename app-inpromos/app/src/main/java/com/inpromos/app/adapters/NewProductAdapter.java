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
import com.inpromos.app.models.QuotationModel;
import com.inpromos.app.models.QuotationProductModel;

import java.util.List;

public class NewProductAdapter extends RecyclerView.Adapter<NewProductAdapter.ViewHolder> {

    private List<QuotationProductModel> newProducts;
    private List<ProductModel> products;
    private Context context;

    public NewProductAdapter(List<QuotationProductModel> newProducts, List<ProductModel> products, Context context) {
        this.newProducts = newProducts;
        this.products = products;
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
        QuotationProductModel newProduct = newProducts.get(position);


        for (ProductModel product : products) {
            if (product.getProductId() == newProduct.getProductId()) {
                holder.mName.setText(product.getProductName());
            }
        }

        holder.mCount.setText(String.valueOf(newProduct.getQuantityItemSelected()));

    }

    @Override
    public int getItemCount() {
        return newProducts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView mImage;
        private TextView mName, mCount;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            mImage = itemView.findViewById(R.id.newProductImg);
            mName = itemView.findViewById(R.id.newProductNameTxt);
            mCount = itemView.findViewById(R.id.newProductCountTxt);

        }

    }

}
