package com.example.android.myquranapp.Fragments;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.myquranapp.API.APIManager;
import com.example.android.myquranapp.API.Models.RadioResponse.Radio;
import com.example.android.myquranapp.API.Models.RadioResponse.RadiosResponse;
import com.example.android.myquranapp.Adapters.RadiosRecyclerAdapter;
import com.example.android.myquranapp.Base.BaseFragment;
import com.example.android.myquranapp.R;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class RadioFragment extends BaseFragment {

    RecyclerView radiosRecyclerView;
    RadiosRecyclerAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    List<Radio> radios;

    View loadingIndicator;


    public RadioFragment() {
        // Required empty public constructor
    }

    View view;

    @Override
    public void onDetach() {
        super.onDetach();
        stopRadio();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_radio, container, false);


        radiosRecyclerView = view.findViewById(R.id.radios_recycler_view);
        adapter = new RadiosRecyclerAdapter(null);
        layoutManager = new LinearLayoutManager(getActivity());

        radiosRecyclerView.setAdapter(adapter);

        adapter.setOnPlayClickListener(new RadiosRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos, Radio radio) {
                playRadio(radio.getURL());

            }
        });

        adapter.setOnStopClickListener(new RadiosRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos, Radio radio) {
                stopRadio();

            }
        });

        radiosRecyclerView.setLayoutManager(layoutManager);

        //SnapHelper snapHelper = new PagerSnapHelper();
        //snapHelper.attachToRecyclerView(radiosRecyclerView);

        loadingIndicator = view.findViewById(R.id.loading_indicator);

        getRadioChannels();



        return view;
    }

    MediaPlayer mediaPlayer;

    public void playRadio (String url) {
        stopRadio();
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(url);
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.start();
                }
            });

        } catch (IOException ioe) {
            showMessage(R.string.error,R.string.cannot_play_radio,R.string.ok);
        }


    }

    public void stopRadio () {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }

    }





    void getRadioChannels () {
        loadingIndicator.setVisibility(View.VISIBLE);
        APIManager.getAPIs()
                .getAllRadioChannels()
                .enqueue(new Callback<RadiosResponse>() {
                    @Override
                    public void onResponse(Call<RadiosResponse> call,
                                           Response<RadiosResponse> response) {
                        loadingIndicator.setVisibility(View.GONE);
                        if (response.isSuccessful()) {
                            adapter.changeData(response.body().getRadios());


                        }

                    }

                    @Override
                    public void onFailure(Call<RadiosResponse> call, Throwable t) {
                        loadingIndicator.setVisibility(View.GONE);
                        showMessage("Error!", t.getLocalizedMessage(),"ok");




                    }
                });

    }

}
