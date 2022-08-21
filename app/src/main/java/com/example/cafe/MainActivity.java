package com.example.cafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{
    private EditText editTextTextPersonName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextTextPersonName = findViewById(R.id.editTextTextPersonName);
        Button buttonRegister = findViewById(R.id.buttonRegister);
        TextView goToRegister = findViewById(R.id.goToRegister);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String name = preferences.getString("name", null);
        if (name != null){
            editTextTextPersonName.setText(name);
        }


        buttonRegister.setOnClickListener(view -> {
            String name1 = editTextTextPersonName.getText().toString().trim();
            SharedPreferences preferences1 = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            preferences1.edit().putString("name", name1).apply();
            if (!name1.isEmpty()) {
                Intent intent = new Intent(getApplicationContext(), CreateOrderActivity.class);
                intent.putExtra("name", name1);
                startActivity(intent);
            } else {
                Toast.makeText(MainActivity.this, R.string.warning_fill_fields, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onClickRegister(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}