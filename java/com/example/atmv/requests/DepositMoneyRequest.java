package com.example.atmv.requests;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.atmv.responses.DepositMoneyResponse;
import com.example.atmv.responses.WithdrawMoneyResponse;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DepositMoneyRequest {

    private long id;
    private long amount;

    public DepositMoneyRequest(long id,long amount){
        this.id=id;
        this.amount=amount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public DepositMoneyResponse deposit(long id, long amount){
        DepositMoneyResponse finalResponse = null;
        try  {
            URL url= null;
            try {
                Gson g = new Gson();
                url = new URL("http://192.168.1.36:8080/app-api/users/deposit");
                HttpURLConnection con = (HttpURLConnection)url.openConnection();
                con.setRequestMethod("POST");
                con.setRequestProperty("Content-Type", "application/json; utf-8");
                con.setRequestProperty("Accept", "application/json");
                con.setDoOutput(true);
                DepositMoneyRequest request=new DepositMoneyRequest(id,amount);
                String  jsonInputString=g.toJson(request);

                try(OutputStream os = con.getOutputStream()) {
                    byte[] input = jsonInputString.getBytes("utf-8");
                    os.write(input, 0, input.length);
                }
                try(BufferedReader br = new BufferedReader(
                        new InputStreamReader(con.getInputStream(), "utf-8"))) {
                    StringBuilder response = new StringBuilder();
                    String responseLine = null;
                    while ((responseLine = br.readLine()) != null) {
                        response.append(responseLine.trim());
                    }
                    finalResponse = g.fromJson(response.toString(), DepositMoneyResponse.class);
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return finalResponse;
    }

}
