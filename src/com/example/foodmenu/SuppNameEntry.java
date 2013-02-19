package com.example.foodmenu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class SuppNameEntry extends BaseActivity implements OnClickListener {

	EditText etSuppName;
	Button btnDone;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.supp_name_entry);
		initialize();
	}

	private void initialize() {
		// TODO Auto-generated method stub
		etSuppName = (EditText) findViewById(R.id.etSQLSuppName);
		btnDone = (Button) findViewById(R.id.btnDone);
		btnDone.setOnClickListener(this);
		
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		Thread th;
		switch(arg0.getId()){
		case R.id.btnDone:
			th=new Thread(){
				public void run(){
					try{
						suppName=etSuppName.getText().toString();
						sleep(10);
					} catch(InterruptedException e){
						e.printStackTrace();
					} finally{
						if(!suppName.equals("")){
							Intent openMainActivity = new Intent("com.example.foodmenu.MAINACTIVITY");
							startActivity(openMainActivity);
						}
					}
				}
			};
			th.start();		
		}
	}

}
