package com.example.atmv;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.atmv.requests.LoginRequest;
import com.example.atmv.responses.LoginResponse;

public class MainActivity extends AppCompatActivity {



    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText idTxt=findViewById(R.id.wtdIdTxt);
        final EditText passTxt=findViewById(R.id.passTxt);
        final TextView messageTxt=findViewById(R.id.messageTxt);
        final Button loginBtn=findViewById(R.id.loginBtn);


        loginBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                idTxt.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
                passTxt.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
                long id=Long.parseLong(idTxt.getText().toString());
                String pass= String.valueOf((passTxt.getText()));

                LoginRequest request=new LoginRequest(id,pass);
                LoginResponse response=request.login(id,pass);
                boolean isCorrect=response.getSuccess();
                if(isCorrect){
                    Intent myIntent = new Intent(MainActivity.this, MenuActivity.class);
                    myIntent.putExtra("id",request.getId());
                    MainActivity.this.startActivity(myIntent);
                }else{
                    messageTxt.setText("Wrong password!");
                }


            }
        });



    }




}