package com.example.android.myquranapp.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.myquranapp.Models.Soura;
import com.example.android.myquranapp.R;

import java.util.List;

public class SourasRecyclerAdapter
        extends RecyclerView.Adapter <SourasRecyclerAdapter.ViewHolder> {

    List<Soura> mItems;

    OnItemClickListener onSouraClickListener;

    public void setOnSouraClickListener(OnItemClickListener onSouraClickListener) {
        this.onSouraClickListener = onSouraClickListener;
    }

    public SourasRecyclerAdapter (List <Soura> items) {
        mItems=items;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_soura, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        final Soura soura = mItems.get(position);
        viewHolder.souraName.setText(soura.getSouraName());

        if (onSouraClickListener != null) {
            viewHolder.souraName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onSouraClickListener.onItemClick(position,soura);
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }





    class ViewHolder extends RecyclerView.ViewHolder  {

        TextView souraName;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            souraName= itemView.findViewById(R.id.soura_name);



        }


    }

    public interface OnItemClickListener {
        public void onItemClick (int pos, Soura soura);
    }

















}
