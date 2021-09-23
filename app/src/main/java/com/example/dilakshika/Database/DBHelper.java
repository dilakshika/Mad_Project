package com.example.dilakshika.Database;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.dilakshika.MainActivity;
import com.example.dilakshika.Student;
import com.example.dilakshika.Teacher;

public class DBHelper extends SQLiteOpenHelper {
    private Context context;
    public DBHelper(Context context) {
        super(context, "final.db" , null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_ENTITY = " CREATE TABLE " + UserMaster.User.TABLE_NAME + ("(") + UserMaster.User.COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT ," + UserMaster.User.COL_2 + " TEXT ," + UserMaster.User.COL_3 + " TEXT ," + UserMaster.User.COL_4 + " TEXT)";

        db.execSQL(SQL_CREATE_ENTITY);
     }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void add(User user){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(UserMaster.User.COL_2,user.getUsername());
        values.put(UserMaster.User.COL_3,user.getPassword());
        values.put(UserMaster.User.COL_4,user.getType());

        long res = db.insert(UserMaster.User.TABLE_NAME,null,values);

        if(res > 0){
            Toast.makeText(this.context,"Insert successfully",Toast.LENGTH_LONG).show();
            context.startActivity(new Intent(context,MainActivity.class));
        }else
            Toast.makeText(this.context,"Insert unsuccessfully",Toast.LENGTH_LONG).show();

    }
    public void signIn(User user){
        SQLiteDatabase db = getReadableDatabase();

        String[] Pro = {UserMaster.User.COL_2, UserMaster.User.COL_3, UserMaster.User.COL_4};

        Cursor cursor = db.query(
                UserMaster.User.TABLE_NAME,
                Pro,
                UserMaster.User.COL_2 + " = ? AND " + UserMaster.User.COL_3 + " = ? ",
                new String[] {user.getUsername(),user.getPassword()},
                null,
                null,
                null

        );
        User user1 = new User();
        while (cursor.moveToNext()){
            user1.setUsername(cursor.getString(0));
            user1.setPassword(cursor.getString(1));
            user1.setType(cursor.getString(2));
        }
        if(user1.getType().equals("Student")){
            Toast.makeText(this.context,"hi",Toast.LENGTH_LONG).show();
            context.startActivity(new Intent(context, Student.class).putExtra("Student",user1.getUsername()));
        }
        else if(user1.getType().equals("teacher")){
            Toast.makeText(this.context,"modata",Toast.LENGTH_LONG).show();
            context.startActivity(new Intent(context, Teacher.class).putExtra("Teacher",user1.getUsername()));
        }


    }
}
