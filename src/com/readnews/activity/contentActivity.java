package com.readnews.activity;

import com.readnews.R;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class contentActivity extends Activity{

	private ProgressDialog dialog = null;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.content);
       
        String text;
        Intent intent = this.getIntent(); 
       	Bundle trans = intent.getBundleExtra("android.intent.extra.INTENT");
       	
		Toast toast = Toast.makeText(this,
			     "正在读取网络数据...", Toast.LENGTH_LONG);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
       	
       	WebView webView = (WebView)findViewById(R.id.newsWebView);
       	webView.getSettings().setJavaScriptEnabled(true);
       	webView.setHorizontalScrollBarEnabled(false);
       	webView.loadUrl(trans.getString("link"));
       	webView.setWebViewClient(new ReadNewsWebViewClient());
//       	webView.setWebChromeClient(new WebChromeClient(){
//
//			@Override
//			public void onProgressChanged(WebView view, int newProgress) {
//				// TODO Auto-generated method stub
//				contentActivity.this.setProgress(newProgress * 100);
////				if (newProgress == 20)
////				{
////					dialog.hide();
////				}
////				
//				
//			}
//       		
//       		
//       	});
//       	
       	
       	
       	
       	
       	
       	
	}
	
	private class ReadNewsWebViewClient extends WebViewClient{

		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			// TODO Auto-generated method stub
			view.loadUrl(url);

			return true;
		}

	
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
		if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {  
			// land do nothing is ok  
		} else if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {  
			// port do nothing is ok  
		}  
	}
	

}