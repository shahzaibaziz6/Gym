package com.shahzaibaziz.gym;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;

public class Payment_and_history extends AppCompatActivity implements View.OnClickListener {

    EditText etvFirstName, etvSecondName, etvPhoneNumber, etvAddress;
    TextView tvStartingDate;
   private SQLiteDatabase myDB;
    Button btnPayment,btnDelete;
    String ID;
    ImageView ivUserPic;
    private SQLiteDatabase mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_and_history);
        etvFirstName = findViewById(R.id.etv_Payment_and_history_first_name);
        etvSecondName = findViewById(R.id.etv_Payment_and_history_last_name);
        etvPhoneNumber = findViewById(R.id.etv_Payment_and_history_phone_number);
        etvAddress = findViewById(R.id.etv_Payment_and_history_address);
        tvStartingDate = findViewById(R.id.tv_Payment_and_history_date);
        btnPayment= findViewById(R.id.btn_Payment_and_history_payment);
        btnDelete= findViewById(R.id.btn_Payment_and_history_delete);
        btnPayment.setOnClickListener(this);

        ivUserPic= findViewById(R.id.iv_Payment_and_history_user_pic);
        btnDelete.setOnClickListener(this);
        Intent intent = getIntent();
        ID = intent.getStringExtra("pri").toString();
        try {
            myDB = openOrCreateDatabase("test1.db", MODE_PRIVATE, null);

            Cursor query = myDB.rawQuery("select * from newentry124 where ID =" + ID + ";", null);

            if (query.moveToFirst()) {
                String firstName, secondName, address, phoneNumber, staringDate;
                firstName = query.getString(1);
                secondName = query.getString(2);
                phoneNumber = query.getString(3);
                address = query.getString(4);
                staringDate = query.getString(5);
                byte[] data= query.getBlob(6);


                ByteArrayInputStream imageStream = new ByteArrayInputStream(data);
                Bitmap theImage= BitmapFactory.decodeStream(imageStream);
                ivUserPic.setImageBitmap(theImage);
                etvFirstName.setText(firstName);
                etvSecondName.setText(secondName);
                etvAddress.setText(address);
                etvPhoneNumber.setText(phoneNumber);
                tvStartingDate.setText(staringDate);


            } else {
                Toast.makeText(this, "No 123 record Found", Toast.LENGTH_SHORT).show();
            }

            myDB.close();
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_Payment_and_history_payment:
                {

                    Intent intent = new Intent(this,Payment.class);
                    intent.putExtra("pri",ID);
                    startActivity(intent);

                    break;
                }
            case R.id.btn_Payment_and_history_delete:
            {

                AlertDialog.Builder builder= new AlertDialog.Builder(Payment_and_history.this);
                builder.setTitle("Are You Sure to Delete this Entry");
                builder.setMessage("Whole Data related to this person is also deleted");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        mydb = getBaseContext().openOrCreateDatabase("test1.db", MODE_PRIVATE, null);
                        mydb.execSQL("DELETE FROM history12 where ID ='"+ID+"';");
                        mydb.execSQL("DELETE FROM newentry124 where ID ='"+ID+"';");


                        mydb.close();
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                break;
            }
        }

    }
}
