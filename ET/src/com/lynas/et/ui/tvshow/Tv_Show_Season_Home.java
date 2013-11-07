package com.lynas.et.ui.tvshow;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.lynas.et.R;
import com.lynas.et.domain.DataProvider_TVShow;
import com.lynas.et.domain.DataStructure_TVShow;

public class Tv_Show_Season_Home extends Activity{
	
	ListView showSeasonList;
	List<Tv_show_season> items = new ArrayList<Tv_show_season>();
	private String showName = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tv_show_season_home);
		showName = getIntent().getExtras().getString("tv_show_name");
		
		Log.d("Selected Tv Show Name", ""+showName);
		
		getFromDb(""+showName);
		
	
		showSeasonList = (ListView) findViewById(R.id.lv_tv_show_season_list);
		Tv_Show_Season_List_customAdapter adapter = new Tv_Show_Season_List_customAdapter(Tv_Show_Season_Home.this, items, showName);
		showSeasonList.setAdapter(adapter);

		
		Log.d("Seasons", ""+items);
		
	}
	
	
private void getFromDb(String showName) {
	
		String tempName="";
		ContentResolver resolver = getContentResolver();
		String[] projection = { 
				
				" DISTINCT "+DataStructure_TVShow.SHOWSEASON
				 
		};
		Cursor cursor = resolver.query(
				DataProvider_TVShow.tvShowTableUri,
				projection, 
				DataStructure_TVShow.SHOWNAME + " = '"+showName+"'", 
				null, 
				null);  
		
		if (cursor.moveToFirst()) {
			do {
				tempName = cursor.getString(cursor.getColumnIndex(DataStructure_TVShow.SHOWSEASON));  
				
				Log.d("Seasons", ""+tempName);
				Tv_show_season tmp=new Tv_show_season();
				tmp.tv_show_season=tempName;
				tmp.tv_show_season_resource_id = R.drawable.ic_launcher;
				items.add(tmp);
			} while (cursor.moveToNext());
		}
		cursor.close();
	}


}
class Tv_show_season{
	public String tv_show_season;
	public int tv_show_season_resource_id;
}