package com.example.foodmenu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLKOTDetails {

	public static final String KEY_ROWID = "id";
	public static final String KEY_SUPPNAME = "supp_name";
	public static final String KEY_TABLENO = "table_no";
	public static final String KEY_TIME = "time";
	public static final String KEY_REFNO = "ref_no";
	public static final String KEY_ITEMID = "item_id";
	public static final String KEY_QUANTITY = "quantity";
	
	private static final String DATABASE_NAME = "FoodMenu1.db";
	private static final String DATABASE_TABLE = "KOTDetails";
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
					KEY_SUPPNAME + " TEXT NOT NULL, " +
					KEY_TABLENO + " TEXT NOT NULL, " +
					KEY_TIME + " TEXT NOT NULL, " +
					KEY_REFNO + " TEXT NOT NULL, " +
					KEY_ITEMID + " TEXT NOT NULL, " +
					KEY_QUANTITY + " TEXT NOT NULL);"
			);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
			onCreate(db);
			
		}

	}
	public SQLKOTDetails(Context c){
		ourContext = c;
	}
	public SQLKOTDetails open() throws SQLException{
		ourHelper = new DbHelper(ourContext);
		ourDatabase = ourHelper.getWritableDatabase();
		return this;
	}
	public void close() {
		ourHelper.close();
	}
	public String getKOTno() throws SQLException{
		// TODO Auto-generated method stub
		//ourDatabase.delete(DATABASE_TABLE, null, null);
		String[] columns = new String[]{KEY_ROWID, KEY_SUPPNAME, KEY_TABLENO, KEY_TIME, KEY_REFNO, KEY_ITEMID, KEY_QUANTITY};
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, null, null, null, null, KEY_REFNO);
		String result = "0";
		if(c!=null){
			c.moveToLast();
			result = c.getString(4);			
			Log.i("Main Activity", "iResult = " + (Integer.parseInt(result)+1) );
			return String.valueOf((Integer.parseInt(result)+1));			
		}
		return result;
	}
	public long createEntry(String str, String tbName, String time,
			String KOTno, String id, String quantity) throws SQLException {
		// TODO Auto-generated method stub
		Log.i("Main Activity", "supp name = " + str );
		Log.i("Main Activity", "table name = " + tbName );
		Log.i("Main Activity", "time = " + time );
		Log.i("Main Activity", "KOT no = " + KOTno );
		Log.i("Main Activity", "id = " + id );
		Log.i("Main Activity", "quantity = " + quantity );
		ContentValues cv = new ContentValues();
		cv.put(KEY_SUPPNAME, str);
		cv.put(KEY_TABLENO, tbName);
		cv.put(KEY_TIME, time);
		cv.put(KEY_REFNO, KOTno);
		cv.put(KEY_ITEMID, id);
		cv.put(KEY_QUANTITY, quantity);	
		return ourDatabase.insert(DATABASE_TABLE, null, cv);	
		
	}
	public String getData(String kOTno) throws SQLException {
		// TODO Auto-generated method stub
		String[] columns = new String[]{KEY_ROWID, KEY_SUPPNAME, KEY_TABLENO, KEY_TIME, KEY_REFNO, KEY_ITEMID, KEY_QUANTITY};
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, KEY_REFNO +"="+ kOTno, null, null, null, KEY_REFNO);
		String result = "";
		int iRow = c.getColumnIndex(KEY_ROWID);
		int iSuppName = c.getColumnIndex(KEY_SUPPNAME);
		int iTableno = c.getColumnIndex(KEY_TABLENO);
		int iTime = c.getColumnIndex(KEY_TIME);
		int iRefNo = c.getColumnIndex(KEY_REFNO);
		int iItemId = c.getColumnIndex(KEY_ITEMID);
		int iQuantity = c.getColumnIndex(KEY_QUANTITY);
		
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
			result = result + c.getString(iRow) + " " + c.getString(iSuppName) +" "+ c.getString(iTableno) + " " + c.getString(iTime) +" " + c.getString(iRefNo)+ " " + c.getString(iItemId) + " "+ c.getString(iQuantity)+ "\n";
		}
		return result;
		
	}
	
	public CharSequence getDataEntry1(String KOTno) {
		// TODO Auto-generated method stub
		String[] columns = new String[]{KEY_ROWID, KEY_SUPPNAME, KEY_TABLENO, KEY_TIME, KEY_REFNO, KEY_ITEMID, KEY_QUANTITY};
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, KEY_REFNO +"="+ KOTno, null, null, null, KEY_REFNO);
		String result = "";
		int length=c.getCount();
		
		for(int i=1;i<=length;i++){
			result = result + i + "\n";
		}
		return result;
	}
	public CharSequence getDataEntry2(String KOTno) {
		// TODO Auto-generated method stub
		String[] columns = new String[]{KEY_ROWID, KEY_SUPPNAME, KEY_TABLENO, KEY_TIME, KEY_REFNO, KEY_ITEMID, KEY_QUANTITY};
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, KEY_REFNO +"="+ KOTno, null, null, null, KEY_REFNO);
		String result = "";
			
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
			String i = c.getString(5);
			SQLDatabase entry = new SQLDatabase(ourContext);
			entry.open();
			String s=entry.getName(Long.parseLong(i));			
			result=result + s + "\n";
			entry.close();
		}
		return result;
	}
	public CharSequence getDataEntry3(String KOTno) {
		// TODO Auto-generated method stub
		String[] columns = new String[]{KEY_ROWID, KEY_SUPPNAME, KEY_TABLENO, KEY_TIME, KEY_REFNO, KEY_ITEMID, KEY_QUANTITY};
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, KEY_REFNO +"="+ KOTno, null, null, null, KEY_REFNO);
		String result = "";
			
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
			String i = c.getString(5);
			SQLDatabase entry = new SQLDatabase(ourContext);
			entry.open();
			String s=entry.getRate(Long.parseLong(i));			
			result=result + s + "\n";
			entry.close();			
		}
		return result;
	}
	public CharSequence getDataEntry4(String KOTno) {
		// TODO Auto-generated method stub
		String[] columns = new String[]{KEY_ROWID, KEY_SUPPNAME, KEY_TABLENO, KEY_TIME, KEY_REFNO, KEY_ITEMID, KEY_QUANTITY};
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, KEY_REFNO +"="+ KOTno, null, null, null, KEY_REFNO);
		String result = "";
			
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
			result = result + c.getString(6) + "\n";
		}
		return result;
	}
	public CharSequence getDataEntry5(String KOTno) {
		// TODO Auto-generated method stub
		String[] columns = new String[]{KEY_ROWID, KEY_SUPPNAME, KEY_TABLENO, KEY_TIME, KEY_REFNO, KEY_ITEMID, KEY_QUANTITY};
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, KEY_REFNO +"="+ KOTno, null, null, null, KEY_REFNO);
		String total = "";
		
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
			String i = c.getString(5);
			SQLDatabase entry = new SQLDatabase(ourContext);
			entry.open();
			String s=entry.getRate(Long.parseLong(i));			
			entry.close();	
			total=total+ (Integer.parseInt(c.getString(6)) * Integer.parseInt(s))+"\n";
		}
		return total;
	}
	public int getGrandTotal(String KOTno) {
		// TODO Auto-generated method stub
		String[] columns = new String[]{KEY_ROWID, KEY_SUPPNAME, KEY_TABLENO, KEY_TIME, KEY_REFNO, KEY_ITEMID, KEY_QUANTITY};
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, KEY_REFNO +"="+ KOTno, null, null, null, KEY_REFNO);
		int total=0;
		
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
			String i = c.getString(5);
			SQLDatabase entry = new SQLDatabase(ourContext);
			entry.open();
			String s=entry.getRate(Long.parseLong(i));			
			entry.close();	
			total=total + (Integer.parseInt(c.getString(6)) * Integer.parseInt(s));
		}
		return total;
	}
	
}
