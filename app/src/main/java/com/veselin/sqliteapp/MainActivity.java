package com.veselin.sqliteapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initButtons();
        ((TextView) findViewById(R.id.welcome_txt)).setText("Welcome, " + getIntent().getStringExtra("username"));
    }

    private void initButtons() {
        findViewById(R.id.load_names_btn).setOnClickListener(view -> {
            DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);
            List<UserModel> users = dataBaseHelper.getAll();
            Adapter adapter = new Adapter(this, users);
            ((RecyclerView)findViewById(R.id.recycler_view)).setAdapter(adapter);
            ((RecyclerView)findViewById(R.id.recycler_view)).setHasFixedSize(true);
            ((RecyclerView)findViewById(R.id.recycler_view)).setLayoutManager(new LinearLayoutManager(this));
        });
        findViewById(R.id.logout_btn).setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, RegisterActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        });
    }

}