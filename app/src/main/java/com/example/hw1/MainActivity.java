package com.example.hw1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements NumberAdapter.NumberClicker {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, NumbersFragment.newInstance())
                .commit();
    }

    @Override
    public void onNumberClicked(Data item) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, NumberFragment.newInstance(item))
                .addToBackStack(null)
                .commit();
    }
}