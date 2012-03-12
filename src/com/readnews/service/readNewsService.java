package com.readnews.service;

import java.io.StringReader;

import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import com.readnews.activity.AppContent;
import com.readnews.utils.HttpDownloader;
import com.readnews.xml.RssGroup;
import com.readnews.xml.RssHandler;
import com.readnews.xml.RssItem;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.text.format.Time;


public class readNewsService extends Service{
	private RssGroup itemList = null;
	private Thread thread = null;
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		System.out.println("on Service ");

	
		updateInformation update = new updateInformation();
		thread = new Thread(update);
		thread.run();


		super.onStart(intent, startId);
	}
	class updateInformation implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			String[] sendItem = new String[AppContent.button.length];
			for (int i = 0; i < AppContent.url.newsUrl.length; ++ i)
			{
				String url = AppContent.url.newsUrl[i];
				System.out.println("runnable + " + AppContent.url.newsUrl[i]);
		        itemList = getNewsFromInternet(url);
		        if (itemList == null)
		        System.out.println("Just Test ");
		        Intent updateItemIntent = new Intent();
		        updateItemIntent.setAction(AppContent.myAction.updateNewsAction);
		        updateItemIntent.putExtra("status", url);
		        
			    Bundle mBundle = new Bundle();

			    mBundle.putSerializable("itemList", itemList);
		        updateItemIntent.putExtras(mBundle);
	        
		        sendBroadcast(updateItemIntent);

		        if (i < 5)
		        {

		        	sendItem[i] = itemList.getItem(0).getTitle();
		        	System.out.println("sendItem + " + sendItem[i]);
		        	
		        }
			}
			
			
			
	       
	    	Intent intent = new Intent();
	    	intent.setAction(AppContent.myAction.updateUrlAction);
	    	intent.putExtra("news", sendItem);
	    	readNewsService.this.sendBroadcast(intent);
	        
	        //设置更新间隔
	        Time time = new Time();
	        long nowTime = System.currentTimeMillis();
	        time.set(nowTime + AppContent.time.updateTime * 60 * 1000);
	        long updateTime = time.toMillis(true);
	        System.out.println("refresh Time + " + updateTime );
	        
	        //定时更新
	        Intent timeIntent = new Intent(AppContent.myAction.updateByTimeAction);
	        timeIntent.setClass(readNewsService.this, readNewsService.class);
	        PendingIntent refreshIntent = PendingIntent.getService(readNewsService.this, 0, timeIntent, 0);
	        
	        AlarmManager alarm = (AlarmManager)readNewsService.this.getSystemService(readNewsService.this.ALARM_SERVICE);
	        alarm.set(AlarmManager.RTC_WAKEUP, updateTime, refreshIntent);
	        readNewsService.this.stopSelf();
	        
		}
		private RssGroup getNewsFromInternet(String url)
	    {
			System.out.println("url " + url);
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
		
	}
	
	
}