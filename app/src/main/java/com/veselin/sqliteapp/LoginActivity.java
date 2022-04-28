package com.veselin.sqliteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        DataBaseHelper dataBaseHelper = new DataBaseHelper(LoginActivity.this);
        //Get login data na try to login
        try {
            findViewById(R.id.login_btn).setOnClickListener(view ->{
                if(dataBaseHelper.checkUser(((EditText)findViewById(R.id.username_input)).getText().toString(),
                        ((EditText)findViewById(R.id.password_input)).getText().toString()))
                    startActivity( new Intent(LoginActivity.this, MainActivity.class).putExtra("username", ((EditText)findViewById(R.id.username_input)).getText().toString()));
            });
        }catch (Error e){
            Toast.makeText(this, "Please try again!", Toast.LENGTH_SHORT).show();
        }
        findViewById(R.id.sign_up_btn).setOnClickListener(view -> startActivity( new Intent(LoginActivity.this, RegisterActivity.class)));
    }
}