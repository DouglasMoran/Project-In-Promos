package com.inpromos.app.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.inpromos.app.R;
import com.inpromos.app.adapters.ProductAdapter;
import com.inpromos.app.models.ProductModel;
import com.inpromos.app.utils.ApplicationKeys;

import java.util.ArrayList;
import java.util.List;

public class ProductsFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private ProductAdapter mAdapter;
    private List<ProductModel> products = new ArrayList<>();
    private Toolbar mToolbar;
    private int categoryId;
    private View mainView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (mainView == null) {
            mainView = inflater.inflate(R.layout.fragment_products, container, false);
        }
        return mainView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getReferences();
        toolbarSetup();

        //Fetch category id
        assert getArguments() != null;
        categoryId = getArguments().getInt(ApplicationKeys.CATEGORY_BUNDLE_KEY);

        recyclerViewSetup();
        loadProducts();

    }

    private void recyclerViewSetup() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new ProductAdapter(products, getActivity());
        mRecyclerView.setAdapter(mAdapter);
    }

    private void loadProducts() {
        FirebaseDatabase.getInstance().getReference(ApplicationKeys.PRODUCT_REFERENCE).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (products.size() > 0) {
                    products.clear();
                }

                if (dataSnapshot.exists()) {
                    for (DataSnapshot tmp : dataSnapshot.getChildren()) {
                        ProductModel product = tmp.getValue(ProductModel.class);
                        Log.d("Debug", product.getProductName());
                        if (product.getCategoryId() == categoryId) {
                            products.add(product);
                            mAdapter.notifyDataSetChanged();
                        }
                        Log.d("Debug", products.toString());
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("Debug", databaseError.getMessage());

            }
        });
    }

    private void toolbarSetup() {
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
    }

    private void getReferences() {
        mToolbar = getActivity().findViewById(R.id.productsToolbar);
        mRecyclerView = getActivity().findViewById(R.id.productRecyclerView);
    }

}
