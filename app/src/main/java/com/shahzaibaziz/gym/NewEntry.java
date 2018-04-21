package com.shahzaibaziz.gym;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Locale;

public class NewEntry extends AppCompatActivity implements View.OnClickListener {

    public EditText etvFirstName,etvLastName,etvPhoneNumber;
    public ImageView ivUserPic;
    public Bitmap image;
    public LinearLayout datepicker;
    public Button btnSave;
    private  int x_days,x_years,x_month;
    private static final int CAMERA_PIC_REQUEST = 2,DAILOG_ID=2;
    private TextView tvDate;
    Calendar myCalendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_entry);
        etvFirstName= findViewById(R.id.etv_new_entry_first_name);
        etvLastName= findViewById(R.id.etv_new_entry_last_name);
        etvPhoneNumber= findViewById(R.id.etv_new_entry_phone_number);
        ivUserPic= findViewById(R.id.iv_new_entry_user_pic);
        ivUserPic.setOnClickListener(this);
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
                tvDate.setText(year+"/"+month+"/"+day);
            }

            };
    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.iv_new_entry_user_pic) {
            askPermission();

        }
        else if(v.getId()==R.id.ll_new_entry_date_picker)
        {
           showDialog(DAILOG_ID);
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
            showCamera();
        }
    }
    private  void showCamera()
    {
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST);

        // start the image capture Intent
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_PIC_REQUEST) {
             image = (Bitmap) data.getExtras().get("data");//sets imageview as the bitmap
            ivUserPic.setImageBitmap(image);
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

}
