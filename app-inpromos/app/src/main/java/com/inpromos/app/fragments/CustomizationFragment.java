package com.inpromos.app.fragments;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.inpromos.app.R;
import com.inpromos.app.adapters.ColorAdapter;
import com.inpromos.app.adapters.SizeAdapter;
import com.inpromos.app.models.ColorModel;
import com.inpromos.app.models.SizeModel;
import com.inpromos.app.utils.ApplicationKeys;

import java.util.ArrayList;
import java.util.List;

public class CustomizationFragment extends Fragment {

    private Uri imgUriPath;
    private RecyclerView mColorRecyclerView, mSizeRecyclerView;
    private ColorAdapter mColorAdapter;
    private SizeAdapter mSizeAdapter;
    private Toolbar mToolbar;
    private List<ColorModel> colors = new ArrayList<>();
    private List<SizeModel> sizes = new ArrayList<>();
    private ImageView mBaseImage, mLessButton, mPlusButton, mTshirtCenterImage, mTshirtStartChestImage, mTshirtEndChestImage;
    private TextView mColorNameTextView;
    private View mLayoutStepOne, mLayoutStepTwo, mLayoutStepThree;
    private EditText mCountText;
    private TextInputEditText mImagePathText;
    private MaterialButton mNextButton, mReturnColorButton, mNextSizeButton, mReturnSizeButton, mFinishButton, mLoadImageButton;
    private Spinner mPositionSpinner;
    private boolean isShirtCenter = false, isShirtStart = false, isShirtEnd = false;

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

        if (colors.isEmpty() || sizes.isEmpty()) {
            generateTempData();
            colorRecyclerViewSetup();
            sizeRecyclerViewSetup();
        } else {
            colorRecyclerViewSetup();
            sizeRecyclerViewSetup();
        }

        positionSpinnerSetup();
        onClickListeners();

    }

    private void colorRecyclerViewSetup() {
        mColorRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 5));
        mColorAdapter = new ColorAdapter(colors, getActivity(), mBaseImage, mColorNameTextView);
        mColorRecyclerView.setAdapter(mColorAdapter);
    }

    private void sizeRecyclerViewSetup() {
        mSizeRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 5));
        mSizeAdapter = new SizeAdapter(sizes, getActivity());
        mSizeRecyclerView.setAdapter(mSizeAdapter);
    }

    private void onClickListeners() {
        //Switch views
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLayoutStepOne.setVisibility(View.GONE);
                mLayoutStepTwo.setVisibility(View.VISIBLE);
                mLayoutStepThree.setVisibility(View.GONE);
            }
        });

        mReturnColorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLayoutStepOne.setVisibility(View.VISIBLE);
                mLayoutStepTwo.setVisibility(View.GONE);
                mLayoutStepThree.setVisibility(View.GONE);
            }
        });

        mNextSizeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLayoutStepOne.setVisibility(View.GONE);
                mLayoutStepTwo.setVisibility(View.GONE);
                mLayoutStepThree.setVisibility(View.VISIBLE);
            }
        });

        mReturnSizeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLayoutStepOne.setVisibility(View.GONE);
                mLayoutStepTwo.setVisibility(View.VISIBLE);
                mLayoutStepThree.setVisibility(View.GONE);
            }
        });

        //Stepper
        mPlusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = Integer.parseInt(mCountText.getText().toString().trim());
                mCountText.setText(String.valueOf(count + 1));
            }
        });

        mLessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = Integer.parseInt(mCountText.getText().toString().trim());
                if (count > 1) {
                    mCountText.setText(String.valueOf(count - 1));
                }
            }
        });

        //Pick image
        mLoadImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickImage();
            }
        });

    }

    private void pickImage() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        String[] mimeTypes = {"image/png"};
        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
        startActivityForResult(intent, ApplicationKeys.IMAGE_PICK_REQUEST_CODE);
    }

    private void defineBooleans(boolean isShirtCenter, boolean isShirtEnd, boolean isShirtStart) {
        this.isShirtCenter = isShirtCenter;
        this.isShirtEnd = isShirtEnd;
        this.isShirtStart = isShirtStart;
    }

    private void defineImageViews() {
        if (imgUriPath != null) {
            if (isShirtCenter) {
                mBaseImage.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_vector_t_shirt));
                mTshirtEndChestImage.setImageDrawable(null);
                mTshirtStartChestImage.setImageDrawable(null);
                mTshirtCenterImage.setImageURI(imgUriPath);
            } else if (isShirtStart) {
                mBaseImage.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_vector_t_shirt));
                mTshirtEndChestImage.setImageDrawable(null);
                mTshirtStartChestImage.setImageURI(imgUriPath);
                mTshirtCenterImage.setImageDrawable(null);
            } else if (isShirtEnd) {
                mBaseImage.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_vector_t_shirt));
                mTshirtEndChestImage.setImageURI(imgUriPath);
                mTshirtStartChestImage.setImageDrawable(null);
                mTshirtCenterImage.setImageDrawable(null);
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK)
            if (requestCode == ApplicationKeys.IMAGE_PICK_REQUEST_CODE) {
                //data.getData returns the content URI for the selected Image
                imgUriPath = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                Cursor cursor = getActivity().getContentResolver().query(imgUriPath,
                        filePathColumn,
                        null,
                        null,
                        null);
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String imgDecodableString = cursor.getString(columnIndex);
                cursor.close();
                mImagePathText.setText(imgDecodableString);
                //positionSpinner.setVisibility(View.VISIBLE);
                defineImageViews();
            }
    }

    private void positionSpinnerSetup() {
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(
                getActivity(),
                R.array.positions,
                android.R.layout.simple_spinner_dropdown_item
        );

        mPositionSpinner.setAdapter(arrayAdapter);
        mPositionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        defineBooleans(true, false, false);
                        defineImageViews();
                        break;
                    case 1:
                        defineBooleans(false, false, true);
                        defineImageViews();
                        break;
                    case 2:
                        defineBooleans(false, true, false);
                        defineImageViews();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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
        mLayoutStepOne = getActivity().findViewById(R.id.layoutStepOne);
        mNextButton = getActivity().findViewById(R.id.colorSelectionNextBtn);
        mColorNameTextView = getActivity().findViewById(R.id.selectedColorNameTxt);
        mLayoutStepTwo = getActivity().findViewById(R.id.layoutStepTwo);
        mNextSizeButton = getActivity().findViewById(R.id.nextSelectSizeBtn);
        mReturnColorButton = getActivity().findViewById(R.id.returnColorSelectorBtn);
        mSizeRecyclerView = getActivity().findViewById(R.id.sizeRecyclerView);
        mReturnSizeButton = getActivity().findViewById(R.id.returnImageSelectorBtn);
        mLayoutStepThree = getActivity().findViewById(R.id.layoutStepThree);
        mLessButton = getActivity().findViewById(R.id.productCountLessBtn);
        mPlusButton = getActivity().findViewById(R.id.productCountPlusBtn);
        mCountText = getActivity().findViewById(R.id.productCountTxt);
        mFinishButton = getActivity().findViewById(R.id.finishCustomizationBtn);
        mImagePathText = getActivity().findViewById(R.id.imagePathTxt);
        mPositionSpinner = getActivity().findViewById(R.id.positionSelectorSpinner);
        mLoadImageButton = getActivity().findViewById(R.id.loadImageBtn);

        //Layout image views
        mBaseImage = getActivity().findViewById(R.id.baseProductDrawableImg);
        mTshirtCenterImage = getActivity().findViewById(R.id.baseShirtCenterImg);
        mTshirtStartChestImage = getActivity().findViewById(R.id.baseShirtStartChestImg);
        mTshirtEndChestImage = getActivity().findViewById(R.id.baseShirtEndChestImg);

    }

    private void generateTempData() {
        colors.add(new ColorModel("Color one", 1, R.color.colorAccentPrimary, ""));
        colors.add(new ColorModel("Color two", 2, R.color.colorAccentSecondary, ""));
        colors.add(new ColorModel("Color three", 3, R.color.colorAccentThird, ""));
        colors.add(new ColorModel("Color four", 4, android.R.color.holo_red_dark, ""));
        colors.add(new ColorModel("Color five", 5, android.R.color.holo_green_dark, ""));

        sizes.add(new SizeModel(1, "XS", 1.0, 1.0, 1.0));
        sizes.add(new SizeModel(2, "S", 1.0, 1.0, 1.0));
        sizes.add(new SizeModel(3, "M", 1.0, 1.0, 1.0));
        sizes.add(new SizeModel(4, "L", 1.0, 1.0, 1.0));
        sizes.add(new SizeModel(5, "XL", 1.0, 1.0, 1.0));

    }

}
