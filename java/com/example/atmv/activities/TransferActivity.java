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
import com.example.atmv.requests.TransferMoneyRequest;
import com.example.atmv.responses.TransferMoneyResponse;

public class TransferActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);

        final EditText transferAmountTxt=findViewById(R.id.transferAmountTxt);
        final EditText receiverIdTxt=findViewById(R.id.receiverIdTxt);
        final TextView transferMsgTxt=findViewById(R.id.transferMsgTxt);
        final Button transferBtn=findViewById(R.id.transferBtn);

        Intent intent = getIntent();
        final Long id = intent.getLongExtra("id",0);

        transferBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                long receiverId=Long.parseLong(receiverIdTxt.getText().toString());
                long amount=Long.parseLong(transferAmountTxt.getText().toString());

                TransferMoneyRequest request=new TransferMoneyRequest(id,receiverId,amount);
                TransferMoneyResponse response=request.transfer(id,receiverId,amount);

                if(response.getSuccess()){
                    transferMsgTxt.setText(Long.toString(response.getSenderNewBalance())+" ₺");
                }else{
                    transferMsgTxt.setText(response.getMessage());
                }





            }
        });

    }
}
