package com.example.android.quizapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.quizapp.model.TestAnswer;

import java.util.ArrayList;
import java.util.List;

public class AnswerUtility {

    public static final int DB_VERSION = 1;
    public static final String DB_NAME ="testeGrila.db";
    public static final String ANSWER_TABLE = "RASPUNSURI";
    public static final String ANSWER_ID = "_id";
    public static final String ANSWER_TEXT = "TextRaspuns";
    public static final String ANSWER_CORRECT = "Corect";
    public static final String QUESTION_ID="IDIntrebare";

    private AnswerUtility.DBUtility dbUtility;
    private SQLiteDatabase sqLiteDatabase;
    private Context context;

    public AnswerUtility(Context context)
    {
        this.context = context;
        dbUtility = new AnswerUtility.DBUtility(context);
    }

    public void openDB()
    {
        sqLiteDatabase=dbUtility.getWritableDatabase();
    }

    public void closeDB()
    {
        sqLiteDatabase.close();
    }

    public void insertAnswer(TestAnswer answers)
    {

        ContentValues cv = new ContentValues();
        cv.put(QUESTION_ID,answers.getAnswer_id());
        cv.put(ANSWER_TEXT,answers.getAnswer_text());
        cv.put(ANSWER_CORRECT,answers.isAnswer_right());
        sqLiteDatabase.insert(ANSWER_TABLE,null,cv);

    }

    public List<TestAnswer> getAnswerList(String question_id){
        List<TestAnswer> lst_answer=new ArrayList<>();
        Cursor cursor=sqLiteDatabase.query(ANSWER_TABLE,null,QUESTION_ID+"=?",new String[]{question_id},null,null,null);
        if(cursor.getCount() < 1){
            cursor.close();
            return null;
        }
        if(cursor.moveToFirst()) {
            do {
                TestAnswer testAnswer=new TestAnswer();
                testAnswer.setAnswer_id(cursor.getInt(cursor.getColumnIndex(ANSWER_ID)));
                testAnswer.setAnswer_text(cursor.getString(cursor.getColumnIndex(ANSWER_TEXT)));
                testAnswer.setAnswer_right(cursor.getInt(cursor.getColumnIndex(ANSWER_CORRECT)));
                lst_answer.add(testAnswer);
            } while (cursor.moveToNext());
        }
        return lst_answer;
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
