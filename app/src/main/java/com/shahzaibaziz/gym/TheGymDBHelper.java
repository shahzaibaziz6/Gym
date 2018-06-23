package com.shahzaibaziz.gym;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.shahzaibaziz.gym.GymDataBaseContract;
public class TheGymDBHelper extends SQLiteOpenHelper {

    public static  final String DATABASE_NAME="theGym.db";
    public static  final int DATABASE_VERSION=1;
    Context context;
    public TheGymDBHelper(Context cont) {
        super(cont,DATABASE_NAME ,null, DATABASE_VERSION);

        this.context=cont;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String SQL_QUERY= "CREATE TABLE "+ GymDataBaseContract.GymModel.TABLE_NAME +
                "("+GymDataBaseContract.GymModel.ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +GymDataBaseContract.GymModel.FIRST_NAME+ " TEXT NOT NULL, "+
                GymDataBaseContract.GymModel.LAST_NAME+" TEXT NOT NULL,"+
                GymDataBaseContract.GymModel.PHONE_NUMBER+" TEXT NOT NULL, "+
                GymDataBaseContract.GymModel.STARTING_DATE +" TEXT NOT NULL,"+
                GymDataBaseContract.GymModel.ADDRESS+" TEXT NOT NULL,"+
                GymDataBaseContract.GymModel.USER_PIC+" BLOB NOT NULL);";
        sqLiteDatabase.execSQL(SQL_QUERY);
        Toast.makeText(context,"TABLE ID CREATED",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL(("DROP TABLE IF EXISTS "+ GymDataBaseContract.GymModel.TABLE_NAME));
        onCreate(sqLiteDatabase);
    }
    public  boolean insertData (SQLiteDatabase db,String firstName,String secondName,String phoneNo,String address,String startingDate,byte [] image)
    {



        db.beginTransaction();
        ContentValues contentValues= new ContentValues();
        contentValues.put(GymDataBaseContract.GymModel.FIRST_NAME,firstName);
        contentValues.put(GymDataBaseContract.GymModel.LAST_NAME,secondName);
        contentValues.put(GymDataBaseContract.GymModel.PHONE_NUMBER,phoneNo);
        contentValues.put(GymDataBaseContract.GymModel.ADDRESS,address);
        contentValues.put(GymDataBaseContract.GymModel.STARTING_DATE,startingDate);
        contentValues.put(GymDataBaseContract.GymModel.USER_PIC,image);
        long result=db.insert(GymDataBaseContract.GymModel.TABLE_NAME,null,contentValues);


        if(result<0)
        {
            db.setTransactionSuccessful();
            return false;
        }
        db.setTransactionSuccessful();
        Toast.makeText(context,"Data is inserted",Toast.LENGTH_SHORT).show();
        return true;
    }

    public Cursor fetchData(String search)
    {
        Cursor cursor=null;

        try {
            SQLiteDatabase db = this.getReadableDatabase();

            String[] projection = {GymDataBaseContract.GymModel.ID, GymDataBaseContract.GymModel.FIRST_NAME,
                    GymDataBaseContract.GymModel.LAST_NAME, GymDataBaseContract.GymModel.PHONE_NUMBER,
                    GymDataBaseContract.GymModel.ADDRESS, GymDataBaseContract.GymModel.STARTING_DATE,
                    GymDataBaseContract.GymModel.USER_PIC};
            String selectionColoum = GymDataBaseContract.GymModel.FIRST_NAME;
            String[] wheresearch = {search};
            cursor = db.query(GymDataBaseContract.GymModel.TABLE_NAME, null, null, null, null, null, null);
            return cursor;
        }
        catch (Exception e)
        {
            Toast.makeText(context,"NO DATA FOUND CURSOR IS NULL",Toast.LENGTH_SHORT).show();
            return cursor;
        }
    }

}
