package com.example.android.quizapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.quizapp.model.TestQuestion;

import java.util.ArrayList;
import java.util.List;

public class QuestionUtility {

    public static final int DB_VERSION = 1;
    public static final String DB_NAME ="testeGrila.db";
    public static final String QUESTION_TABLE="INTREBARI";
    public static final String QUESTION_ID = "_id";
    public static final String QUESTION_TEXT = "TextIntrebare";
    public static final String QUESTION_NOANS = "NrRasp";
    public static final String QUESTION_MULTCH = "RaspMult";
    public static final String QUESTION_SCORE = "Punctaj";
    public static final String ID_Test="IDTest";


    private DBUtility dbUtility;
    private SQLiteDatabase sqLiteDatabase;
    private Context context;

    public QuestionUtility(Context context)
    {
        this.context = context;
        dbUtility = new DBUtility(context);
    }

    public void openDB()
    {
        sqLiteDatabase=dbUtility.getWritableDatabase();
    }

    public void closeDB()
    {
        sqLiteDatabase.close();
    }

    public void insertQuestion(TestQuestion questions)
    {

        ContentValues cv = new ContentValues();
        cv.put(QUESTION_TEXT,questions.getQuestion_text());
        cv.put(QUESTION_SCORE,questions.getQuestion_points());
        cv.put(QUESTION_MULTCH,questions.getQuestion_multch());
        cv.put(ID_Test,questions.getQuestion_id());
        sqLiteDatabase.insert(QUESTION_TABLE,null,cv);

    }

    public String getQuestionId(String column)
    {
        Cursor cursor = sqLiteDatabase.query(QUESTION_TABLE,null,null,null,null, null, null );
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

    public List<TestQuestion> getQuestion(String test_id){
        List<TestQuestion> lst_question=new ArrayList<>();
        Cursor cursor=sqLiteDatabase.query(QUESTION_TABLE,null,ID_Test+"=?",new String[]{test_id},null,null,null);
        if(cursor.getCount() < 1){
            cursor.close();
            return null;
        }
        if(cursor.moveToFirst()) {
            do {
                TestQuestion testQuestion = new TestQuestion();
                testQuestion.setQuestion_id(cursor.getInt(cursor.getColumnIndex(QUESTION_ID)));
                testQuestion.setQuestion_text(cursor.getString(cursor.getColumnIndex(QUESTION_TEXT)));
                testQuestion.setQuestion_points(cursor.getFloat(cursor.getColumnIndex(QUESTION_SCORE)));
                testQuestion.setQuestion_multch(cursor.getInt(cursor.getColumnIndex(QUESTION_MULTCH)));
                lst_question.add(testQuestion);
            } while (cursor.moveToNext());
        }
        return lst_question;
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
