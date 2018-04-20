package com.faisal.totassignment.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.faisal.totassignment.R;
import com.faisal.totassignment.helper.WebViewClientHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class WebviewFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout swipeRefreshLayout;
    private WebView mccWebview;

    public WebviewFragment() {
        // Required empty public constructor
    }

    public static WebviewFragment newInstance() {
        WebviewFragment fragment = new WebviewFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_webview, container, false);
        swipeRefreshLayout = view.findViewById(R.id.swipe_webview);
        mccWebview = view.findViewById(R.id.webView_mcc);

        swipeRefreshLayout.setOnRefreshListener(this);

        loadWebView();
/*
        swipeRefreshLayout.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        swipeRefreshLayout.setRefreshing(true);
                                        loadWebView();

                                    }
                                }
        );*/

        return view;
    }

    @Override
    public void onRefresh() {
        mccWebview.reload();
        swipeRefreshLayout.setRefreshing(false);
    }

    private void loadWebView() {

        mccWebview.loadUrl(getResources().getString(R.string.webview_url));
        swipeRefreshLayout.setRefreshing(false);
        //Open another link from main website
        mccWebview.setWebViewClient(new WebViewClientHelper(getActivity()));

        WebSettings webSettings = mccWebview.getSettings();
        webSettings.setJavaScriptEnabled(true);

    }
}
