package com.readnews.activity;

import android.app.ProgressDialog;
import android.app.TabActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;


import com.readnews.R;


import com.readnews.activity.NewsListActivity.newsReceiver;
import com.readnews.service.readNewsService;
import com.readnews.xml.RssGroup;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.GestureDetector.OnGestureListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.TabHost;
import android.widget.Toast;
import android.widget.TabHost.TabSpec;

public class mainActivity extends TabActivity{

	private TabHost tabHost;
	private TabSpec nationNews;
	private TabSpec internationNews;
	private TabSpec enterNews;
	private TabSpec sportNews;
	private Context context = this;
	private int CurrentView = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.main);
		
//		
			Toast toast = Toast.makeText(this,
				     "正在载入程序...", Toast.LENGTH_LONG);
			toast.setGravity(Gravity.CENTER, 0, 0);
			toast.show();

//			tabHost = (TabHost)this.findViewById(android.R.id.tabhost);
//
//			for (int i = 0; i < AppContent.url.newsUrl.length; ++ i)
//			{
//
//				System.out.println("system.out.println + " + AppContent.url.newsUrl[i]);
//				NewsListActivity newsActivity = new NewsListActivity();
//				TabSpec newsSpec = tabHost.newTabSpec(Integer.toString(i));
//				Intent transIntent = new Intent(this, newsActivity.getClass());
//				transIntent.putExtra("url", AppContent.url.newsUrl[i]);
//				newsSpec.setIndicator(AppContent.url.newsTab[i]).setContent(transIntent);
//
//				tabHost.addTab(newsSpec);
//				System.out.println("Before set Tab");
//
//				tabHost.setCurrentTabByTag(Integer.toString(i));
//			}
//			
//			
//		
//			
//			tabHost.setCurrentTabByTag(Integer.toString(0));
//			
//			Intent updateIntent = new Intent();
//			updateIntent.setClass(this, readNewsService.class);
//			this.startService(updateIntent);
//			
//		
//			
//			this.getWindow().setBackgroundDrawableResource(R.color.black);
//
//		
//
//		
//		
//		
		


	}
	
	@Override
	//控制屏幕旋转之后不销毁activity
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
		if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {  
			// land do nothing is ok  
		} else if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {  
			// port do nothing is ok  
		}  
	}

	
	/*  
	*@return boolean return true if the application can access the internet  
	*/

//	@Override
//	public boolean onKeyDown(int kCode, KeyEvent event) {
//		// TODO Auto-generated method stub
//		TabHost tabHost = this.getTabHost();
//		
//		if (kCode == KeyEvent.KEYCODE_DPAD_LEFT)
//		{
//			int a = tabHost.getCurrentTab();
//			System.out.println("left Before + " + a);
//			if (a == 0)
//			{
//				a = AppContent.url.newsUrl.length - 1;
//			}
//			else 
//			{
//				a--;
//			}
//			System.out.println("left After + " + a);
//
//			tabHost.setCurrentTab(a);
//		}
//		else if(kCode == KeyEvent.KEYCODE_DPAD_RIGHT)
//		{
//				int a = tabHost.getCurrentTab();
//				System.out.println("right Before + " + a);
//				if (a == AppContent.url.newsUrl.length - 1)
//				{
//					a = 0;
//				}
//				else 
//				{
//					++ a;
//					
//				}
//				System.out.println("right After + " + a);
//
//				tabHost.setCurrentTab(a);
//		}
//		
//		return false;
//		//return super.onKeyDown(kCode, event);
//	}


}
