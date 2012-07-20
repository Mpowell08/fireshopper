package com.fireminder.fireshopper;

import java.text.NumberFormat;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Content extends Activity implements OnClickListener{
	int flag;
	public static final String PREFS_NAME = "MyPrefsFile";
	EditText priceet, price2et;
	TextView totaltv;
	Spinner spinner, spinner2;
	float salestax, discount, discount2;
	Button button, twoitems;
	ArrayAdapter<CharSequence> adapter, adapter2;
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.content2);
		priceet = (EditText) findViewById(R.id.priceet);
		price2et = (EditText) findViewById(R.id.price2et);
		totaltv = (TextView) findViewById(R.id.totaltv);
		button = (Button) findViewById(R.id.button1);
		twoitems = (Button) findViewById(R.id.twoitems);
		spinner2 = (Spinner) findViewById(R.id.spinner2);
		spinner = (Spinner) findViewById(R.id.spinner1);
		
		flag = 0;
		spinner2.setVisibility(View.GONE);
		price2et.setVisibility(View.INVISIBLE);
		
		adapter = ArrayAdapter.createFromResource(this, R.array.discount_list, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				discount = Float.parseFloat((adapter.getItem(arg2).toString()));
			}

			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		// Spinner 2
		adapter2 = ArrayAdapter.createFromResource(this, R.array.discount_list, android.R.layout.simple_spinner_item);
		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner2.setAdapter(adapter2);
		spinner2.setOnItemSelectedListener(new OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				discount2 = Float.parseFloat((adapter2.getItem(arg2).toString()));
				
			}

			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		//get sharedpref
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		salestax = settings.getFloat("salesTax", 0) / 100;
		
		button.setOnClickListener(this);
		twoitems.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				if(flag == 0){
				spinner2.setVisibility(View.VISIBLE);
				price2et.setVisibility(View.VISIBLE);
				spinner.setVisibility(View.INVISIBLE);
				spinner2.setSelection(10);
				price2et.setText(priceet.getText().toString());
				flag++;
				
				}else{
					flag--;
					spinner2.setVisibility(View.INVISIBLE);
					spinner.setVisibility(View.VISIBLE);
					price2et.setVisibility(View.INVISIBLE);
				}
				
			}
			
		});
		
		
		
	}
	public void onClick(View v) {
		if(flag == 0){
			try{
				float price = Float.parseFloat(priceet.getText().toString());
				float total = price * (1-discount/100) * (1+salestax);
				total = (float) (Math.round(total*100.0)/100.0);
				totaltv.setText("$" + total);
			} catch (Exception e) {
				totaltv.setText("Make sure the discount is 0!");
			}
			
	
	}else {
		try{
			float price = Float.parseFloat(priceet.getText().toString());
			float price2 = Float.parseFloat(price2et.getText().toString());
			float total = ((price + price2*(1-discount2/100)) * (1+salestax));
			total = (float) (Math.round(total*100.0)/100.0);
			totaltv.setText("$" + total);
			
			
		}catch (Exception e) {
			totaltv.setText("Error");
		}
		
	}
	
	}
}
