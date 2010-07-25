package org.ale.crowdmap;

import java.math.BigInteger;
import java.security.SecureRandom;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

public class CMConfigActivity extends Activity {
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
        
        final EditText et = (EditText) findViewById(R.id.other);
        et.setEnabled(false);
        et.setVisibility(4);
        
        final Spinner s = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(
                this, R.array.carriers, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter);
        s.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// If it's "Other", enable the Other EditView
				if(arg2 == 17){
					et.setEnabled(true);
					et.setVisibility(0);
				}
				else{
					et.setEnabled(false);
					et.setVisibility(4);
				}
				
			}

        });
        
        Button b = (Button)findViewById(R.id.thebutton);
        b.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//Do the stuff
				SharedPreferences prefs;
				final SharedPreferences.Editor editor;
				prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
				editor = prefs.edit();
				
				String unique = prefs.getString("unique", "");
				String carrier = prefs.getString("carrier", "");
				
				if(unique.equals("")){
					unique = new BigInteger(130, new SecureRandom()).toString(20);
					editor.putString("unique", unique);
				}
			
				int pos = s.getSelectedItemPosition();
				if(pos == 17){
					carrier = et.getText().toString();
				}
				else{
					carrier = s.getSelectedItem().toString();
				}
				editor.putString("carrier", carrier);
				
				Intent i = new Intent(null, ReporterService.class);
				startService(i);
			}
        	
        });
        
    }
}