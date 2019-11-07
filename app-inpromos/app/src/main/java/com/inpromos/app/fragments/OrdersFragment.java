package com.inpromos.app.fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.inpromos.app.R;
import com.inpromos.app.adapters.OrderAdapter;
import com.inpromos.app.models.OrderModel;

import java.util.ArrayList;
import java.util.List;

public class OrdersFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private OrderAdapter mAdapter;
    private List<OrderModel> orders = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_orders, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        generateTempData();
        setRecyclerView();

    }

    private void setRecyclerView() {
        mRecyclerView = getActivity().findViewById(R.id.ordersRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new OrderAdapter(orders, getActivity());
        mRecyclerView.setAdapter(mAdapter);
    }


    private void generateTempData() {
        for (int i = 1; i <= 10; i++) {
            orders.add(new OrderModel(i, "Number " + i, "Date " + i, i));
        }
    }
}
