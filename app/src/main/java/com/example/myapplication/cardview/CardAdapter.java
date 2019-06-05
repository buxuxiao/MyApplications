package com.example.myapplication.cardview;

import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.cardview.helper.ItemTouchHelper;

import java.util.List;

//test2
public class CardAdapter<T> extends RecyclerView.Adapter<CardAdapter.ViewHolder> {

    private List<T> list = null;
    private RecyclerView mRecyclerView;
    private ItemTouchHelper mItemTouchHelper;

    public CardAdapter(RecyclerView recyclerView, ItemTouchHelper touchHelper, List<T> list) {
        this.mRecyclerView = recyclerView;
        this.mItemTouchHelper = touchHelper;
        this.list = list;
    }

    @Override
    public CardAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_card_item2, parent, false));
    }

    @Override
    public void onBindViewHolder(CardAdapter.ViewHolder holder, int position) {
        String s = "text" + position;
        for (int i = 0; i < 5; i++) {
            s += s;
        }
        holder.setTextView(s);

        if (position == 0) {
            holder.itemView.setOnTouchListener(mOnTouchListener);
        } else {
            holder.itemView.setOnTouchListener(null);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text1);
        }

        public void setTextView(String s) {
            textView.setText(s);
        }
    }

    private View.OnTouchListener mOnTouchListener = new View.OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            RecyclerView.ViewHolder childViewHolder = mRecyclerView.getChildViewHolder(v);
            if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                mItemTouchHelper.startSwipe(childViewHolder);
            }

            return false;
        }
    };
}
