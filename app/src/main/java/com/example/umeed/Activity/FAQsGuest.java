package com.example.umeed.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.umeed.R;

public class FAQsGuest extends AppCompatActivity {

    WebView guest_video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faqs_guest);
        guest_video= findViewById(R.id.guest_video);
        WebSettings webSettings=guest_video.getSettings();
        webSettings.setJavaScriptEnabled(true);
        guest_video.setWebViewClient(new Callback());
        guest_video.loadUrl("https://youtu.be/yYzwODMrHWw");

    }

    private class Callback extends WebViewClient {
        @Override
        public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {return false;}
    }
}