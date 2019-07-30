package com.example.katak.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button SELL,BUY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SELL=findViewById(R.id.SELL);
        BUY=findViewById(R.id.BUY);
        SELL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMain2Activity();
            }
        });
        BUY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMain4Activity();
            }
        });

    }
    public void openMain2Activity()
    {
        Intent intent=new Intent(this,Main2Activity.class);
        startActivity(intent);

    }
    public void openMain4Activity()
    {
        Intent intent=new Intent(this,Main4Activity.class);
        startActivity(intent);

    }
}
