package com.inpromos.app.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.inpromos.app.R;
import com.inpromos.app.models.OrderModel;

import java.util.ArrayList;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    private List<OrderModel> orders;
    private Context context;

    public OrderAdapter(List<OrderModel> orders, Context context) {
        this.orders = orders;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final OrderModel order = orders.get(position);

        holder.mTitle.setText(order.getOrder_title());
        holder.mDate.setText(order.getOrder_date());
        holder.mPrice.setText("$" + order.getOrder_cost());

    }

    @Override
    public int getItemCount() {
        return orders.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mTitle, mDate, mPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mTitle = itemView.findViewById(R.id.orderTitleTxt);
            mDate = itemView.findViewById(R.id.orderDateTxt);
            mPrice = itemView.findViewById(R.id.orderPriceTxt);

        }

    }

}
