package com.example.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "Userdata.db", null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Userdetails(id Text Primary key, name Text , contact Text, email Text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop Table if exists Userdetails");
    }
    public  boolean insertuserdata(String id,String name, String contact, String email){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id",id);
        contentValues.put("name",name);
        contentValues.put("contact",contact);
        contentValues.put("email",email);
        long result = DB.insert("Userdetails",null,contentValues);
        if (result==-1){
            return false;
        }
        else{
            return true;
        }
    }
    public  boolean updateuserdata(String name, String contact, String email){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("contact",contact);
        contentValues.put("email",email);
        Cursor cursor = DB.rawQuery("Select * from Userdetails where name=?", new String[] {name});
        if (cursor.getCount()>0){
            long result = DB.update("Userdetails",contentValues,"name=?", new String[] {name});
            if (result==-1){
                return false;
            }
            else{
                return true;
            }
        }
        else {
            return false;
        }

    }


    public  boolean deleteuserdata(String name){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails where name=?", new String[] {name});
        if (cursor.getCount()>0){
            long result = DB.delete("Userdetails","name=?", new String[] {name});
            if (result==-1){
                return false;
            }
            else{
                return true;
            }
        }
        else {
            return false;
        }

    }



    public  Cursor getdata(){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails", null );
        return cursor;
    }
}
