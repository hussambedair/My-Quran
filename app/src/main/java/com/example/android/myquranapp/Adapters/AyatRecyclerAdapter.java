package com.example.android.myquranapp.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.myquranapp.Models.Aya;
import com.example.android.myquranapp.Models.Soura;
import com.example.android.myquranapp.R;

import java.util.List;

public class AyatRecyclerAdapter extends RecyclerView.Adapter<AyatRecyclerAdapter.ViewHolder> {

    List<Aya> mAyas;

    OnItemClickListener onAyaClickListener;


    public void setOnAyaClickListener(OnItemClickListener onAyaClickListener) {
        this.onAyaClickListener = onAyaClickListener;
    }

    public AyatRecyclerAdapter (List <Aya> ayas) {
        mAyas = ayas;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_aya, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        final Aya aya = mAyas.get(position);
        viewHolder.ayaContent.setText(aya.getmAyatext());

        if (onAyaClickListener != null) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onAyaClickListener.onItemClick(position,aya);
                }
            });

        }



        }





    @Override
    public int getItemCount() {
        return mAyas.size();
    }




    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView ayaContent;

        public ViewHolder(@NonNull View view) {
            super(view);
            ayaContent = view.findViewById(R.id.aya_text_view);


        }
    }

    public interface OnItemClickListener {
        public void onItemClick (int pos, Aya aya);
    }






}
