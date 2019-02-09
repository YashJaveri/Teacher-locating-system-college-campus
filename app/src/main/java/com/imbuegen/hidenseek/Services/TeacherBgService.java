package com.imbuegen.hidenseek.Services;

import android.Manifest;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

public class TeacherBgService extends Service {
    int mstartMode;
    private LocationRequest mLocationRequest = LocationRequest.create();
    private LocationCallback mLocationCallback;
    private FusedLocationProviderClient mFusedLocationClient;
    private double latitude = 0.0, longitude = 0.0, altitude = 0.0;
    //TAG
    private static final String TAG = "Debug";
    /** Called when the service is being created. */
    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate: ");
    }

    private void init() {
        //location
        Log.d(TAG, "init: ");
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        mLocationRequest.setInterval(5000);
        mLocationRequest.setFastestInterval(2000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        mLocationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null)
                    return;
                for (Location location : locationResult.getLocations()) {
                    if (location != null) {
                        Log.d(TAG, "onLocationResult: "+location);
                        latitude = location.getLatitude();
                        longitude = location.getLongitude();
                        altitude = location.getAltitude();
                        String s = "Latitude: "+latitude+"\nLongitude: "+longitude+ "\nAltitude: "+altitude;
                        Log.d(TAG, s);
                    }
                }
            }
        };
        initiateLocListener();
    }
    private void initiateLocListener() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mFusedLocationClient.requestLocationUpdates(mLocationRequest,
                    mLocationCallback,
                    null);
        }
    }
    /** The service is starting, due to a call to startService() */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        init();
        return mstartMode;
    }

    /** Called when The service is no longer used and is being destroyed */
    @Override
    public void onDestroy() {
        //remove listener
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
