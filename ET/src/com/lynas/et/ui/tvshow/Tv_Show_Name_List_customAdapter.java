package com.lynas.et.ui.tvshow;

import java.util.List;

import com.lynas.et.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class Tv_Show_Name_List_customAdapter extends BaseAdapter {
	
	LayoutInflater inflater;
	List<TV_Show_Names> items;
	private Context acontext;

	public Tv_Show_Name_List_customAdapter(Activity context,
			List<TV_Show_Names> items) {
		acontext = context;
		this.items = items;
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
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		TV_Show_Names item = items.get(position);
		View vi = convertView;

		if (convertView == null) {
			vi = inflater.inflate(R.layout.tv_show_each_show_name2, null);
		}
		final TextView txtV = (TextView) vi.findViewById(R.id.tv_sh_each_name2); 
		txtV.setText(item.showName);
		txtV.setBackgroundResource(item.showImage); 
		
		txtV.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.d("Show Name", txtV.getText().toString());
				
				Intent tvShowSeason = new Intent(acontext, Tv_Show_Season_Home.class);
				tvShowSeason.putExtra("tv_show_name", txtV.getText().toString());
				acontext.startActivity(tvShowSeason);
				
			}
		});
		

		return vi;
	}

}
