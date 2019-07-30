package com.example.katak.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class Main4Activity extends AppCompatActivity {

    Button button;
    EditText Profit, BuyPrice, TotalShare;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        spinner = findViewById(R.id.spinner);
        List<String> list = new ArrayList<String>();
        list.add(0, "Maybank");
        list.add("BIMB");
        list.add("Hong Leong");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //   String searchItem = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Profit = findViewById(R.id.Profit);
        BuyPrice = findViewById(R.id.BuyPrice);
        TotalShare = findViewById(R.id.TotalShare);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateFields();
            }
        });
    }

    public void calculate() {
        Intent intent = new Intent(this, Main5Activity.class);
        intent.putExtra("Profit", Profit.getText().toString());
        intent.putExtra("BuyPrice", BuyPrice.getText().toString());
        intent.putExtra("TotalShare", TotalShare.getText().toString());
        intent.putExtra("Spinner", spinner.getSelectedItem().toString());
        startActivity(intent);

    }

    private boolean validateFields() {
        int yourDesiredLength = 1;
        int minimum_share=1000;
        if (Profit.getText().length() < yourDesiredLength || BuyPrice.getText().length() < yourDesiredLength || TotalShare.getText().length() < yourDesiredLength) {
            if (Profit.getText().length() < yourDesiredLength) {
                Profit.setError("Your Input is Invalid");
            }
            if (BuyPrice.getText().length() < yourDesiredLength) {
                BuyPrice.setError("Your Input is Invalid");
            }
            if (TotalShare.getText().length() < minimum_share) {
                TotalShare.setError("Your Input is Invalid");
            }
            return false;
        }
        else {
            calculate();
            return true;
        }
    }
}