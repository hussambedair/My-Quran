
package com.example.android.myquranapp.API.Models.RadioResponse;

import java.util.List;

import com.google.gson.annotations.SerializedName;



public class RadiosResponse {

    @SerializedName("Radios")
    private List<Radio> mRadios;

    public List<Radio> getRadios() {
        return mRadios;
    }

    public void setRadios(List<Radio> radios) {
        mRadios = radios;
    }



}
