package com.imbuegen.hidenseek;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.imbuegen.hidenseek.Services.TeacherBgService;
import com.imbuegen.hidenseek.Studentside.StudentHomePage;
import com.imbuegen.hidenseek.Teacherside.TeacherHomePage;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    //TAG
    Button teacher, student;
    FirebaseAuth auth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.chooser);
        Objects.requireNonNull(getSupportActionBar()).setElevation(0);
        handlePermissions();
        init();
        setOnCLicks();
    }

    private void init() {
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        teacher = findViewById(R.id.Teacher);
        student = findViewById(R.id.Student);
    }

    private void setOnCLicks() {
        teacher.setOnClickListener(arg0 -> {
            if (user == null) {
                Intent myIntent = new Intent(MainActivity.this,
                        RegistrationActivity.class);
                startActivity(myIntent);
                finish();
            }
            else{
                Intent myIntent = new Intent(MainActivity.this,
                        TeacherHomePage.class);
                startActivity(myIntent);
                finish();
            }
        });
        student.setOnClickListener(arg0 -> {
            Intent intent = new Intent(MainActivity.this,
                    StudentHomePage.class);
            startActivity(intent);
            finish();
        });

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