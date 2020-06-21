package com.example.atmv;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.atmv.requests.BalanceInfoRequest;
import com.example.atmv.requests.DepositMoneyRequest;
import com.example.atmv.requests.LoginRequest;
import com.example.atmv.requests.TransferMoneyRequest;
import com.example.atmv.requests.WithdrawMoneyRequest;
import com.example.atmv.responses.BalanceInfoResponse;
import com.example.atmv.responses.DepositMoneyResponse;
import com.example.atmv.responses.LoginResponse;
import com.example.atmv.responses.TransferMoneyResponse;
import com.example.atmv.responses.WithdrawMoneyResponse;

public class MenuActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        final EditText wtdAmountTxt=findViewById(R.id.wtdAmountTxt);
        final EditText depAmountTxt=findViewById(R.id.depAmountTxt);
        final EditText receiverIdTxt=findViewById(R.id.receiverIdTxt);
        final EditText transferAmountTxt=findViewById(R.id.transferAmountTxt);
        final TextView balanceTxt=findViewById(R.id.balanceTxt);
        final TextView wtdBalanceTxt=findViewById(R.id.wtdBalanceTxt);
        final TextView depBalanceTxt=findViewById(R.id.depBalanceTxt);
        final TextView transferMsgTxt=findViewById(R.id.transferMsgTxt);
        final Button wtdBtn=findViewById(R.id.wtdBtn);
        final Button depBtn=findViewById(R.id.depBtn);
        final Button balanceBtn=findViewById(R.id.balanceBtn);
        final Button transferBtn=findViewById(R.id.transferBtn);



        Intent intent = getIntent();
        final Long id = intent.getLongExtra("id",0);

        wtdBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                long amount=Long.parseLong(wtdAmountTxt.getText().toString());

                WithdrawMoneyRequest request=new WithdrawMoneyRequest(id,amount);
                WithdrawMoneyResponse response=request.withdraw(id,amount);
                wtdBalanceTxt.setText(Long.toString(response.getNewBalance()));

            }
        });

        depBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                long amount=Long.parseLong(depAmountTxt.getText().toString());

                DepositMoneyRequest request=new DepositMoneyRequest(id,amount);
                DepositMoneyResponse response=request.deposit(id,amount);
                depBalanceTxt.setText(Long.toString(response.getNewBalance()));

            }
        });

        balanceBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                BalanceInfoRequest request=new BalanceInfoRequest(id);
                BalanceInfoResponse response=request.balance(id);
                balanceTxt.setText(Long.toString(response.getBalance()));

            }
        });

        transferBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                long receiverId=Long.parseLong(receiverIdTxt.getText().toString());
                long amount=Long.parseLong(transferAmountTxt.getText().toString());

                TransferMoneyRequest request=new TransferMoneyRequest(id,receiverId,amount);
                TransferMoneyResponse response=request.transfer(id,receiverId,amount);
                transferMsgTxt.setText(Long.toString(response.getSenderNewBalance()));

            }
        });

    }
}
