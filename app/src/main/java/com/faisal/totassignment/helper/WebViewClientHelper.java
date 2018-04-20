package com.faisal.totassignment.helper;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.faisal.totassignment.R;

public class WebViewClientHelper extends WebViewClient {

    private Context mContext;

    public WebViewClientHelper(Context context) {
        mContext = context;
    }

    @Override

    public boolean shouldOverrideUrlLoading(WebView view, String url) {

        if (Uri.parse(url).getHost().equals(mContext.getResources().getString(R.string.webview_url))) {

            return false;

        }

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));

        mContext.startActivity(intent);

        return true;
    }
}