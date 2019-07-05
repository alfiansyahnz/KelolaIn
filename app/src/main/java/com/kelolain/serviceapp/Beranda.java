package com.kelolain.serviceapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.kelolain.serviceapp.Fragment.BerandaFragment;
import com.kelolain.serviceapp.Fragment.RiwayatFragment;
import com.kelolain.serviceapp.Fragment.ProfileFragment;
import com.kelolain.serviceapp.Fragment.LainnyaFragment;


public class Beranda extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beranda);

        loadFragment(new BerandaFragment());

        BottomNavigationView navigation = findViewById(R.id.bn_main);
        navigation.setOnNavigationItemSelectedListener(this);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.navigation_beranda:
                fragment = new BerandaFragment();
                break;

            case R.id.navigation_riwayat:
                fragment = new RiwayatFragment();
                break;
            case R.id.navigation_lainnya:
                fragment = new LainnyaFragment();
                break;

            case R.id.navigation_akun:
                fragment = new ProfileFragment();
                break;

        }

        return loadFragment(fragment);
    }
//kelas pinjam parkiran jadwal
    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fl_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }
}
