package com.imbuegen.hidenseek;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.imbuegen.hidenseek.Adapters.StudentAdapter;
import com.imbuegen.hidenseek.Models.Teacher;

import java.util.ArrayList;
import java.util.List;

public class TeacherList extends AppCompatActivity {
    private ListView listView;
    DatabaseReference databaseReference;
    List<Teacher>teacherList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_list);

        listView=findViewById(R.id.list_view);
        databaseReference= FirebaseDatabase.getInstance().getReference("Teacher");
        teacherList=new ArrayList<>();
    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot teacherSnapshot : dataSnapshot.getChildren()){
                    Teacher teacher= teacherSnapshot.getValue(Teacher.class);
                    teacherList.add(teacher);
                }
                StudentAdapter studentHomePage=new StudentAdapter(TeacherList.this,teacherList);
                listView.setAdapter(studentHomePage);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
