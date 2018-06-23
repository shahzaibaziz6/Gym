package com.shahzaibaziz.gym;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;
import java.util.Locale;
import java.util.regex.Pattern;

public class NewEntry extends AppCompatActivity implements View.OnClickListener {

    TheGymDBHelper gymHelper;
    public EditText etvFirstName,etvLastName,etvPhoneNumber,etvAddress;
    public ImageView ivUserPic;
    public Bitmap image;
    public LinearLayout datepicker;
    public Button btnSave;
    private  int x_days,x_years,x_month;
    private static final int CAMERA_PIC_REQUEST = 2,DAILOG_ID=2,SELECT_FILE=1;
    private TextView tvDate;
    Calendar myCalendar;
   private Bitmap bitmap;
    ByteArrayOutputStream baos;
    private SQLiteDatabase mydb;
    private  SQLiteDatabase testDB;
    private  TheGymDBHelper testGymHelper;
    private byte[] imageInByte;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_entry);
        etvFirstName= findViewById(R.id.etv_new_entry_first_name);
        etvLastName= findViewById(R.id.etv_new_entry_last_name);
        etvPhoneNumber= findViewById(R.id.etv_new_entry_phone_number);
        ivUserPic= findViewById(R.id.iv_new_entry_user_pic);
        ivUserPic.setOnClickListener(this);
        etvAddress= findViewById(R.id.etv_new_entry_address);
        btnSave= findViewById(R.id.btn_new_entry_save);
        tvDate=findViewById(R.id.tv_new_entry_date);
        tvDate.setOnClickListener(this);
        btnSave.setOnClickListener(this);
        datepicker= findViewById(R.id.ll_new_entry_date_picker);
        datepicker.setOnClickListener(this);
        Calendar calendar= Calendar.getInstance();
        x_years=calendar.get(Calendar.YEAR);
        x_month= calendar.get(Calendar.MONTH);
        x_days= calendar.get(Calendar.DAY_OF_MONTH);

        //db = (TheGymDBHelper) new TheGymDBHelper(this);

        testGymHelper = new TheGymDBHelper(this);


        testDB= testGymHelper.getWritableDatabase();






    }

    @Override
    public Dialog onCreateDialog(int id)
    {
        return new DatePickerDialog(this,dpickListnier,x_years,x_month,x_days);

    }
    public   DatePickerDialog.OnDateSetListener dpickListnier=
            new DatePickerDialog.OnDateSetListener(){
            @Override
                public void onDateSet(DatePicker vew,int year,int month,int day){
                x_days= year;
                x_month=month;
                x_days=day;
                tvDate.setText(day+"/"+month+"/"+year);
            }

            };
    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.iv_new_entry_user_pic) {
            selectImage();
            askPermission();
            Toast.makeText(this,"BUTOON CLICK ",Toast.LENGTH_SHORT).show();



        }
        else if(v.getId()==R.id.ll_new_entry_date_picker)
        {
           showDialog(DAILOG_ID);
        }
        else if(v.getId()==R.id.btn_new_entry_save)
        {
            if(validateFields())
            {
//                bitmap= ((BitmapDrawable)ivUserPic.getDrawable()).getBitmap();
//                ByteArrayOutputStream stream = new ByteArrayOutputStream();
//                bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
//                byte [] imageInByte =stream.toByteArray();
                try {
//                ivUserPic.setDrawingCacheEnabled(true);
//                ivUserPic.buildDrawingCache();
//                Bitmap bitmap = ivUserPic.getDrawingCache();
//                ByteArrayOutputStream boas= new ByteArrayOutputStream();
//                bitmap.compress(Bitmap.CompressFormat.JPEG,98,boas);
//                byte[] data = boas.toByteArray();

                    bitmap = ((BitmapDrawable) ivUserPic.getDrawable()).getBitmap();
                    baos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                    imageInByte = baos.toByteArray();








                mydb = getBaseContext().openOrCreateDatabase("test1.db",MODE_PRIVATE,null);
//
                mydb.execSQL("Create table IF NOT EXISTS newentry124 (ID INTEGER PRIMARY KEY   AUTOINCREMENT,firstName Text,secondName Text,phoneNumber Text ," +
                        "address Text , startingDate Text,photo BLOB)");


//                    TODO Database by using raw Query
//                    ContentValues filedata= new ContentValues();
//                    filedata.put("photo",imageInByte);
//                String sql="insert into newentry1 (firstName,secondName,phoneNumber,address,startingDate,photo) values ('"+
//                    etvFirstName.getText().toString()+"','"+etvLastName.getText().toString()+"','"+etvPhoneNumber.getText().toString() +
//                        "','"+etvAddress.getText().toString()+"','"+tvDate.getText().toString()+"',"+filedata+");";
//
//
////                mydb.rawQuery(sql,new String[] {String.valueOf(imageInByte)});
//                                    mydb.execSQL(sql);
//                    Toast.makeText(this,sql,Toast.LENGTH_SHORT).show();
                    // TODO SQllite Database is created  with Help of content values
                    //TODO jo comment kia howa hai wo b try kia hai helper class class say b try kia hai
                    //TODO lakin ab wo es mai nahi use ho rahi
                    //TODO Basically waha say data enter hona hai



                    ContentValues contentValues= new ContentValues();
                    contentValues.put("firstName",etvFirstName.getText().toString());
                    contentValues.put("secondName",etvLastName.getText().toString());
                    contentValues.put("phoneNumber",etvPhoneNumber.getText().toString());
                    contentValues.put("address",etvAddress.getText().toString());
                    contentValues.put("startingDate",tvDate.getText().toString());
                    contentValues.put("photo",imageInByte);
                    if(mydb.insertOrThrow("newentry124",null,contentValues)>0)
                    {
                        Toast.makeText(this,"Data is inserted \n"+contentValues.toString(),Toast.LENGTH_LONG).show();

                    }
                    contentValues.clear();


                    //TODO Database entry with the help of DATABASE HELPER CLASS


//                    if(gymHelper.insertData(mydb,etvFirstName.getText().toString(),
//                            etvLastName.getText().toString(),
//                            etvPhoneNumber.getText().toString(),
//                            etvAddress.getText().toString(),
//                            tvDate.getText().toString(),
//                            imageInByte))
//                    {
//                        Toast.makeText(this,"HELLOW WORLD ",Toast.LENGTH_SHORT).show();
//                    }
//                    else
//                    {
//                        Toast.makeText(this,"Not Inserted ",Toast.LENGTH_SHORT).show();
//                    }


//                    if(insertedvalues()>0)
//                    {
//                        Toast.makeText(this,"Data is inserted ",Toast.LENGTH_SHORT).show();
//                    }
//                    else
//                    {
//                        Toast.makeText(this,"Data is  not inserted ",Toast.LENGTH_SHORT).show();
//                    }






                }
                catch (Exception e)
                {
                    Toast.makeText(this,e.getMessage()+" \nthis is me",Toast.LENGTH_LONG).show();
                }

//                etvAddress.setText("");
//                etvFirstName.setText("");
//                etvLastName.setText("");
//                tvDate.setText("");
//                etvPhoneNumber.setText("");


            }
        }


    }
    private void askPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.CAMERA)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CAMERA},
                        CAMERA_PIC_REQUEST);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted
//            showCamera();
        }
    }
    private  void showCamera()
    {
        try {
            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST);
        }
        catch (Exception e)
        {
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this,HomeActivity.class);
            startActivity(intent);
        }
        // start the image capture Intent
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_PIC_REQUEST) {
             image = (Bitmap) data.getExtras().get("data");//sets imageview as the bitmap
            ivUserPic.setImageBitmap(image);
        }
        else if(requestCode==SELECT_FILE)
        {
            Uri selectImageUri=data.getData();
            ivUserPic.setImageURI(selectImageUri);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case CAMERA_PIC_REQUEST: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was grantoned, yay! Do the
                    // contacts-related task you need to do.
                    showCamera();

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }

    public boolean validateFields() {

        String regexStr = "^[0-9]$";
        String firstName,lastName,phoneNumber,date,Address;
        firstName = etvFirstName.getText().toString();
        lastName = etvLastName.getText().toString();
        phoneNumber = etvPhoneNumber.getText().toString();
        date = tvDate.getText().toString();
        Address=etvAddress.getText().toString();

        if (firstName.equals("")) {
            etvFirstName.requestFocus();
            etvFirstName.setError(" First name is not Empty");
            return false;
        }

        if (lastName.equals("")) {
            etvLastName.requestFocus();
            etvLastName.setError("Last name is not Empty");
            return false;
        }

        if (!(phoneNumber.length()>10 && phoneNumber.length()<13)) {
            etvPhoneNumber.requestFocus();
            etvPhoneNumber.setError("Valid number");
            return false;
        }
        if (Address.equals("")) {
            etvAddress.requestFocus();
            etvAddress.setError("Not Empty");
            return false;

        }
        if (date.equals("")) {
            tvDate.requestFocus();
           tvDate.setError("Not Empty");
            return false;
        }

        return true;
    }



    private void selectImage()
    {
        final CharSequence [] item ={"Camera","Gallery","Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(NewEntry.this);
        builder.setTitle("Add Image ");
        builder.setItems(item, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (item[which].equals("Camera"))
                {

                    showCamera();
                }
                else if(item[which].equals("Gallery"))
                {

                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(intent.createChooser(intent,"Select File"),SELECT_FILE);

                }
                else if(item[which].equals("Cancel"))
                {
                    dialog.dismiss();
                }
            }
        });

        builder.show();


    }

    public  long insertedvalues()
    {
        ContentValues contentValues= new ContentValues();
        contentValues.put(GymDataBaseContract.GymModel.FIRST_NAME,etvFirstName.getText().toString());
        contentValues.put(GymDataBaseContract.GymModel.LAST_NAME,etvLastName.getText().toString());
        contentValues.put(GymDataBaseContract.GymModel.PHONE_NUMBER,etvPhoneNumber.getText().toString());
        contentValues.put(GymDataBaseContract.GymModel.ADDRESS,etvAddress.getText().toString());
        contentValues.put(GymDataBaseContract.GymModel.STARTING_DATE,tvDate.getText().toString());

        return mydb.insert(GymDataBaseContract.GymModel.TABLE_NAME,null,contentValues);



    }


}
