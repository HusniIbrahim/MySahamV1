package com.example.katak.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {


    TextView SellPrice,TotalShare,BuyPrice,Broker,Answer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);


        SellPrice=findViewById(R.id.SellPrice);
        SellPrice.setText(getIntent().getExtras().getString("SellPrice"));
        Broker=findViewById(R.id.Broker);
        Broker.setText(getIntent().getExtras().getString("Spinner"));
        Broker.setText(String.valueOf(IdentifyBroker()));
        TotalShare=findViewById(R.id.TotalShare);
        TotalShare.setText(getIntent().getExtras().getString("TotalShare"));
        BuyPrice=findViewById(R.id.BuyPrice);
        BuyPrice.setText(getIntent().getExtras().getString("BuyPrice"));
        Answer=findViewById(R.id.Answer);


        //Declare all input into double
        double sellPrice=Double.parseDouble(SellPrice.getText().toString());
        double broker=Double.parseDouble(Broker.getText().toString());
        double buyPrice=Double.parseDouble(BuyPrice.getText().toString());
        double total_share=Double.parseDouble(TotalShare.getText().toString());
        double CleareanceFee=0.0003;

        //Calculate Buy Price Part
        double BuyPrice_Proceed=buyPrice*total_share;
        double BuyPrice_BrokerageFee=Check_BrokerageFee(broker,buyPrice,total_share);
        double BuyPrice_StampDuty=Check_StampDuty(total_share,buyPrice);
        double BuyPrice_ClearenceFee=total_share*buyPrice*CleareanceFee;
        double BuyPrice_ContractPrice=BuyPrice_Proceed+BuyPrice_BrokerageFee+BuyPrice_StampDuty+BuyPrice_ClearenceFee;

        //Calculate Sell Price Part
        double SellPrice_Proceed=sellPrice*total_share;
        double SellPrice_BrokerageFee=Check_BrokerageFee(broker,sellPrice,total_share);
        double SellPrice_StampDuty=Check_StampDuty(total_share,sellPrice);
        double SellPrice_ClearenceFee=total_share*sellPrice*CleareanceFee;
        double SellPrice_ContractPrice=SellPrice_Proceed-SellPrice_BrokerageFee-SellPrice_StampDuty-SellPrice_ClearenceFee;

        //Calculate Answer
        double answer=SellPrice_ContractPrice-BuyPrice_ContractPrice;

        //Show Output
        Answer.setText(String.format("%.2f",answer));
        Broker.setText(String.format("%.4f",broker));
        BuyPrice.setText(String.format("%.2f",buyPrice));
        SellPrice.setText(String.format("%.2f",sellPrice));
        TotalShare.setText(String.format("%.2f",total_share));
    }
    public double IdentifyBroker()
    {

        double ans;
        if (Broker.getText().toString().equals("Maybank"))
        {
            ans=0.001;
        }
        else
        {
            ans=0.003;
        }
        return ans;
    }
    public double Check_StampDuty(Double x,Double y)
    {
        int a,b;
        double X=x,Y=y;
        double total=x*y;
        if (total%1000==0) {
            int TotalShare = (int) X;
            int BuyPrice = (int) Y;
            a = (TotalShare * BuyPrice) / 1000;
        }
        else
        {
            int TotalShare = (int) X;
            int BuyPrice = (int) Y;
            b = (TotalShare * BuyPrice) / 1000;
            a=b+1;
            if (a<1)
            {
                a=1;
            }
        }
        return  a;
    }
    public double Check_BrokerageFee(Double x, Double y, Double z) {
        double answer;

        if (x==0.001)
        {
            answer=y*z*x;
            if(answer<8)
            {
                answer=8.48;
            }
            else
            {
                answer=x*y*z+((x*y*z)*0.006);
            }
        }
        else
        {
            answer=10.0;
        }
        return answer;
    }
}

