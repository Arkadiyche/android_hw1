package com.example.hw1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements NumberAdapter.NumberClicker {

    private boolean isNumberActivity = false;
    static NumbersFragment fragment = new NumbersFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getSupportFragmentManager().findFragmentById(R.id.fragment_container) == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, new NumbersFragment())
                    .commit();
        }
    }



    @Override
    public void onNumberClicked(Data item) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, NumberFragment.newInstance(item))
                .addToBackStack(null)
                .commit();
        isNumberActivity = true;
    }
}