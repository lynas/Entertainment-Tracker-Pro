package com.lynas.et;

import com.lynas.et.ui.anime.Anime_Home;
import com.lynas.et.ui.tvshow.TV_Home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;

public class A extends Fragment implements OnClickListener{
	View view_a;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			
		view_a = inflater.inflate(R.layout.home_a, container, false);
		
		view_a.setOnClickListener(this);
		
		return view_a;
	}

	@Override
	public void onClick(View v) {
		Log.d("TAG", "Side c");
		
		
		Intent animeHomeIntent = new Intent(getActivity(), Anime_Home.class);
		startActivity(animeHomeIntent); 
		
	}

}
