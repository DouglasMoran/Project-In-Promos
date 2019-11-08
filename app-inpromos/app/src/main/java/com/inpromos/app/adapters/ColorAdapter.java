package com.inpromos.app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.inpromos.app.R;
import com.inpromos.app.models.ColorModel;

import java.util.List;

public class ColorAdapter extends RecyclerView.Adapter<ColorAdapter.ViewHolder> {

    private List<ColorModel> colors;
    private Context context;
    private ImageView baseDrawable;
    private int currentPosition;

    public ColorAdapter(List<ColorModel> colors, Context context, ImageView baseDrawable) {
        this.colors = colors;
        this.context = context;
        this.baseDrawable = baseDrawable;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_color, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final ColorModel color = colors.get(position);

        holder.mColor.setColorFilter(context.getResources().getColor(color.getColor_resource()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPosition = position;
                //Switch base drawable color
                baseDrawable.setColorFilter(context.getResources().getColor(color.getColor_resource()));
                notifyDataSetChanged();
            }
        });

        //Selected color
        if (position == currentPosition) {
            holder.mStroke.setVisibility(View.VISIBLE);
        } else {
            holder.mStroke.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return colors.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView mColor;
        private View mStroke;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            mColor = itemView.findViewById(R.id.colorDrawable);
            mStroke = itemView.findViewById(R.id.colorDrawableStroke);

        }

    }

}
