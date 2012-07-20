package com.fireminder.fireshopper;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class fireshopperActivity extends Activity implements OnClickListener{
    /** Called when the activity is first created. */
	EditText et;
	TextView donetv;
	public static final String PREFS_NAME = "MyPrefsFile";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        et = (EditText) findViewById(R.id.editText1);
        donetv = (TextView) findViewById(R.id.donetv);
        
        donetv.setClickable(true);
        donetv.setOnClickListener(this);
        
        // Shared Pref
    }
	public void onClick(View v) {
		try{
		float tax = Float.parseFloat(et.getText().toString());
		
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		SharedPreferences.Editor editor = settings.edit();
		editor.putFloat("salesTax", tax);
		editor.commit();
		
		
		Intent i = new Intent("com.fireminder.Content");
		startActivity(i);
		} catch(Exception e) {
			Toast.makeText(this, "Enter Sales Tax", Toast.LENGTH_SHORT).show();
			
		}
		
	}
}