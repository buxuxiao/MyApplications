package com.example.myapplication.list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hulifei on 2019/4/29.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<String> list = new ArrayList<>();

    public MyAdapter(List<String> list) {
        this.list = list;
    }

    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyAdapter.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent,false));
    }

    @Override
    public void onBindViewHolder(MyAdapter.MyViewHolder holder, int position) {
        holder.textView.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
        }
    }


}
