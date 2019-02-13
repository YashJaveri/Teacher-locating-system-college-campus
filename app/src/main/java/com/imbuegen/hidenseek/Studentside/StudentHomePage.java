package com.imbuegen.hidenseek.Studentside;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.imbuegen.hidenseek.Adapters.StudentAdapter;
import com.imbuegen.hidenseek.Models.Teacher;
import com.imbuegen.hidenseek.TeacherList;

import java.util.ArrayList;
import java.util.List;

public class StudentHomePage extends AppCompatActivity {

    private ListView listView;
    DatabaseReference databaseReference;
    ArrayList<Teacher> teacherList;
    final String TAG = "Debug";
    private Boolean exists = false;
    StudentAdapter studentAdapter;

    @Override
    protected void onResume() {
        super.onResume();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                teacherList = new ArrayList<>();
                for(DataSnapshot teacherSnapshot : dataSnapshot.getChildren()){
                    Teacher teacher= teacherSnapshot.getValue(Teacher.class);
                    teacherList.add(teacher);
                }
                if(!exists) {
                    studentAdapter  = new StudentAdapter(StudentHomePage.this, teacherList);
                    listView.setAdapter(studentAdapter);
                    exists = true;
                }
                else
                    studentAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(StudentHomePage.this, "Unable to fetch data :(", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
