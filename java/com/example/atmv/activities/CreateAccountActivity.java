package com.example.atmv.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.atmv.R;
import com.example.atmv.requests.CreateUserRequest;
import com.example.atmv.responses.CreateUserResponse;

public class CreateAccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        final EditText nameTxt=findViewById(R.id.nameTxt);
        final EditText surnameTxt=findViewById(R.id.surnameTxt);
        final EditText passTxt=findViewById(R.id.createPassTxt);
        final EditText chkPassTxt=findViewById(R.id.chkPassTxt);
        final EditText balanceTxt=findViewById(R.id.createBalanceTxt);
        final Button createActBtn=findViewById(R.id.createActBtn);
        final TextView msgTxt=findViewById(R.id.createActMsgTxt);


        createActBtn.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            public void onClick(View v) {
                String name=String.valueOf(nameTxt.getText());
                String surname=String.valueOf(surnameTxt.getText());
                String pass=String.valueOf(passTxt.getText());
                String chkPass=String.valueOf(chkPassTxt.getText());
                long balance=Long.parseLong(balanceTxt.getText().toString());

                if(pass.equals(chkPass)){
                    CreateUserRequest request=new CreateUserRequest(name,surname,pass,balance);
                    CreateUserResponse response=request.create(name,surname,pass,chkPass,balance);
                    msgTxt.setText("Welcome "+response.getName() +" "+ response.getSurname()
                            +" Your account is successfully created! Your id is "+response.getId());
                    nameTxt.getText().clear();
                    surnameTxt.getText().clear();
                    passTxt.getText().clear();
                    chkPassTxt.getText().clear();
                    balanceTxt.getText().clear();
                }else{
                    msgTxt.setText("Password Mismatch");
                }



            }
        });

    }

}
