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

public class MainActivity extends AppCompatActivity {
public WebView webView;
public SwipeRefreshLayout swipeRefreshLayout;
Thread temp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        temp=new Thread(){
            @Override
            public void run() {
                try {
                    temp.sleep(1999999999);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                super.run();
            }
        };

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
    protected void onPause() {
        onResume();
        Log.i("onPause","");
        temp.start();
        try {
            temp.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
            Toast.makeText(this, "Inturrepted", Toast.LENGTH_SHORT).show();
        }
        Toast.makeText(this, "onPause", Toast.LENGTH_SHORT).show();
        super.onPause();
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
