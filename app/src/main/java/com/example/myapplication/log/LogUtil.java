package com.example.myapplication.log;

import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class LogUtil {
    public static void logAll() {
        String[] running = new String[]{/*"logcat", "-s",*/ "adb logcat *: W"};
        Process exec = null;
        try {
            exec = Runtime.getRuntime().exec(running);
        } catch (IOException e) {
            e.printStackTrace();
        }
        final InputStream is = exec.getInputStream();
        FileOutputStream os = null;
        try {
            //新建一个路径信息
            os = new FileOutputStream("/sdcard/Log/Log1.txt");
            int len = 0;
            byte[] buf = new byte[1024];
            while (-1 != (len = is.read(buf))) {
                os.write(buf, 0, len);
                os.flush();
            }
        } catch (Exception e) {
            Log.d("writelog",
                    "read logcat process failed. message: "
                            + e.getMessage());
        } finally {
            if (null != os) {
                try {
                    os.close();
                    os = null;
                } catch (IOException e) {
                    // Do nothing
                }
            }
        }
    }
}
