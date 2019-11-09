package com.inpromos.app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.inpromos.app.R;
import com.inpromos.app.models.SizeModel;

import java.util.List;

public class SizeAdapter extends RecyclerView.Adapter<SizeAdapter.ViewHolder> {

    private List<SizeModel> sizes;
    private Context context;
    private int currentPosition;

    public SizeAdapter(List<SizeModel> sizes, Context context) {
        this.sizes = sizes;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_size, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        SizeModel size = sizes.get(position);

        holder.mName.setText(size.getSize_name());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPosition = position;
                notifyDataSetChanged();
            }
        });

        if (currentPosition == position) {
            holder.mBg.setBackgroundTintList(context.getResources().getColorStateList(R.color.colorAccentSecondary));
        } else {
            holder.mBg.setBackgroundTintList(context.getResources().getColorStateList(R.color.colorPrimarySomeDark));
        }
    }

    @Override
    public int getItemCount() {
        return sizes.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mName;
        private View mBg;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            mName = itemView.findViewById(R.id.sizeNameTxt);
            mBg = itemView.findViewById(R.id.sizeBackgroundView);

        }

    }

}
