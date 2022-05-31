package com.example.cafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class CreateOrderActivity extends AppCompatActivity {
    private CheckBox checkBoxMilk;
    private CheckBox checkBoxSugar;
    private CheckBox checkBoxLemon;
    private Spinner spinnerTea;
    private Spinner spinnerCoffee;
    private TextView textViewAdditions;
    private EditText editTextNumberTable;

    private StringBuilder builderAdditions;

    private String name;
    private String drink;
    private String numberTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_order);
        drink = getString(R.string.tea);
        Intent intent = getIntent();
        if(intent.hasExtra("password") && intent.hasExtra("name")) {
            name = intent.getStringExtra("name");
            String password = intent.getStringExtra("password");
        }else {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
        String hello = String.format(getString(R.string.order_question), name);

        builderAdditions = new StringBuilder();
        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        RadioButton radioButtonTea = findViewById(R.id.radioButtonTea);
        RadioButton radioButtonCoffee = findViewById(R.id.radioButtonCoffee);
        checkBoxMilk = findViewById(R.id.checkBoxMilk);
        checkBoxSugar = findViewById(R.id.checkBoxSugar);
        checkBoxLemon = findViewById(R.id.checkBoxLemon);
        spinnerTea = findViewById(R.id.spinnerTea);
        spinnerCoffee = findViewById(R.id.spinnerCoffee);
        Button buttonCreate = findViewById(R.id.buttonCreate);
        TextView textViewHello = findViewById(R.id.textViewHello);
        textViewAdditions = findViewById(R.id.textViewAdditions);
        editTextNumberTable = findViewById(R.id.editTextNumberTable);
        textViewHello.setText(hello);


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.radioButtonTea){
                    drink = getString(R.string.tea);
                    spinnerTea.setVisibility(View.VISIBLE);
                    spinnerCoffee.setVisibility(View.INVISIBLE);
                    checkBoxLemon.setVisibility(View.VISIBLE);
                }else if(i == R.id.radioButtonCoffee){
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
                numberTable = editTextNumberTable.getText().toString().trim();
                if (!numberTable.isEmpty()) {
                    builderAdditions.setLength(0);
                    if (checkBoxMilk.isChecked()) {
                        builderAdditions.append(getString(R.string.milk)).append(" ");
                    }
                    if (checkBoxSugar.isChecked()) {
                        builderAdditions.append(getString(R.string.sugar)).append(" ");
                    }
                    if (checkBoxLemon.isChecked() && drink.equals(getString(R.string.tea))) {
                        builderAdditions.append(getString(R.string.lemon)).append(" ");
                    }
                    String optionOfDrink = "";
                    if (drink.equals(getString(R.string.tea))){
                        optionOfDrink = spinnerTea.getSelectedItem().toString();
                    }else {
                        optionOfDrink = spinnerCoffee.getSelectedItem().toString();
                    }
                    String order = String.format(getString(R.string.order), name, numberTable, drink, optionOfDrink);
                    String additions;
                    if (builderAdditions.length() > 0){
                        additions = getString(R.string.additions)+ builderAdditions.toString();
                    }else {
                        additions = "";
                    }
                    String fullOrder = order+additions;

                    Intent intentOrder = new Intent(getApplicationContext(), OrderDitailActivity.class);
                    intentOrder.putExtra("viber", fullOrder);
                    intentOrder.putExtra("order", name);
                    startActivity(intentOrder);
                }else {
                    Toast.makeText(CreateOrderActivity.this, R.string.table_toast, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}