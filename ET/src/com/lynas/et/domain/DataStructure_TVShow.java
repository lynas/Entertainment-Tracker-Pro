package com.lynas.et.domain;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataStructure_TVShow extends SQLiteOpenHelper{

	public static final String DATABASE_NAME = "entertainmenttracker";  //setting up db name
	public static final String TABLE_NAME = "tv_show";  // setting up table name
	
	
	
	public static String SHOWID = "show_id";
    public static String SHOWNAME = "show_name";
    public static String SHOWSEASON = "season_name";
    public static String EPISODENAME = "episode_name";
    public static String EPISODESTATUS = "episode_status";
	
	

	
	/* query to create db if it is not exist on the phone db*/
	

	
	public static final String DBCREATE_TV_SHOW = "CREATE TABLE IF NOT EXISTS tv_show(" +
			SHOWID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
			SHOWNAME+" VARCHAR, " +
			SHOWSEASON+" VARCHAR, " +
			EPISODENAME+" VARCHAR, " +
			EPISODESTATUS+" VARCHAR);";
	
	
	public static final int VERSION = 1;
	
	
	

	public DataStructure_TVShow(Context context) {
		super(context, DATABASE_NAME, null,VERSION);

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DBCREATE_TV_SHOW);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		db.execSQL("DROP TABLE "+TABLE_NAME );
	}
	

}
