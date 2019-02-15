package com.imbuegen.hidenseek.Teacherside;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.imbuegen.hidenseek.Models.Teacher;
import com.imbuegen.hidenseek.R;
import com.imbuegen.hidenseek.Services.TeacherBgService;

import java.util.Objects;

public class TeacherHomePage extends AppCompatActivity {
    Intent intent;
    FirebaseAuth auth;
    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;
    Teacher teacher;
    Switch busySwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.teacher_home);
        Objects.requireNonNull(getSupportActionBar()).setElevation(0);
        init();
        setOnCLicks();
    }

    private void init() {
        intent = new Intent(getApplicationContext(), TeacherBgService.class);
        teacher = (Teacher) getIntent().getSerializableExtra("Teacher");
        intent.putExtra("Teacher", teacher);
        if (teacher == null) {
            auth = FirebaseAuth.getInstance();
            firebaseUser = auth.getCurrentUser();
            databaseReference = FirebaseDatabase.getInstance().getReference("Teacher");
            assert firebaseUser != null;
            databaseReference.child(firebaseUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    teacher = snapshot.getValue(Teacher.class);
                    Log.d("Login", "onDataChange: " + teacher.getName());
                    intent.putExtra("Teacher", teacher);
                    handlePermissions();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(TeacherHomePage.this, "Fail to fetch data :(. Retry",
                            Toast.LENGTH_SHORT).show();
                }
            });
        } else
            handlePermissions();

        busySwitch = findViewById(R.id.switch_busy);
    }

    private void setOnCLicks() {
        busySwitch.setOnCheckedChangeListener((buttonView, isChecked) -> updateBusy(isChecked));
    }

    private void updateBusy(Boolean t) {
        if (t && teacher != null) {
            if (auth == null || databaseReference == null) {
                auth = FirebaseAuth.getInstance();
                firebaseUser = auth.getCurrentUser();
                databaseReference = FirebaseDatabase.getInstance().getReference("Teacher");
            }
            teacher.setBusy(1);
            if (firebaseUser != null && databaseReference != null)
                databaseReference.child(firebaseUser.getUid()).setValue(teacher);
            intent.putExtra("Teacher", teacher);
            startService(intent);
        } else if (!t && teacher != null) {
            if (auth == null || databaseReference == null) {
                auth = FirebaseAuth.getInstance();
                firebaseUser = auth.getCurrentUser();
                databaseReference = FirebaseDatabase.getInstance().getReference("Teacher");
            }
            teacher.setBusy(0);
            if (firebaseUser != null && databaseReference != null)
                databaseReference.child(firebaseUser.getUid()).setValue(teacher);
            intent.putExtra("Teacher", teacher);
            startService(intent);
        }
    }

    private void handlePermissions() {
        int locationRequestCode = 1000;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    locationRequestCode);
        else
            startService(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1000: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    startService(intent);
                else
                    Toast.makeText(this, "Permissions denied", Toast.LENGTH_SHORT).show();
                break;
            }
        }
    }
}
