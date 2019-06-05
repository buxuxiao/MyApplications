package com.example.myapplication.aspect;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.Resources;
import android.net.Uri;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.DrawableRes;
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

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                share();
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

    private void share(){
        String path = getResourcesUri(R.drawable.ic_launcher_background);
        Intent imageIntent = new Intent(Intent.ACTION_SEND);
        imageIntent.setType("image/jpeg");
        imageIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse(path));
        startActivity(Intent.createChooser(imageIntent, "分享"));
    }

    private String getResourcesUri(@DrawableRes int id) {
        Resources resources = getResources();
        String uriPath = ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
                resources.getResourcePackageName(id) + "/" +
                resources.getResourceTypeName(id) + "/" +
                resources.getResourceEntryName(id);
      //  Toast.makeText(this, "Uri:" + uriPath, Toast.LENGTH_SHORT).show();
        return uriPath;
    }
}
