package com.example.umeed.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.umeed.R;

public class FAQsRegisater extends AppCompatActivity {

    WebView register_video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faqs_regisater);
        register_video= findViewById(R.id.register_video);
        WebSettings webSettings=register_video.getSettings();
        webSettings.setJavaScriptEnabled(true);
        register_video.setWebViewClient(new Callback());
        register_video.loadUrl("https://youtu.be/zMrr7OKBhfQ");

    }

    private class Callback extends WebViewClient {
        @Override
        public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
            return false;
        }
    }
}