package com.inpromos.app.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.inpromos.app.R;
import com.inpromos.app.adapters.NewProductAdapter;
import com.inpromos.app.models.ProductModel;
import com.inpromos.app.utils.ApplicationKeys;

import java.util.ArrayList;
import java.util.List;

public class NewOrderActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private NewProductAdapter mAdapter;
    private FloatingActionButton mFab;
    private List<ProductModel> newProducts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_order);
        getReferences();
        toolbarConfiguration();

        onClickListeners();
        recyclerViewSetup();

    }

    private void recyclerViewSetup() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new NewProductAdapter(newProducts, this);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void onClickListeners() {
        //Start catalogue activity waiting for result
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getBaseContext(), CustomizationActivity.class), ApplicationKeys.PRODUCT_RESULT_CODE);
            }
        });

    }

    private void toolbarConfiguration() {
        getWindow().setStatusBarColor(getResources().getColor(R.color.colorAccentThird));
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getReferences() {
        mToolbar = findViewById(R.id.newOrderToolbar);
        mRecyclerView = findViewById(R.id.newOrderRecyclerView);
        mFab = findViewById(R.id.addProductFab);
    }

}
