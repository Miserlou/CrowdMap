package org.ale.crowdmap;

import android.app.Activity;
import android.os.Bundle;
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
        
        Spinner s = (Spinner) findViewById(R.id.spinner);
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
				
				
			}
        	
        });
        
    }
}