package com.lynas.et.ui.anime;

import java.util.ArrayList;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.lynas.et.R;
import com.lynas.et.domain.DataProvider_Anime;
import com.lynas.et.domain.DataStructure_Anime;

public class Anime_Episode_Home extends FragmentActivity{
	ArrayList<String> animeList;
	ListView animeListView;
	ArrayAdapter<String> animeListAdapter;
	Button nextButton;
	String prevID="0";
	String lastID="0";
	String gottenAnimeName="";
	Button prevButton;
	boolean prevStat = true;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.anime_episode_home);
		nextButton = (Button) findViewById(R.id.bt_next);
		prevButton = (Button) findViewById(R.id.bt_previous);
		
		animeList = new ArrayList<String>();
		gottenAnimeName =getIntent().getExtras().getString("selected_anime_name"); 
		
		getFromDb(gottenAnimeName);
		
		
		
		animeListView = (ListView) findViewById(R.id.lv_anime_episode_list);
		
		animeListAdapter = new ArrayAdapter<String>(this,	R.layout.tv_show_each_show_name, animeList);
		animeListView.setAdapter(animeListAdapter);
		
		
		prevButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) { 
				
				if(prevID.equals("0")){
					Toast.makeText(Anime_Episode_Home.this, "This is First", Toast.LENGTH_SHORT).show();
				}else{
					prevStat = true;
					animeList.removeAll(animeList);
					getFromDb2(gottenAnimeName);
					animeListAdapter.notifyDataSetChanged();
				}
				
				/*animeList.removeAll(animeList);
				getFromDb(gottenAnimeName);
				animeListAdapter.notifyDataSetChanged();*/
				
			}
		});
		
		
		nextButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				prevStat = true;
				animeList.removeAll(animeList);
				getFromDb(gottenAnimeName);
				animeListAdapter.notifyDataSetChanged();
				
				
			}
		});
		
	}
	
	
	
	private void getFromDb(String selectedAnimeName) {

		String tempName = "";
		ContentResolver resolver = getContentResolver();

		String[] projection = {
				DataStructure_Anime.ANIMEID,
				DataStructure_Anime.ANIMENAME,
				DataStructure_Anime.ANIMEAIRDAY,
				DataStructure_Anime.ANIMEEPISODE,
				DataStructure_Anime.ANIMEEPISODESTATUS
		};
		Cursor cursor = resolver.query(
				DataProvider_Anime.animeTableUri,
				projection, 
				DataStructure_Anime.ANIMENAME+ "= '"+selectedAnimeName+"' AND "+DataStructure_Anime.ANIMEID + " > " + lastID,  
				null,
				DataStructure_Anime.ANIMEID +" ASC LIMIT 0 , 10");
		if (cursor.moveToFirst()) {
			do {
				tempName = cursor.getString(cursor.getColumnIndex(DataStructure_Anime.ANIMEEPISODE));
				lastID = cursor.getString(cursor.getColumnIndex(DataStructure_Anime.ANIMEID));
				animeList.add(tempName);
				
				
				if(prevStat){
					prevID = lastID;
					prevStat = false;
				}
			} while (cursor.moveToNext());
		}
		
		Log.d("Names : ", "" + lastID);

		cursor.close();
	}
	
	
	private void getFromDb2(String selectedAnimeName) {

		String tempName = "";
		ContentResolver resolver = getContentResolver();

		String[] projection = {
				DataStructure_Anime.ANIMEID,
				DataStructure_Anime.ANIMENAME,
				DataStructure_Anime.ANIMEAIRDAY,
				DataStructure_Anime.ANIMEEPISODE,
				DataStructure_Anime.ANIMEEPISODESTATUS
		};
		Cursor cursor = resolver.query(
				DataProvider_Anime.animeTableUri,
				projection, 
				DataStructure_Anime.ANIMENAME+ "= '"+selectedAnimeName+"' AND "+DataStructure_Anime.ANIMEID + " < " + prevID,  
				null,
				DataStructure_Anime.ANIMEID +" LIMIT "+(Integer.parseInt(prevID) - 11)+", 10");
		if (cursor.moveToFirst()) {
			do {
				tempName = cursor.getString(cursor.getColumnIndex(DataStructure_Anime.ANIMEEPISODE));
				
				animeList.add(tempName);
				
				
				if(prevStat){
					prevID = cursor.getString(cursor.getColumnIndex(DataStructure_Anime.ANIMEID));
				}
				lastID = cursor.getString(cursor.getColumnIndex(DataStructure_Anime.ANIMEID));
				
				
			} while (cursor.moveToNext());
		}
		
		Log.d("Names : ", "" + lastID);

		cursor.close();
	}

	
	/*
		SELECT * 
		FROM  `user` 
		WHERE id <14
		ORDER BY id
		LIMIT 10 , 3
	 */
	

}
