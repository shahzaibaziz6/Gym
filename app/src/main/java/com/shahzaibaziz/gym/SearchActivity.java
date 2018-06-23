package com.shahzaibaziz.gym;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity implements  View.OnClickListener{
    SQLiteDatabase myDB123;
    private SQLiteDatabase mydbtest;
    LinearLayout llSearch;
    Button btnSeatch;
    RecyclerView recyclerView;
    List<String>  listName= new ArrayList<String>();
    List <String> listAddress= new ArrayList<String>();
    List<String>  listPhoneNumber= new ArrayList<String>();

    private TheGymDBHelper gymDBHelper;
    EditText etvSearch,etvFirstName,etvSecondName,etvaddress,etvPhone;
    TextView tvDate;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

//        listPhoneNumber.add("123");
//        listPhoneNumber.add("123");
//        listPhoneNumber.add("123");
//        listPhoneNumber.add("123");
//
//
//        listAddress.add("address");
//        listAddress.add("address");
//        listAddress.add("address");
//        listAddress.add("address");
//
//        listName.add("shahzaib");
//        listName.add("shahzaib");
//        listName.add("shahzaib");
//        listName.add("shahzaib");







        btnSeatch= findViewById(R.id.btn_search_search);
        recyclerView= findViewById(R.id.rcv_search);
        recyclerView.setLayoutManager( new LinearLayoutManager(this));



        etvSearch= findViewById(R.id.etv_search_query);

        btnSeatch.setOnClickListener(this);



    }

    @SuppressLint("LongLogTag")
    @Override
    public void onClick(View v) {

        try {
            if (v.getId() == R.id.btn_search_search) {


//                myDB123 = getBaseContext().openOrCreateDatabase("test1.db",MODE_PRIVATE,null);
//
                String sql = "select * from newentry124 where " + "firstName" + " LIKE '%" + etvSearch.getText() + "%';";
//
//                Cursor query111 = myDB123.rawQuery(sql,null);
//
//
//                Toast.makeText(this, "BUTTON CLICKED", Toast.LENGTH_SHORT).show();

//                gymDBHelper = new TheGymDBHelper(SearchActivity.this);
//                myDB123=gymDBHelper.getWritableDatabase();

//                Cursor query111 = gymDBHelper.fetchData(etvSearch.getText().toString());
//                Cursor query111=fetch();

                mydbtest = getBaseContext().openOrCreateDatabase("test1.db", MODE_PRIVATE, null);
//
                Cursor query111 = mydbtest.rawQuery(sql,null);
//                        mydbtest.query("newentry124", null, null, null, null, null, null);




                if (query111.moveToFirst()) {
                    Log.v(" KURTA Move tp first called", "waha tak teek chal raha hai");
//
                    int counter=0;
                    do {

                        if (query111 == null) {
                            Toast.makeText(this, "Cursor is null", Toast.LENGTH_SHORT).show();
                            return;
                        }




                        //TODO waha masla a raha hai first time toast show hota hai lakin phir error ata hai jo Toast mai show ho raha hai


                        // TODO agr es lines ko comment kro toh jitna record insert kia hai cursor otni time moveToNext par call ho jata hai
                        String firstName = query111.getString(1);
                        String secondName = query111.getString(2);
                        String phone = query111.getString(3);
                        String address = query111.getString(4);
                        String date = query111.getString(5);
                        byte[] bytes = query111.getBlob(query111.getColumnIndex("photo"));
                        Toast.makeText(getApplicationContext(), "Move to first " + firstName + " <<<<<<<" + secondName + phone + date, Toast.LENGTH_LONG).show();
//                    llSearch.setVisibility(View.VISIBLE);
//                    etvFirstName.setText(firstName);
//                    etvSecondName.setText(secondName);
//                    etvPhone.setText(phone);
//                    etvaddress.setText(address);
//                    tvDate.setText(date);

//                    listName.add(query111.getString(1)+ " "+ query111.getString(2));
//                    listAddress.add(query111.getString(4));
//                    listPhoneNumber.add(query111.getString(3));

//                    listName.add(query111.getString(1)+ " "+ query111.getString(2));
//                    listAddress.add(query111.getString(4));
//                    listPhoneNumber.add(query111.getString(3));

//                    int counter=0;
//                   do
//                    {
//
//
//
//
////                            listName.add(query111.getString(query111.getColumnIndex(GymDataBaseContract.GymModel.FIRST_NAME))+" "+ query111.getString(query111.getColumnIndex(GymDataBaseContract.GymModel.LAST_NAME)));
////                            listPhoneNumber.add(query111.getString(query111.getColumnIndex(GymDataBaseContract.GymModel.PHONE_NUMBER)));
////                            listAddress.add(query111.getString(query111.getColumnIndex(GymDataBaseContract.GymModel.ADDRESS)));
////                        listName.add(query111.getString(1)+ " "+ query111.getString(2));
////                        listAddress.add(query111.getString(4));
////                        listPhoneNumber.add(query111.getString(3));
////                        listName.add(query111.getString(1)+" "+ query111.getString(2));
////
//
////                            String firstName1 = query111.getString(query111.getColumnIndex("firstName"));
////                            String secondName1 = query111.getString(query111.getColumnIndex("secondName"));
////                            String phone1 = query111.getString(query111.getColumnIndex("phoneNumber"));
////                            String address1 = query111.getString(query111.getColumnIndex("address"));
////                            String date1 = query111.getString(query111.getColumnIndex("startingDate"));
////                            byte[] bytes1 = query111.getBlob(query111.getColumnIndex("photo"));
////                            Toast.makeText(getApplicationContext(), "Move to first " + firstName1 + " <<<<<<<" + secondName1 + phone1 + address1 + date1 + bytes1, Toast.LENGTH_LONG).show();
//
//                            counter++;
//                            Toast.makeText(this,String.valueOf(counter),Toast.LENGTH_LONG).show();
//
//
//                    }
//                    while (query111.moveToNext());


                        Log.v("KURTA RECYLER VIEW START HONY WALA HAI", "almost start");
//                    recyclerView.setAdapter(new CustomeAdapter(getBaseContext(),query111));

                        counter++;
                        Toast.makeText(this,String.valueOf(counter),Toast.LENGTH_LONG).show();
//

                    }
                    while (query111.moveToNext());
                }
//            }


            }

        } catch (Exception e) {
            Toast.makeText(this, e.getMessage().toString() + " ___________________error is here ", Toast.LENGTH_LONG).show();
        }
    }


    public  Cursor fetch()
    {
         return myDB123.query(GymDataBaseContract.GymModel.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null);


    }

}
