package weather.wu.com.activity;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.orhanobut.logger.Logger;

import weather.wu.com.weather.R;

/**
 * Created by 吴海辉 on 2017/2/20.
 */
public class WebViewActivity extends Activity {
    WebView mWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        mWebView = (WebView)findViewById(R.id.webview);
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        String url = getIntent().getStringExtra("url");
        Logger.d(url);
        mWebView.loadUrl(url);
    }
}
