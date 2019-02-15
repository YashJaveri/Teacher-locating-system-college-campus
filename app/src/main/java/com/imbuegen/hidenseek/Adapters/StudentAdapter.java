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
    private double startLatitude = 0.0, startLongitude = 0.0, endLatitude = 10.0, endLongitude = 10.0; //set later

    public StudentAdapter(Activity context, List<Teacher> teacherList){
        super(context,R.layout.teacher_item,teacherList);
        this.context=context;
        this.teacherList=teacherList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater =context.getLayoutInflater();
        View listView=inflater.inflate(R.layout.teacher_item,parent,false);

        TextView classroom = listView.findViewById(R.id.txt_classroom);
        TextView floor = listView.findViewById(R.id.txt_floor);
        TextView name = listView.findViewById(R.id.name);
        TextView email = listView.findViewById(R.id.txt_email);
        TextView dept = listView.findViewById(R.id.txt_dept);
        TextView number = listView.findViewById(R.id.txt_number);
        TextView busy = listView.findViewById(R.id.txt_busy);

        Teacher teacher=teacherList.get(position);
        classroom.setText(teacher.getCls().getName());
        String str = "Floor: " + Integer.toString(teacher.getCls().getFloor());
        floor.setText(str);
        name.setText(teacher.getName());
        email.setText(teacher.getEmail());
        number.setText(teacher.getNumber());
        dept.setText(teacher.getDepartment());
        if(startLatitude>teacher.getLatitude() || teacher.getLatitude()>endLatitude || startLongitude>teacher.getLongitude() && teacher.getLongitude()>endLongitude)
            busy.setText("Out of coverage");
        else if(teacher.getBusy()==0)
            busy.setText(" ");
        else if(teacher.getBusy()==1)
            busy.setText("Busy");

        return listView;


    }
}
