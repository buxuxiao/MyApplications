package com.example.myapplication.cardview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class CardSlipeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CardAdapter adapter;
    private List<String> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_slipe);

        initView();
        initData();
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        adapter = new CardAdapter(list);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new CardItemTouchHelperCallback(list, adapter, new OnSwipeListener() {
            @Override
            public void onSwiping(RecyclerView.ViewHolder viewHolder, float ratio, int direction) {

            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, Object o, int direction) {

            }

            @Override
            public void onSwipedClear() {

            }
        }));
        recyclerView.setLayoutManager(new CardLayoutManager(recyclerView, itemTouchHelper));
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    private void initData() {
        for (int i = 0; i < 10; i++) {
            list.add("");
        }
        adapter.notifyDataSetChanged();
    }


}
