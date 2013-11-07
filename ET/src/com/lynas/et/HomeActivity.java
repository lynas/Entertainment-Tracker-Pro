package com.lynas.et;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends FragmentActivity{
	Fragment t = new T();
	Fragment m = new M();
	Fragment a = new A();
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		
		FragmentManager mainFragManager = getSupportFragmentManager();
		FragmentTransaction fragtrans = mainFragManager.beginTransaction();
		fragtrans.replace(R.id.frameLeft, t);
		fragtrans.replace(R.id.frameRight, m);
		fragtrans.replace(R.id.sectionBot, a);
		fragtrans.commit();
		
		
		
		
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		Log.wtf("menu", " working");
		return super.onCreateOptionsMenu(menu);
	}


	
        
	

}
