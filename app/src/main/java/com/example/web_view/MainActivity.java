package com.example.web_view;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintJob;
import android.print.PrintManager;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private WebView webView;
    Button createpdf;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createpdf = findViewById(R.id.main_button1);

        createpdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    createPDF(v);
                }
            }
        });

        webView = findViewById(R.id.main_webview);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.loadUrl("http://www.webking.epizy.com/?i=1");
        webView.setWebViewClient(new WebViewClient());
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack())
        {
            webView.goBack();
        }
        else super.onBackPressed();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void createPDF(View view)
    {
        PrintManager printManager = (PrintManager)getSystemService(Context.PRINT_SERVICE);
        PrintDocumentAdapter adapter = null;
        adapter = webView.createPrintDocumentAdapter();
        String jobname = getString(R.string.app_name)+"Document";
        PrintJob printJob = printManager.print(jobname, adapter, new PrintAttributes.Builder().build());

    }
}