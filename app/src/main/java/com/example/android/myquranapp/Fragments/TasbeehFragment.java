package com.example.android.myquranapp.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.android.myquranapp.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class TasbeehFragment extends Fragment {

    ImageView sebha;
    ImageView refresh;
    TextView tasbeehCount;
    TextView tasbeehTotal;

    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;



    int tasbeehTimes = 0;
    int totalTasbeehs= 0;






    public TasbeehFragment() {
        // Required empty public constructor
    }

    View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_tasbeeh, container, false);

        sebha = view.findViewById(R.id.sebha_image_view);
        refresh = view.findViewById(R.id.refresh_icon);
        tasbeehCount = view.findViewById(R.id.tasbeh_count_textView);
        tasbeehTotal = view.findViewById(R.id.tasbeeh_total_textView);

        spinner = view.findViewById(R.id.spinner);

        // Create an ArrayAdapter using the string array and a default spinner layout
        adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.planets_array, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tasbeehTimes = 0;
                tasbeehCount.setText(tasbeehTimes+"");


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        sebha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tasbeehCounter();
                tasbeehCount.setText(tasbeehTimes+"");
                tasbeehTotal.setText(totalTasbeehs+"");

            }
        });

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear();
                tasbeehCount.setText(tasbeehTimes+"");
                tasbeehTotal.setText(totalTasbeehs+"");
            }
        });


        return view;
    }

    public void tasbeehCounter () {
        tasbeehTimes++;
        totalTasbeehs++;
    }

    public void clear () {
        tasbeehTimes=0;
        totalTasbeehs=0;

    }





}
