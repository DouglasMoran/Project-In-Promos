package com.inpromos.app.fragments;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.inpromos.app.R;
import com.inpromos.app.adapters.CategoryAdapter;
import com.inpromos.app.models.CategoryModel;

import java.util.ArrayList;
import java.util.List;

public class CatalogueFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private CategoryAdapter mAdapter;
    private List<CategoryModel> categories = new ArrayList<>();
    private Toolbar mToolbar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_catalogue, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getReferences();
        toolbarSetup();

        if (categories.isEmpty()) {
            recyclerViewSetup();
        } else {
            recyclerViewSetup();
        }

    }

    private void recyclerViewSetup() {
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        mAdapter = new CategoryAdapter(categories, getActivity());
        mRecyclerView.setAdapter(mAdapter);
    }

    private void toolbarSetup() {
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

    }

    private void getReferences() {
        mRecyclerView = getActivity().findViewById(R.id.categoryRecyclerView);
        mToolbar = getActivity().findViewById(R.id.catalogueToolbar);
    }

}
