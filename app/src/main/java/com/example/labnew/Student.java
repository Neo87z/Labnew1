package com.example.labnew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.labnew.Database.MessageDatabaseController;

import java.util.ArrayList;
import java.util.List;

public class Student extends AppCompatActivity {

    TextView Welcome;
    ListView MessageList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        Intent i1 = getIntent();
        String WelcomeText = "Welcome "+ i1.getStringExtra("Username");

        Welcome=findViewById(R.id.textView3);
        Welcome.setText(WelcomeText);
        MessageList=findViewById(R.id.SubjectList);
        MessageDatabaseController MSDB = new MessageDatabaseController(getApplicationContext());
        List MySubjectList= new ArrayList();
        MySubjectList=MSDB.GetAllMessageSubject();
        ArrayAdapter arrayAdapter= new ArrayAdapter(this, android.R.layout.simple_list_item_1,MySubjectList);
        MessageList.setAdapter(arrayAdapter);

        MessageList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Student.this, "Position is "+position, Toast.LENGTH_SHORT).show();
                position++;
                String pos=Integer.toString(position);
                Intent i1= new Intent(getApplicationContext(),Message.class);
                i1.putExtra("ID",pos);
                startActivity(i1);
            }
        });





    }
}