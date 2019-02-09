package com.imbuegen.hidenseek;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.Objects;

public class RegisterationActivity extends AppCompatActivity {
    //Variables

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        Objects.requireNonNull(getSupportActionBar()).setElevation(0);
        setContentView(R.layout.registeration);
    }

    @Override
    protected void onResume() {
        super.onResume();
        init();
        setListeners();
    }

    private void init() {
        //initialise stuff like views, lists etc
    }

    private void setListeners() {
        //For REGISTER:
        //call nullChecker()

        //For AlreadyRegistered:
        //call alreadyRegistered()
    }

    private void confirmPassword(){
        //Show toast on error else call register(email, pas.....)
    }

    private void nullChecker(){
        //read all edit texts
        //NOTE: if Mobile==null then mobileTxt="Not provided" anything else null Show toast accordingly(cz mobile numb is optional)
        //call confirmPassword()
    }

    private void register(String email, String password, String name, String mobile, String dept){
        //Authenticate here
        //Set user data(tutorial on whats app)
        //redirect to teachers' screen(ill design later)
    }

    private void alreadyRegistered(){
        //REPLACE SCREEN with login
    }

}
