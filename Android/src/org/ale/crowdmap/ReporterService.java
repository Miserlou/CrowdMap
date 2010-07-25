package org.ale.crowdmap;

import org.ale.crowdmap.MyLocation.LocationResult;

import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.os.IBinder;

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
