package com.readnews.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.app.TabActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;


import com.readnews.R;


import com.readnews.service.readNewsService;
import com.readnews.xml.NewsAdapter;
import com.readnews.xml.RssGroup;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.GestureDetector.OnGestureListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TabHost.TabSpec;

public class mainActivity extends Activity implements OnItemClickListener{
	
	private TabHost tabHost;
	private TabSpec nationNews;
	private TabSpec internationNews;
	private TabSpec enterNews;
	private TabSpec sportNews;
	private Context context = this;
	private int CurrentView = 0;
	private String url = null;
	private DrawAsyncTask asyncTask = null;
	private ListView listView = null; 
	private ProgressDialog dialog = null;
	private RssGroup itemList = null;
	private  newsReceiver receiver = null;
	private Button button1 = null;
	private Button button2 = null;
	private Button button3 = null;
	private Button button4 = null;
	private Button button5 = null;
	private RssGroup[] allList = null;
	private TextView itemNum = null;
	private LinearLayout mainLinearLayout = null;
	
	public static void launch(Context c)
	{
		Intent intent = new Intent(c, mainActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		c.startActivity(intent);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.main);
		mainLinearLayout = (LinearLayout)this.findViewById(R.id.mainLinearLayout);
	//	mainActivity.this.setTheme(R.style.mytheme);

		
		//this.requestWindowFeature(Window.FEATURE_PROGRESS);
		button1 = (Button)findViewById(R.id.button1);
		button2 = (Button)findViewById(R.id.button2);
		button3 = (Button)findViewById(R.id.button3);
		button4 = (Button)findViewById(R.id.button4);
		button5 = (Button)findViewById(R.id.button5);
		itemNum = (TextView)this.findViewById(R.id.itemnum);
			
//			
//		button1.setText("国内新闻");
//		button2.setText("国际新闻");
//		button3.setText("娱乐新闻");
//		button4.setText("体育新闻");
//		button5.setText("科技新闻");

		allList = new RssGroup[8];
		url = AppContent.url.newsUrl[0];
		receiver = new newsReceiver();
		listView = (ListView)findViewById(R.id.itemList);
		listView.setBackgroundResource(R.drawable.beijing);
		listView.setPadding(25, 35, 25, 40);
		
		
		dialog = new ProgressDialog(this);
		
		IntentFilter filter = new IntentFilter();
		filter.addAction(AppContent.myAction.updateNewsAction);
		this.registerReceiver(receiver, filter);
		if (!this.isNetworkAvailable())
		{
			Toast toast = Toast.makeText(this,
				"无法链接网络...", Toast.LENGTH_LONG);
			toast.setGravity(Gravity.CENTER, 0, 0);
			toast.show();
			
		}
		
		else
		{
			
//			
			Toast toast = Toast.makeText(this,
					"正在载入程序...", Toast.LENGTH_LONG);
			toast.setGravity(Gravity.CENTER, 0, 0);
			toast.show();
			
			listView.setOnItemSelectedListener(new OnItemSelectedListener(){

				@Override
				public void onItemSelected(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					System.out.println("the id is :" + arg2 + "and total id is " + listView.getCount());
					
					itemNum.setText(++arg2 + "/" + listView.getCount());
				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub
					
				}
				
				
			});
			
			
			button1.setOnClickListener(new Button.OnClickListener()
			{
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					url = AppContent.url.newsUrl[0];
					System.out.println("BUTTON1");
					NewsAdapter newsAdapter = new NewsAdapter(allList[0], listView.getContext());
					listView.setCacheColorHint(0);

					listView.setAdapter(newsAdapter);
				}
			});
			button2.setOnClickListener(new Button.OnClickListener()
			{
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					url = AppContent.url.newsUrl[1];
					NewsAdapter newsAdapter = new NewsAdapter(allList[1], listView.getContext());
					listView.setCacheColorHint(0);

					listView.setAdapter(newsAdapter);

					System.out.println("BUTTON2");
				}
			});
			button3.setOnClickListener(new Button.OnClickListener()
			{
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					NewsAdapter newsAdapter = new NewsAdapter(allList[2], listView.getContext());
					listView.setCacheColorHint(0);

					listView.setAdapter(newsAdapter);

					url = AppContent.url.newsUrl[2];
					System.out.println("BUTTON3");
				}
			});
			button4.setOnClickListener(new Button.OnClickListener()
			{
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					NewsAdapter newsAdapter = new NewsAdapter(allList[3], listView.getContext());
					listView.setCacheColorHint(0);

					listView.setAdapter(newsAdapter);

					url = AppContent.url.newsUrl[3];
					System.out.println("BUTTON4");
				}
			});
			button5.setOnClickListener(new Button.OnClickListener()
			{
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					NewsAdapter newsAdapter = new NewsAdapter(allList[4], listView.getContext());
					listView.setCacheColorHint(0);

					listView.setAdapter(newsAdapter);

					url = AppContent.url.newsUrl[4];
					System.out.println("BUTTON5");
				}
			});
			
			
			Intent updateIntent = new Intent();
			updateIntent.setClass(this, readNewsService.class);
			this.startService(updateIntent);
			
		
			
			listView.setOnItemClickListener(this);
			
		}
		
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
	protected void onDestroy() {
		// TODO Auto-generated method stub
		this.finish();
		this.unregisterReceiver(receiver);
		
		super.onDestroy();
	}
	
	class newsReceiver extends BroadcastReceiver{
		
		@Override
		public void onReceive(Context context, Intent intent) {
			String trans = intent.getStringExtra("status");
			System.out.println("OnReceive news Receiver " + trans);
			
			for (int i = 0; i < AppContent.url.newsUrl.length; ++ i)
			{
				if (AppContent.url.newsUrl[i].equals(trans))
				{
					System.out.println("i " + i);
					asyncTask = new DrawAsyncTask(listView, dialog, itemList, intent, i);
					asyncTask.execute();
				}
			}
		}
	}
	public void onItemClick(AdapterView parent, View v, int postion,
			long id) {
		System.out.println("in OnItemClick");
		
		Intent intent = new Intent(this, contentActivity.class);
		Bundle trans = new Bundle();
		itemList = asyncTask.getItemList();
		
		
		if (itemList.getItem(postion).getTitle() != null)
		{
			trans.putString("title", itemList.getItem(postion).getTitle());
			trans.putString("link", itemList.getItem(postion).getLink());
			trans.putString("pubDate", itemList.getItem(postion).getPubDate());
			trans.putString("description", itemList.getItem(postion).getDescription());
			
			intent.putExtra("android.intent.extra.INTENT", trans);
			
			startActivityForResult(intent,0);
		}
	}
	class DrawAsyncTask extends AsyncTask<Void, Void, Void>{
		
		private ListView listView = null; 
		private String url;
		private ProgressDialog dialog = null;
		private RssGroup itemList = null;
		private Intent intent = null;
		private int iTemp = 6;
		
		public DrawAsyncTask(ListView listView, ProgressDialog dialog, RssGroup itemList, Intent intent, int i){
			this.listView = listView;
			this.dialog = dialog;
			this.itemList = itemList;
			this.intent = intent;
			this.iTemp = i;
		}
		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			itemList =  (RssGroup) intent.getSerializableExtra("itemList");
			System.out.println("iTemp " + iTemp);
			allList[iTemp] = itemList;
			return null;
		}
		
		@Override
		protected void onCancelled() {
			// TODO Auto-generated method stub
			super.onCancelled();
		}
		
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			if (iTemp == 0)
			{
				NewsAdapter newsAdapter = new NewsAdapter(allList[0], listView.getContext());
				listView.setAdapter(newsAdapter);
			}
			dialog.hide();
			
			
		}
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			dialog.show();
		}
		
		@Override
		protected void onProgressUpdate(Void... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
		}
		public RssGroup getItemList() {
			return itemList;
		}
		
		
	}

	public boolean isNetworkAvailable() {
		Context context = getApplicationContext();
		ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity == null) {
			//boitealerte(this.getString(R.string.alert),"getSystemService rend null");
		} else {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}
		}
		return false;
	}



}
