package com.example.android.myquranapp.Activities;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.myquranapp.Adapters.AyatRecyclerAdapter;
import com.example.android.myquranapp.Adapters.SourasRecyclerAdapter;
import com.example.android.myquranapp.Base.BaseActivity;
import com.example.android.myquranapp.Fragments.QuranFragment;
import com.example.android.myquranapp.Models.Aya;
import com.example.android.myquranapp.Models.Soura;
import com.example.android.myquranapp.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ReadQuranActivity extends BaseActivity {

    RecyclerView ayatRecyclerView;
    AyatRecyclerAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    List<Aya> ayas;

    TextView souraName;
    MediaPlayer mediaPlayer;

    @Override
    protected void onPause() {
        super.onPause();
        stopAya();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_quran);

        souraName = findViewById(R.id.soura_name_textview);


        souraName.setText("سورة" + getIntent().getStringExtra("sura_name"));


        createAyat();


        ayatRecyclerView = findViewById(R.id.ayat_recycler_view);

        adapter = new AyatRecyclerAdapter(ayas);

        layoutManager = new LinearLayoutManager(ReadQuranActivity.this);

        ayatRecyclerView.setAdapter(adapter);

        adapter.setOnAyaClickListener(new AyatRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos, Aya aya) {
                //Toast.makeText(ReadQuranActivity.this, "Aya Pressed",Toast.LENGTH_SHORT).show();
                String url;

                String souraPos = getIntent().getStringExtra("pos_name");
                String ayaPos = pos + 1 + "";

                if (souraPos.length() == 1) {
                    souraPos = "00" + souraPos;
                } else if (souraPos.length() == 2) {
                    souraPos = "0" + souraPos;
                } else if (souraPos.length() == 3) {
                    souraPos = "" + souraPos;
                }

                if (ayaPos.length() == 1) {
                    ayaPos = "00" + ayaPos;
                } else if (ayaPos.length() == 2) {
                    ayaPos = "0" + ayaPos;
                } else if (ayaPos.length() == 3) {
                    ayaPos = "" + ayaPos;
                }


                url = "https://verse.mp3quran.net/data/Abu_Bakr_Ash-Shaatree_64kbps/"
                        + souraPos + ayaPos + ".mp3";

                playAya(url);
            }
        });


        ayatRecyclerView.setLayoutManager(layoutManager);


    }

    public void playAya(String url) {
        stopAya();
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
            showMessage(R.string.error, R.string.cannot_play_radio, R.string.ok);
        }
    }

    public void stopAya() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }

    }

    private void createAyat() {

        ayas = new ArrayList<Aya>();

        try {

            final InputStream file = getAssets().open(getIntent().getStringExtra("pos_name") + ".txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                Log.e("aya_line", line);
                ayas.add(new Aya(line));
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }


    }
}
