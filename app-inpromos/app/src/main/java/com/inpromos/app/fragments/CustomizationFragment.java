package com.inpromos.app.fragments;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.material.button.MaterialButton;
import com.inpromos.app.R;
import com.inpromos.app.adapters.ColorAdapter;
import com.inpromos.app.models.ColorModel;

import java.util.ArrayList;
import java.util.List;

public class CustomizationFragment extends Fragment {

    private RecyclerView mColorRecyclerView;
    private ColorAdapter mColorAdapter;
    private Toolbar mToolbar;
    private List<ColorModel> colors = new ArrayList<>();
    private ImageView mBaseImage;
    private View mLayoutStepOne;
    private MaterialButton mNextButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_customization, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getReferences();
        toolbarSetup();

        if (colors.isEmpty()) {
            generateTempData();
            colorRecyclerViewSetup();
        } else {
            colorRecyclerViewSetup();
        }

        switchViews();
    }

    private void colorRecyclerViewSetup() {
        mColorRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 5));
        mColorAdapter = new ColorAdapter(colors, getActivity(), mBaseImage);
        mColorRecyclerView.setAdapter(mColorAdapter);
    }

    private void switchViews() {
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLayoutStepOne.setVisibility(View.GONE);
            }
        });
    }

    private void toolbarSetup() {
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
    }

    private void getReferences() {
        mColorRecyclerView = getActivity().findViewById(R.id.colorsRecyclerView);
        mToolbar = getActivity().findViewById(R.id.customizationToolbar);
        mBaseImage = getActivity().findViewById(R.id.baseProductDrawableImg);
        mLayoutStepOne = getActivity().findViewById(R.id.layoutStepOne);
        mNextButton = getActivity().findViewById(R.id.colorSelectionNextBtn);
    }

    private void generateTempData() {
        colors.add(new ColorModel(1, R.color.colorAccentPrimary, ""));
        colors.add(new ColorModel(2, R.color.colorAccentSecondary, ""));
        colors.add(new ColorModel(3, R.color.colorAccentThird, ""));
        colors.add(new ColorModel(4, android.R.color.holo_red_dark, ""));
        colors.add(new ColorModel(5, android.R.color.holo_green_dark, ""));
        colors.add(new ColorModel(1, R.color.colorAccentPrimary, ""));
        colors.add(new ColorModel(2, R.color.colorAccentSecondary, ""));
        colors.add(new ColorModel(3, R.color.colorAccentThird, ""));
        colors.add(new ColorModel(4, android.R.color.holo_red_dark, ""));
        colors.add(new ColorModel(5, android.R.color.holo_green_dark, ""));
    }

}
