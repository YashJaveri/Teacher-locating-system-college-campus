package com.imbuegen.hidenseek;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.imbuegen.hidenseek.Models.Classroom;
import com.imbuegen.hidenseek.Studentside.StudentHomePage;
import com.imbuegen.hidenseek.Teacherside.TeacherHomePage;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    //TAG
    Button teacher, student;
    FirebaseAuth auth;
    FirebaseUser user;
    DatabaseReference databaseReferenceClass;
    public static ArrayList<Classroom> classroomArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.chooser);
        Objects.requireNonNull(getSupportActionBar()).setElevation(0);
        init();
        setOnCLicks();
    }

    private void init() {
        auth = FirebaseAuth.getInstance();
        databaseReferenceClass = FirebaseDatabase.getInstance().getReference("Classrooms");
        user = auth.getCurrentUser();
        teacher = findViewById(R.id.Teacher);
        student = findViewById(R.id.Student);
        databaseReferenceClass.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Classroom> arrayList = new ArrayList<>();
                for(DataSnapshot classSnap : dataSnapshot.getChildren()){
                    Log.d("Debug", "onDataChange: "+classSnap);
                    Classroom cls = classSnap.getValue(Classroom.class);
                    arrayList.add(cls);
                }
                MainActivity.classroomArrayList = arrayList;
                Log.d("Debug", "onDataChange: "+MainActivity.classroomArrayList);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "Unable to fetch data :(", Toast.LENGTH_SHORT).show();
            }
        });

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
}