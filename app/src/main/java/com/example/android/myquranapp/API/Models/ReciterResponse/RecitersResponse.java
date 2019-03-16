
package com.example.android.myquranapp.API.Models.ReciterResponse;

import java.util.List;

import com.google.gson.annotations.SerializedName;


public class RecitersResponse {

    @SerializedName("reciters")
    private List<Reciter> mReciters;

    public List<Reciter> getReciters() {
        return mReciters;
    }

    public void setReciters(List<Reciter> reciters) {
        mReciters = reciters;
    }

}
