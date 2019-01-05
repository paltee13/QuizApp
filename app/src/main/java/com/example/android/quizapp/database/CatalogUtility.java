package com.example.android.quizapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.quizapp.model.Catalog;

import java.util.ArrayList;
import java.util.List;

public class CatalogUtility {

    private List<Catalog> lst_catalog = new ArrayList<>();

    public static final int DB_VERSION = 1;
    public static final String DB_NAME ="testeGrila.db";
    public static final String PUNCTAJ_TABLE = "PUNCTAJ";
    public static final String USER_ID = "IDUser";
    public static final String TEST_ID = "IDTest";
    public static final String MATERIE_ID = "IDMaterie";
    public static final String PUNCTAJ = "Punctaj";

    private CatalogUtility.DBUtility dbUtility;
    private SQLiteDatabase sqLiteDatabase;
    private Context context;


    public CatalogUtility(Context context)
    {
        this.context = context;
        dbUtility = new CatalogUtility.DBUtility(context);
    }

    public void openDB()
    {
        sqLiteDatabase=dbUtility.getWritableDatabase();
    }

    public void closeDB()
    {
        sqLiteDatabase.close();
    }

    public void insertMark(String id_user,String id_materie, String id_test, float punctaj){
        ContentValues cv=new ContentValues();
        cv.put(USER_ID,id_user);
        cv.put(MATERIE_ID,id_materie);
        cv.put(TEST_ID,id_test);
        cv.put(PUNCTAJ,punctaj);
        sqLiteDatabase.insert(PUNCTAJ_TABLE,null,cv);

    }


    public List<Catalog> getCatalog(String userId)
    {
        final String query="select * from PUNCTAJ punct INNER JOIN MATERII mat on punct.IDMaterie=mat._id where punct.IDUser=?";
        Cursor cursor = sqLiteDatabase.rawQuery(query, new String[]{userId});
        if(cursor.getCount() < 1){
            cursor.close();
            return null;
        }
        if(cursor.moveToFirst()){
            do{
                Catalog catalog = new Catalog();
                catalog.setSubject(cursor.getString(cursor.getColumnIndex("Nume")));
                catalog.setScore(cursor.getInt(cursor.getColumnIndex("Punctaj")));
                lst_catalog.add(catalog);

            }while(cursor.moveToNext());
        }
        return lst_catalog;
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
