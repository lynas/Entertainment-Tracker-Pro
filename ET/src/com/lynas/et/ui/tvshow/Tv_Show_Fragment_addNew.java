package com.lynas.et.ui.tvshow;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.lynas.et.R;

public class Tv_Show_Fragment_addNew extends Fragment implements OnClickListener{ 
	View view_tvshowAddNew;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			
		view_tvshowAddNew = inflater.inflate(R.layout.tv_show_add_new_show, container, false);
		
		view_tvshowAddNew.setOnClickListener(this);
		
		return view_tvshowAddNew;
	}
 
	@Override
	public void onClick(View v) {
		Intent addNewTvShow = new Intent(getActivity(), TV_show_add_new_show_form.class);
		startActivity(addNewTvShow);
		
	}

}
