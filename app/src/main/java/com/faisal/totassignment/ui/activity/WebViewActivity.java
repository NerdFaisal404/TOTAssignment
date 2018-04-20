package com.faisal.totassignment.ui.activity;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.faisal.totassignment.R;
import com.faisal.totassignment.helper.WebViewClientHelper;

public class WebViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        WebView myWebView = (WebView) findViewById(R.id.webView_mcc);

        myWebView.loadUrl(getResources().getString(R.string.webview_url));

        //Open another link from main website
        myWebView.setWebViewClient(new WebViewClientHelper(this));

        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }

}
