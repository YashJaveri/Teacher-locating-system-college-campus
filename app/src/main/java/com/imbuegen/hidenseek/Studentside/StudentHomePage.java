package com.imbuegen.hidenseek.Studentside;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.widget.ListView;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.imbuegen.hidenseek.Adapters.StudentAdapter;
import com.imbuegen.hidenseek.MainActivity;
import com.imbuegen.hidenseek.Models.Classroom;
import com.imbuegen.hidenseek.Models.Teacher;
import com.imbuegen.hidenseek.R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class StudentHomePage extends AppCompatActivity {

    private ListView listView;
    DatabaseReference databaseReferenceTeach, databaseReferenceClass;
    ArrayList<Teacher> teacherList;
    ArrayList<Double> arrayDistance;
    final String TAG = "Yash";
    private Boolean exists = false;
    StudentAdapter studentAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.student_home);
        Objects.requireNonNull(getSupportActionBar()).setElevation(0);

        init();
    }

    private void init() {
        listView  = findViewById(R.id.student_list);
        Log.d(TAG, "init: "+listView);
        databaseReferenceTeach = FirebaseDatabase.getInstance().getReference("Teacher");
        databaseReferenceClass = FirebaseDatabase.getInstance().getReference("Classrooms");
        arrayDistance = new ArrayList<>();
        fetch();
    }

    private void fetch(){
        databaseReferenceTeach.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                teacherList = new ArrayList<>();
                for(DataSnapshot teacherSnapshot : dataSnapshot.getChildren()){
                    Teacher teacher= teacherSnapshot.getValue(Teacher.class);
                    teacherList.add(teacher);
                }
                Log.d(TAG, "onDataChange: "+teacherList.size());
                for(Teacher t: teacherList){
                    arrayDistance = new ArrayList<>();
                    for(Classroom c: MainActivity.classroomArrayList){
                        double distance = Math.pow(Math.pow((t.getAltitude() - c.getAltitude()), 2) + Math.pow((t.getLatitude() - c.getlatitude()), 2) + Math.pow((t.getLongitude() - c.getLongitude()), 2), 0.5);
                        arrayDistance.add(distance);
                    }
                    Double min = Collections.min(arrayDistance);
                    Classroom clsroom = MainActivity.classroomArrayList.get(arrayDistance.indexOf(min));
                    Log.d(TAG, "onDataChange: "+clsroom.getName());
                    t.setCls(clsroom);
                }
                    studentAdapter  = new StudentAdapter(StudentHomePage.this, teacherList);
                    listView.setAdapter(studentAdapter);
                    exists = true;
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(StudentHomePage.this, "Unable to fetch data :(", Toast.LENGTH_SHORT).show();
            }
        });
    }
}