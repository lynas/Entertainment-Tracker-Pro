package com.lynas.et.ui.tvshow;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.lynas.et.HomeActivity;
import com.lynas.et.R;
import com.lynas.et.domain.DataProvider_TVShow;
import com.lynas.et.domain.DataStructure_TVShow;

public class TV_show_add_new_show_form extends Activity{
	Button addNewShowButton;
	EditText showNameText;
	Spinner selectShowSeason;
	Spinner selectNoOfEpisode;
	Spinner previousTvShow;
	String getPreviousStoredPageName = "1";
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tv_show_add_new_form);
		
		this.setTitle("ET > TV > Add New");
		
		addNewShowButton = (Button) findViewById(R.id.btAddNewTvShow);
		showNameText = (EditText) findViewById(R.id.etTVShowName); 
		selectShowSeason = (Spinner) findViewById(R.id.spSelectTVShowSeason);
		selectNoOfEpisode = (Spinner) findViewById(R.id.spTVSelectNoOfEpisode); 
		//getSharedPreferences(PreferenceConstants.animeEpisodePageNo, 0);
		
		//getPreviousStoredPageName = getSharedPreferences(PreferenceConstants.animeEpisodePageNo, 0).getString("savedAnimePage", "1");
		addNewShowButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Log.d("Show","Name : " +showNameText.getText()
				// +" SeasonNo : "+selectShowSeason.getSelectedItem().toString()+" NoOfEpisode : "+selectNoOfEpisode.getSelectedItem().toString());
				//savetoDB();
				//updateDb();
				//getFromDb(getPreviousStoredPageName);
				//deleteFromDB();
				
				if(formValidityChecker()){
					savetoDB();
				}else{
					Toast.makeText(TV_show_add_new_show_form.this, "Show Name Field Empty..", Toast.LENGTH_LONG).show();
					
				}
			}
		});

	}
	private boolean formValidityChecker() {
		if(showNameText.getText().toString().equals("")){
			return false;
		}else
			return true;
		
	}

	private void savetoDB() {
		new RunInBackGround().execute(Integer.parseInt(selectNoOfEpisode.getSelectedItem().toString()));
		
		/*ContentValues values = new ContentValues();
		values.put(DataStructure_TVShow.SHOWNAME, showNameText.getText()
				.toString());
		values.put(DataStructure_TVShow.SHOWSEASON, selectShowSeason
				.getSelectedItem().toString());
		values.put(DataStructure_TVShow.EPISODENAME, selectNoOfEpisode.getSelectedItem().toString());
		values.put(DataStructure_TVShow.EPISODESTATUS, "false");
		// values.put(DataStructure_TVShow.SHOWNAME, name.getText().toString());

		ContentResolver cr = this.getContentResolver();
		cr.insert(DataProvider_TVShow.tvShowTableUri, values);
*/
		Log.wtf("List", "success ::D");

	}
	private void updateDb(){
		ContentValues values = new ContentValues();
		ContentResolver resolver = getContentResolver();
		int x=99;
		values.put(DataStructure_TVShow.SHOWID, 99);
		
		x = resolver.update(DataProvider_TVShow.tvShowTableUri, values, DataStructure_TVShow.SHOWID+" = "+ "1", null);
		
	}


	private void getFromDb(String initiator) {
		Log.i("initiatior : ", initiator);
		String tempId="1";
		ContentResolver resolver = getContentResolver();
		String[] projection = { DataStructure_TVShow.SHOWID,
				DataStructure_TVShow.SHOWNAME, DataStructure_TVShow.SHOWSEASON,
				DataStructure_TVShow.EPISODENAME,
				DataStructure_TVShow.EPISODESTATUS };
		
		/*Cursor cursor = resolver.query(
				DataProvider_TVShow.tvShowTableUri,
				projection, 
				DataStructure_TVShow.SHOWID+" > "+ initiator, 
				null, 
				DataStructure_TVShow.SHOWID+" LIMIT 4");  */
		Cursor cursor = resolver.query(
				DataProvider_TVShow.tvShowTableUri,
				projection, 
				null, 
				null, 
				null);  
		
		if (cursor.moveToFirst()) {
			do {

				 
				tempId = cursor.getString(cursor.getColumnIndex(DataStructure_TVShow.EPISODENAME));  
				Log.wtf("List",	""	+ tempId);

			} while (cursor.moveToNext());
		}
		getPreviousStoredPageName = tempId;
		Log.d("tempid",	""	+ tempId);
		/*
		SharedPreferences.Editor editor = getSharedPreferences(PreferenceConstants.animeEpisodePageNo, 0).edit();
		editor.putString("savedAnimePage", tempId);
		editor.commit();
		*/
		

	}
	private void deleteFromDB(){
		ContentResolver resolver = getContentResolver();
		
		resolver.delete(DataProvider_TVShow.tvShowTableUri, DataStructure_TVShow.SHOWID + " = " + "99", null);
		
	}
	
	private class RunInBackGround extends AsyncTask<Integer, Integer, String>{
		ContentResolver cr = TV_show_add_new_show_form.this.getContentResolver();
		ProgressDialog myProgDialog;
		@Override
		protected void onPreExecute(){
			myProgDialog = new ProgressDialog(TV_show_add_new_show_form.this);
			myProgDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); 
			myProgDialog.setMessage("Working Please Wait....");
			myProgDialog.setMax(100);
			myProgDialog.show();
		}
		
		@Override
		protected String doInBackground(Integer...params) {
			
			for(int i=1;i<=params[0];i++){
				publishProgress(1);
				ContentValues values = new ContentValues();
				values.put(DataStructure_TVShow.SHOWNAME, showNameText.getText().toString());
				values.put(DataStructure_TVShow.SHOWSEASON, selectShowSeason.getSelectedItem().toString());
				values.put(DataStructure_TVShow.EPISODENAME, "Episode" + i);
				values.put(DataStructure_TVShow.EPISODESTATUS, "false");
				// values.put(DataStructure_TVShow.SHOWNAME, name.getText().toString());
				cr.insert(DataProvider_TVShow.tvShowTableUri, values);
				
			}
			myProgDialog.dismiss();
			return "success";
		}
		@Override
		protected void onProgressUpdate(Integer...integers){
			myProgDialog.incrementProgressBy(integers[0]);
		}
		@Override
		protected void onPostExecute(String Result){
			
			Intent homeIntent = new Intent(TV_show_add_new_show_form.this, HomeActivity.class);
			startActivity(homeIntent);
			
		}
		
	}

	

}
