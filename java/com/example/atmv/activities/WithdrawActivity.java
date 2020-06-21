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
import com.example.atmv.requests.WithdrawMoneyRequest;
import com.example.atmv.responses.WithdrawMoneyResponse;

public class WithdrawActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw);

        final EditText wtdAmountTxt=findViewById(R.id.wtdAmountTxt);
        final TextView wtdBalanceTxt=findViewById(R.id.wtdBalanceTxt);
        final Button wtdBtn=findViewById(R.id.wtdBtn);

        Intent intent = getIntent();
        final Long id = intent.getLongExtra("id",0);

        wtdBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                long amount=Long.parseLong(wtdAmountTxt.getText().toString());

                WithdrawMoneyRequest request=new WithdrawMoneyRequest(id,amount);
                WithdrawMoneyResponse response=request.withdraw(id,amount);
                if(response.getSuccess()){
                    wtdBalanceTxt.setText(Long.toString(response.getNewBalance())+" ₺");
                }else{
                    wtdBalanceTxt.setText(response.getMessage());
                }


            }
        });

    }
}
