package com.lynas.et.ui.movie;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.lynas.et.R;

public class Movie_Home extends FragmentActivity{

	Fragment movie_add_new_fragment = new Movie_Fragment_addNew();
	Fragment movie_list_of_movies = new Movie_Fragment_Move_List();

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.tv_show_home);

		FragmentManager mainFragManager = getSupportFragmentManager();
		FragmentTransaction fragtrans = mainFragManager.beginTransaction();
		fragtrans.replace(R.id.flTv_show_add_new, movie_add_new_fragment);
		fragtrans.replace(R.id.flTv_show_list, movie_list_of_movies);
		
		fragtrans.commit();
	}
	

}
