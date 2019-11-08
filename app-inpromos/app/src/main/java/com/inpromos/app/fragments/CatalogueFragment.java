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
            generateTempData();
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

    private void generateTempData() {
        categories.add(new CategoryModel(
                1,
                "Gorras",
                "https://image.freepik.com/foto-gratis/sombrero-lona-deporte-manera-blanco_1203-5371.jpg"));

        categories.add(new CategoryModel(
                2,
                "Tazas",
                "https://image.freepik.com/foto-gratis/surtido-plano-laico-taza-fondo-rosa_23-2148295652.jpg"));

        categories.add(new CategoryModel(
                3,
                "Camisetas",
                "https://image.freepik.com/foto-gratis/camisetas-negras-rojas-blancas_23-2147730483.jpg"
        ));

        categories.add(new CategoryModel(
                4,
                "Botellas",
                "https://image.freepik.com/foto-gratis/mano-sujetando-termo-maqueta-sobre-fondo-amarillo_23-2148295782.jpg"
        ));
    }

}
