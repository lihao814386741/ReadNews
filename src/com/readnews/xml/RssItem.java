package com.readnews.xml;

import java.io.Serializable;

public class RssItem implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 123L;
	private String title = null;
	private String description = null;
	private String pubDate = null;
	private String category = null;
	private String link = null;
	
	RssItem()
	{
		
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPubDate() {
		return pubDate;
	}

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	public String toString()
	{
		if (title.length() > 42)
		{
			return title.substring(0, 42) + "...";
		}
		
		return title;
	}
	
	
	
}