package com.readnews.xml;

import java.util.HashMap;
import java.util.Map;

import com.readnews.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class NewsAdapter extends BaseAdapter{

	private RssGroup newsList = null;
	private Context context = null;
	private Map<Integer, View> rowViews = new HashMap<Integer, View>();
	private LayoutInflater layoutInflater = null;

	
	public NewsAdapter (RssGroup newsList, Context context)
	{
		this.newsList = newsList;
		this.context = context;
		layoutInflater = LayoutInflater.from(context);
	}
	
	
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return newsList.getItemCount();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return newsList.getItem(arg0);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
			ViewHolder holder;
		if (convertView == null)
		{
			convertView = layoutInflater.inflate(R.layout.item, null);
			holder = new ViewHolder();
			holder.titleTextView = (TextView)convertView.findViewById(R.id.titleTextView);
			holder.timeTextView = (TextView)convertView.findViewById(R.id.timeTextView);	
			holder.desTextView = (TextView)convertView.findViewById(R.id.desTextView);
			convertView.setTag(holder);
			
		}
		else
		{
			holder = (ViewHolder)convertView.getTag();
		}
		
		RssItem rssItem = (RssItem)this.getItem(position);
		holder.titleTextView.setText(rssItem.getTitle());
		holder.timeTextView.setText(rssItem.getPubDate());
		holder.desTextView.setText(rssItem.getDescription());

		
		return convertView;
	}
	static class ViewHolder{
		TextView titleTextView;
		TextView timeTextView;
		TextView desTextView;
		
	}
	public void setNewsList(RssGroup newsList) {
		this.newsList = newsList;
	}

}
