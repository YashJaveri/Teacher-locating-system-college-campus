package com.imbuegen.hidenseek;

import android.app.Activity;
import android.content.Context;
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

public class StudentHomePage extends ArrayAdapter<Teacher> {
    private Activity context;
    private List<Teacher> teacherList;



    public StudentHomePage(Activity context, List<Teacher> teacherList){
        super(context,R.layout.list_view,teacherList);
        this.context=context;
        this.teacherList=teacherList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater =context.getLayoutInflater();
        View listView=inflater.inflate(R.layout.list_view,null,true);

        TextView latitude =(TextView)listView.findViewById(R.id.latitude);
        TextView longitude =(TextView)listView.findViewById(R.id.longitude);
        TextView name =(TextView)listView.findViewById(R.id.name);

        Teacher teacher=teacherList.get(position);
        latitude.setText(teacher.getLatitude());
        longitude.setText(teacher.getLongitude());
        name.setText(teacher.getName());

        return listView;


    }
}
