package com.lynas.et.ui.tvshow;

import java.util.List;
import com.lynas.et.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lynas.et.R;

public class Tv_Show_Season_List_customAdapter extends BaseAdapter {

	LayoutInflater inflater;
	List<Tv_show_season> items;
	private Context acontext;
	String showName;
	

	public Tv_Show_Season_List_customAdapter(Activity context,
			List<Tv_show_season> items, String showName) {
		acontext = context;
		this.items = items;
		this.showName = showName;
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
	public View getView(int position, View convertView, ViewGroup parent) {
		Tv_show_season item = items.get(position);
		View vi = convertView;

		if (convertView == null) {
			vi = inflater.inflate(R.layout.tv_show_each_show_name2, null);
		}
		final TextView txtV = (TextView) vi.findViewById(R.id.tv_sh_each_name2);
		txtV.setText(item.tv_show_season);
		txtV.setBackgroundResource(R.drawable.season_background);

		txtV.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent tvShowSeason = new Intent(acontext,	Tv_Show_Episode_Home.class);
				tvShowSeason.putExtra("tv_show_name", showName);
				tvShowSeason.putExtra("tv_show_season", txtV.getText().toString());
				acontext.startActivity(tvShowSeason);
			}
		});

		return vi;
	}

}
