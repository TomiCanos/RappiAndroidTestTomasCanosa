package com.example.admin.rappiandroidtesttomascanosa.view;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.example.admin.rappiandroidtesttomascanosa.R;

import java.util.Locale;


public class MainActivity extends AppCompatActivity {
    private Locale locale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locale = getResources().getConfiguration().locale;

        Bundle bundle = new Bundle();
        bundle.putString(HomeFragment.LANGUAGE, locale.toString());
        HomeFragment homeFragment = new HomeFragment();
        homeFragment.setArguments(bundle);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.main_activity_fragment_containter, homeFragment);
        fragmentTransaction.commit();
    }

}
