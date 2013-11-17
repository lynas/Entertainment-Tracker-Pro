package com.lynas.et.domain;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataStructure_Movie extends SQLiteOpenHelper{

	public static final String DATABASE_NAME = "etpro_movie";  //setting up db name
	public static final String TABLE_NAME = "movie";  // setting up table name
	
	
	
	public static String MOVIE_ID = "movie_id";
    public static String MOVIE_NAME = "movie_name";
    public static String MOVIE_RELEASE_DATE = "movie_release_date";
    public static String MOVIE_STATUS = "movie_status";
	
	

	
	/* query to create db if it is not exist on the phone db*/
	

	
	public static final String DBCREATE_MOVIE = "CREATE TABLE IF NOT EXISTS movie(" +
			MOVIE_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
			MOVIE_NAME+" VARCHAR, " +
			MOVIE_RELEASE_DATE+" VARCHAR, " +
			MOVIE_STATUS+" VARCHAR);";
	
	
	public static final int VERSION = 1;
	
	public DataStructure_Movie(Context context) {
		super(context, DATABASE_NAME, null,VERSION);

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DBCREATE_MOVIE);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE "+TABLE_NAME );
	}
	

}
