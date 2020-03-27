package com.example.triviaapp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

import static android.icu.text.MessagePattern.ArgType.SELECT;

public class DBHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "usersdb";
    private static final String TABLE_Users = "userdetails";
    private static final String KEY_ID = "id";
    private static final String KEY_time = "time";
    private static final String KEY_NAME = "name";
    private static final String KEY_Ans1 = "answer1";
    private static final String KEY_Ans2 = "answer2";
    public DBHelper(Context context){
        super(context,DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_TABLE = "CREATE TABLE " + TABLE_Users + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +KEY_time+ " TEXT,"+ KEY_NAME + " TEXT,"
                + KEY_Ans1 + " TEXT,"
                + KEY_Ans2 + " TEXT"+ ")";
        db.execSQL(CREATE_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        // Drop older table if exist
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Users);
        // Create tables again
        onCreate(db);
    }
    // **** CRUD (Create, Read, Update, Delete) Operations ***** //

    // Adding new User Details
    void insertUserDetails( String time, String name, String answer1, String answer2){
        //Get the Data Repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();
        //Create a new map of values, where column names are the keys
        ContentValues cValues = new ContentValues();
       //  cValues.put(KEY_ID, id);
        cValues.put(KEY_time, time);
        cValues.put(KEY_NAME, name);
        cValues.put(KEY_Ans1, answer1);
        cValues.put(KEY_Ans2, answer2);
        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(TABLE_Users,null, cValues);
        //db.update(TABLE_Users,KEY_ID,"name = ?",new String[]{name});
       // db.update(TABLE_Users, cValues, id +"="+id, null);
        // db.insert(TABLE_Users, null, cValues);

        db.close();
    }
    // Get User Details
    public ArrayList<HashMap<String, String>> GetUsers(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        String query = "SELECT  time, name, answer1, answer2 FROM "+ TABLE_Users;
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            HashMap<String,String> user = new HashMap<>();
           // user.put("id",cursor.getString(cursor.getColumnIndex(KEY_ID)));
            user.put("time",cursor.getString(cursor.getColumnIndex(KEY_time)));
            user.put("name",cursor.getString(cursor.getColumnIndex(KEY_NAME)));
            user.put("answer1",cursor.getString(cursor.getColumnIndex(KEY_Ans1)));
            user.put("answer2",cursor.getString(cursor.getColumnIndex(KEY_Ans2)));
            userList.add(user);
        }
        return  userList;
    }
    // Get User Details based on userid
    public ArrayList<HashMap<String, String>> GetUserByUserId(int userid){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        String query = "SELECT name, answer1, answer2 FROM "+ TABLE_Users;
        Cursor cursor = db.query(TABLE_Users, new String[]{KEY_NAME, KEY_Ans1, KEY_Ans2}, KEY_ID+ "=?",new String[]{String.valueOf(userid)},null, null, null, null);
        if (cursor.moveToNext()){
            HashMap<String,String> user = new HashMap<>();
            //user.put("id",cursor.getString(cursor.getColumnIndex(KEY_ID)));
            user.put("name",cursor.getString(cursor.getColumnIndex(KEY_NAME)));
            user.put("answer1",cursor.getString(cursor.getColumnIndex(KEY_Ans1)));
            user.put("answer2",cursor.getString(cursor.getColumnIndex(KEY_Ans2)));
            userList.add(user);
        }
        return  userList;
    }
    // Delete User Details
    public void DeleteUser(int userid){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_Users, KEY_ID+" = ?",new String[]{String.valueOf(userid)});
        db.close();
    }
    // Update User Details
    public int UpdateUserDetails(String answer1, String answer2, int id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cVals = new ContentValues();
        cVals.put(KEY_Ans1, answer1);
        cVals.put(KEY_Ans2, answer2);
        int count = db.update(TABLE_Users, cVals, KEY_ID+" = ?",new String[]{String.valueOf(id)});
        return  count;
    }
}