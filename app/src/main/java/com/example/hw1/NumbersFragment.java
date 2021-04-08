package com.example.hw1;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class    NumbersFragment extends Fragment implements View.OnClickListener {
    private NumberAdapter adapter;
    private List<Data> numbers = new ArrayList<>();
    private int count = 100;

    public NumbersFragment() {
        super();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            //Log.d("countIf", String.valueOf(count));
            count = savedInstanceState.getInt("NumCount", 100);
            //savedInstanceState.putInt("NumCount",count);
        }
        //Log.d("count", String.valueOf(count));
        for (int i = 0; i < count; ++i) {
            numbers.add(createItemToAdd());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_number_list, parent, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycleview);
        NumberAdapter.NumberClicker listener = (NumberAdapter.NumberClicker) getActivity();
        adapter = new NumberAdapter(numbers, listener);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), getResources().getConfiguration().orientation + 2));
        view.findViewById(R.id.button).setOnClickListener(this);
        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        adapter.clearRefs();
    }


    @Override
    public void onClick(View view) {
        addItem();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle state) {
        Log.d("onSaveInstanceState();", String.valueOf(numbers.size()));
        state.putInt("NumCount", numbers.size());
        super.onSaveInstanceState(state);
    }

    private void addItem() {
        Data item = createItemToAdd();
        numbers.add(item);
        adapter.notifyItemInserted(item.num - 1);
    }

    private Data createItemToAdd() {
        int index = numbers.size() + 1;
        int color;
        if (index % 2 ==0) {
            color = Color.RED;
        } else {
            color = Color.BLUE;
        }
        return new Data(index, color);
    }

}
