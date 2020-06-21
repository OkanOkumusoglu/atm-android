package com.example.atmv.requests;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.atmv.responses.TransferMoneyResponse;
import com.example.atmv.responses.WithdrawMoneyResponse;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class TransferMoneyRequest {

    private long senderId;
    private long receiverId;
    private long amount;

    public TransferMoneyRequest(long senderId,long receiverId,long amount){
        this.senderId=senderId;
        this.receiverId=receiverId;
        this.amount=amount;
    }

    public long getSenderId() {
        return senderId;
    }

    public void setSenderId(long senderId) {
        this.senderId = senderId;
    }

    public long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(long receiverId) {
        this.receiverId = receiverId;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public TransferMoneyResponse transfer(long senderId,long receiverId,long amount){
        TransferMoneyResponse finalResponse = null;
        try  {
            URL url= null;
            try {
                Gson g = new Gson();
                url = new URL("http://192.168.1.36:8080/app-api/users/transfer");
                HttpURLConnection con = (HttpURLConnection)url.openConnection();
                con.setRequestMethod("POST");
                con.setRequestProperty("Content-Type", "application/json; utf-8");
                con.setRequestProperty("Accept", "application/json");
                con.setDoOutput(true);
                TransferMoneyRequest request=new TransferMoneyRequest(senderId,receiverId,amount);
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
                    finalResponse = g.fromJson(response.toString(), TransferMoneyResponse.class);
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
