package com.example.labnew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.labnew.Database.UserDatabaseManagement;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText user,pass;
    Button btn,btn1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = findViewById(R.id.EditTextUserName);
        pass = findViewById(R.id.EditTextpassowrd);
        btn = findViewById(R.id.LoginButton);
        btn1 = findViewById(R.id.RegisterButton);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1= new Intent(getApplicationContext(),Register.class);
                startActivity(i1);
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckLoginDetails();
            }
        });


    }

    public void CheckLoginDetails(){
        user = findViewById(R.id.EditTextUserName);
        pass = findViewById(R.id.EditTextpassowrd);

        UserDatabaseManagement UB= new UserDatabaseManagement(getApplicationContext());
        List MyData= new ArrayList();
        MyData=UB.CheckLoginDetails(user.getText().toString(),pass.getText().toString());
        if(MyData.size() == 0){
            Toast.makeText(this, "Invalid Login Details", Toast.LENGTH_SHORT).show();
        }else if(MyData.get(1).equals("Teacher")){
            Toast.makeText(this, "Logged In As a Teacher", Toast.LENGTH_SHORT).show();
            Intent i1 = new Intent(getApplicationContext(),Teacher.class);
            i1.putExtra("Username",user.getText().toString());
            startActivity(i1);

        }else{
            Toast.makeText(this, "Logged In as A Student ", Toast.LENGTH_SHORT).show();
            Intent i1 = new Intent(getApplicationContext(),Student.class);
            i1.putExtra("Username",user.getText().toString());
            startActivity(i1);
        }

    }

}