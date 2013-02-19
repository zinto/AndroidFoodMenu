package com.example.foodmenu;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;


public class BaseActivity extends Activity {
	
	public static String suppName;
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		Thread th;
		switch(item.getItemId()){
			case R.id.mPlaceOrder:
				th=new Thread(){
					public void run(){
						try{
							sleep(10);
						} catch(InterruptedException e){
							e.printStackTrace();
						} finally{
							Intent openMainActivity = new Intent("com.example.foodmenu.SUPPNAMEENTRY");
							startActivity(openMainActivity);
						}
					}
				};
				th.start();
				return true;
			case R.id.mAddItem:
				th=new Thread(){
					public void run(){
						try{
							sleep(10);
						} catch(InterruptedException e){
							e.printStackTrace();
						} finally{
							Intent openMainActivity = new Intent("com.example.foodmenu.ADDITEM");
							startActivity(openMainActivity);
						}
					}
				};
				th.start();
				return true;
			case R.id.mEditDeleteItem:
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
				return true;
			case R.id.mAddSupp:
				th=new Thread(){
					public void run(){
						try{
							sleep(10);
						} catch(InterruptedException e){
							e.printStackTrace();
						} finally{
							Intent openMainActivity = new Intent("com.example.foodmenu.ADMINLOGIN");
							startActivity(openMainActivity);
						}
					}
				};
				th.start();
				return true;
			case R.id.mEditDeleteSupp:
				th=new Thread(){
					public void run(){
						try{
							sleep(10);
						} catch(InterruptedException e){
							e.printStackTrace();
						} finally{
							Intent openMainActivity = new Intent("com.example.foodmenu.ADMINLOGIN");
							startActivity(openMainActivity);
						}
					}
				};
				th.start();
				return true;
			case R.id.mExit:
				System.exit(1);
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
			
	}
}
