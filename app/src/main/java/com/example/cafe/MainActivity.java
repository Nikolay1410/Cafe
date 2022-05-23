package com.example.cafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{
    private EditText editTextTextPersonName;
    private EditText editTextTextPassword;
    private Button buttonRegister;
    private TextView goToRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextTextPersonName = findViewById(R.id.editTextTextPersonName);
        editTextTextPassword = findViewById(R.id.editTextTextPassword);
        buttonRegister = findViewById(R.id.buttonRegister);
        goToRegister = findViewById(R.id.goToRegister);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextTextPersonName.getText().toString().trim();
                String password = editTextTextPersonName.getText().toString().trim();
                if (!name.isEmpty() && !password.isEmpty()) {
                    Intent intent = new Intent(getApplicationContext(), CreateOrderActivity.class);
                    intent.putExtra("name", name);
                    intent.putExtra("password", password);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, R.string.warning_fill_fields, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}