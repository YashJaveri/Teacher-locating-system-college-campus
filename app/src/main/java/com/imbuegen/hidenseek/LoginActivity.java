package com.imbuegen.hidenseek;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.imbuegen.hidenseek.Teacherside.TeacherHomePage;

public class LoginActivity extends AppCompatActivity {
    EditText email_login, password_login;
    String email, password;
    FirebaseAuth auth;

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
                Intent intent = new Intent(LoginActivity.this, TeacherHomePage.class);
                startActivity(intent);
                finish();
            }
        });

    }


    private void notRegistered() {
        Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
        startActivity(intent);
        finish();
    }
}
