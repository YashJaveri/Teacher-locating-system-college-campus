package com.imbuegen.hidenseek.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.imbuegen.hidenseek.Models.Teacher;
import com.imbuegen.hidenseek.R;

import java.util.List;

public class StudentAdapter extends ArrayAdapter<Teacher> {
    private Activity context;
    private List<Teacher> teacherList;

    public StudentAdapter(Activity context, List<Teacher> teacherList){
        super(context,R.layout.list_view,teacherList);
        this.context=context;
        this.teacherList=teacherList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater =context.getLayoutInflater();
        View listView=inflater.inflate(R.layout.list_view,parent,false);

        TextView classroom = listView.findViewById(R.id.txt_classroom);
        TextView floor = listView.findViewById(R.id.txt_floor);
        TextView name = listView.findViewById(R.id.name);
        TextView email = listView.findViewById(R.id.txt_email);
        TextView dept = listView.findViewById(R.id.txt_dept);
        TextView number = listView.findViewById(R.id.txt_number);

        Teacher teacher=teacherList.get(position);
        classroom.setText(teacher.getCls().getName());
        String str = "Floor: " + Integer.toString(teacher.getCls().getFloor());
        floor.setText(str);
        name.setText(teacher.getName());
        email.setText(teacher.getEmail());
        number.setText(teacher.getNumber());
        dept.setText(teacher.getDepartment());

        return listView;


    }
}
