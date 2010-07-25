package org.ale.crowdmap;

import org.ale.crowdmap.MyLocation.LocationResult;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.IBinder;
import android.preference.PreferenceManager;

public class ReporterService extends Service {
	
	MyLocation myLocation = new MyLocation();
	
	private void locationClick() {
	    myLocation.getLocation(this, locationResult);
	}
	
	public LocationResult locationResult = new LocationResult(){
	    @Override
	    public void gotLocation(final Location location){
	        //Got the location!
	    	//Prep and send that shit!
	    	
	    	double lat = location.getLatitude();
	    	double lon = location.getLongitude();
	    	String prov = location.getProvider();
	    	
			SharedPreferences prefs;
			final SharedPreferences.Editor editor;
			prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
			editor = prefs.edit();
			String unique = prefs.getString("unique", "");
			String carrier = prefs.getString("carrier", "");
			
			
	        }
	    };

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void onStart(Intent intent, int startId) {	
		//do everything
	
	}
	}
