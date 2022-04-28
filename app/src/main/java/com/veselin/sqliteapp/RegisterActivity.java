package com.veselin.sqliteapp;

import androidx.annotation.IntRange;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {
    UserModel user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        loginBtn();
        registerBtn();
    }
    //getting the data from the input fields and creating user
    private void createUser(){
        if(((EditText) findViewById(R.id.username_input)).getText().toString().isEmpty()
                || ((EditText) findViewById(R.id.password_input)).getText().toString().isEmpty()
                || ((EditText) findViewById(R.id.email_input)).getText().toString().isEmpty()) {
            Toast.makeText(this, "Please fill all of the fields!", Toast.LENGTH_SHORT).show();
        }else{
            String name = String.valueOf(((EditText) findViewById(R.id.username_input)).getText());
            String pass = String.valueOf(((EditText) findViewById(R.id.password_input)).getText());
            String email = String.valueOf(((EditText) findViewById(R.id.email_input)).getText());
            String gender = String.valueOf(((RadioButton) findViewById(((RadioGroup) findViewById(R.id.radioSex)).getCheckedRadioButtonId())).getText());
            user = new UserModel(name, email, pass, gender, -1);
            if (validate(user)) registerUser(user);
        }
    }
    private void loginBtn(){
        findViewById(R.id.login_btn).setOnClickListener(view -> startActivity(new Intent(RegisterActivity.this, LoginActivity.class)));
    }
    private void registerBtn(){
        findViewById(R.id.sign_up_btn).setOnClickListener(view -> createUser());
    }
    private Boolean validate(UserModel user){
        DataBaseHelper dataBaseHelper = new DataBaseHelper(RegisterActivity.this);
        Boolean isValid = true;
        //username validations
        if(Pattern.compile("[ !\"#$%&'()*+,-./:;<=>?@^_`{|}~]").matcher(user.getUsername()).find()) {
            Toast.makeText(this, "Name mustn't contain any special characters!", Toast.LENGTH_SHORT).show();
            isValid = false;
        }else if(Character.isLowerCase(user.getUsername().charAt(0))){
            Toast.makeText(this, "Name must start with capital letter!", Toast.LENGTH_SHORT).show();
            isValid = false;
        }else if(user.getUsername().length() <= 4 || user.getUsername().length() >= 8){
            Toast.makeText(this, "Username must be between 4 and 8 characters long!", Toast.LENGTH_SHORT).show();
            isValid = false;
        }else if(dataBaseHelper.checkUser(user.getUsername())){
            Toast.makeText(this, "This username is already taken!", Toast.LENGTH_SHORT).show();
            isValid = false;
        }
        //email validations
        else if(!user.getEmail().matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$")){
            isValid = false;
            Toast.makeText(this, "Invalid Email", Toast.LENGTH_SHORT).show();
        }
        //password validation
        else if(!user.getPass().matches( "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[ !\"#$%&'()*+,-./:;<=>?@^_`{|}~])(?=\\S+$).{5,9}$")) {
            isValid = false;
            Toast.makeText(this, "Invalid Password", Toast.LENGTH_SHORT).show();
        }
        return  isValid;
    }
    private void registerUser(UserModel user){
        //database code
        DataBaseHelper dataBaseHelper = new DataBaseHelper(this);
        dataBaseHelper.addOne(user);
        startActivity(new Intent(RegisterActivity.this, MainActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK)
                .putExtra("username", ((EditText)findViewById(R.id.username_input)).getText().toString()));
        finish();
    }
}