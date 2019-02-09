package com.imbuegen.hidenseek;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState,@Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        Objects.requireNonNull(getSupportActionBar()).setElevation(0);
        setContentView(R.layout.login);
    }

    private void init() {
        //initialise stuff like views, lists etc
    }

    private void setListeners() {
        //For LOGIN:
        //call nullChecker()

        //For NotRegistered:
        //call notRegistered()
    }

    private void nullChecker(){
        //read all edit texts Show toast accordingly
        //call login()
    }

    private void login(String email, String password){
        //Authenticate here
        //redirect to teachers' screen(ill design later)
    }


    private void alreadyRegistered(){
        //REPLACE SCREEN with register
    }
}
