package com.example.foodmenu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Splash extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		Thread th=new Thread(){
			public void run(){
				try{
					sleep(1000);
				} catch(InterruptedException e){
					e.printStackTrace();
				} finally{
					Intent openMainActivity = new Intent("com.example.foodmenu.SUPPNAMEENTRY");
					startActivity(openMainActivity);
				}
			}
		};
		th.start();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}	
}