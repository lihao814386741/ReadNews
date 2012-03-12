package com.readnews.xml;

import java.io.Serializable;
import java.util.List;
import java.util.Vector;

public class RssGroup implements Serializable{
	/**
	 * 
	 */
    private static final long serialVersionUID = 122l;  

	private int itemCount = 0;
	private List<RssItem> itemList;
	
	RssGroup(){
		itemList = new Vector(0);
	}
	
	public void addItem(RssItem item){
		itemList.add(item);
		itemCount++;
	}
	public RssItem getItem(int position){
		return itemList.get(position);
	}

	public List<RssItem> getItemList() {
		return itemList;
	}

	public int getItemCount() {
		return itemCount;
	}

	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}

	
	
	
	
}