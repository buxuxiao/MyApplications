package com.example.myapplication.aspect;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.R;
import com.luculent.mobileSxgf3.IMath;

public class AspectTestActivity extends AppCompatActivity {
    private IMath iMath;
    private Button button;

    private ServiceConnection connection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d("abc","onServiceConnected");
            iMath=IMath.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d("abc","onServiceDisconnected");

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aspect_test);

        button= (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test(v);
            }
        });

        Intent intent=new Intent();
        intent.setAction("com.yn.bindertest.server");
        intent.setPackage("com.luculent.mobileSxgf3");
//        bindService(intent,connection, Context.BIND_AUTO_CREATE);

    }
    @TestAnnoTrace("hello")
    public void test(View view) {
        Log.d("abc","b, I am CSDN_LQR");
        /*try {
            button.setText(""+iMath.add(3,4));
        } catch (RemoteException e) {
            e.printStackTrace();
        }*/
    }
}
