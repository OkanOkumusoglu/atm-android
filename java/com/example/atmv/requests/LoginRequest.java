package com.example.atmv.requests;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.atmv.responses.LoginResponse;
import com.example.atmv.responses.WithdrawMoneyResponse;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class LoginRequest {

    private long id;
    private String password;

    public LoginRequest(long id,String password){
        this.id=id;
        this.password=password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public LoginResponse login(long id, String password){
        LoginResponse finalResponse = null;
        try  {
            URL url= null;
            try {
                Gson g = new Gson();
                url = new URL("http://192.168.1.36:8080/app-api/users/login");
                HttpURLConnection con = (HttpURLConnection)url.openConnection();
                con.setRequestMethod("POST");
                con.setRequestProperty("Content-Type", "application/json; utf-8");
                con.setRequestProperty("Accept", "application/json");
                con.setDoOutput(true);
                LoginRequest request=new LoginRequest(id,password);
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
                    finalResponse = g.fromJson(response.toString(), LoginResponse.class);
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
