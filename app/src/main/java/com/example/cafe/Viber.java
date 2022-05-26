package com.example.cafe;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;


public class Viber extends AppCompatActivity {

    private static String order;
    private static HttpURLConnection con;
    private static String tgToken = "5313472131:AAF6L-Qwqc7bv9toBXitat7qO1lBH8BbWHU";
    private static int  chatId = 958180426;
    private static String urlToken = "https://api.telegram.org/bot"+tgToken+"/sendMessage";

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        Intent intent = getIntent();
        if (intent.hasExtra("viber")){
            order = intent.getStringExtra("viber");
        }
    }
    public static void main(String[] args) throws IOException{
        //текст сообщения
        String txt = order;

        String urlParameters = "chat_id="+chatId+"&text="+order;
        byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);

        try {

            URL url = new URL(urlToken);
            con = (HttpURLConnection) url.openConnection();

            con.setDoOutput(true);
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", "Java upread.ru client");
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                wr.write(postData);
            }

            StringBuilder content;

            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(con.getInputStream()))) {
                String line;
                content = new StringBuilder();

                while ((line = br.readLine()) != null) {
                    content.append(line);
                    content.append(System.lineSeparator());
                }
            }
            System.out.println(content.toString());

        } finally {
            con.disconnect();
        }
    }
}
