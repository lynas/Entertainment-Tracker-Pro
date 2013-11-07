package com.lynas.et.ui.anime;

import com.lynas.et.R;
import com.lynas.et.domain.DataProvider_Anime;
import com.lynas.et.domain.DataStructure_Anime;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Anime_Add_new_form extends Activity{
	EditText animeName;
	Spinner animeNoOfEpisode;
	Spinner animeAirDate;
	Button animeAddNewButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.anime_add_new_anime);
		animeName = (EditText) findViewById(R.id.etAnimeName);
		animeNoOfEpisode = (Spinner) findViewById(R.id.spAnimeNoOfEpisode);
		animeAirDate = (Spinner) findViewById(R.id.spAnimeAirDate);
		animeAddNewButton = (Button) findViewById(R.id.btAnimeAdd);
		
		
		animeAddNewButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(formValidityChecker()){
					savetoDB();
				}else{
					Toast.makeText(Anime_Add_new_form.this, "Anime Name Field Empty..", Toast.LENGTH_LONG).show();
					
				}
				
			}

			

			
		});
		
		
	}
	
	
	private boolean formValidityChecker() {
		if(animeName.getText().equals("")){
			return false;
		}else{
			return true;	
		}
		
	}
	
	private void savetoDB() {
		new RunInBackGround().execute(Integer.parseInt(animeNoOfEpisode.getSelectedItem().toString()));
	}
	
	
	
	private class RunInBackGround extends AsyncTask<Integer, Integer, String>{
		ContentResolver cr = Anime_Add_new_form.this.getContentResolver();
		ProgressDialog myProgDialog;
		@Override
		protected void onPreExecute(){
			myProgDialog = new ProgressDialog(Anime_Add_new_form.this);
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
				values.put(DataStructure_Anime.ANIMENAME, animeName.getText().toString());
				values.put(DataStructure_Anime.ANIMEEPISODE, " Episode " + i);
				values.put(DataStructure_Anime.ANIMEAIRDAY, animeAirDate.getSelectedItem().toString());
				values.put(DataStructure_Anime.ANIMEEPISODESTATUS, "false");
				cr.insert(DataProvider_Anime.animeTableUri, values);
				
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
			
			Intent homeIntent = new Intent(Anime_Add_new_form.this, Anime_Home.class);
			startActivity(homeIntent);
			
		}
		
	}
	

}
