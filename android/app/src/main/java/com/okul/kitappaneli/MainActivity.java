package com.okul.kitappaneli;

import android.os.Bundle;
import android.webkit.CookieManager;
import android.webkit.WebView;

import com.getcapacitor.BridgeActivity;

public class MainActivity extends BridgeActivity {

    @Override
    public void onStart() {
        super.onStart();

        // Bridge is initialized after super.onStart()
        WebView webView = getBridge().getWebView();

        // Allow all cookies including third-party (needed for Google OAuth)
        CookieManager.getInstance().setAcceptThirdPartyCookies(webView, true);
        CookieManager.getInstance().setAcceptCookie(true);

        // Remove "wv" (WebView) marker that Google OAuth detects and blocks
        String userAgent = webView.getSettings().getUserAgentString();
        userAgent = userAgent.replace("; wv", "");
        webView.getSettings().setUserAgentString(userAgent);

        // DOM storage enabled for auth state persistence
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setDatabaseEnabled(true);
    }
}
