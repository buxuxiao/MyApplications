package com.example.myapplication.list_control;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapplication.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hulifei on 2019/4/30.
 */

public class ListControlAdapter extends BaseAdapter {

    private Context context;
    private List<String> list;

    public ListControlAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_control_item, parent, false);
            viewHolder = new ViewHolder();

            viewHolder.textView = convertView.findViewById(R.id.textView);
            viewHolder.up = convertView.findViewById(R.id.up);
            viewHolder.down = convertView.findViewById(R.id.down);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.textView.setText(list.get(position));

        viewHolder.up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPosition(position, -1);
            }
        });

        viewHolder.down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPosition(position, 1);
            }
        });

        return convertView;
    }

    class ViewHolder {
        TextView textView;
        TextView up;
        TextView down;
    }

    private void setPosition(int position, int offset) {
        int newPos = position + offset;
        if (newPos >= 0 && newPos <= list.size()) {
            String e = list.remove(position);
            list.add(newPos, e);
            notifyDataSetChanged();
        }
    }
}
