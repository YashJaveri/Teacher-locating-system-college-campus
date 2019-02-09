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
}
