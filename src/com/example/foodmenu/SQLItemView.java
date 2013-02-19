package com.example.foodmenu;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SQLItemView extends BaseActivity implements OnClickListener {

	Button btnEdit, btnDelete;
	EditText sqlId;
	private final String TAG = "Main Activity";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.item_view);
		
		TextView tv1 = (TextView) findViewById(R.id.tvSQLid);
		TextView tv2 = (TextView) findViewById(R.id.tvSQLname);
		TextView tv3 = (TextView) findViewById(R.id.tvSQLrate);
		
		btnEdit = (Button) findViewById(R.id.editButton);
		btnDelete = (Button) findViewById(R.id.deleteButton);
		btnEdit.setOnClickListener(this);
		btnDelete.setOnClickListener(this);
		
		sqlId = (EditText) findViewById(R.id.etSQLItemID);
		
		SQLDatabase info = new SQLDatabase(this);
		info.open();
		String data1 = info.getData1();
		String data2 = info.getData2();
		String data3 = info.getData3();
		info.close();
		tv1.setText(data1);
		tv2.setText(data2);
		tv3.setText(data3);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		String id = new String();
		switch(arg0.getId()){
		
			case R.id.editButton:
				Log.i(TAG,"asdasd");
				id = sqlId.getText().toString();
				if(!id.equals("")){
					Thread th=new Thread(){
						public void run(){
							try{
								sleep(10);
								
							} catch(InterruptedException e){
								e.printStackTrace();
							} finally {
								Bundle basket = new Bundle();
								String id1 = sqlId.getText().toString();
								basket.putString("key", id1);
								Intent openActivity = new Intent("com.example.foodmenu.EDITITEMACTIVITY");
								openActivity.putExtras(basket);
								startActivity(openActivity);
							}
						}
					};
					th.start();
				}
				
				break;
			case R.id.deleteButton:
				Log.i(TAG,"asdasd");
				//boolean didItWork = true;
				try{
					id = sqlId.getText().toString();
					long lid = Long.parseLong(id);
					SQLDatabase info = new SQLDatabase(this);
					info.open();
					info.deleteEntry(lid);
					info.close();
				} catch(Exception e) {
							
				} finally {
					Intent openActivity = new Intent("com.example.foodmenu.SQLITEMVIEW");
					startActivity(openActivity);
				}			
				break;
		}
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}
	
}
