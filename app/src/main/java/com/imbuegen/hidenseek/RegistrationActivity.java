package com.imbuegen.hidenseek;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.imbuegen.hidenseek.Teacherside.TeacherHomePage;

public class RegistrationActivity extends AppCompatActivity {
    private EditText name, email, number, department, confirm_password, password;
    private String name_str, email_str, number_str, department_str, password_str, confrmPassword_str;
    private FirebaseAuth Auth;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.registration);
        init();
        Auth = FirebaseAuth.getInstance();
        setListeners();
    }

    private void init() {
        //Variables
        name = findViewById(R.id.editTxt_fullName);
        email = findViewById(R.id.editTxt_email);
        number = findViewById(R.id.editTxt_mobileNumber);
        department = findViewById(R.id.editTxt_department);
        password = findViewById(R.id.editTxt_password);
        confirm_password = findViewById(R.id.editTxt_confirmPassword);
    }

    private void setListeners() {
        Button register_btn = findViewById(R.id.btn_register);
        register_btn.setOnClickListener(arg0 -> nullChecker());

        //For AlreadyRegistered:
        TextView already_registered = findViewById(R.id.already_user);
        already_registered.setOnClickListener(arg0 -> alreadyRegistered());


    }

    private void confirmPassword() {
        if (password_str.equals(confrmPassword_str)) {
            register(email_str, password_str, name_str, number_str, department_str);
        } else {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
        }
    }

    private void nullChecker() {
        name_str = name.getText().toString();
        email_str = email.getText().toString();
        department_str = department.getText().toString();
        password_str = password.getText().toString();
        number_str = number.getText().toString();
        confrmPassword_str = confirm_password.getText().toString();

        if (name_str.equals(""))
            Toast.makeText(this, "Enter Name", Toast.LENGTH_SHORT).show();
        else if (email_str.equals(""))
            Toast.makeText(this, "Enter Email", Toast.LENGTH_SHORT).show();
        else if (department_str.equals(""))
            Toast.makeText(this, "Enter Department", Toast.LENGTH_SHORT).show();
        else if (password_str.equals(""))
            Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show();
        else if (confrmPassword_str.equals(""))
            Toast.makeText(this, "Enter Password Again", Toast.LENGTH_SHORT).show();
        else if (number_str.equals("")) {
            number_str = "Not Provided :(";
            confirmPassword();
        }
        else
            confirmPassword();
    }

    private void register(String email, String password, String name, String number, String department) {

        Auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(RegistrationActivity.this, task -> {
            if (!task.isSuccessful())
                Toast.makeText(RegistrationActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
            else{
                Intent myIntent = new Intent(RegistrationActivity.this, TeacherHomePage.class);
                startActivity(myIntent);
                finish();
            }
        });
    }

    private void alreadyRegistered() {
        Intent myIntent = new Intent(RegistrationActivity.this, LoginActivity.class);
        startActivity(myIntent);
        finish();
    }

}
