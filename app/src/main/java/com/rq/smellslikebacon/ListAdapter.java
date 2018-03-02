package com.rq.smellslikebacon;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Faydee on 2018/3/2.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {

    private Context context;

    public class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.itemImageView) ImageView itemImageView;
        @BindView(R.id.itemTextView) TextView itemTextView;

        public ListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        public void bindView(int position) {
            itemImageView.setImageResource(Recipes.resourceIds[position]);
            itemTextView.setText(Recipes.names[position]);
        }

        @Override
        public void onClick(View v) {

        }
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        ListViewHolder viewHolder = new ListViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        holder.bindView(position);
    }

    @Override
    public int getItemCount() {
        return Recipes.names.length;
    }


}
