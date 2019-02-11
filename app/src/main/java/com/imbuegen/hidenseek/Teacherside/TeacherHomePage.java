package com.imbuegen.hidenseek.Teacherside;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import com.imbuegen.hidenseek.R;
import com.imbuegen.hidenseek.Services.TeacherBgService;

import java.util.Objects;

public class TeacherHomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.teacher_home);
        Objects.requireNonNull(getSupportActionBar()).setElevation(0);
        handlePermissions();
        init();
        setOnCLicks();
    }

    private void init() {
    }

    private void setOnCLicks() {
    }


    private void handlePermissions() {
        int locationRequestCode = 1000;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    locationRequestCode);
        else
            startService(new Intent(getApplicationContext(), TeacherBgService.class));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1000: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    startService(new Intent(getApplicationContext(), TeacherBgService.class));
                else
                    Toast.makeText(this, "Permissions denied", Toast.LENGTH_SHORT).show();
                break;
            }
        }
    }
}
