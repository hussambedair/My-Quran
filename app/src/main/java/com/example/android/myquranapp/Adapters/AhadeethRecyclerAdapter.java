package com.example.android.myquranapp.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.myquranapp.Models.Aya;
import com.example.android.myquranapp.Models.Hadeeth;
import com.example.android.myquranapp.R;

import java.util.List;

public class AhadeethRecyclerAdapter
        extends RecyclerView.Adapter<AhadeethRecyclerAdapter.ViewHolder> {

    List<Hadeeth> mhadeeths;

    public AhadeethRecyclerAdapter(List<Hadeeth> mhadeeths) {
        this.mhadeeths = mhadeeths;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_hadeeth, parent, false);
        return new AhadeethRecyclerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Hadeeth hadeeth = mhadeeths.get(position);
        viewHolder.hadeethContent.setText(hadeeth.getmHadeethText());

    }

    @Override
    public int getItemCount() {
        return mhadeeths.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView hadeethContent;

        public ViewHolder(@NonNull View view) {
            super(view);
            hadeethContent = view.findViewById(R.id.hadeeth_text_view);


        }
    }




}
