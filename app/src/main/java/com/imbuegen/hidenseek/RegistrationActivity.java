package com.imbuegen.hidenseek;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthSettings;
import com.google.firebase.auth.FirebaseUser;

import java.io.Console;
import java.util.Objects;

public class RegistrationActivity extends AppCompatActivity {
    //Variables
    private EditText name, email, number, department, password, confirm_password;
    private String name_str, email_str, number_str, department_str, password_str, confirm_password_str;
    private Button registerbtn;
    private TextView already_registered;
    private FirebaseAuth Auth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        setContentView(R.layout.registration);
        init();


    }

    @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.registration);
        Auth = FirebaseAuth.getInstance();
        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    Intent intent = new Intent(RegistrationActivity.this, TeacherHome.class);
                    startActivity(intent);
                    finish();
                    return;
                }
            }
        };
        Auth.addAuthStateListener(firebaseAuthListener);
        setListeners();
    }

    private void init() {
        //initialise stuff like views, lists etc
    }

    private void setListeners() {
        //For REGISTER:
        //call nullChecker()
        registerbtn = (Button) findViewById(R.id.btn_register);
        registerbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                nullChecker();
            }
        });

        //For AlreadyRegistered:
        //call alreadyRegistered()
        already_registered = (TextView) findViewById(R.id.already_user);
        already_registered.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {


                alreadyRegistered();

            }
        });


    }

    private void confirmPassword() {
        if (password_str.equals(confirm_password_str)) {
            register(email_str, password_str, name_str, number_str, department_str);
        } else {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
        }
        //Show toast on error else call register(email, pas.....)
    }

    private void nullChecker() {
        name = (EditText) findViewById(R.id.editTxt_fullName);

        email = (EditText) findViewById(R.id.editTxt_email);
        number = (EditText) findViewById(R.id.editTxt_mobileNumber);
        department = (EditText) findViewById(R.id.editTxt_department);
        password = (EditText) findViewById(R.id.editTxt_password);
        confirm_password = (EditText) findViewById(R.id.editTxt_confirmPassword);
        name_str = name.getText().toString();
        email_str = email.getText().toString();
        department_str = department.getText().toString();
        password_str = password.getText().toString();
        number_str = number.getText().toString();
        confirm_password_str = confirm_password.getText().toString();
        if (name_str.equals("")) {
            Toast.makeText(this, "Enter Name", Toast.LENGTH_SHORT).show();

        }
        else if (email_str.equals("")) {
            Toast.makeText(this, "Enter Email", Toast.LENGTH_SHORT).show();

        }
        else if (department_str.equals("")) {
            Toast.makeText(this, "Enter Department", Toast.LENGTH_SHORT).show();

        }
        else if (password_str.equals("")) {
            Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show();

        }
        else if (confirm_password_str.equals("")) {
            Toast.makeText(this, "Enter Password Again", Toast.LENGTH_SHORT).show();

        }


        //NOTE: if Mobile==null then mobileTxt="Not provided" anything else null Show toast accordingly(cz mobile numb is optional)
        else{
            confirmPassword();
        }
    }

    private void register(String email_str, String password_str, String name_str, String number_str, String department_str) {

        Auth.createUserWithEmailAndPassword(email_str, password_str).addOnCompleteListener(RegistrationActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) {
                    Toast.makeText(RegistrationActivity.this, "Not", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //Authenticate here
        //Set user data(tutorial on whats app)
        //redirect to teachers' screen(ill design later)
    }

    private void alreadyRegistered() {
        Intent myIntent = new Intent(RegistrationActivity.this, LoginActivity.class);
        startActivity(myIntent);
    }

}
