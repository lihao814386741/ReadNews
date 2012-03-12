package com.readnews.activity.async;

import com.readnews.xml.NewsAdapter;
import com.readnews.xml.RssGroup;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.ListView;

public class DrawAsyncTask extends AsyncTask<Void, Void, Void>{

	private ListView listView = null; 
	private String url;
	private ProgressDialog dialog = null;
	private RssGroup itemList = null;
	private Intent intent = null;

	public DrawAsyncTask(ListView listView, ProgressDialog dialog, RssGroup itemList, Intent intent){
		this.listView = listView;
		this.dialog = dialog;
		this.itemList = itemList;
		this.intent = intent;
	}
	@Override
	protected Void doInBackground(Void... params) {
		// TODO Auto-generated method stub
		itemList =  (RssGroup) intent.getSerializableExtra("itemList");

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
    	NewsAdapter newsAdapter = new NewsAdapter(itemList, listView.getContext());
    	listView.setAdapter(newsAdapter);
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
