package com.lynas.et.ui.anime;

import java.util.ArrayList;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

import com.lynas.et.R;
import com.lynas.et.domain.DataProvider_Anime;
import com.lynas.et.domain.DataStructure_Anime;

public class Anime_Fragment_Anime_list extends Fragment implements OnItemClickListener{

	View animeview;
	ListView animeListView;
	ArrayAdapter<String> animeListAdapter;
	ArrayList<String> animeList;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		animeList = new ArrayList<String>();
		getFromDb();

		animeview = inflater.inflate(R.layout.tv_show_show_list, container,
				false);
		animeListView = (ListView) animeview.findViewById(R.id.lv_tv_show_list);
		animeListAdapter = new ArrayAdapter<String>(getActivity(),
				R.layout.tv_show_each_show_name, animeList);
		animeListView.setAdapter(animeListAdapter);
		animeListView.setOnItemClickListener(this);

		return animeview;
	}

	private void getFromDb() {

		String tempName = "";
		ContentResolver resolver = getActivity().getContentResolver();
		String[] projection = {

		" DISTINCT " + DataStructure_Anime.ANIMENAME,

		};
		Cursor cursor = resolver.query(
				DataProvider_Anime.animeTableUri,
				projection, 
				null, 
				null, 
				DataStructure_Anime.ANIMENAME+ " ASC ");

		if (cursor.moveToFirst()) {
			do {
				tempName = cursor.getString(cursor
						.getColumnIndex(DataStructure_Anime.ANIMENAME));
				Log.d("Names : ", "" + tempName);
				animeList.add(tempName);
			} while (cursor.moveToNext());
		}

		cursor.close();
	}

	@Override
	public void onItemClick(AdapterView<?> aview, View selectedView, int position, long id) {
		TextView tv = (TextView) selectedView.findViewById(R.id.eachrow);
		Log.d("selected anime : ",""+tv.getText());
		
		Intent ii = new Intent(getActivity(), Anime_Episode_Home.class);
		ii.putExtra("selected_anime_name", ""+tv.getText());
		startActivity(ii);
		
	}

}
