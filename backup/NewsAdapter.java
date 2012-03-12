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
	
	public NewsAdapter (RssGroup newsList, Context context)
	{
		this.newsList = newsList;
		this.context = context;
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
		View rowView = rowViews.get(position);
		
		if (rowView == null)
		{
			LayoutInflater layoutInflater = LayoutInflater.from(context);
			rowView = layoutInflater.inflate(R.layout.item, null);
			
			TextView titleTextView = (TextView)rowView.findViewById(R.id.titleTextView);
			TextView timeTextView = (TextView)rowView.findViewById(R.id.timeTextView);
			RssItem rssItem = (RssItem)this.getItem(position);
			titleTextView.setText(rssItem.getTitle());
			timeTextView.setText(rssItem.getPubDate());
			rowViews.put(position, rowView);
			
		}
		
		return rowView;
	}

}
