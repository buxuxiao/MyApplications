package com.example.myapplication.cardview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.R;

import java.util.List;
//test2
public class CardAdapter<T> extends RecyclerView.Adapter<CardAdapter.ViewHolder> {

    private List<T> list = null;

    public CardAdapter(List<T> list) {
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
}
