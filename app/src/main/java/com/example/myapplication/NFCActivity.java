package com.example.myapplication;

import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.MifareClassic;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Arrays;

public class NFCActivity extends AppCompatActivity {

    private NfcAdapter nfcAdapter;
    private String uid;

    private String tagInfo;

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfc);

        textView = (TextView) findViewById(R.id.textView);

        init(getIntent());
    }
    //e60a0bfb

    //045146ca293580



    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        init(intent);
    }

    private void init(Intent intent) {

        //判断NFC设备准备情况
        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if (nfcAdapter == null) {
            //提醒持NFC功能
            Toast.makeText(this, "this device not support nfc", Toast.LENGTH_SHORT).show();
        } else if (nfcAdapter.isEnabled() == false) {
            //提醒用户手机的NFC功能没有开启
            Toast.makeText(this, "this device not open nfc", Toast.LENGTH_SHORT).show();
        } else {
            //取出封装在intent中的TAG
            Tag tagFromIntent = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            byte[] uidBytes = tagFromIntent.getId();
            int id=byteArrayToInt(uidBytes);
            Log.d("abc","id="+id);
            this.uid = this.bytesToHexString(uidBytes);
            String action = this.getIntent().getAction();
            if (NfcAdapter.ACTION_TECH_DISCOVERED.equals(action)) {
                //调用方法，处理ACTION_TECH_DISCOVERED类型的数据
                TECH(tagFromIntent);
            } else if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(action)) {
                //调用方法，处理ACTION_NDEF_DISCOVERED类型的数据
                NDEF(intent);
            }
        }
    }

    private void TECH(Tag tagFromIntent) {

        //读取TAG
        MifareClassic mfc = MifareClassic.get(tagFromIntent);
        try {
            if (null != mfc) {
                mfc.connect();
                int sectorCount = mfc.getSectorCount();//获取TAG中包含的扇区数

                boolean auth = false;
                for (int j = 0; j < sectorCount; j++) {
            /*
             *  使用KeyA验证扇区
             */
                    auth = mfc.authenticateSectorWithKeyA(j, MifareClassic.KEY_DEFAULT);
                    int bIndex;
                    if (auth) {
                        // 读取扇区中的块
                        bIndex = mfc.sectorToBlock(j);
                        byte[] data = mfc.readBlock(bIndex);

                        String s = this.bytesToHexString(data);
                        if (null != s && !"".equals(s) && !"null".equals(s)) {
                            this.tagInfo += s;
                        }
                    } else {
                        tagInfo += "扇区" + j + "验证失败";
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        textView.setText(uid);
        textView.append("\n");
        textView.append("" + tagInfo);
    }

    private void NDEF(Intent intent) {

        Parcelable[] data = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);

        if (data != null) {
            try {
                for (int i = 0; i < data.length; i++) {
                    NdefRecord[] recs = ((NdefMessage) data[i]).getRecords();
                    for (int j = 0; j < recs.length; j++) {
                        if ((
                                recs[j].getTnf() == NdefRecord.TNF_WELL_KNOWN && Arrays.equals(recs[j].getType(), NdefRecord.RTD_TEXT))
                                || (recs[j].getTnf() == NdefRecord.TNF_WELL_KNOWN && Arrays.equals(recs[j].getType(), NdefRecord.RTD_URI))
                                ) {
                    /*
                     *读取普通的文本（即NdefRecord.RTD_TEXT）
                     *或者是网址（即NdefRecord.RTD_URI）
                     */
                            byte[] payload = recs[j].getPayload();
                            String textEncoding = "UTF-16";
                            if ((payload[0] & 0200) == 0) {
                                textEncoding = "UTF-8";
                            }

                            int langCodeLen = payload[0] & 0077;

                            String s = new String(payload, langCodeLen, payload.length - langCodeLen, textEncoding);
                            if (!"".equals(s) && !"null".equals(s)) {
                                this.tagInfo += s;
                            }
                        }
                    }
                }
            } catch (Exception e) {
                Log.e("TagDispatch", e.toString());
            }
        }
        textView.setText(uid);
        textView.append("\n");
        textView.append("" + tagInfo);

    }

    //字节数组转换为16进制字符串
    private String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("0x");
        if (src == null || src.length <= 0) {
            return null;
        }
        char[] buffer = new char[2];
        for (int i = 0; i < src.length; i++) {
            buffer[0] = Character.forDigit((src[i] >>> 4) & 0x0F, 16);
            buffer[1] = Character.forDigit(src[i] & 0x0F, 16);
            System.out.println(buffer);
            stringBuilder.append(buffer);
        }
        return stringBuilder.toString();
    }

    public static int byteArrayToInt(byte[] b) {
        return   b[3] & 0xFF |
                (b[2] & 0xFF) << 8 |
                (b[1] & 0xFF) << 16 |
                (b[0] & 0xFF) << 24;
    }

}
