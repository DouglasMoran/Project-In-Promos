package com.inpromos.app.fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.inpromos.app.R;
import com.inpromos.app.adapters.CategoryAdapter;
import com.inpromos.app.models.CategoryModel;
import com.inpromos.app.utils.ApplicationKeys;

import java.util.ArrayList;
import java.util.List;

public class CatalogueFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private CategoryAdapter mAdapter;
    private List<CategoryModel> categories = new ArrayList<>();
    private Toolbar mToolbar;
    private ProgressBar mProgressBar;

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

        recyclerViewSetup();
        loadCategoriesFromFirebase();

    }

    private void recyclerViewSetup() {
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        mAdapter = new CategoryAdapter(categories, getActivity());
        mRecyclerView.setAdapter(mAdapter);
    }

    private void loadCategoriesFromFirebase() {
        mProgressBar.setVisibility(View.VISIBLE);
        FirebaseDatabase.getInstance().getReference(ApplicationKeys.CATEGORY_REFERENCE).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (categories.size() > 0) {
                    categories.clear();
                }

                if (dataSnapshot.exists()) {
                    for (DataSnapshot tmp : dataSnapshot.getChildren()) {
                        CategoryModel category = tmp.getValue(CategoryModel.class);
                        categories.add(category);
                        mAdapter.notifyDataSetChanged();
                        mProgressBar.setVisibility(View.GONE);
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
                getActivity().finish();
            }
        });

    }

    private void getReferences() {
        mRecyclerView = getActivity().findViewById(R.id.categoryRecyclerView);
        mToolbar = getActivity().findViewById(R.id.catalogueToolbar);
        mProgressBar = getActivity().findViewById(R.id.catalogueProgressBar);
    }

}
