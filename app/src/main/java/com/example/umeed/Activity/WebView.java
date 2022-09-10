package com.example.umeed.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;

import com.example.umeed.R;

public class WebView extends AppCompatActivity {

   android.webkit.WebView cowin_website;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        cowin_website= findViewById(R.id.cowin_website);
        WebSettings webSettings=cowin_website.getSettings();
        webSettings.setJavaScriptEnabled(true);
        cowin_website.setWebViewClient(new Callback());
        cowin_website.loadUrl("https://www.cowin.gov.in/");

    }

    private class Callback extends WebViewClient {
        @Override
        public boolean shouldOverrideKeyEvent(android.webkit.WebView view, KeyEvent event) {
            return false;
        }
    }
}