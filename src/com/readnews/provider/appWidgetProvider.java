package com.readnews.provider;

import com.readnews.R;
import com.readnews.activity.AppContent;
import com.readnews.activity.mainActivity;
import com.readnews.service.readNewsService;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.Button;
import android.widget.RemoteViews;

public class appWidgetProvider extends AppWidgetProvider{

	private String showWord[] =  {"正在从服务器获取新闻...",
								  "正在从服务器获取新闻...",
								  "正在从服务器获取新闻...",
								  "正在从服务器获取新闻...",
								  "正在从服务器获取新闻..."};
	private String updateButtonAction = "com.read.news.action.update.news";
	private RemoteViews remoteViews = null;
	private String textShow = "点击此处获取新闻...";

	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		// TODO Auto-generated method stub
		super.onDeleted(context, appWidgetIds);
	}

	@Override
	public void onDisabled(Context context) {
		// TODO Auto-generated method stub
		super.onDisabled(context);
	}

	@Override
	public void onEnabled(Context context) {
		// TODO Auto-generated method stub
	
		super.onEnabled(context);
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		
		String action = intent.getAction();

		if (updateButtonAction.equals(action))
		{
			String[] showWord = new String[AppContent.button.length];
			showWord = intent.getStringArrayExtra("news");
			System.out.println("on Receive" + showWord[0]);
			
			textShow = "";
			int i;
			for ( i = 0; i < showWord.length - 1; ++ i)
			{
				textShow += "-";
					textShow += showWord[i];
				textShow += "\n\n";
			}
			textShow += "-";
			textShow += showWord[i];
			System.out.println("textShow " + textShow);
			RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.appwidget);
			
				remoteViews.setTextViewText(R.id.appButton, textShow);
			AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
		
			ComponentName componentName = new ComponentName(context, appWidgetProvider.class);
			appWidgetManager.updateAppWidget(componentName, remoteViews);
		}
		else
		{
			super.onReceive(context, intent);
		}
	}

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		// TODO Auto-generated method stub

		for (int i = 0; i < appWidgetIds.length; ++i)
		{
				System.out.println(appWidgetIds[i]);
	
				Intent intent = new Intent(context, mainActivity.class);

				PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
				remoteViews = new RemoteViews(context.getPackageName(), R.layout.appwidget);
				remoteViews.setOnClickPendingIntent(R.id.appButton, pendingIntent);
				remoteViews.setTextViewText(R.id.appButton, textShow);

				
				appWidgetManager.updateAppWidget(appWidgetIds[i], remoteViews);
		}
		
		super.onUpdate(context, appWidgetManager, appWidgetIds);
	}
	
	
	
}