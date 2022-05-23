package com.example.cafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class CreateOrderActivity extends AppCompatActivity {
    private RadioGroup radioGroup;
    private RadioButton radioButtonTea;
    private RadioButton radioButtonCoffee;
    private CheckBox checkBoxMilk;
    private CheckBox checkBoxSugar;
    private CheckBox checkBoxLemon;
    private Spinner spinnerTea;
    private Spinner spinnerCoffee;
    private Button buttonCreate;
    private TextView textViewHello;
    private TextView textViewAdditions;

    private StringBuilder builderAdditions;

    private String name;
    private String password;
    private String drink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_order);
        drink = getString(R.string.tea);
        Intent intent = getIntent();
        if(intent.hasExtra("password") && intent.hasExtra("name")) {
            name = intent.getStringExtra("name");
            password = intent.getStringExtra("password");
        }else {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }

        String hello = String.format(getString(R.string.order_question), name);
        builderAdditions = new StringBuilder();
        radioGroup = findViewById(R.id.radioGroup);
        radioButtonTea = findViewById(R.id.radioButtonTea);
        radioButtonCoffee = findViewById(R.id.radioButtonCoffee);
        checkBoxMilk = findViewById(R.id.checkBoxMilk);
        checkBoxSugar = findViewById(R.id.checkBoxSugar);
        checkBoxLemon = findViewById(R.id.checkBoxLemon);
        spinnerTea = findViewById(R.id.spinnerTea);
        spinnerCoffee = findViewById(R.id.spinnerCoffee);
        buttonCreate = findViewById(R.id.buttonCreate);
        textViewHello = findViewById(R.id.textViewHello);
        textViewAdditions = findViewById(R.id.textViewAdditions);
        textViewHello.setText(hello);


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int id = radioGroup.getId();
                if (id == R.id.radioButtonTea){
                    drink = getString(R.string.tea);
                    spinnerTea.setVisibility(View.VISIBLE);
                    spinnerCoffee.setVisibility(View.INVISIBLE);
                    checkBoxLemon.setVisibility(View.VISIBLE);
                }else if(id == R.id.radioButtonCoffee){
                    drink = getString(R.string.coffee);
                    spinnerTea.setVisibility(View.INVISIBLE);
                    spinnerCoffee.setVisibility(View.VISIBLE);
                    checkBoxLemon.setVisibility(View.INVISIBLE);
                }
                String additions = String.format(getString(R.string.what_to_add), drink);
                textViewAdditions.setText(additions);
            }
        });

        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}