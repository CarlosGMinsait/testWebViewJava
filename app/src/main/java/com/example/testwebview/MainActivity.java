package com.example.testwebview;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    WebView webView;
    String url = "http://192.168.1.194:4200/";

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        webView = (WebView) findViewById(R.id.webView);
        final WebSettings ajustesWebView = webView.getSettings();
        ajustesWebView.setJavaScriptEnabled(true);
        ajustesWebView.setDomStorageEnabled(true);
        ajustesWebView.setGeolocationEnabled(true);
        ajustesWebView.setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public  boolean onConsoleMessage(ConsoleMessage consoleMessage){
                Log.d("app", "mensaje: " + consoleMessage.message() + " -- linea " +
                        consoleMessage.lineNumber() + " de " + consoleMessage.sourceId());
                return true;
            }
        });

        webView.loadUrl(url);
    }
}