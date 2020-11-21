package com.example.labnew.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class UserDatabaseManagement extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="NewDB1.Db";
    public UserDatabaseManagement( Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String Query=
                "CREATE TABLE " + UserMaster.User.TABLE_NAME + " ("+
                        UserMaster.User._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        UserMaster.User.COLUMN_NAME_USERNAME + " TEXT," +
                        UserMaster.User.COLUMN_NAME_PASSWORD + " TEXT," +
                        UserMaster.User.COLUMN_NAME_TYPE + " TEXT)";
        db.execSQL(Query);
        String Quer2=
                "CREATE TABLE " + MessageMaster.Message.TABLE_NAME + " ("+
                        MessageMaster.Message._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        MessageMaster.Message.COLUMN_NAME_USERNAME + " TEXT," +
                        MessageMaster.Message.COLUMN_NAME_SUBJECT + " TEXT," +
                        MessageMaster.Message.COLUMN_NAME_MESSAGE + " TEXT)";
        db.execSQL(Quer2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }
    public long AddUser(String Username, String Type, String Password){
        String x;

        SQLiteDatabase DB= getWritableDatabase();
        ContentValues CV= new ContentValues();
        CV.put(UserMaster.User.COLUMN_NAME_USERNAME,Username);
        CV.put(UserMaster.User.COLUMN_NAME_TYPE,Type);
        CV.put(UserMaster.User.COLUMN_NAME_PASSWORD,Password);

        long Exec= DB.insert(UserMaster.User.TABLE_NAME,null,CV);
        return Exec;
    }


    public List CheckLoginDetails(String Username, String Password1){
        SQLiteDatabase DB= getReadableDatabase();

        String [] Projection={
                UserMaster.User.COLUMN_NAME_USERNAME,
                UserMaster.User.COLUMN_NAME_PASSWORD,
                UserMaster.User.COLUMN_NAME_TYPE
        };
        String Selection=UserMaster.User.COLUMN_NAME_USERNAME + " Like ?";
        String [] Args ={ Username };

        Cursor cursor= DB.query(
                UserMaster.User.TABLE_NAME,
                Projection,
                Selection,
                Args,
                null,
                null,
                null
                );

        List UserData= new ArrayList();

        String Type;
        String Passowrd;

        while (cursor.moveToNext()){
            Passowrd=cursor.getString(cursor.getColumnIndexOrThrow(UserMaster.User.COLUMN_NAME_PASSWORD));
            if(Passowrd.equals(Password1)){
                Type=cursor.getString(cursor.getColumnIndexOrThrow(UserMaster.User.COLUMN_NAME_TYPE));
                UserData.add(Username);
                UserData.add(Type);
            }

        }


        return UserData;




    }


}
