package com.example.android.myquranapp.API;

import com.example.android.myquranapp.API.Models.RadioResponse.RadiosResponse;
import com.example.android.myquranapp.API.Models.ReciterResponse.RecitersResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiCalls {

    @GET ("radio//radio_ar.json")
    public Call<RadiosResponse> getAllRadioChannels() ;


    @GET ("_arabic.json")
    public Call<RecitersResponse> getAllreciters();










}
