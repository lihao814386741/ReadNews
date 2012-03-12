package com.readnews.activity;

import com.readnews.R;

public interface AppContent {
	public class myAction{
			public static final  String updateUrlAction = "com.read.news.action.update.news";
			public static final  String updateNewsAction = "com.read.news.action.update.item";
			public static final  String updateByTimeAction = "com.read.news.action.update.time" ;
			public static final	 String updateCurrentAction = "com.read.news.action.update.current.page";
	}
	public class url{
			public static final String[] newsUrl = {
				"http://rss.sina.com.cn/news/china/focus15.xml",
				"http://rss.sina.com.cn/news/world/focus15.xml", 
				"http://rss.sina.com.cn/news/allnews/ent.xml",
				"http://rss.sina.com.cn/roll/sports/hot_roll.xml",
				"http://rss.sina.com.cn/tech/rollnews.xml",
//				"http://rss.sina.com.cn/roll/finance/hot_roll.xml",
//				"http://rss.sina.com.cn/roll/mil/hot_roll.xml",
//				"http://rss.sina.com.cn/news/allnews/eladies.xml",
//				"http://rss.sina.com.cn/news/allnews/auto.xml",
//				"http://rss.sina.com.cn/book/info.xml",
//				"http://rss.sina.com.cn/roll/edu/hot_roll.xml",
//				"http://rss.sina.com.cn/jiaju/news.xml",
//				"http://rss.sina.com.cn/news/allnews/games.xml",
//				"http://rss.sina.com.cn/games/dzjj.xml"
				};
			
			public static final int nation = 0;
			public static final int internation = 1;
			public static final int enter = 2;
			public static final int sport = 3;
			public static final int tech = 4;
			public static final int finance = 5;
			public static final int mil = 6;
			public static final int lady = 7;
			public static final int car = 8;
			public static final int book = 9;
			public static final int edu = 10;
			public static final int jiaju = 11;
			public static final int game = 12;
			public static final int jingji = 13;
			
			public static final String[] newsTab = {
				"国内新闻",
				"国际新闻",
				"娱乐新闻",
				"体育新闻",
				"科技新闻",
//				"财经新闻",
//				"军事新闻",
//				"女性新闻",
//				"汽车新闻",
//				"读书要闻",
//				"文化教育",
//				"家居新闻",
//				"游戏新闻",
//				"电子竞技"
			};
	}
	public class time{
		public static final int updateTime = 30;
	}
	public class button{
		public static final int length = 5;

	}
}
