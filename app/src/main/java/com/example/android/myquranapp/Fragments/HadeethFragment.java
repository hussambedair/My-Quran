package com.example.android.myquranapp.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.myquranapp.Adapters.AhadeethRecyclerAdapter;
import com.example.android.myquranapp.Adapters.SourasRecyclerAdapter;
import com.example.android.myquranapp.Models.Aya;
import com.example.android.myquranapp.Models.Hadeeth;
import com.example.android.myquranapp.Models.Soura;
import com.example.android.myquranapp.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HadeethFragment extends Fragment {

    RecyclerView ahadeethRecyclerView;
    AhadeethRecyclerAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    List<Hadeeth> hadeeths;


    public HadeethFragment() {
        // Required empty public constructor
    }

    View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_hadeeth, container, false);

        createAhadeeth();

        ahadeethRecyclerView = view.findViewById(R.id.ahadeeth_recycler_view);
        adapter = new AhadeethRecyclerAdapter(hadeeths);
        layoutManager = new LinearLayoutManager(getActivity());

        ahadeethRecyclerView.setAdapter(adapter);
        ahadeethRecyclerView.setLayoutManager(layoutManager);



        return view;
    }

    private void createAhadeeth() {

        hadeeths = new ArrayList<Hadeeth>();

        try{

            final InputStream file = getActivity().getAssets().open("ahadeth.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(file));
            String line ;
            while( (line = reader.readLine()) != null ){
                Log.d("hadeeth_line", line);
                hadeeths.add(new Hadeeth(line));

            }
        } catch(IOException ioe){
            ioe.printStackTrace();
        }
    }


}
