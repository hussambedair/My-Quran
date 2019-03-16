package com.example.android.myquranapp.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.myquranapp.API.Models.RadioResponse.Radio;
import com.example.android.myquranapp.R;

import java.util.List;

public class RadiosRecyclerAdapter
        extends RecyclerView.Adapter<RadiosRecyclerAdapter.ViewHolder> {

    List<Radio> mRadios;

    OnItemClickListener onPlayClickListener;
    OnItemClickListener onStopClickListener;

    public RadiosRecyclerAdapter(List<Radio> radios) {
        mRadios = radios;

    }

    public void setOnPlayClickListener(OnItemClickListener onPlayClickListener) {
        this.onPlayClickListener = onPlayClickListener;
    }

    public void setOnStopClickListener(OnItemClickListener onStopClickListener) {
        this.onStopClickListener = onStopClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_radio, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        final Radio radio = mRadios.get(position);
        viewHolder.radioName.setText(radio.getName());

        if (onPlayClickListener != null) {
            viewHolder.playIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onPlayClickListener.onItemClick(position, radio);
                }
            });

        }

        if (onStopClickListener != null) {
            viewHolder.stopIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onStopClickListener.onItemClick(position, radio);
                }
            });

        }


    }

    public void changeData(List<Radio> radios) {
        mRadios = radios;
        notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        if (mRadios == null) {
            return 0;
        }
        return mRadios.size();
    }


    public interface OnItemClickListener {
        public void onItemClick(int pos, Radio radio);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView radioName;
        ImageView playIcon;
        ImageView stopIcon;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            radioName = itemView.findViewById(R.id.radio_name_text_view);
            playIcon = itemView.findViewById(R.id.play_button);
            stopIcon = itemView.findViewById(R.id.stop_button);


        }


    }


}
