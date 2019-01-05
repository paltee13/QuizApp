package com.example.android.quizapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.quizapp.model.ChoiceTest;

import java.util.ArrayList;
import java.util.List;

public class TestUtility {

    public static final int DB_VERSION = 1;
    public static final String DB_NAME ="testeGrila.db";
    public static final String TEST_TABLE = "TESTE";
    public static final String TEST_ID = "_id";
    public static final String MATERIE_ID = "IDMaterie";
    public static final String QUESTION_NO = "NrIntrebari";
    public static final String TEST_NAME="NumeTest";
    public static final String TEST_AUTHOR="Autor";

    private TestUtility.DBUtility dbUtility;
    private SQLiteDatabase sqLiteDatabase;
    private Context context;

    public TestUtility(Context context)
    {
        this.context = context;
        dbUtility = new TestUtility.DBUtility(context);
    }

    public void openDB()
    {
        sqLiteDatabase=dbUtility.getWritableDatabase();
    }

    public void closeDB()
    {
        sqLiteDatabase.close();
    }

    public void writeTest(ChoiceTest choiceTest){
        ContentValues cv=new ContentValues();
        cv.put(TEST_NAME,choiceTest.getTest_name());
        cv.put(TEST_AUTHOR,choiceTest.getTest_author());
        cv.put(QUESTION_NO,choiceTest.getTest_question_no());
        cv.put(MATERIE_ID,choiceTest.getSubject_id());
        sqLiteDatabase.insert(TEST_TABLE,null,cv);
    }

    public List<ChoiceTest> getTestList(){
        List<ChoiceTest> choiceTests=new ArrayList<>();
        final String query="select tst.*,mat.Nume from TESTE tst INNER JOIN MATERII mat on tst.IDMaterie=mat._id";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if(cursor.getCount() < 1){
            cursor.close();
            return null;
        }
        if(cursor.moveToFirst()){
            do{
                ChoiceTest choiceTest=new ChoiceTest();
                choiceTest.setTest_id(cursor.getInt(cursor.getColumnIndex(TEST_ID)));
                choiceTest.setTest_name(cursor.getString(cursor.getColumnIndex(TEST_NAME)));
                choiceTest.setTest_subject(cursor.getString(cursor.getColumnIndex("Nume")));
                choiceTest.setTest_author(cursor.getString(cursor.getColumnIndex(TEST_AUTHOR)));
                choiceTests.add(choiceTest);

            }while(cursor.moveToNext());
        }
        return choiceTests;
    }
    public String getTestId(String column)
    {
        Cursor cursor = sqLiteDatabase.query(TEST_TABLE,null,null,null,null, null, null );
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
