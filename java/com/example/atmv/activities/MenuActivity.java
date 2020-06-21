package com.example.atmv.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.atmv.R;

public class MenuActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button wtdBtn=findViewById(R.id.wtdBtn);
        Button balanceBtn=findViewById(R.id.balanceBtn);
        Button depBtn=findViewById(R.id.depBtn);
        Button transferBtn=findViewById(R.id.transferBtn);


        Intent intent = getIntent();
        final Long id = intent.getLongExtra("id",0);

        wtdBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(MenuActivity.this, WithdrawActivity.class);
                myIntent.putExtra("id",id);
                MenuActivity.this.startActivity(myIntent);

            }
        });

        depBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(MenuActivity.this, DepositActivity.class);
                myIntent.putExtra("id",id);
                MenuActivity.this.startActivity(myIntent);

            }
        });

        balanceBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(MenuActivity.this, CheckBalanceActivity.class);
                myIntent.putExtra("id",id);
                MenuActivity.this.startActivity(myIntent);

            }
        });

        transferBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(MenuActivity.this, TransferActivity.class);
                myIntent.putExtra("id",id);
                MenuActivity.this.startActivity(myIntent);
            }
        });

    }
}
