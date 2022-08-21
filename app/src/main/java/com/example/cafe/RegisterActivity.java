package com.example.cafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutionException;

public class RegisterActivity extends AppCompatActivity {
    private TextView name;
    private TextView number;
    private TextView data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name = findViewById(R.id.editTextTextPersonNameRegister);
        number = findViewById(R.id.editTextPhone);
        data = findViewById(R.id.editTextYouDate);
    }

    public void onClickDataRegister(View view) {
        if (name.length() > 0){
            if (number.length()==9){
                String myData = data.getText().toString().trim();
                int day = Character.getNumericValue(myData.charAt(0));
                int month = Character.getNumericValue(myData.charAt(3));
                int year = Character.getNumericValue(myData.charAt(6));
                if (day<3 && month<2 && year<3 && myData.length() == 10){
                    OrderDetailActivity.DownloadTask task = new OrderDetailActivity.DownloadTask();
                    String myName = name.getText().toString().trim();
                    String myPhone = number.getText().toString().trim();

                    String customer = String.format("Регистрация!!!\nИмя: %s\nТелефон: +380 %s\nДата рожденья: %s", myName, myPhone, myData);
                    String botText = "chat_id=958180426&text="+customer;
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
                    } catch (ExecutionException | InterruptedException e) {
                        e.printStackTrace();
                    }

                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(this, "Спасибо.\nРегистрация прошла успешно.", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this, "Вы ввели не правильный формат даты", Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(this, "Вы ввели не правильный формат номера", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(this, "Введите ваше имя", Toast.LENGTH_SHORT).show();
        }
    }
}