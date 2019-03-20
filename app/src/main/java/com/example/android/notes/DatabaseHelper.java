package com.example.android.notes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import static java.sql.Types.NULL;

public class DatabaseHelper extends SQLiteOpenHelper {

   private static final int DATABASE_VERSION = 3;
   private static final String DATABASE_NAME = "list.db";
   public static final String TABLE_NAME= "breakfast_table";
   public static final String TABLE_NAME1= "lunch_table";
   public static final String TABLE_NAME2= "dinner_table";
    public static final String COL_1 = "id ";
    public static final String COL_2 = "breakfast ";
    public static final String  COL_3 = "date ";




    public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

     @Override

    public void onCreate(SQLiteDatabase db) {


         String query = " CREATE TABLE " + TABLE_NAME + "(" +
                 COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                 COL_2 + " TEXT NOT NULL, " +
                 COL_3 + "TEXT NOT NULL );";

         String query1 = "CREATE TABLE " + TABLE_NAME1 + "(" +
                 COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                 COL_2 + " TEXT NOT NULL, " +
                 COL_3 + "TEXT NOT NULL );";

         String query2 = "CREATE TABLE " + TABLE_NAME2 + "(" +
                 COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                 COL_2 + " TEXT NOT NULL, " +
                 COL_3 + "TEXT NOT NULL );";

db.execSQL(query);
db.execSQL(query1);
db.execSQL(query2);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(" DROP TABLE IF EXISTS " +  TABLE_NAME);
        db.execSQL(" DROP TABLE IF EXISTS " +  TABLE_NAME1);
        db.execSQL(" DROP TABLE IF EXISTS " +  TABLE_NAME2);

        onCreate(db);

    }

    //Adding data to breakfast table

    public  boolean insertData(String breakfast, String date) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();  //class in android to store values and insert values in single stmnt
        values.put(COL_2, breakfast);
       values.put(COL_3, date);
        long result = db.insert(TABLE_NAME,null ,values);

        if(result == -1)
            return false;
        else
            return true;
    }

    //Adding data to lunch table

    public boolean insertData1(String breakfast, String date) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();  //class in android to store values and insert values in single stmnt
        values.put(COL_2, breakfast);
        values.put(COL_3, date);
        long result = db.insert(TABLE_NAME1,null ,values);

        if(result == -1)
            return false;
        else
            return true;

    }

    //Adding data to dinner table

    public boolean insertData2(String breakfast, String date) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();  //class in android to store values and insert values in single stmnt
        values.put(COL_2, breakfast);
        values.put(COL_3, date);
        long result = db.insert(TABLE_NAME2,null ,values);

        if(result == -1)
            return false;
        else
            return true;

    }

    public Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?",new String[] {id});
    }

    public Integer deleteData1 (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME1, "ID = ?",new String[] {id});
    }

    public Integer deleteData2 (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME2, "ID = ?",new String[] {id});
    }


    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }

    public Cursor getAllData1() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME1, null);
        return res;
    }

    public Cursor getAllData2() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME2, null);
        return res;
    }


}

