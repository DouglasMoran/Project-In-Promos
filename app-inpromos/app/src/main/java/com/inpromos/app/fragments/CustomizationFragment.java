package com.inpromos.app.fragments;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.util.Log;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.inpromos.app.R;
import com.inpromos.app.adapters.ColorAdapter;
import com.inpromos.app.adapters.SizeAdapter;
import com.inpromos.app.models.ColorModel;
import com.inpromos.app.models.ProductModel;
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
    private ProductModel mProduct;
    private List<SizeModel> sizes = new ArrayList<>();
    private List<ColorModel> colors = new ArrayList<>();
    private ImageView
            mBaseImage,
            mLessButton,
            mPlusButton,
            mTShirtCenterImage,
            mTShirtStartImage,
            mTShirtEndImage,
            mTShirtFilterImage,
            mTShirtBackTopImage,
            mTShirtLeftShoulderImage,
            mTShirtRightShoulderImage;
    private TextView mColorNameTextView;
    private View mLayoutStepOne, mLayoutStepTwo, mLayoutStepThree;
    private EditText mCountText, mImagePathText;
    private MaterialButton mNextButton, mReturnColorButton, mNextSizeButton, mReturnSizeButton, mFinishButton, mLoadImageButton;
    private Spinner mPositionSpinner;

    //SWITCH BOOLEANS
    private boolean isShirtCenter = false,
            isShirtStart = false,
            isShirtEnd = false,
            isShirtBackTop = false,
            isShirtLeftShoulder = false,
            isShirtRightShoulder = false,
            isShirtBack = false;

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

        //Fetch product
        mProduct = (ProductModel) getArguments().getSerializable(ApplicationKeys.PRODUCT_BUNDLE_KEY);
        getColors();

        colorRecyclerViewSetup(colors);
        sizeRecyclerViewSetup();
        positionSpinnerSetup();
        onClickListeners();

    }

    private void getColors() {
        FirebaseDatabase.getInstance().getReference("colors").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot tmp : dataSnapshot.getChildren()) {
                        ColorModel color = tmp.getValue(ColorModel.class);
                        for (ColorModel productColor : mProduct.getColors()) {
                            if (color.getColorId() == productColor.getColorId()) {
                                colors.add(color);
                            }
                        }
                        mColorAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void colorRecyclerViewSetup(List<ColorModel> colors) {
        mColorRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 5));
        mColorAdapter = new ColorAdapter(colors, getActivity(), mTShirtFilterImage, mColorNameTextView);
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

    private void defineBooleans(boolean isShirtCenter, boolean isShirtEnd, boolean isShirtStart,
                                boolean isShirtLeftShoulder, boolean isShirtRightShoulder,
                                boolean isShirtBackTop, boolean isShirtBack) {
        this.isShirtCenter = isShirtCenter;
        this.isShirtEnd = isShirtEnd;
        this.isShirtStart = isShirtStart;
        this.isShirtLeftShoulder = isShirtLeftShoulder;
        this.isShirtRightShoulder = isShirtRightShoulder;
        this.isShirtBackTop = isShirtBackTop;
        this.isShirtBack = isShirtBack;
    }

    private void defineImageViews() {
        if (imgUriPath != null) {
            if (isShirtCenter) {
                mBaseImage.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.vector_shirt_front));
                mTShirtFilterImage.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.vector_shirt_front));
                mTShirtEndImage.setImageDrawable(null);
                mTShirtStartImage.setImageDrawable(null);
                mTShirtBackTopImage.setImageDrawable(null);
                mTShirtLeftShoulderImage.setImageDrawable(null);
                mTShirtRightShoulderImage.setImageDrawable(null);
                mTShirtCenterImage.setImageURI(imgUriPath);
            } else if (isShirtStart) {
                mBaseImage.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.vector_shirt_front));
                mTShirtFilterImage.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.vector_shirt_front));
                mTShirtEndImage.setImageDrawable(null);
                mTShirtStartImage.setImageURI(imgUriPath);
                mTShirtBackTopImage.setImageDrawable(null);
                mTShirtLeftShoulderImage.setImageDrawable(null);
                mTShirtRightShoulderImage.setImageDrawable(null);
                mTShirtCenterImage.setImageDrawable(null);
            } else if (isShirtEnd) {
                mBaseImage.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.vector_shirt_front));
                mTShirtFilterImage.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.vector_shirt_front));
                mTShirtEndImage.setImageURI(imgUriPath);
                mTShirtStartImage.setImageDrawable(null);
                mTShirtBackTopImage.setImageDrawable(null);
                mTShirtLeftShoulderImage.setImageDrawable(null);
                mTShirtRightShoulderImage.setImageDrawable(null);
                mTShirtCenterImage.setImageDrawable(null);
            } else if (isShirtBack) {
                mBaseImage.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.vector_shirt_back));
                mTShirtFilterImage.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.vector_shirt_back));
                mTShirtEndImage.setImageDrawable(null);
                mTShirtStartImage.setImageDrawable(null);
                mTShirtBackTopImage.setImageDrawable(null);
                mTShirtLeftShoulderImage.setImageDrawable(null);
                mTShirtRightShoulderImage.setImageDrawable(null);
                mTShirtCenterImage.setImageURI(imgUriPath);
            } else if (isShirtBackTop) {
                mBaseImage.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.vector_shirt_back));
                mTShirtFilterImage.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.vector_shirt_back));
                mTShirtEndImage.setImageDrawable(null);
                mTShirtStartImage.setImageDrawable(null);
                mTShirtBackTopImage.setImageURI(imgUriPath);
                mTShirtLeftShoulderImage.setImageDrawable(null);
                mTShirtRightShoulderImage.setImageDrawable(null);
                mTShirtCenterImage.setImageDrawable(null);
            } else if (isShirtLeftShoulder) {
                mBaseImage.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.vector_shirt_left_shoulder));
                mTShirtFilterImage.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.vector_shirt_left_shoulder));
                mTShirtEndImage.setImageDrawable(null);
                mTShirtStartImage.setImageDrawable(null);
                mTShirtBackTopImage.setImageDrawable(null);
                mTShirtLeftShoulderImage.setImageURI(imgUriPath);
                mTShirtRightShoulderImage.setImageDrawable(null);
                mTShirtCenterImage.setImageDrawable(null);
            } else if (isShirtRightShoulder) {
                mBaseImage.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.vector_shirt_right_shoulder));
                mTShirtFilterImage.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.vector_shirt_right_shoulder));
                mTShirtEndImage.setImageDrawable(null);
                mTShirtStartImage.setImageDrawable(null);
                mTShirtBackTopImage.setImageDrawable(null);
                mTShirtLeftShoulderImage.setImageDrawable(null);
                mTShirtRightShoulderImage.setImageURI(imgUriPath);
                mTShirtCenterImage.setImageDrawable(null);
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
                mPositionSpinner.setEnabled(true);
                //positionSpinner.setVisibility(View.VISIBLE);
                defineImageViews();
            }
    }

    private void positionSpinnerSetup() {
        mPositionSpinner.setEnabled(false);

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
                    case 0: //Center front position
                        defineBooleans(
                                true,
                                false,
                                false,
                                false,
                                false,
                                false,
                                false);
                        defineImageViews();
                        break;
                    case 1: //Left chest position
                        defineBooleans(
                                false,
                                false,
                                true,
                                false,
                                false,
                                false,
                                false);
                        defineImageViews();
                        break;
                    case 2: //Right chest position
                        defineBooleans(
                                false,
                                true,
                                false,
                                false,
                                false,
                                false,
                                false);
                        defineImageViews();
                        break;
                    case 3: //Back center position
                        defineBooleans(
                                false,
                                false,
                                false,
                                false,
                                false,
                                false,
                                true
                        );
                        defineImageViews();
                        break;
                    case 4:
                        defineBooleans(
                                false,
                                false,
                                false,
                                false,
                                false,
                                true,
                                false
                        );
                        defineImageViews();
                        break;
                    case 5:
                        defineBooleans(
                                false,
                                false,
                                false,
                                true,
                                false,
                                false,
                                false
                        );
                        defineImageViews();
                        break;
                    default:
                        defineBooleans(
                                false,
                                false,
                                false,
                                false,
                                true,
                                false,
                                false
                        );
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

        //TShirt Layout image views
        mBaseImage = getActivity().findViewById(R.id.baseProductDrawableImg);
        mTShirtFilterImage = getActivity().findViewById(R.id.baseProductDrawableFilterImg);
        mTShirtCenterImage = getActivity().findViewById(R.id.baseShirtCenterImg);
        mTShirtStartImage = getActivity().findViewById(R.id.baseShirtStartChestImg);
        mTShirtEndImage = getActivity().findViewById(R.id.baseShirtEndChestImg);
        mTShirtBackTopImage = getActivity().findViewById(R.id.baseShirtBackTopImg);
        mTShirtLeftShoulderImage = getActivity().findViewById(R.id.baseShirtLeftShoulderImg);
        mTShirtRightShoulderImage = getActivity().findViewById(R.id.baseShirtRightShoulderImg);

    }

}
