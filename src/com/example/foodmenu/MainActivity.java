package com.example.foodmenu;

import java.util.Date;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends BaseActivity implements OnClickListener {
	
	private final String TAG = "Main Activity";
	private String KOTno="1";
	EditText etSuppName, etTableName, etItemName, etQuantity;
	ListView listView;
	TextView tvItemId,tvRate, po1, po2, po3, po4, po5, grdTotal;
	Button bAddItem;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_place_order);
		initialize();
		
	}

	private void initialize() {
		// TODO Auto-generated method stub
		
		etSuppName = (EditText) findViewById(R.id.etSQLSuppName);
		etTableName = (EditText) findViewById(R.id.etSQLTableName);
		etQuantity = (EditText) findViewById(R.id.etSQLQuantity);
		etItemName = (EditText) findViewById(R.id.etSQLItemName);
		tvItemId = (TextView) findViewById(R.id.tvSQLId);
		tvRate = (TextView) findViewById(R.id.tvSQLRate);
		listView = (ListView) findViewById(R.id.list);
		bAddItem = (Button) findViewById(R.id.bSQLAddItem);
		po1 = (TextView) findViewById(R.id.po1);
		po2 = (TextView) findViewById(R.id.po2);
		po3 = (TextView) findViewById(R.id.po3);
		po4 = (TextView) findViewById(R.id.po4);
		po5 = (TextView) findViewById(R.id.po5);
		grdTotal = (TextView) findViewById(R.id.grandTotal);
		bAddItem.setOnClickListener(this);
		boolean didItWork = true;
		try{
			               
			SQLKOTDetails entry = new SQLKOTDetails(getApplicationContext());
			entry.open();
			KOTno=entry.getKOTno();
			if(KOTno.equals(null)){
				KOTno="1";
			}
			entry.close();
		} catch(Exception e) {
			Log.i(TAG,"SQL error message = "+e.getMessage());
		} finally {
			if(didItWork){						
				Log.i(TAG,"KOT REference number = "+KOTno);
			}
		}
		
		etItemName.addTextChangedListener(new TextWatcher(){
		
			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
				// TODO Auto-generated method stub
				
				SQLDatabase info = new SQLDatabase(getApplicationContext());
				info.open();
				final String str = arg0.toString();
				String[] list=info.getItemList(str);				
				info.close();
				if(list.length>0){
					final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, list);
					listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
					listView.setBackgroundColor(Color.parseColor("#666666"));
					listView.setFadingEdgeLength(200);
					listView.setAdapter(adapter);
					
					listView.setOnItemClickListener(new OnItemClickListener() {
			            
						@Override
						public void onItemClick(AdapterView<?> arg0, View arg1,
								int position, long arg3) {
							// TODO Auto-generated method stub
							//Log.d(TAG, adapter.getItem(position) + " has been selected! "+arg3+" "+position);
							SQLDatabase info = new SQLDatabase(getApplicationContext());
							info.open();
							String name = info.getItemName(str,position);
							String rate = info.getItemRate(str,position);
							String id = info.getItemId(str,position);
							info.close();
							tvItemId.setText(id.toString());
							tvRate.setText("Rs. "+rate);
							etItemName.setText(name);
						}
			        });
				}
								
			}
		});
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
		
		switch(arg0.getId()){
			
			case R.id.bSQLAddItem:
				
				boolean didItWork = true;
				try{
					String tbName = etTableName.getText().toString();
					String id = tvItemId.getText().toString();
					String quantity = etQuantity.getText().toString();
					if(!tbName.equals("")&&!id.equals("")&&!quantity.equals("")&&!KOTno.equals("")){
						Date date = new Date();
						long time = date.getTime();	                
						SQLKOTDetails entry = new SQLKOTDetails(getApplicationContext());
						entry.open();
						entry.createEntry(suppName,  tbName, String.valueOf(time), KOTno, id, quantity);
						String s=entry.getData(KOTno);
						Log.i(TAG,s);
						po1.setText(entry.getDataEntry1(KOTno));
						po2.setText(entry.getDataEntry2(KOTno));
						po3.setText(entry.getDataEntry3(KOTno));
						po4.setText(entry.getDataEntry4(KOTno));
						po5.setText(entry.getDataEntry5(KOTno));
						grdTotal.setText("Rs. "+entry.getGrandTotal(KOTno));
						entry.close();
					}
				} catch(Exception e) {
					Log.i( TAG, e.getMessage());
				} finally {
					if(didItWork){						
						etItemName.setText("");
						tvRate.setText("");
						tvItemId.setText("");
					}
				}			
				break;
			}
		}
	}

