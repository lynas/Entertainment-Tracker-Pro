package com.lynas.et.ui.tvshow;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.lynas.et.R;
import com.lynas.et.domain.DataProvider_TVShow;
import com.lynas.et.domain.DataStructure_TVShow;

public class Tv_Show_Episode_Home extends Activity implements
		OnItemClickListener {
	ListView showEpisodeList;
	List<Tv_show_episode> items = new ArrayList<Tv_show_episode>();
	private String showName = "";
	private String seasonNo = "";

	// using "Tv_show_season" because the structure is same
	//

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tv_show_season_home);
		showName = getIntent().getExtras().getString("tv_show_name");
		seasonNo = getIntent().getExtras().getString("tv_show_season");

		getFromDb(showName, seasonNo);

		showEpisodeList = (ListView) findViewById(R.id.lv_tv_show_season_list);
		Tv_Show_Episode_List_customAdapter adapter = new Tv_Show_Episode_List_customAdapter(
				Tv_Show_Episode_Home.this, items, showName, seasonNo);
		showEpisodeList.setAdapter(adapter);

		showEpisodeList.setOnItemClickListener(this);

		Log.d("Seasons", "" + showName + " ---- " + seasonNo);

	}

	@Override
	public void onBackPressed() {
		Intent TvShowAction = new Intent(Tv_Show_Episode_Home.this,
				TV_Home.class);
		TvShowAction.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
		startActivity(TvShowAction);
	}

	private void getFromDb(String showName, String seasonNo) {

		String tempName = "";
		ContentResolver resolver = getContentResolver();
		String[] projection = { DataStructure_TVShow.EPISODENAME,
				DataStructure_TVShow.EPISODESTATUS };
		Cursor cursor = resolver.query(DataProvider_TVShow.tvShowTableUri,
				projection, DataStructure_TVShow.SHOWNAME + " = '" + showName
						+ "' AND " + DataStructure_TVShow.SHOWSEASON + " = '"
						+ seasonNo + "'", null, null);

		if (cursor.moveToFirst()) {
			do {
				tempName = cursor.getString(cursor
						.getColumnIndex(DataStructure_TVShow.EPISODENAME));

				Log.d("Seasons", "" + tempName);
				Tv_show_episode tmp = new Tv_show_episode();
				tmp.tv_show_episode = tempName;
				tmp.tv_show_episode_status = cursor.getString(cursor
						.getColumnIndex(DataStructure_TVShow.EPISODESTATUS));

				// by default it will show unwatched; if however status is true
				// then add the resource for watched
				tmp.tv_show_episode_resource_id = R.drawable.unwatched;
				if (cursor
						.getString(
								cursor.getColumnIndex(DataStructure_TVShow.EPISODESTATUS))
						.equals("true")) {
					tmp.tv_show_episode_resource_id = R.drawable.watched;
				}
				items.add(tmp);
			} while (cursor.moveToNext());
		}
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

		TextView tv = (TextView) arg1.findViewById(R.id.tv_sh_each_name2);
		/*
		 * Log.d("sss", ""+tv.getText()); tv.setText("something");
		 * tv.setBackgroundResource(R.drawable.dexter);
		 */

		Tv_show_episode ttt;
		ttt = (Tv_show_episode) arg0.getItemAtPosition(arg2);
 
		ContentResolver resolver = getContentResolver();

		if (ttt.tv_show_episode_status.equals("false")) {
			ttt.tv_show_episode_status = "true";
			ContentValues values = new ContentValues();
			values.put(DataStructure_TVShow.EPISODESTATUS, "true");

			resolver.update(DataProvider_TVShow.tvShowTableUri, values,
					DataStructure_TVShow.SHOWNAME + " = '" + showName
							+ "' AND " + DataStructure_TVShow.SHOWSEASON
							+ " = '" + seasonNo + "' AND "
							+ DataStructure_TVShow.EPISODENAME + " = '"
							+ tv.getText().toString() + "'", null);
			tv.setBackgroundResource(R.drawable.watched);
			
			Log.d("in :"," if");
		} else {
			ttt.tv_show_episode_status = "false";
			Log.d("in :"," else");
			ContentValues values = new ContentValues();
			values.put(DataStructure_TVShow.EPISODESTATUS, "false");

			resolver.update(DataProvider_TVShow.tvShowTableUri, values,
					DataStructure_TVShow.SHOWNAME + " = '" + showName
							+ "' AND " + DataStructure_TVShow.SHOWSEASON
							+ " = '" + seasonNo + "' AND "
							+ DataStructure_TVShow.EPISODENAME + " = '"
							+ tv.getText().toString() + "'", null);
			tv.setBackgroundResource(R.drawable.unwatched);
		}
		resolver.notifyChange(DataProvider_TVShow.tvShowTableUri, null);

		// notifyDataSetChanged();

	}

}

class Tv_show_episode {
	public String tv_show_episode;
	public String tv_show_episode_status;
	public int tv_show_episode_resource_id;
}
