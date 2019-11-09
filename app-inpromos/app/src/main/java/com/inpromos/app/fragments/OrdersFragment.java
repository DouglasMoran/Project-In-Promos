package com.inpromos.app.fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.inpromos.app.R;
import com.inpromos.app.activities.NewOrderActivity;
import com.inpromos.app.adapters.OrderAdapter;
import com.inpromos.app.models.OrderModel;
import com.inpromos.app.utils.ApplicationKeys;

import java.util.ArrayList;
import java.util.List;

public class OrdersFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private OrderAdapter mAdapter;
    private List<OrderModel> orders = new ArrayList<>();
    private FloatingActionButton mFab;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_orders, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getReferences();

        setRecyclerView();

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomSheet();
            }
        });

    }

    private void setRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new OrderAdapter(orders, getActivity());
        mRecyclerView.setAdapter(mAdapter);
    }

    private void showBottomSheet() {
        //Setting view
        final BottomSheetDialog bottomSheet = new BottomSheetDialog(getActivity(), R.style.BottomSheetDialogStyle);
        View sheetView = LayoutInflater.from(getActivity()).inflate(R.layout.sheet_selector, null, false);
        bottomSheet.setContentView(sheetView);

        bottomSheet.show();

        //Listeners
        sheetView.findViewById(R.id.sheetCompanyBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Debug", "Empresarial.");
                bottomSheet.dismiss();
                startNewOrderActivity(false);

            }
        });

        sheetView.findViewById(R.id.sheetCustomBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Debug", "Personalizado.");
                bottomSheet.dismiss();
                startNewOrderActivity(true);
            }
        });

        sheetView.findViewById(R.id.sheetCloseBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheet.dismiss();
            }
        });

    }

    private void getReferences() {
        mFab = getActivity().findViewById(R.id.addOrderFab);
        mRecyclerView = getActivity().findViewById(R.id.ordersRecyclerView);
    }

    private void startNewOrderActivity(boolean isCustom) {
        Intent intent = new Intent(getActivity(), NewOrderActivity.class);
        intent.putExtra(ApplicationKeys.IS_CUSTOM_KEY, isCustom);
        getActivity().startActivity(intent);
    }

}
