package com.example.hw1;

import android.graphics.Color;
import android.os.Bundle;
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

public class NumbersFragment extends Fragment implements View.OnClickListener {
    private NumberAdapter adapter;
    private List<Data> numbers = new ArrayList<>();

    public NumbersFragment() {
        super();
    }

    static NumbersFragment newInstance() {
        return new NumbersFragment();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        for (int i = 0; i < 100; ++i) {
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
