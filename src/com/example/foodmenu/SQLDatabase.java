package com.example.foodmenu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLDatabase {

	public static final String KEY_ROWID = "id";
	public static final String KEY_NAME = "item_name";
	public static final String KEY_RATE = "rate";
	
	private static final String DATABASE_NAME = "FoodMenu.db";
	private static final String DATABASE_TABLE = "ItemDetails";
	private static final int DATABASE_VERSION = 1;
	
	private DbHelper ourHelper;
	private final Context ourContext;
	private SQLiteDatabase ourDatabase;
	
	private static class DbHelper extends SQLiteOpenHelper {

		public DbHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL("CREATE TABLE " + DATABASE_TABLE + " (" +
					KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
					KEY_NAME + " TEXT NOT NULL, " +
					KEY_RATE + " TEXT NOT NULL);"
			);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
			onCreate(db);
		}
		
	}
	public SQLDatabase(Context c){
		ourContext = c;
	}
	public SQLDatabase open() throws SQLException{
		ourHelper = new DbHelper(ourContext);
		ourDatabase = ourHelper.getWritableDatabase();
		return this;
	}
	public void close() {
		ourHelper.close();
	}
	public long createEntry(String name, String rate){
		ContentValues cv = new ContentValues();
		cv.put(KEY_NAME, name);
		cv.put(KEY_RATE, rate);
		return ourDatabase.insert(DATABASE_TABLE, null, cv);
	}
	public String getData() throws SQLException {
		// TODO Auto-generated method stub
		String[] columns = new String[]{KEY_ROWID,KEY_NAME,KEY_RATE};
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, null, null, null, null, null);
		String result = "";
		int iRow = c.getColumnIndex(KEY_ROWID);
		int iName = c.getColumnIndex(KEY_NAME);
		int iRate = c.getColumnIndex(KEY_RATE);
		
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
			result = result + c.getString(iRow) + " " + c.getString(iName) +" "+ c.getString(iRate) + "\n";
		}
		return result;
	}
	public String getData1() throws SQLException {
		// TODO Auto-generated method stub
		String[] columns = new String[]{KEY_ROWID,KEY_NAME,KEY_RATE};
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, null, null, null, null, null);
		String result = "";
		int iRow = c.getColumnIndex(KEY_ROWID);
				
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
			result = result + c.getString(iRow)  + "\n";
		}
		return result;
	}	
	public String getData2() throws SQLException {
		// TODO Auto-generated method stub
		String[] columns = new String[]{KEY_ROWID,KEY_NAME,KEY_RATE};
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, null, null, null, null, null);
		String result = "";
		
		int iName = c.getColumnIndex(KEY_NAME);
		
		
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
			result = result +  c.getString(iName) + "\n";
		}
		return result;
	}
	public String getData3() throws SQLException {
		// TODO Auto-generated method stub
		String[] columns = new String[]{KEY_ROWID,KEY_NAME,KEY_RATE};
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, null, null, null, null, null);
		String result = "";
		
		int iRate = c.getColumnIndex(KEY_RATE);
		
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
			result = result + c.getString(iRate) + "\n";
		}
		return result;
	}
	public void deleteEntry(Long id) throws SQLException {
		// TODO Auto-generated method stub
		ourDatabase.delete(DATABASE_TABLE, KEY_ROWID + "=" +id, null);
		
	}
	public String getName(Long id) throws SQLException {
		// TODO Auto-generated method stub
		String[] columns = new String[]{KEY_ROWID,KEY_NAME,KEY_RATE};
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, KEY_ROWID + "=" +id, null, null, null, null);
		String result = "";
		if(c!=null){
			c.moveToFirst();
			result = c.getString(1);
			return result;
		}
		return null;
	}
	public String getRate(Long id) throws SQLException {
		// TODO Auto-generated method stub
		String[] columns = new String[]{KEY_ROWID,KEY_NAME,KEY_RATE};
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, KEY_ROWID + "=" +id, null, null, null, null);
		String result = "";
		if(c!=null){
			c.moveToFirst();
			result = c.getString(2);
			return result;
		}
		return null;
	}
	public void updateEntry(Long id, String strName, String strRate) throws SQLException{
		// TODO Auto-generated method stub
		ContentValues cv = new ContentValues();
		cv.put(KEY_NAME, strName);
		cv.put(KEY_RATE, strRate);
		ourDatabase.update(DATABASE_TABLE, cv, KEY_ROWID + "=" +id, null);
	}
		
	public String[] getItemList(String str) throws SQLException {
		// TODO Auto-generated method stub
		
		String[] columns = new String[]{KEY_ROWID,KEY_NAME,KEY_RATE};
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, KEY_NAME +" LIKE '%"+str+"%'", null, null, null, KEY_NAME);
		
		String result[] = new String[c.getCount()];
		
		int iName = c.getColumnIndex(KEY_NAME);
		//int iRate = c.getColumnIndex(KEY_RATE);
		int i=0;
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
			result[i]=" "+c.getString(iName);
			//Log.i(TAG,result[i]+" "+i);
			i++;			
		}
		return result;
		
	}
	public String getItemName(String str, int position) {
		// TODO Auto-generated method stub
		String[] columns = new String[]{KEY_ROWID,KEY_NAME,KEY_RATE};
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, KEY_NAME +" LIKE '%"+str+"%'", null, null, null, KEY_NAME);
		
		String result = new String();
		
		int iName = c.getColumnIndex(KEY_NAME);
		//int iRate = c.getColumnIndex(KEY_RATE);
		
		c.moveToPosition(position);
		result= c.getString(iName);
		
		return result;		
	}
	public String getItemRate(String str, int position) {
		// TODO Auto-generated method stub
		String[] columns = new String[]{KEY_ROWID,KEY_NAME,KEY_RATE};
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, KEY_NAME +" LIKE '%"+str+"%'", null, null, null, KEY_NAME);
		
		String result = new String();
		
		int iRate = c.getColumnIndex(KEY_RATE);
		//int iRate = c.getColumnIndex(KEY_RATE);
		
		c.moveToPosition(position);
		result= c.getString(iRate);
		
		return result;
	}
	public String getItemId(String str, int position) {
		// TODO Auto-generated method stub
		String[] columns = new String[]{KEY_ROWID,KEY_NAME,KEY_RATE};
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, KEY_NAME +" LIKE '%"+str+"%'", null, null, null, KEY_NAME);
		
		String result = new String();
		
		int iId = c.getColumnIndex(KEY_ROWID);
		//int iRate = c.getColumnIndex(KEY_RATE);
		
		c.moveToPosition(position);
		result= c.getString(iId);
		
		return result;
		
	}	
}
