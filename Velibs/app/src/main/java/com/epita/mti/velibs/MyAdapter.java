package com.epita.mti.velibs;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Lorris on 12/05/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private ArrayList<Velib> mDataset;

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTextView;
        private ImageView iconImageView;

        public ViewHolder(final View itemView) {
            super(itemView);
            nameTextView = (TextView) itemView.findViewById(R.id.velib_name);
            iconImageView = (ImageView) itemView.findViewById(R.id.velib_icon);
        }
    }

    public MyAdapter() {}

    public void setData(ArrayList<Velib> velibs) {
       mDataset = velibs;
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, final int position) {
        holder.nameTextView.setText(mDataset.get(position).getFields().getName());
        setIcon(holder.iconImageView, mDataset.get(position).getFields().getStatus());

        holder.nameTextView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), PagerActivity.class);
                intent.putExtra("position", position);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset == null ? 0 : mDataset.size() ;
    }

    private void setIcon(ImageView imageView, String status) {
        if (status.equals("OPEN")) {
            imageView.setImageResource(R.drawable.ic_check_circle);
            imageView.setColorFilter(Color.GREEN);
        }
        else {
            imageView.setImageResource(R.drawable.ic_remove_circle);
            imageView.setColorFilter(Color.RED);
        }
    }
}
