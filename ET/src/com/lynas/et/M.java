package com.lynas.et;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

public class M extends Fragment implements OnClickListener {

	View view_b;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			
		view_b = inflater.inflate(R.layout.home_m, container, false);
		
		view_b.setOnClickListener(this);
		
		return view_b;
	}

	@Override
	public void onClick(View v) {
		Log.d("TAG", "Side b");
		
	}

}
