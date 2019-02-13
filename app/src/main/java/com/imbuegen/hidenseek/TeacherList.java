package com.imbuegen.hidenseek;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.imbuegen.hidenseek.Adapters.StudentAdapter;
import com.imbuegen.hidenseek.Models.Teacher;
import java.util.ArrayList;

public class TeacherList extends AppCompatActivity {
    private ListView listView;
    DatabaseReference databaseReference;
    ArrayList<Teacher>teacherList;
    int flag = 0;
    final String TAG = "Debug";

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
                teacherList = new ArrayList<>();
                for(DataSnapshot teacherSnapshot : dataSnapshot.getChildren()){
                    Teacher teacher= teacherSnapshot.getValue(Teacher.class);
                    Log.d(TAG, "onDataChange: " + teacher);
                    teacherList.add(teacher);
                }
                StudentAdapter studentAdapter=new StudentAdapter(TeacherList.this,teacherList);
                listView.setAdapter(studentAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(TeacherList.this, "Registration Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
