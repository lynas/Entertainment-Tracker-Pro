package com.lynas.et.ui.tvshow;

import com.lynas.et.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class TV_Home extends FragmentActivity {
	Fragment tv_show_add_new = new Tv_Show_Fragment_addNew();
	Fragment tv_show_show_list = new Tv_Show_Fragment_show_list();

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.tv_show_home);

		FragmentManager mainFragManager = getSupportFragmentManager();
		FragmentTransaction fragtrans = mainFragManager.beginTransaction();
		fragtrans.replace(R.id.flTv_show_add_new, tv_show_add_new);
		fragtrans.replace(R.id.flTv_show_list, tv_show_show_list);
		
		fragtrans.commit();
	}

}

