package com.example.cafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class OrderDitailActivity extends AppCompatActivity {
    private TextView textViewExpectation;

    private static String order;
    private static HttpURLConnection con;
    private static String tgToken = "5313472131:AAF6L-Qwqc7bv9toBXitat7qO1lBH8BbWHU";
    private static int  chatId = 958180426;
    private static String urlToken = "https://api.telegram.org/bot"+tgToken+"/sendMessage";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_ditail);
        textViewExpectation = findViewById(R.id.textViewExpectation);
        Viber viber = new Viber();

        Intent intent  = getIntent();
        if(intent.hasExtra("order")){
            String order = String.format(getString(R.string.text_expectation), intent.getStringExtra("order"));
            textViewExpectation.setText(order);
            String bot = String.format("https://api.telegram.org/bot5313472131:AAF6L-Qwqc7bv9toBXitat7qO1lBH8BbWHU/sendMessage?chat_id=958180426&text=%s", order);
            Log.i("jjj", bot);
            Uri.parse(bot);

        }else {
            Intent backLogin = new Intent(this, MainActivity.class);
            startActivity(backLogin);
            Toast.makeText(this, R.string.error, Toast.LENGTH_SHORT).show();
        }

    }
}