package com.lynas.et.domain;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class DataProvider_TVShow extends ContentProvider{
	
	private static final String AUTH = "com.lynas.et.domain.DataProvider_TVShow";
	public static final Uri tvShowTableUri = Uri.parse("content://"+AUTH+"/"+DataStructure_TVShow.TABLE_NAME);
	
	private static int COMMENT = 1;
	SQLiteDatabase db;
	DataStructure_TVShow dbhelper;
	
	private final static UriMatcher uriMatcher;
	
	static{
		uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		uriMatcher.addURI(AUTH, DataStructure_TVShow.TABLE_NAME, COMMENT);
		
	}
	
	
	@Override
	public boolean onCreate() {
		dbhelper = new DataStructure_TVShow(getContext()); 
		return true;
	}
	
	
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		db = dbhelper.getWritableDatabase();
		return db.delete(DataStructure_TVShow.TABLE_NAME, selection, selectionArgs);
		
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		
		db = dbhelper.getWritableDatabase();
		
		if(uriMatcher.match(uri)==COMMENT){
			db.insert(DataStructure_TVShow.TABLE_NAME, null, values);
		}
		db.close();
		
		getContext().getContentResolver().notifyChange(uri, null);
		
		
		return null;
	}

	

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		Cursor cursor;
		db = dbhelper.getReadableDatabase();
		
		cursor = db.query(DataStructure_TVShow.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
		cursor.setNotificationUri(getContext().getContentResolver(), uri);
		
		return cursor;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		int cursor;
		db = dbhelper.getReadableDatabase();
		
		cursor = db.update(DataStructure_TVShow.TABLE_NAME, values, selection, selectionArgs);
		//cursor.setNotificationUri(getContext().getContentResolver(), uri);
		db.close();
		return cursor;
	}

}
