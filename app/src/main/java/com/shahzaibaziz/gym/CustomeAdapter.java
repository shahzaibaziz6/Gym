package com.shahzaibaziz.gym;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class CustomeAdapter extends RecyclerView.Adapter<CustomeAdapter.MyViewHolder> {

   public LinearLayout linearLayout;
    private List<String> listName;
    private  List <String> listAddress;
    private  List <String> listPhone;
    Context context;
    SQLiteDatabase myDB1;
    public    String PrimeryKey;
    Cursor cursor;


    public CustomeAdapter(Context con,Cursor cur) {
        cursor=cur;
        context=con;

    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


         View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);




        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
//
//        String name = listName.get(position);
//        String adddress= listAddress.get(position);
//        String phone = listPhone.get(position);
//        holder.tvNameList.setText(name.toString());
//        holder.tvAddressList.setText(adddress.toString());
//        holder.tvListPhone.setText(phone.toString());


        if(!cursor.moveToPosition(position))
        {
            return;
        }
        String name1=cursor.getString(cursor.getColumnIndex(GymDataBaseContract.GymModel.FIRST_NAME));
        String lastName=cursor.getString(cursor.getColumnIndex(GymDataBaseContract.GymModel.LAST_NAME));
        String Phone = cursor.getString(cursor.getColumnIndex(GymDataBaseContract.GymModel.PHONE_NUMBER));
        String Address= cursor.getString(cursor.getColumnIndex(GymDataBaseContract.GymModel.ADDRESS));
        holder.tvNameList.setText(name1+" "+lastName);
        holder.tvListPhone.setText(Phone);
        holder.tvAddressList.setText(Address);



    }

    @Override
    public int getItemCount() {


        return cursor.getCount();

    }



    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvNameList,tvAddressList,tvListPhone;

        public MyViewHolder(View itemView) {
            super(itemView);

            tvNameList= itemView.findViewById(R.id.tv_list_item_name);
            tvAddressList= itemView.findViewById(R.id.tv_list_item_address);
            linearLayout= itemView.findViewById(R.id.ll_list_item_parent);
            tvListPhone= itemView.findViewById(R.id.tv_list_item_phone);
//            linearLayout.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    String searchFirstName ,searchSecondName,searchPhoneNumber,searchAddress;
//                    String []arr =tvNameList.getText().toString().split(" ");
//                    searchFirstName=arr[0];
//                    searchSecondName= arr[1];
//                    searchPhoneNumber=tvListPhone.getText().toString();
//                    searchAddress= tvAddressList.getText().toString();
//
//
//
//                    try {
//                        myDB1 = context.openOrCreateDatabase("test1.db", MODE_PRIVATE, null);
//
//
//                        Cursor query123 = myDB1.rawQuery("select * from newentry124 where " + "firstName" + " ='" + searchFirstName + "' AND "+ "secondName"+ " ='" + searchSecondName + "' AND "+ "phoneNumber" + " ='" +searchPhoneNumber+"' AND "+ "address" +" ='"+searchAddress+"';", null);
//
//                        if (query123.moveToFirst()) {
//                            PrimeryKey=String.valueOf(query123.getInt(0));
//                            Toast.makeText(context,PrimeryKey, Toast.LENGTH_SHORT).show();
//                            Intent intent = new Intent(context,Payment_and_history.class);
//                            intent.putExtra("pri",PrimeryKey);
//                            context.startActivity(intent);
//
//
//
//                        }
//                        else
//                        {
//                            Toast.makeText(context, "No 678 record Found", Toast.LENGTH_SHORT).show();
//                        }
//
//
//                    }
//                    catch (Exception e)
//                    {
//                        Toast.makeText(context,e.getMessage(),Toast.LENGTH_LONG).show();
//                    }
//
//                }
//            });
//
        }
    }


}
