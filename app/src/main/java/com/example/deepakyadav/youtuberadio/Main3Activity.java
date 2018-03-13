package com.example.deepakyadav.youtuberadio;

import android.os.Build;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {
    public WebView webView;
    public SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        swipeRefreshLayout=findViewById(R.id.swipe_container);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                webView.reload();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        webView=findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.setWebChromeClient(new WebChromeClient());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            webView.getSettings().setSafeBrowsingEnabled(true);
        }
        webView.setWebViewClient(new WebViewClient(){

        });
        webView.loadUrl("https://youtube.com");
    }

    @Override
    public void onBackPressed() {
        if(webView.canGoBack()){
            webView.goBack();
        }else{
            super.onBackPressed();
        }

    }
}
