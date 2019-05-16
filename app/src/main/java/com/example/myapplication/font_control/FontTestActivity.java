package com.example.myapplication.font_control;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.myapplication.App;
import com.example.myapplication.R;

public class FontTestActivity extends AppCompatActivity {

    private FontSizeView fontSizeView;

    private int[] themes = {
            R.style.Theme_Small,
            R.style.Theme_Medium,
            R.style.Theme_Large,
            R.style.Theme_Super_Large,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(App.ctx.theme);
        setContentView(R.layout.activity_front_test);

        initView();
        initData();
    }

    private void initView() {

        fontSizeView = (FontSizeView) findViewById(R.id.fontSizeView);
        fontSizeView.setChangeCallbackListener(new FontSizeView.OnChangeCallbackListener() {
            @Override
            public void onChangeListener(int position) {
                App.ctx.theme = themes[position];
                recreate();
            }
        });

        int i = 0;
        for (; i < themes.length; i++) {
            if (themes[i] == App.ctx.theme) {
                break;
            }
        }

       fontSizeView.setDefaultPosition(i);
    }

    private void initData() {

    }
}
