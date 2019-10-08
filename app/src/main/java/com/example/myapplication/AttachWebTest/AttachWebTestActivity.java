package com.example.myapplication.AttachWebTest;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.myapplication.R;

public class AttachWebTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attach_web_test);

        WebView webView= (WebView) findViewById(R.id.webView);


        webView.setWebChromeClient(new WebChromeClient(){

        });
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
            }
        });

        webView.loadUrl("https://szlk.luculent.net/Liems/www/index.html?page=workflowType&openid=oeKsT1QX5MB4WDwrIitgw3N-xyiE");
    }
}
