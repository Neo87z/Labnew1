package com.example.labnew;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.labnew.Database.UserDatabaseManagement;

public class Register extends AppCompatActivity {

    EditText username,password;
    Button btn;
    CheckBox cT,CS;
    String Type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username = findViewById(R.id.EditTextUsername);
        password = findViewById(R.id.EditTextpassowrd);
        cT = findViewById(R.id.TeacherCheckBox);
        CS = findViewById(R.id.StudentCheckbox);
        btn = findViewById(R.id.RegisterButton1);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddUser();
            }
        });
    }

    public void AddUser(){
        username = findViewById(R.id.EditTextUsername);
        password = findViewById(R.id.EditTextpassword);
        cT = findViewById(R.id.TeacherCheckBox);
        CS = findViewById(R.id.StudentCheckbox);
        if (cT.isChecked() == true){
            Type="Teacher";
        }else{
            Type="Student";
        }
        UserDatabaseManagement us = new UserDatabaseManagement(getApplicationContext());
        long exec=us.AddUser(username.getText().toString(),Type,password.getText().toString());
        if(exec > 0){
            Toast.makeText(this, "User Added", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Fail", Toast.LENGTH_SHORT).show();
        }


    }
}