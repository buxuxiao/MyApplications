package com.example.myapplication.cardview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

import com.example.myapplication.R;
import com.example.myapplication.cardview.helper.ItemTouchHelper;

import java.util.ArrayList;
import java.util.List;

public class CardSlipeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CardAdapter adapter;
    private List<String> list = new ArrayList<>();
    ItemTouchHelper itemTouchHelper = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_slipe);

        initView();
        initData();
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        itemTouchHelper = new ItemTouchHelper(new CardItemTouchHelperCallback(new OnSwipeListener() {
            @Override
            public void onSwiping(RecyclerView.ViewHolder viewHolder, float ratio, int direction) {

            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                if (direction == ItemTouchHelper.LEFT) {
                    Log.d("abc", "左滑");
                    return;
                }

                int pos = viewHolder.getLayoutPosition();
                list.remove(pos);
                adapter.notifyDataSetChanged();
            }
        }));


        adapter = new CardAdapter(recyclerView, itemTouchHelper, list);
        recyclerView.setLayoutManager(new CardLayoutManager());
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
