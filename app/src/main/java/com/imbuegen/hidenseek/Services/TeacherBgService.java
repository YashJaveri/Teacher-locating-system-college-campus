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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.imbuegen.hidenseek.Models.Teacher;

public class TeacherBgService extends Service {
    int mstartMode;
    private LocationRequest mLocationRequest = LocationRequest.create();
    private LocationCallback mLocationCallback;
    private FusedLocationProviderClient mFusedLocationClient;
    private FirebaseUser firebaseUser;
    private FirebaseAuth auth;
    private DatabaseReference databaseReference;
    private double latitude = 0.0, longitude = 0.0, altitude = 0.0;
    private Teacher teacher;
    //TAG
    private static final String TAG = "Debug";

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate: ");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        init(intent);
        return mstartMode;
    }

    private void init(Intent intent) {
        //firebase
        auth = FirebaseAuth.getInstance();
        firebaseUser = auth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("Teacher");
        //Objects
        teacher = (Teacher) intent.getSerializableExtra("Teacher");
        //location
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
                        latitude = location.getLatitude();
                        longitude = location.getLongitude();
                        altitude = location.getAltitude();
                        //firebase push
                        teacher.setLongitude(longitude);
                        teacher.setLatitude(latitude);
                        teacher.setAltitude(altitude);
                        if (firebaseUser != null && databaseReference != null)
                            databaseReference.child(firebaseUser.getUid()).setValue(teacher);
                        //Log.d(TAG, "Latitude: " + latitude + "\nLongitude: " + longitude + "\nAltitude: " + altitude);
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
