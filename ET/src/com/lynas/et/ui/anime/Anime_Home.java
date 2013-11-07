package com.lynas.et.ui.anime;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.lynas.et.R;


public class Anime_Home extends FragmentActivity{

	Fragment anime_add_new = new Anime_Fragment_addNew();
	Fragment anime_anime_list = new Anime_Fragment_Anime_list();

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.anime_home);

		FragmentManager mainFragManager = getSupportFragmentManager();   
		FragmentTransaction fragtrans = mainFragManager.beginTransaction();
		fragtrans.replace(R.id.flanime_add_new, anime_add_new);
		fragtrans.replace(R.id.flanime_anime_list, anime_anime_list); 
		
		fragtrans.commit();
	}
	

}
