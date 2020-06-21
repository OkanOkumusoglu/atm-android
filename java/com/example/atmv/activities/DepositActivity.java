package com.example.atmv.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.atmv.R;
import com.example.atmv.requests.DepositMoneyRequest;
import com.example.atmv.responses.DepositMoneyResponse;

public class DepositActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit);

        final EditText depAmountTxt=findViewById(R.id.depAmountTxt);
        final TextView depBalanceTxt=findViewById(R.id.depBalanceTxt);
        final Button depBtn=findViewById(R.id.depBtn);

        Intent intent = getIntent();
        final Long id = intent.getLongExtra("id",0);

        depBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                long amount=Long.parseLong(depAmountTxt.getText().toString());

                DepositMoneyRequest request=new DepositMoneyRequest(id,amount);
                DepositMoneyResponse response=request.deposit(id,amount);
                depBalanceTxt.setText(Long.toString(response.getNewBalance())+" ₺");

            }
        });

    }
}
