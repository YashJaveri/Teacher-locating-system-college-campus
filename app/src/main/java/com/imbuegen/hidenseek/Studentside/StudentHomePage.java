package com.imbuegen.hidenseek.Studentside;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.imbuegen.hidenseek.Adapters.StudentAdapter;
import com.imbuegen.hidenseek.Models.Classroom;
import com.imbuegen.hidenseek.Models.Teacher;
import com.imbuegen.hidenseek.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class StudentHomePage extends AppCompatActivity {

    private ListView listView;
    DatabaseReference databaseReferenceTeach, databaseReferenceClass;
    ArrayList<Teacher> teacherList;
    ArrayList<Classroom> classList;
    ArrayList<Double> arrayDistance;
    HashMap<Teacher, Classroom> teacherHash;
    final String TAG = "Debug";
    private Boolean exists = false;
    StudentAdapter studentAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.student_home);
    }

    @Override
    protected void onResume() {
        super.onResume();

        init();
    }

    private void init() {
        listView  = findViewById(R.id.student_list);
        Log.d(TAG, "init: "+listView);
        databaseReferenceTeach = FirebaseDatabase.getInstance().getReference("Teacher");
        databaseReferenceClass = FirebaseDatabase.getInstance().getReference("Classrooms");
        arrayDistance = new ArrayList<>();
        teacherHash = new HashMap<>();
        fetch();
    }

    private void fetch(){
        databaseReferenceClass.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                classList = new ArrayList<>();
                for(DataSnapshot classSnap : dataSnapshot.getChildren()){
                    Log.d(TAG, "onDataChange: "+classSnap);
                    Classroom cls = classSnap.getValue(Classroom.class);
                    classList.add(cls);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(StudentHomePage.this, "Unable to fetch data :(", Toast.LENGTH_SHORT).show();
            }
        });

        databaseReferenceTeach.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                teacherList = new ArrayList<>();
                for(DataSnapshot teacherSnapshot : dataSnapshot.getChildren()){
                    Teacher teacher= teacherSnapshot.getValue(Teacher.class);
                    teacherList.add(teacher);
                }
                for(Teacher t: teacherList){
                    arrayDistance = new ArrayList<>();
                    for(Classroom c: classList){
                        double distance = Math.pow(Math.pow((t.getAltitude() - c.getAltitude()), 2) + Math.pow((t.getLatitude() - c.getlatitude()), 2) + Math.pow((t.getLongitude() - c.getLongitude()), 2), 0.5);
                        arrayDistance.add(distance);
                    }
                    Double min = Collections.min(arrayDistance);
                    Classroom clsroom = classList.get(arrayDistance.indexOf(min));
                    Log.d(TAG, "onDataChange: "+clsroom);
                    teacherHash.put(t, clsroom);
                }
                if(!exists) {
                    //studentAdapter  = new StudentAdapter(StudentHomePage.this, teacherList);
                    //listView.setAdapter(studentAdapter);
                    exists = true;
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(StudentHomePage.this, "Unable to fetch data :(", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
