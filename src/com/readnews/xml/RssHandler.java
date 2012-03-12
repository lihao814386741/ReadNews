package com.readnews.xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class RssHandler extends DefaultHandler {
	private RssGroup itemList;
	private RssItem item;
	private String tagName;

	public RssGroup getItemList() {
		return itemList;
	}

	public void startDocument() throws SAXException {
		System.out.println("````````begin````````");
		
		itemList = new RssGroup();
		item = new RssItem();
	}

	public void endDocument() throws SAXException {
		System.out.println("````````end````````");
	}

	public void startElement(String namespaceURI, String localName,
			String qName, Attributes attr) throws SAXException {
		tagName = localName;
		
		if (localName.equals("item")) {
			item = new RssItem();
		}
	}

	public void endElement(String namespaceURI, String localName, String qName)
			throws SAXException {
		tagName = "";
		
		if (localName.equals("item")) {
			itemList.addItem(item);
		}
	}
	
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		
		if (length <= 5)
				return ;
		
		if (tagName.equals("title"))
			item.setTitle(new String(ch, start, length));
		else if (tagName.equals("link"))
			item.setLink(new String(ch, start, length));
		else if (tagName.equals("category"))
			item.setCategory(new String(ch, start, length));
		else if (tagName.equals("pubDate"))
			item.setPubDate(new String(ch, start, length));
		else if (tagName.equals("description"))
			item.setDescription(new String(ch, start, length));
	}


}
