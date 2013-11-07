package com.lynas.et.ui.tvshow;

import java.util.List;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lynas.et.R;
import com.lynas.et.domain.DataProvider_TVShow;
import com.lynas.et.domain.DataStructure_TVShow;

public class Tv_Show_Episode_List_customAdapter extends BaseAdapter {
	
	LayoutInflater inflater;
	List<Tv_show_episode> items;
	private Context acontext;
	String showName;
	String seasonNo;
	String seasonEpisodeStat;
	

	public Tv_Show_Episode_List_customAdapter(Activity context, List<Tv_show_episode> items, String showName, String seasonNo) {
		acontext = context;
		this.items = items;
		this.showName = showName;
		this.seasonNo = seasonNo;
		this.inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	}
	
	

	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		Tv_show_episode item = items.get(position);
		View vi = convertView;

		if (convertView == null) {
			vi = inflater.inflate(R.layout.tv_show_each_show_name2, null);
		}
		final TextView txtV = (TextView) vi.findViewById(R.id.tv_sh_each_name2);
		txtV.setTextColor(Color.WHITE);
		txtV.setTextSize(40);
		txtV.setText(item.tv_show_episode);
		txtV.setBackgroundResource(item.tv_show_episode_resource_id);

		/*txtV.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				Log.d("Passing info",""+showName+"--"+seasonNo+"--"+txtV.getText().toString()+"--"+items.get(position).tv_show_episode_status);
				ContentResolver resolver = acontext.getContentResolver();
				
				if(items.get(position).tv_show_episode_status.equals("false")){
				
				ContentValues values = new ContentValues();
				values.put(DataStructure_TVShow.EPISODESTATUS, "true");

				resolver.update(
						DataProvider_TVShow.tvShowTableUri, 
						values, 
						DataStructure_TVShow.SHOWNAME + " = '"+showName+"' AND " + DataStructure_TVShow.SHOWSEASON +" = '"+seasonNo+"' AND "+DataStructure_TVShow.EPISODENAME+" = '"+txtV.getText().toString()+"'", 
						null
				);
				
				}else{
					ContentValues values = new ContentValues();
					values.put(DataStructure_TVShow.EPISODESTATUS, "false");

					resolver.update(
							DataProvider_TVShow.tvShowTableUri, 
							values, 
							DataStructure_TVShow.SHOWNAME + " = '"+showName+"' AND " + DataStructure_TVShow.SHOWSEASON +" = '"+seasonNo+"' AND "+DataStructure_TVShow.EPISODENAME+" = '"+txtV.getText().toString()+"'", 
							null
					);					
				}
				Intent intent = new Intent(acontext,Tv_Show_Episode_Home.class);
				intent.putExtra("tv_show_name", showName);
				intent.putExtra("tv_show_season", seasonNo);
				intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
				acontext.startActivity(intent);
				
				
			}
		});*/

		return vi;
	}

}


























