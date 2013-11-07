package com.lynas.et.ui.tvshow;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.lynas.et.R;
import com.lynas.et.domain.DataProvider_TVShow;
import com.lynas.et.domain.DataStructure_TVShow;

public class Tv_Show_Fragment_show_list extends Fragment {
	View view_tvshowAddNew;
	ListView tv_show_list;
	ArrayAdapter<String> showNameAdapter;

	String[] FRUITS = new String[] { "Apple", "Avocado", "Banana", "Blueberry",
			"Coconut", "Durian", "Guava", "Kiwifruit", "Jackfruit", "Mango",
			"Olive", "Pear", "Sugar-apple" };
	List<TV_Show_Names> items = new ArrayList<TV_Show_Names>();
	List<TV_Show_Names> itemsResource = new ArrayList<TV_Show_Names>();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		itemsResource.add(new TV_Show_Names() {	{showName = "Dexter";showImage = R.drawable.dexter;}});
		itemsResource.add(new TV_Show_Names() {	{showName = "Other";showImage = R.drawable.ic_launcher;}});
		
		
		getFromDb();
		
		

		view_tvshowAddNew = inflater.inflate(R.layout.tv_show_show_list,
				container, false);

		tv_show_list = (ListView) view_tvshowAddNew.findViewById(R.id.lv_tv_show_list);

		// ArrayAdapter<String> showNameAdapter = new
		// ArrayAdapter<String>(getActivity().getApplicationContext(),
		// R.layout.tv_show_each_show_name, FRUITS);
		
		/*showNameAdapter = new ArrayAdapter<String>(getActivity(), R.layout.tv_show_each_show_name, FRUITS);
		tv_show_list.setAdapter(showNameAdapter);*/
		
		Tv_Show_Name_List_customAdapter adapter = new Tv_Show_Name_List_customAdapter(getActivity(), items);
		tv_show_list.setAdapter(adapter);
		
		tv_show_list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position,
					long id) {
				
				Log.d("pressed",""+position);
				
				// TODO Auto-generated method stub
				
			}
			
		});
		
        
		return view_tvshowAddNew;  
	}
	
	
	private void getFromDb() { 
		
		String tempName="";
		ContentResolver resolver = getActivity().getContentResolver();
		String[] projection = { 
				
				" DISTINCT "+DataStructure_TVShow.SHOWNAME, 
				 
		};
		Cursor cursor = resolver.query(
				DataProvider_TVShow.tvShowTableUri,
				projection, 
				null, 
				null, 
				null);  
		
		if (cursor.moveToFirst()) {
			do {
				tempName = cursor.getString(cursor.getColumnIndex(DataStructure_TVShow.SHOWNAME));  
				Log.d("Names : ",	""	+ tempName);
			
				TV_Show_Names tmp=new TV_Show_Names();
				tmp.showName=tempName;
				tmp.showImage = getResourceForThisShow(tempName);
				items.add(tmp);
			} while (cursor.moveToNext());
		}
		
		cursor.close();
	}


	private int getResourceForThisShow(String tempName) {
		int retValue = R.drawable.ic_launcher;
		Log.d("Coming : ", "yes"+tempName+""+itemsResource.size());
		for(int i=0;i<itemsResource.size();i++){
			if(itemsResource.get(i).showName.equals(tempName)){
				Log.d("resource : ", "found");
				retValue =   itemsResource.get(i).showImage;
				break;
			}
		}
		return retValue;
	}

} 

class TV_Show_Names{
	String showName;
	int showImage;
}
