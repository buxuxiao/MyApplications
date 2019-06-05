package com.example.myapplication.fingerprint;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import com.example.myapplication.R;

public class FingerPrintTestActivity extends AppCompatActivity {

    private Switch mSwitch;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finger_print_test);

        initView();
    }

    private void initView(){
        mSwitch= (Switch) findViewById(R.id.switch1);
        button= (Button) findViewById(R.id.button3);

        final FingerPrintAuthHelper fingerPrintAuthHelper=new FingerPrintAuthHelper(this);
        mSwitch.setChecked(fingerPrintAuthHelper.isAuthSupport());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fingerPrintAuthHelper.auth(new SimpleAuthCallback() {
                    @Override
                    public void onError(int code, CharSequence msg) {
                        button.setText("onError");
                    }

                    @Override
                    public void onFailed() {
                        button.setText("onFailed");
                    }

                    @Override
                    public void onSuccess() {
                        button.setText("onSuccess");
                    }
                });
            }
        });



    }
}
