package com.shahzaibaziz.gym;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener
{

    TextView tvNewEntry,tvSearchEntry,tvDeleteEntry;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tvDeleteEntry= findViewById(R.id.tv_home_delete_entry);
        tvNewEntry= findViewById(R.id.tv_home_new_entry);
        tvSearchEntry= findViewById(R.id.tv_home_search_entry);
        tvSearchEntry.setOnClickListener(this);
        tvDeleteEntry.setOnClickListener(this);
        tvNewEntry.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent =null;
        switch (v.getId())
        {
            case(R.id.tv_home_new_entry):
            {
                intent= new Intent(this,NewEntry.class);
                break;
            }

            case(R.id.tv_home_search_entry):
            {
                intent= new Intent(this,SearchActivity.class);
                break;
            }

            case(R.id.tv_home_delete_entry):
            {
                intent= new Intent(this,DeleteActivity.class);
                break;
            }
        }
        if(intent!=null)
        {
            startActivity(intent);
        }

    }
}
