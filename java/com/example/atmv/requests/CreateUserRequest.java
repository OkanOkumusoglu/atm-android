package com.example.atmv.requests;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.atmv.responses.CreateUserResponse;
import com.example.atmv.responses.WithdrawMoneyResponse;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class CreateUserRequest {

    private String name;
    private String surname;
    private String password;
    private long balance;

    public CreateUserRequest(String name,String surname,String password,long balance){
        this.name=name;
        this.surname=surname;
        this.password=password;
        this.balance=balance;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public CreateUserResponse create(String name, String surname,String password,String chkPass,long balance){
        CreateUserResponse finalResponse = null;
        try  {
            URL url= null;
            try {
                Gson g = new Gson();
                url = new URL("http://192.168.1.36:8080/app-api/users/create");
                HttpURLConnection con = (HttpURLConnection)url.openConnection();
                con.setRequestMethod("POST");
                con.setRequestProperty("Content-Type", "application/json; utf-8");
                con.setRequestProperty("Accept", "application/json");
                con.setDoOutput(true);
                CreateUserRequest request=new CreateUserRequest(name,surname,password,balance);
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
                    System.out.println(response);
                    finalResponse = g.fromJson(response.toString(), CreateUserResponse.class);
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
