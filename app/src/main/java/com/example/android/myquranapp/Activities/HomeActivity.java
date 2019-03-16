package com.example.android.myquranapp.Activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.MenuItem;

import com.example.android.myquranapp.Base.BaseActivity;
import com.example.android.myquranapp.Fragments.QuranFragment;
import com.example.android.myquranapp.Fragments.RadioFragment;
import com.example.android.myquranapp.Fragments.TasbeehFragment;
import com.example.android.myquranapp.Fragments.HadeethFragment;
import com.example.android.myquranapp.R;

public class HomeActivity extends BaseActivity {


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            int id = menuItem.getItemId();
            Fragment fragment = null;

            if (id == R.id.navigation_quran) {
                fragment = new QuranFragment();


            } else if (id == R.id.navigation_tasbeeh) {
                fragment = new TasbeehFragment();


            } else if (id == R.id.navigation_hadeeth) {
                fragment = new HadeethFragment();
            } else if (id == R.id.navigation_radio) {
                fragment = new RadioFragment();
            } 

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container,fragment)
                    //.addToBackStack(null)
                    .commit();


            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //make the QuranFragment the default selected fragment in the navigation
        navigation.setSelectedItemId(R.id.navigation_quran);




    }

}
