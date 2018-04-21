package com.shahzaibaziz.gym;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.shahzaibaziz.gym.GymDataBaseContract;

public class TheGymDBHelper extends SQLiteOpenHelper {


    public TheGymDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public static  final String DATABASE_NAME="gym.db";
    public static  final int DATABASE_VERSION=1;

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String SQL_QUERY= "CREATE TABLE "+ GymDataBaseContract.GymModel.TABLE_NAME+
                "(  "+GymDataBaseContract.GymModel.ID+" INTEGER PRIMARY KEY AUTOINCREMENT ,"
                +GymDataBaseContract.GymModel.FIRST_NAME+" TEXT NOT NULL, "+
                GymDataBaseContract.GymModel.LAST_NAME+"TEXT NOT NULL,"+
                GymDataBaseContract.GymModel.PHONE_NUMBER+" TEXT NOT NULL, "+
                GymDataBaseContract.GymModel.STARTING_DATE +"TEXT NOT NULL,"+
                GymDataBaseContract.GymModel.ADDRESS+"TEXT NOT NULL ,"+
                GymDataBaseContract.GymModel.USER_PIC+"BLOB );";
        sqLiteDatabase.execSQL(SQL_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL(("DROP TABLE IF EXISTS "+ GymDataBaseContract.GymModel.TABLE_NAME));
        onCreate(sqLiteDatabase);
    }
}
