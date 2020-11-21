package com.example.labnew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.labnew.Database.MessageDatabaseController;

public class Teacher extends AppCompatActivity {
    TextView Welcome;
    EditText Subject,Message;
    Button Save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);
        Intent i1 = getIntent();
        String WelcomeText = "Welcome "+ i1.getStringExtra("Username");

        Welcome=findViewById(R.id.WelcomeString);
        Subject=findViewById(R.id.EditTextSubject);
        Message=findViewById(R.id.EditTextMessage);
        Welcome.setText(WelcomeText);
        Save=findViewById(R.id.button4);
        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveMEssageDetails();

            }
        });

    }

    public void SaveMEssageDetails(){
        Subject=findViewById(R.id.EditTextSubject);
        Message=findViewById(R.id.EditTextMessage);
        Intent i1 = getIntent();
        String Username=i1.getStringExtra("Username");
        MessageDatabaseController MSDB = new MessageDatabaseController(this);
        long exec= MSDB.SaveMEssage(Username, Subject.getText().toString(),Message.getText().toString());
        if (exec > 0 ){
            Toast.makeText(this, "Message Added", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }


    }

}