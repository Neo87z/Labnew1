package com.example.labnew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.labnew.Database.MessageDatabaseController;

import java.util.ArrayList;
import java.util.List;

public class Message extends AppCompatActivity {
    TextView Subject;
    EditText MessageBody1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        Intent i1 = getIntent();
        String Position = i1.getStringExtra("ID").toString();
        MessageDatabaseController MSDB = new MessageDatabaseController(getApplicationContext());
        List MyArrayMessages= new ArrayList();
        MyArrayMessages=MSDB.GetMessageByID(Position);
        Subject=findViewById(R.id.Subject);
        MessageBody1=findViewById(R.id.MessageView);
        Subject.setText(MyArrayMessages.get(0).toString());
        MessageBody1.setText(MyArrayMessages.get(1).toString());
    }
}