package com.example.lorris.tp_retrofit;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Lorris on 12/05/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private ArrayList<Article> mDataset;

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextView;

        public ViewHolder(final View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.list_text_item);
        }
    }

    public MyAdapter(ArrayList<Article> myDataset) {
        mDataset = myDataset;
    }

    public void setData(ArrayList<Article> articles) {
       mDataset = articles;
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, final int position) {
        holder.mTextView.setText(mDataset.get(position).getUri());

        holder.mTextView.setOnClickListener(new View.OnClickListener() {

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
}
