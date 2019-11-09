package com.inpromos.app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.inpromos.app.R;
import com.inpromos.app.models.ProductModel;

import java.util.List;
import java.util.Objects;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private List<ProductModel> products;
    private Context context;
    private int selectedPosition;

    public ProductAdapter(List<ProductModel> products, Context context) {
        this.products = products;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final ProductModel product = products.get(position);

        holder.mName.setText(product.getProductName());
        holder.mDescription.setText(product.getProductDescription());

        //Expandable function
        boolean isExpanded = product.isExpanded();
        holder.mContainer.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
        holder.mExpand.setRotation(isExpanded ? 90 : 0);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isExpanded = product.isExpanded();
                product.setExpanded(!isExpanded);
                notifyItemChanged(position);
            }
        });

        holder.mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(Objects.requireNonNull(((FragmentActivity) context)
                        .getSupportFragmentManager().findFragmentById(R.id.customizationNavHostFragment)))
                        .navigate(R.id.customizationFragment);
            }
        });

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView mImage, mExpand;
        private TextView mName, mDescription;
        private MaterialButton mButton;
        private ConstraintLayout mContainer;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            mImage = itemView.findViewById(R.id.productImg);
            mButton = itemView.findViewById(R.id.productCustomizeBtn);
            mDescription = itemView.findViewById(R.id.productDescriptionTxt);
            mExpand = itemView.findViewById(R.id.productExpandIcon);
            mName = itemView.findViewById(R.id.productNameTxt);
            mContainer = itemView.findViewById(R.id.expandContainer);

        }

    }

}
