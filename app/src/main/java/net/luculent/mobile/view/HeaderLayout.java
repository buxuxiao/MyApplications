package net.luculent.mobile.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.myapplication.R;


public class HeaderLayout extends LinearLayout {
    LayoutInflater mInflater;
    RelativeLayout header;
    TextView titleView;
    LinearLayout leftContainer, rightContainer;
    Button backBtn;

    public HeaderLayout(Context context) {
        super(context);
        init();
    }

    public HeaderLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mInflater = LayoutInflater.from(getContext());
        header = (RelativeLayout) mInflater.inflate(R.layout.chat_common_base_header, null, false);
        titleView = (TextView) header.findViewById(R.id.titleView);
        leftContainer = (LinearLayout) header.findViewById(R.id.leftContainer);
        rightContainer = (LinearLayout) header.findViewById(R.id.rightContainer);
        backBtn = (Button) header.findViewById(R.id.backBtn);
        addView(header);
    }

    public void showTitle(int titleId) {
        titleView.setText(titleId);
    }

    public void showTitle(String s) {
        titleView.setText(s);
    }

    public void showLeftBackButton(OnClickListener listener) {
        showLeftBackButton(R.string.chat_common_emptyStr, listener);
    }

    public void showLeftBackButton() {
        showLeftBackButton(null);
    }

    public void showLeftBackButton(int backTextId, OnClickListener listener) {
        backBtn.setVisibility(View.VISIBLE);
        backBtn.setText(backTextId);
        if (listener == null) {
            listener = new OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((Activity) getContext()).finish();
                }
            };
        }
        backBtn.setOnClickListener(listener);
    }

    public void showRightText(int resId, OnClickListener listener) {
        TextView tv = new TextView(getContext());
        tv.setText(resId);
        tv.setTextColor(Color.WHITE);
        tv.setOnClickListener(listener);
        rightContainer.addView(tv);
    }

}
