package com.inpromos.app.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
    private List<ProductModel> thisProducts = new ArrayList<>();
    private Toolbar mToolbar;
    private int categoryId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_products, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getReferences();
        toolbarSetup();

        //Fetch category id
        assert getArguments() != null;
        categoryId = getArguments().getInt(ApplicationKeys.CATEGORY_BUNDLE_KEY);

        if (products.isEmpty()) {
            generateTempData();
            recyclerViewSetup();
        } else {
            recyclerViewSetup();
        }



    }

    private void recyclerViewSetup() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new ProductAdapter(thisProducts, getActivity());
        mRecyclerView.setAdapter(mAdapter);
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

    private void generateTempData() {

        products.add(new ProductModel(
                1,
                "Gorra 1",
                getString(R.string.lorem_ipsum),
                10.99,
                0,
                1));

        products.add(new ProductModel(
                2,
                "Gorra 2",
                getString(R.string.lorem_ipsum),
                10.99,
                0,
                1));

        products.add(new ProductModel(
                3,
                "Taza 1",
                getString(R.string.lorem_ipsum),
                10.99,
                0,
                2));

        products.add(new ProductModel(
                4,
                "Taza 2",
                getString(R.string.lorem_ipsum),
                10.99,
                0,
                2));

        products.add(new ProductModel(
                5,
                "Camiseta 1",
                getString(R.string.lorem_ipsum),
                10.99,
                0,
                3));

        products.add(new ProductModel(
                6,
                "Camiseta 2",
                getString(R.string.lorem_ipsum),
                10.99,
                0,
                3));

        products.add(new ProductModel(
                7,
                "Botella 1",
                getString(R.string.lorem_ipsum),
                10.99,
                0,
                4));

        products.add(new ProductModel(
                8,
                "Botella 2",
                getString(R.string.lorem_ipsum),
                10.99,
                0,
                4));

        for (ProductModel product : products) {
            if (product.getProduct_category_id() == categoryId) {
                thisProducts.add(product);
            }
        }

    }

}
