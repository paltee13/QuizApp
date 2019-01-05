package com.example.android.quizapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.android.quizapp.model.User;

import java.util.ArrayList;
import java.util.List;

public class LoginUtility {

    private List<User> lst_stud = new ArrayList<>();

    public static final int DB_VERSION = 1;
    public static final String DB_NAME ="testeGrila.db";
    public static final String USER_TABLE="USERS";
    public static final String USER_ID = "_id";
    public static final String USER_NAME = "Nume";
    public static final String USER_EMAIL = "Email";
    public static final String USER_PASSWORD = "Parola";
    public static final String USER_TYPE = "Tip_Cont";
    public static final String CREATE_TABLE_USERS =
            " create table " + USER_TABLE + "(" + USER_ID
                    + "integer primary key, " + USER_NAME +
                    " text, " + USER_EMAIL + " text, " +
                    USER_PASSWORD + " text, " + USER_TYPE + " integer);";


    private DBUtility dbUtility;
    private SQLiteDatabase sqLiteDatabase;
    private Context context;

    public LoginUtility(Context context)
    {
        this.context = context;
        dbUtility = new DBUtility(context);
    }



    public LoginUtility openDB()
    {
        sqLiteDatabase=dbUtility.getWritableDatabase();

        return this;
    }

    public void closeDB()
    {
        sqLiteDatabase.close();
    }

    public void insertUsers(User[] users) {
        for(int i=0;i<users.length;i++)
        {
            ContentValues cv = new ContentValues();
            cv.put(USER_NAME,users[i].getUserName());
            cv.put(USER_EMAIL,users[i].getUserEmail());
            cv.put(USER_PASSWORD,users[i].getUserPassword());
            cv.put(USER_TYPE,users[i].getUserType());
            long id = sqLiteDatabase.insert(USER_TABLE,null,cv);

            if(id != -1)
                Toast.makeText(context,"ID of inserted row: " + id,
                        Toast.LENGTH_SHORT).show();
        }
    }

    public String getUser(String userName, String column)
    {
        Cursor cursor = sqLiteDatabase.query("USERS",null,"Nume=?",new String[]{userName},null, null, null );
        if(cursor.getCount()<1)
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String password = cursor.getString(cursor.getColumnIndex(column));
        cursor.close();
        return password;
    }

    public List<User> getStudents(String userType){
        Cursor cursor = sqLiteDatabase.query("USERS",null,"Tip_Cont=?",new String[]{userType},null, null, null );
        if (cursor.getCount()<1)
        {
            cursor.close();
            return null;
        }
        if(cursor.moveToFirst()){
            do{
                User user = new User();
                user.setUserName(cursor.getString(cursor.getColumnIndex("Nume")));
                user.setUserId(cursor.getInt(cursor.getColumnIndex("_id")));
                lst_stud.add(user);
            }while(cursor.moveToNext());
        }
        return lst_stud;
    }

    class DBUtility extends SQLiteOpenHelper
    {

        public DBUtility(Context context)
        {
            super(context, DB_NAME, null, DB_VERSION);
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
