package com.example.keegz_000.question2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by keegz_000 on 2017/05/17.
 */

public class DBAdapter
{
    static final String KEY_ROWID = "_ID";
    static final String KEY_STUDENT_NUMBER = "StudentNumber";
    static final String KEY_STUDENT_NAME = "StudentName";
    static final String KEY_STUDENT_SURNAME = "StudentSurname";
    static final String KEY_COURSE_CODE = "CourseCode";
    static final String KEY_MODULE_CODE = "ModuleCode";
    static final String KEY_MARK_PERCENTAGE = "MarkPercentage";
    static final String TAG = "DBAdapter";
    //***********************************************************************
    static final String DATABASE_NAME = "Student.db";
    static final String DATABASE_TABLE = "tbl_marks";
    static final int DATABASE_VERSION = 1;
    //************************************************************************
    static final String DATABASE_CREATE = "create table " + DATABASE_TABLE + "(_ID integer primary key autoincrement,"
            + "StudentNumber text not null, StudentName text not null, StudentSurname text not null, CourseCode text not null, ModuleCode text not null, MarkPercentage text not null);";

    final Context context;
    DatabaseHelper DBHelper;
    SQLiteDatabase db;

    public DBAdapter(Context ctx)
    {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }

    public static class DatabaseHelper extends SQLiteOpenHelper
    {
        DatabaseHelper(Context context)
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db)
        {
            try{
                db.execSQL(DATABASE_CREATE);
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS "+ DATABASE_TABLE);
            onCreate(db);
        }

    }

    public DBAdapter open() throws SQLException
    {
        db = DBHelper.getWritableDatabase();
        return this;
    }
    public boolean insertData(String studentNum, String name, String surname, String courseC, String moduleC, String marks)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_STUDENT_NUMBER, studentNum);
        initialValues.put(KEY_STUDENT_NAME, name);
        initialValues.put(KEY_STUDENT_SURNAME, surname);
        initialValues.put(KEY_COURSE_CODE, courseC);
        initialValues.put(KEY_MODULE_CODE, moduleC);
        initialValues.put(KEY_MARK_PERCENTAGE, marks);
        long result = db.insert(DATABASE_TABLE, null, initialValues);
        if(result == -1)
            return false;
        else
            return true;
    }
    //*****************************************************************************************

    public Cursor getAllData()
    {
        return db.query(DATABASE_TABLE, new String[] {KEY_ROWID, KEY_STUDENT_NUMBER, KEY_STUDENT_NAME, KEY_STUDENT_SURNAME, KEY_COURSE_CODE, KEY_MODULE_CODE, KEY_MARK_PERCENTAGE}, null, null, null, null, null, null);
    }
    //***************************************************************************************
    public boolean updateData(String id, String studentNum, String name, String surname, String courseC, String moduleC, String marks)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_ROWID, id);
        initialValues.put(KEY_STUDENT_NUMBER, studentNum);
        initialValues.put(KEY_STUDENT_NAME, name);
        initialValues.put(KEY_STUDENT_SURNAME, surname);
        initialValues.put(KEY_COURSE_CODE, courseC);
        initialValues.put(KEY_MODULE_CODE, moduleC);
        initialValues.put(KEY_MARK_PERCENTAGE, marks);
        return db.update(DATABASE_TABLE,initialValues,KEY_ROWID+"="+id,null)>0;

    }
    //****************************************************************************************
    public Integer deleteData(String id)
    {
        return db.delete(DATABASE_TABLE, "_ID = ?",new String[] {id} );
    }
    //******************************************************************************************
    //---closes the database---
    public void close()
    {
        DBHelper.close();
    }
}
