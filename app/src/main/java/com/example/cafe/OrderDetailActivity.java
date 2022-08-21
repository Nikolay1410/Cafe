package com.example.cafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class OrderDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_ditail);
        TextView textViewExpectation = findViewById(R.id.textViewExpectation);
        DownloadTask task = new DownloadTask();
        Intent intent  = getIntent();

        if(intent.hasExtra("order") && intent.hasExtra("viber") ){
            String order = String.format(getString(R.string.text_expectation), intent.getStringExtra("order"));
            String botText = "chat_id=958180426&text="+intent.getStringExtra("viber");
            URI uri = null;
            try {
                uri = new URI(
                        "https",
                        "api.telegram.org",
                        "/bot5313472131:AAF6L-Qwqc7bv9toBXitat7qO1lBH8BbWHU/sendMessage",
                        botText,
                        null);
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            assert uri != null;
            String uriCon = uri.toString();
            try {
                String result = task.execute(uriCon).get();
                if (!result.isEmpty()){
                    textViewExpectation.setText(order);
                }else {
                    Intent backLogin = new Intent(this, MainActivity.class);
                    startActivity(backLogin);
                    Toast.makeText(this, R.string.error, Toast.LENGTH_SHORT).show();
                }
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }else {
            Intent backLogin = new Intent(this, MainActivity.class);
            startActivity(backLogin);
            Toast.makeText(this, R.string.error, Toast.LENGTH_SHORT).show();
        }
    }
    public static class DownloadTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            StringBuilder result = new StringBuilder();
            URL url;
            HttpURLConnection urlConnection = null;
            try {
                url = new URL(strings[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                BufferedReader bufferedReader = new BufferedReader(reader);
                String line = bufferedReader.readLine();
                while (line!=null){
                    result.append(line);
                    line = bufferedReader.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (urlConnection!=null){
                    urlConnection.disconnect();
                }
            }
            return result.toString();
        }
    }
}