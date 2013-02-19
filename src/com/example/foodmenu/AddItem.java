package com.example.foodmenu;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddItem extends BaseActivity implements OnClickListener {
private final String TAG = "Main Activity";
	
	Button sqlAdd;
	EditText sqlName,sqlRate;
	TextView notify;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_item);
		sqlAdd = (Button) findViewById(R.id.bSQLAdd);
		sqlName = (EditText) findViewById(R.id.etSQLName);
		sqlRate = (EditText) findViewById(R.id.etSQLRate);
		notify = (TextView) findViewById(R.id.tvNotify);
		sqlAdd.setOnClickListener(this);
		Log.i(TAG,"On Create");
	}
		
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
		Log.i(TAG,"On Pause");
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.i(TAG,"On Resume");
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getId()){
		
		case R.id.bSQLAdd:
			Log.i(TAG,"asdasd");
			boolean didItWork = true;
			try{
				String name = sqlName.getText().toString();
				String rate = sqlRate.getText().toString();
				SQLDatabase entry = new SQLDatabase(AddItem.this);
				entry.open();
				entry.createEntry(name, rate);
				entry.close();
			} catch(Exception e) {
				didItWork = false;
				String error = e.toString();
				Dialog d = new Dialog(this);
				d.setTitle("Dang It!");
				TextView tv = new TextView(this);
				tv.setGravity(1);
				tv.setText(error);
				d.setContentView(tv);
				d.show();			
			} finally {
				if(didItWork){
					
					notify.setText("Congratz! Item Successfully Added");
					sqlName.setText("");
					sqlRate.setText("");					
				}
			}			
			break;
		}
	}
}
