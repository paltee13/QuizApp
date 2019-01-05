package com.example.android.quizapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SubjectUtility {

    public static final int DB_VERSION = 1;
    public static final String DB_NAME ="testeGrila.db";
    public static final String SUBJECT_TABLE = "MATERII";
    public static final String SUBJECT_NAME ="Nume";
    public static final String SUBJECT_ID="_id";

    private SubjectUtility.DBUtility dbUtility;
    private SQLiteDatabase sqLiteDatabase;
    private Context context;

    public SubjectUtility(Context context)
    {
        this.context = context;
        dbUtility = new SubjectUtility.DBUtility(context);
    }

    public void openDB()
    {
        sqLiteDatabase=dbUtility.getWritableDatabase();
    }

    public void closeDB()
    {
        sqLiteDatabase.close();
    }

    public void writeSubject(String subject){
        ContentValues cv=new ContentValues();
        cv.put(SUBJECT_NAME,subject);
        sqLiteDatabase.insert(SUBJECT_TABLE,null,cv);
    }

    public String getSubjectIdId(String column)
    {
        Cursor cursor = sqLiteDatabase.query(SUBJECT_TABLE,null,null,null,null, null, null );
        if(cursor.getCount()<1)
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToLast();
        String id = cursor.getString(cursor.getColumnIndex(column));
        cursor.close();
        return id;
    }
    public String getSubjectId(String column,String subjname)
    {
        Cursor cursor = sqLiteDatabase.query(SUBJECT_TABLE,null,SUBJECT_NAME+"=?",new String[]{subjname},null, null, null );
        if(cursor.getCount()<1)
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToLast();
        String id = cursor.getString(cursor.getColumnIndex(column));
        cursor.close();
        return id;
    }

    public class DBUtility extends SQLiteOpenHelper
    {
        public DBUtility(Context context)
        {
            super(context,DB_NAME,null,DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db)
        {

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVers, int newVers)
        {

        }
    }


}
