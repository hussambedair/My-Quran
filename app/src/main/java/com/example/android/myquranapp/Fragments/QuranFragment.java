package com.example.android.myquranapp.Fragments;


import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.android.myquranapp.Adapters.SourasRecyclerAdapter;
import com.example.android.myquranapp.CirclePagerIndicatorDecoration;
import com.example.android.myquranapp.DataHolder;
import com.example.android.myquranapp.Models.Soura;
import com.example.android.myquranapp.R;
import com.example.android.myquranapp.Activities.ReadQuranActivity;
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuranFragment extends Fragment {

    RecyclerView sourasRecyclerView;
    SourasRecyclerAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    List<Soura> souras;



    public QuranFragment() {
        // Required empty public constructor
    }

    View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_quran, container, false);

        createSourasList();

        sourasRecyclerView = view.findViewById(R.id.souras_recycler_view);
        adapter = new SourasRecyclerAdapter(souras);
        layoutManager = new GridLayoutManager(getContext(),3,
                LinearLayoutManager.HORIZONTAL,true);

        sourasRecyclerView.setAdapter(adapter);
        sourasRecyclerView.setLayoutManager(layoutManager);



        //sourasRecyclerView.addItemDecoration(new CirclePagerIndicatorDecoration());

        //Paging effect int the recycler view
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(sourasRecyclerView);




        adapter.setOnSouraClickListener(new SourasRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos, Soura soura) {
                Intent intent = new Intent (getActivity(), ReadQuranActivity.class);

                //take the following paremeters with you to the destination activty
                String souraName = soura.getSouraName();
                int souraPosition = pos+1; //because the pos parameter begins from zero

                //put the parameters in the intent
                intent.putExtra("sura_name",souraName);
                intent.putExtra("pos_name",souraPosition+"");

                // start the destination activity
                startActivity(intent);

            }

        });

        return view;

    }

    private void createSourasList() {

        souras = new ArrayList<Soura>();


        for (int i = 0; i< DataHolder.arrSuras.length; i++) {
            souras.add(new Soura(DataHolder.arrSuras[i]));
        }


    }

}
