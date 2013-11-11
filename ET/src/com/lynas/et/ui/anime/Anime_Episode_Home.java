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

public class Anime_Episode_Home extends FragmentActivity {
	ArrayList<String> animeList;
	ListView animeListView;
	ArrayAdapter<String> animeListAdapter;
	ArrayList<String> tempAnimeList;
	Button nextButton;
	String prevID = "0";
	String lastID = "0";
	String gottenAnimeName = "";
	Button prevButton;
	boolean prevStat = true;
	boolean showPageNext = true;
	boolean showPagePrev = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.anime_episode_home);
		nextButton = (Button) findViewById(R.id.bt_next);
		prevButton = (Button) findViewById(R.id.bt_previous);

		animeList = new ArrayList<String>();
		gottenAnimeName = getIntent().getExtras().getString("selected_anime_name");
		getFromDb(gottenAnimeName);
		animeListView = (ListView) findViewById(R.id.lv_anime_episode_list);

		animeListAdapter = new ArrayAdapter<String>(this, R.layout.tv_show_each_show_name, animeList);
		animeListView.setAdapter(animeListAdapter);

		prevButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				prevStat = true;
				
				getFromDb2(gottenAnimeName);
				if (showPagePrev) {
					animeListAdapter.notifyDataSetChanged();
				}else{
					Toast.makeText(Anime_Episode_Home.this, "Nothing before this page", Toast.LENGTH_SHORT).show();
				}
			}
		});

		nextButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				prevStat = true;
				
				getFromDb(gottenAnimeName);
				if (showPageNext) {
					animeListAdapter.notifyDataSetChanged();
				}else{
					Toast.makeText(Anime_Episode_Home.this, "Nothing After this page", Toast.LENGTH_SHORT).show();
				}
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
				DataStructure_Anime.ANIMENAME + "= '"+ selectedAnimeName + "' AND "	+ DataStructure_Anime.ANIMEID + " > " + lastID, 
				null,
				DataStructure_Anime.ANIMEID + " ASC LIMIT 0 , 10");
		if(cursor!=null && cursor.getCount()>0){
			animeList.removeAll(animeList);
		}
		if (cursor.moveToFirst()) {
			do {
				tempName = cursor.getString(cursor.getColumnIndex(DataStructure_Anime.ANIMEEPISODE));
				lastID = cursor.getString(cursor.getColumnIndex(DataStructure_Anime.ANIMEID));
				animeList.add(tempName);

				if (prevStat) {
					prevID = lastID;
					prevStat = false;
				}
			} while (cursor.moveToNext());
		}
		Log.d("prevID : ", "" + prevID);
		Log.d("lastID : ", "" + lastID);
		Log.wtf("list : ", "" + "" + animeList.size());

		if (animeList.size() == 0) {
			showPageNext = false;
		} else {
			showPageNext = true;
		}

		cursor.close();
	}

	private void getFromDb2(String selectedAnimeName) {

		String tempName = "";
		String tempId = "";
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
				DataStructure_Anime.ANIMENAME + "= '"+ selectedAnimeName + "' AND "	+ DataStructure_Anime.ANIMEID + " < " + prevID, 
				null,
				DataStructure_Anime.ANIMEID + " DESC LIMIT 0, 10");
		if(cursor!=null && cursor.getCount()>0){
			animeList.removeAll(animeList);
		}
		if (cursor.moveToLast()) {
			do {
				tempName = cursor.getString(cursor.getColumnIndex(DataStructure_Anime.ANIMEEPISODE));
				tempId = cursor.getString(cursor.getColumnIndex(DataStructure_Anime.ANIMEID));
				animeList.add(tempName);

				if (prevStat) {
					prevID = tempId;
					prevStat = false;
				}
				lastID = tempId;

			} while (cursor.moveToPrevious()); 
		}
		Log.d("prevID : ", "" + prevID);
		Log.d("lastID : ", "" + lastID);
		Log.wtf("list : ", "" + "" + animeList.size());

		if (animeList.size() == 0) {
			showPagePrev = false;
		} else {
			showPagePrev = true;
		}

		cursor.close();
	}
}
