package com.example.myapplication.list_control2;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.R;

import java.util.List;

/**
 * Created by hulifei on 2019/5/5.
 */

public class ListControl2Adapter extends RecyclerView.Adapter<ListControl2Adapter.ViewHolder> {
    private List<String> list;

    private int[] colors = {
            0xff2ec7c9, 0xffb6a2de, 0xff5ab1ef, 0xffffb980, 0xffd87a80,
            0xff8d98b3, 0xffe5cf0d, 0xff97b552, 0xff95706d, 0xffdc69aa,
            0xff07a2a4, 0xff9a7fd1, 0xff588dd5, 0xfff5994e, 0xffc05050,
            0xff59678c, 0xffc9ab00, 0xff7eb00a, 0xff6f5553, 0xffc14089,
    };

    public ListControl2Adapter(List<String> list) {
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(list.get(position));
        holder.textView.setBackgroundColor(colors[position % colors.length]);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);

//            itemView.getLayoutParams().width = itemView.getContext().getResources().getDisplayMetrics().widthPixels / 4;

            textView = itemView.findViewById(R.id.textView);
        }
    }
}
