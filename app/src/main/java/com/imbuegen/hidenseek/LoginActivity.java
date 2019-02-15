package com.imbuegen.hidenseek;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseError;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.imbuegen.hidenseek.Models.Teacher;
import com.imbuegen.hidenseek.Teacherside.TeacherHomePage;

public class LoginActivity extends AppCompatActivity {
    EditText email_login, password_login;
    String email, password;
    Intent myIntent;
    FirebaseAuth auth;
    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.login);
        init();
        setListeners();
    }

    private void init() {
        auth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Teacher");

        myIntent = new Intent(LoginActivity.this, TeacherHomePage.class);
        email_login = findViewById(R.id.editTxt_email);
        password_login = findViewById(R.id.editTxt_password);
    }

    private void setListeners() {
        Button login = findViewById(R.id.btn_login);
        login.setOnClickListener(arg0 -> nullChecker());

        TextView not_register = findViewById(R.id.txt_not_registered);
        not_register.setOnClickListener(arg0 -> notRegistered());
    }

    private void nullChecker() {
        email = email_login.getText().toString();
        password = password_login.getText().toString();
        if (email.equals("")) {
            Toast.makeText(this, "Enter email", Toast.LENGTH_SHORT).show();
        } else if (password.equals("")) {
            Toast.makeText(this, "Enter password", Toast.LENGTH_SHORT).show();
        } else {
            login(email, password);
        }
    }

    private void login(String email, String password) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, task -> {
            if (!task.isSuccessful())
                    Toast.makeText(LoginActivity.this, "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
            else {
                firebaseUser = auth.getCurrentUser();
                assert firebaseUser != null;
                databaseReference.child(firebaseUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Teacher teacher = snapshot.getValue(Teacher.class);
                        Log.d("Login", "onDataChange: "+teacher.getName());
                        myIntent.putExtra("Teacher", teacher);
                        startActivity(myIntent);
                        finish();
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(LoginActivity.this, "Fail to fetch data :(. Retry",
                                Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }


    private void notRegistered() {
        Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
        startActivity(intent);
        finish();
    }
}
