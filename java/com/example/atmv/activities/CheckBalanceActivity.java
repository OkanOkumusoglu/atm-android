package com.example.atmv.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.atmv.R;
import com.example.atmv.requests.BalanceInfoRequest;
import com.example.atmv.responses.BalanceInfoResponse;

public class CheckBalanceActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_balance);

        final TextView balanceTxt=findViewById(R.id.createBalanceTxt);
        final Button balanceBtn=findViewById(R.id.balanceBtn);

        Intent intent = getIntent();
        final Long id = intent.getLongExtra("id",0);

        balanceBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                BalanceInfoRequest request=new BalanceInfoRequest(id);
                BalanceInfoResponse response=request.balance(id);
                balanceTxt.setText(Long.toString(response.getBalance())+" ₺");

            }
        });

    }
}
