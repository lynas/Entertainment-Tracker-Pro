package com.lynas.et.domain;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DataStructure_Anime extends SQLiteOpenHelper{
	public static final String DATABASE_NAME = "entertainmenttracker2";  //setting up db name
	public static final String TABLE_NAME = "anime";  // setting up table name
	
	public static String ANIMEID = "anime_id";
    public static String ANIMENAME = "anime_name";
    public static String ANIMEAIRDAY = "anime_air_day";
    public static String ANIMEEPISODE = "anime_episode";
    public static String ANIMEEPISODESTATUS = "anime_episode_status";
    
    public static final String DBCREATE_ANIME = "CREATE TABLE IF NOT EXISTS anime(" +
    		ANIMEID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
    		ANIMENAME+" VARCHAR, " +
    		ANIMEAIRDAY+" VARCHAR, " +
    		ANIMEEPISODE+" VARCHAR, " +
    		ANIMEEPISODESTATUS+" VARCHAR);";
	
    
    public static final int VERSION = 1;
    

    public DataStructure_Anime(Context context) {
		super(context, DATABASE_NAME, null,VERSION);

	}

    @Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DBCREATE_ANIME);
		
	}

    @Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		db.execSQL("DROP TABLE "+TABLE_NAME );
	}

}
