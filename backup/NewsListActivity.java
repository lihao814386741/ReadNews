package com.readnews.activity;

import java.io.StringReader;

import javax.xml.parsers.SAXParserFactory;


import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import com.readnews.R;
import com.readnews.service.readNewsService;
import com.readnews.utils.HttpDownloader;
import com.readnews.xml.NewsAdapter;
import com.readnews.xml.RssGroup;
import com.readnews.xml.RssHandler;
import com.readnews.xml.RssItem;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TabHost;
import android.widget.AdapterView.OnItemClickListener;

public class NewsListActivity extends Activity implements OnItemClickListener {
	private Button readButton = null;
	private RssGroup itemList = null;
	private ListView listView = null; 
	private String url;
	private  newsReceiver receiver = null;

	public NewsListActivity()
	{
		//this.url = aUrl;
		//this.url = url;
	}
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
        

        Intent urlIntent = this.getIntent();
        url = urlIntent.getStringExtra("url");
        
//		Intent updateIntent = new Intent();
//		updateIntent.putExtra("url", url);
//		updateIntent.setClass(this, readNewsService.class);
//		this.startService(updateIntent);
		
        receiver = new newsReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction(AppContent.myAction.updateNewsAction);
		this.registerReceiver(receiver, filter);

  //      updateDisplay();
    }
    
    private void updateDisplay() {
    	
//        itemList = getNewsFromInternet(url);

    	Intent intent = new Intent();
    	intent.setAction(AppContent.myAction.updateUrlAction);
    	intent.putExtra("news", itemList.getItem(0).getTitle());
    	this.sendBroadcast(intent);
    	

    	
    	listView = (ListView)findViewById(R.id.itemList);
    	
    	
//    	ArrayAdapter<RssItem> adapter = new ArrayAdapter<RssItem>(this, android.R.layout.simple_list_item_1, itemList.getItemList());
    	NewsAdapter newsAdapter = new NewsAdapter(itemList, this);
    	
    	listView.setAdapter(newsAdapter);
    	listView.setOnItemClickListener(this);
    	listView.setSelection(0);
	}

	private RssGroup getNewsFromInternet(String url)
    {
		HttpDownloader hd = new HttpDownloader();
		String resultStr = hd.download(url);
		
		try{
			
			SAXParserFactory factory = SAXParserFactory.newInstance();
			XMLReader reader = factory.newSAXParser().getXMLReader();
			RssHandler rssHandler = new RssHandler();
			
			reader.setContentHandler(rssHandler);
			reader.parse(new InputSource(new StringReader(resultStr)));
			
			return rssHandler.getItemList();
			
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
    }
	
	//点击Item之后会进入到contentActivity中
	public void onItemClick(AdapterView parent, View v, int postion,
			long id) {
		
		Intent intent = new Intent(this, contentActivity.class);
		Bundle trans = new Bundle();
		
		trans.putString("title", itemList.getItem(postion).getTitle());
		trans.putString("link", itemList.getItem(postion).getLink());
		trans.putString("pubDate", itemList.getItem(postion).getPubDate());
		trans.putString("description", itemList.getItem(postion).getDescription());
		
		intent.putExtra("android.intent.extra.INTENT", trans);
		
		startActivityForResult(intent,0);
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

	//接收从service来的消息
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		NewsListActivity.this.unregisterReceiver(receiver);
		super.onDestroy();
	}
	class newsReceiver extends BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {
			String trans = intent.getStringExtra("status");
			
			if (url.equals(trans))
			{
				itemList =  (RssGroup) intent.getSerializableExtra("itemList");
				updateDisplay();
			}
			
		}
	}

	
}