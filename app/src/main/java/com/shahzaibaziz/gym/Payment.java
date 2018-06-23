package com.shahzaibaziz.gym;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Payment extends AppCompatActivity implements View.OnClickListener {

    TextView tvDate;
    EditText etvAmount;
    RecyclerView rcvHistory;
    private SQLiteDatabase mydb;
    LinearLayout linearLayout;
    private  int x_days,x_years,x_month;
    public  static final int DAILOG_ID=2;
    Button btnPayment,btnHistory;
    String ID2;
    List<String> listDatePay=new ArrayList<String>();
    List<String> listAmount=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        try {
            tvDate = findViewById(R.id.tv_payment_date);
            linearLayout = findViewById(R.id.ll_payment_pay_part);
            linearLayout.setOnClickListener(this);
            rcvHistory = findViewById(R.id.rcv_payment_history);
            Calendar calendar = Calendar.getInstance();
            x_years = calendar.get(Calendar.YEAR);
            x_month = calendar.get(Calendar.MONTH);
            x_days = calendar.get(Calendar.DAY_OF_MONTH);
            btnPayment = findViewById(R.id.btn_payment_pay);
            btnPayment.setOnClickListener(this);
            Intent intent = getIntent();
            ID2 = intent.getStringExtra("pri");
            etvAmount = findViewById(R.id.etv_payment_amount);
            btnHistory = findViewById(R.id.btn_payment_history);
            btnHistory.setOnClickListener(this);


        }
        catch (Exception e)
        {
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.ll_payment_pay_part:
            {
                showDialog(DAILOG_ID);
                break;
            }
            case R.id.btn_payment_history:
            {

                try {
                    mydb = getBaseContext().openOrCreateDatabase("test1.db", MODE_PRIVATE, null);

                    Cursor que= mydb.rawQuery("select * from newenty124 where ID ='"+ID2+"' Order by date DESC;",null);

                    if(que.moveToFirst())
                    {
                        listDatePay.add(que.getString(1));
                        listAmount.add(que.getString(2));
                        while(que.moveToNext())
                        {
                            listDatePay.add(que.getString(1));
                            listAmount.add(que.getString(2));
                        }
                        try
                        {

                            rcvHistory.setLayoutManager(new LinearLayoutManager(this));
                            rcvHistory.setAdapter(new HistoryAdapter(listDatePay,listAmount));


                        }
                        catch(Exception e)
                        {
                            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
                        }


                    }




                    Toast.makeText(this,"COmply",Toast.LENGTH_SHORT).show();
                }
                catch (Exception e)
                {
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
                }

                break;
            }

            case R.id.btn_payment_pay:
            {
                if(tvDate.getText().length()==0)
                {
                    Toast.makeText(this,"Date is Empty",Toast.LENGTH_SHORT).show();
                    break;
                }
                if(etvAmount.getText().length()==0)
                {
                    Toast.makeText(this,"Amount is not set",Toast.LENGTH_SHORT).show();
                    break;
                }
                try
                {
                    mydb = getBaseContext().openOrCreateDatabase("test1.db",MODE_PRIVATE,null);
                    mydb.execSQL("Create table IF NOT EXISTS history124 (ID Text,date Text,amount text);");
                    String sql="INSERT INTO history12 (ID,date,amount) values ('"+ID2+"','"+tvDate.getText()+"','"+etvAmount.getText().toString()+"');";
                    mydb.execSQL(sql);
                    Toast.makeText(this,"Payment Accepted",Toast.LENGTH_SHORT).show();

                }
                catch (Exception e)
                {
                    Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
                }
                break;
            }

        }
    }

    @Override
    public Dialog onCreateDialog(int id)
    {
        return new DatePickerDialog(this,dpickListnier,x_years,x_month,x_days);

    }
    public   DatePickerDialog.OnDateSetListener dpickListnier=
            new DatePickerDialog.OnDateSetListener(){
                @Override
                public void onDateSet(DatePicker vew, int year, int month, int day){
                    x_days= year;
                    x_month=month;
                    x_days=day;
                    tvDate.setText(day+"/"+month+"/"+year);
                }

            };
}
//
//try {
//        mydb = getBaseContext().openOrCreateDatabase("test.db", MODE_PRIVATE, null);
//
//        Cursor query= mydb.rawQuery("select * from history1 where ID ='"+ID2+"';", null);
//        if (query.moveToFirst()) {
//
//        listDatePay.add(query.getString(1));
//        listAmount.add(query.getString(2));
//        while (query.moveToNext()) {
//        listDatePay.add(query.getString(1));
//        listAmount.add(query.getString(2));
//        }
//        rcvHistory.setLayoutManager(new LinearLayoutManager(this));
//        rcvHistory.setAdapter(new HistoryAdapter(listDatePay, listAmount));
//
//        }
//        else {
//        Toast.makeText(this, "No record is found", Toast.LENGTH_LONG).show();
//        }
//
//        }
//        catch (Exception e)
//        {
//        Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
//        }