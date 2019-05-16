package com.example.myapplication;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.IOException;

public class VoiceRecordActivity extends AppCompatActivity {

    private Button buttonStar, buttonEnd;

    private MediaRecorder mediaRecorder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice_record);

        initView();
        initData();
    }

    private void initView() {
        buttonStar = (Button) findViewById(R.id.button_star);
        buttonEnd = (Button) findViewById(R.id.button_end);

        buttonStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                star();
            }
        });

        buttonEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                end();
            }
        });
    }

    private void initData() {

    }

    private void star() {
       String path= new File(Environment.getExternalStorageDirectory(), "abc.amr").toString();
        if (mediaRecorder == null) {
            try {
                mediaRecorder = new MediaRecorder();
                mediaRecorder.setAudioSource(MediaRecorder.AudioSource.DEFAULT);
                mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.AAC_ADTS);
                mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
                mediaRecorder.setOutputFile(path);
                mediaRecorder.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            mediaRecorder.reset();
            mediaRecorder.setOutputFile(path);
        }

        mediaRecorder.start();
    }

    private void end() {
        if(mediaRecorder!=null){
            try{
                mediaRecorder.stop();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                mediaRecorder.release();
                mediaRecorder=null;
            }
        }
    }
}
