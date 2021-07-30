package com.ajit.github;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText username;
    Button search;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username=(EditText)findViewById(R.id.mainuser);
        search=(Button)findViewById(R.id.Button1);


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = username.getText().toString();
                if (TextUtils.isEmpty(value)) {
                    Toast.makeText(MainActivity.this, "Please enter user name", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent i = new Intent(MainActivity.this, ProfileActivity.class);
                    i.putExtra("username", value);
                    startActivity(i);
                }

            }
        });
    }
}