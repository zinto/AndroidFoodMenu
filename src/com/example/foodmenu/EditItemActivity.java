package com.example.foodmenu;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class EditItemActivity extends BaseActivity implements OnClickListener {

	private final String TAG = "Main Activity";
	Button btnEdit;
	EditText name,rate;
	String gotBread;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_item);
		initialize();
		Bundle gotBasket = getIntent().getExtras();
		gotBread = gotBasket.getString("key");
		Long ll = Long.parseLong(gotBread);
		SQLDatabase info = new SQLDatabase(this);
		info.open();
		String data1 = info.getName(ll);
		String data2 = info.getRate(ll);
		info.close();
		name.setText(data1);
		rate.setText(data2);
		
	}

	private void initialize() {
		// TODO Auto-generated method stub

		name = (EditText) findViewById(R.id.editText1);
		rate = (EditText) findViewById(R.id.editText2);
		btnEdit = (Button) findViewById(R.id.button1);
		btnEdit.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getId()){
		
		case R.id.button1:
			Log.i(TAG,"asdasd");
			Bundle gotBasket = getIntent().getExtras();
			gotBread = gotBasket.getString("key");
			Long id = Long.parseLong(gotBread);
			String strName = name.getText().toString();
			String strRate = rate.getText().toString();
			SQLDatabase info = new SQLDatabase(this);
			info.open();
			info.updateEntry(id,strName,strRate);
			info.close();
			Thread th;
			th=new Thread(){
				public void run(){
					try{
						sleep(10);
					} catch(InterruptedException e){
						e.printStackTrace();
					} finally{
						Intent openMainActivity = new Intent("com.example.foodmenu.SQLITEMVIEW");
						startActivity(openMainActivity);
					}
				}
			};
			th.start();
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
