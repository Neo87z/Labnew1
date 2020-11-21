package com.example.labnew.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MessageDatabaseController extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="NewDB1.Db";
    public MessageDatabaseController( Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long SaveMEssage(String Username, String Subject,String MEssage){
        SQLiteDatabase DB = getWritableDatabase();
        ContentValues CV= new ContentValues();
        CV.put(MessageMaster.Message.COLUMN_NAME_USERNAME,Username);
        CV.put(MessageMaster.Message.COLUMN_NAME_SUBJECT,Subject);
        CV.put(MessageMaster.Message.COLUMN_NAME_MESSAGE,MEssage);

        long Exec= DB.insert(MessageMaster.Message.TABLE_NAME,null,CV);
        return Exec;
    }

    public List GetAllMessageSubject(){
        SQLiteDatabase DB =getReadableDatabase();

        String [] Projection= {
                MessageMaster.Message.COLUMN_NAME_SUBJECT

        };

        Cursor curser=DB.query(
                MessageMaster.Message.TABLE_NAME,
                Projection,
                null,
                null,
                null,
                null,
                null

        );

        List MySubjects= new ArrayList();
        while (curser.moveToNext()){
            MySubjects.add(curser.getString(curser.getColumnIndexOrThrow(MessageMaster.Message.COLUMN_NAME_SUBJECT)));
        }

        return MySubjects;

    }
    public List GetMessageByID (String id){

        SQLiteDatabase DB= getReadableDatabase();
        String[] Projection ={
                MessageMaster.Message.COLUMN_NAME_SUBJECT,
                MessageMaster.Message.COLUMN_NAME_MESSAGE
        };

        String Selection= MessageMaster.Message._ID +" Like ?";
        String [] args={ id };
        Cursor curser=DB.query(
                MessageMaster.Message.TABLE_NAME,
                Projection,
                Selection,
                args,
                null,
                null,
                null
        );


        List DataRerived = new ArrayList();

        while(curser.moveToNext()){
            DataRerived.add(curser.getString(curser.getColumnIndex(MessageMaster.Message.COLUMN_NAME_SUBJECT)));
            DataRerived.add(curser.getString(curser.getColumnIndex(MessageMaster.Message.COLUMN_NAME_MESSAGE)));
        }

        return DataRerived;
    }
}
