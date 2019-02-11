package com.imbuegen.hidenseek;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
    private EditText email_login,password_login;
    private Button login;
    private TextView not_register;
    String email,password;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState,@Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);


    }

    @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.login);
        setListeners();
    }

    private void init() {
        //initialise stuff like views, lists etc
    }

    private void setListeners() {
        //For LOGIN:
        //call nullChecker()
        login=(Button)findViewById(R.id.btn_login);
        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
               nullChecker();
            }
        });
        not_register=(TextView)findViewById(R.id.not_registered);
        not_register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                notRegistered();
            }
        });
        //For NotRegistered:
        //call notRegistered()
    }

    private void nullChecker(){
        //read all edit texts Show toast accordingly
        email_login=(EditText)findViewById(R.id.editTxt_email);
        password_login=(EditText)findViewById(R.id.editTxt_password);
        email=email_login.getText().toString();
        password=password_login.getText().toString();

        if (email.equals("")){
            Toast.makeText(this, "Enter email", Toast.LENGTH_SHORT).show();
        }
        else if (password.equals("")){
            Toast.makeText(this, "Enter password", Toast.LENGTH_SHORT).show();
        }
        else{
            login(email,password);
        }

        //call login()
    }

    private void login(String email, String password){
        //Authenticate here
        //redirect to teachers' screen(ill design later)
    }


    private void notRegistered(){
        //REPLACE SCREEN with register
        Intent intent =new Intent(LoginActivity.this,RegistrationActivity.class);
        startActivity(intent);
    }
}
