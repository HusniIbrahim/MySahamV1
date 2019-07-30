package com.example.katak.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;

public class Main5Activity extends AppCompatActivity {


    TextView Profit,TotalShare,BuyPrice,Broker,Answer,BrokerName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        Profit=findViewById(R.id.Profit);
        Profit.setText(getIntent().getExtras().getString("Profit"));
        BrokerName=findViewById(R.id.BrokerName);
        BrokerName.setText(getIntent().getExtras().getString("Spinner"));
        Broker=findViewById(R.id.Broker);
        Broker.setText(getIntent().getExtras().getString("Spinner"));
        Broker.setText(String.valueOf(IdentifyBroker()));
        TotalShare=findViewById(R.id.TotalShare);
        TotalShare.setText(getIntent().getExtras().getString("TotalShare"));
        BuyPrice=findViewById(R.id.BuyPrice);
        BuyPrice.setText(getIntent().getExtras().getString("BuyPrice"));

        //Declare all input into double
        double profit=Double.parseDouble(Profit.getText().toString());
        double broker=Double.parseDouble(Broker.getText().toString());
        double buyPrice=Double.parseDouble(BuyPrice.getText().toString());
        double total_share=Double.parseDouble(TotalShare.getText().toString());
        double CleareanceFee=0.0003;

        //Calculate Buy Price Part
        double BuyPrice_Proceed=total_share*buyPrice;
        double BuyPrice_BrokerageFee=Check_BuyPrice_BrokerageFee(broker,buyPrice,total_share);
        double BuyPrice_StampDuty=Check_BuyPrice_StampDuty(total_share,buyPrice);
        double BuyPrice_ClearenceFee=total_share*buyPrice*CleareanceFee;
        double BuyPrice_ContractPrice=BuyPrice_Proceed+BuyPrice_BrokerageFee+BuyPrice_StampDuty+BuyPrice_ClearenceFee;

        //Calculate Sell Price Part
        double SellPrice_Proceed=total_share;
        double Pre_SellPrice_BrokerageFee=Pre_Check_SellPrice_BrokerageFee(broker,total_share);
        double Pre_SellPrice_StampDuty=Pre_Check_SellPrice_StampDuty(total_share);
        double SellPrice_ClearenceFee=total_share*CleareanceFee;
        double Pre_SellPrice_ContractPrice=SellPrice_Proceed-Pre_SellPrice_BrokerageFee-Pre_SellPrice_StampDuty-SellPrice_ClearenceFee;
        double Pre_SellPrice=(BuyPrice_ContractPrice+profit)/Pre_SellPrice_ContractPrice;
        double SellPrice_BrokerageFee=Check_SellPrice_BrokerageFee(broker,Pre_SellPrice,total_share);
        double SellPrice_StampDuty=Check_SellPrice_StampDuty(total_share,Pre_SellPrice);
        double SellPrice_ContractPrice=SellPrice_Proceed+SellPrice_ClearenceFee;
        double SellPrice=(BuyPrice_ContractPrice+profit+SellPrice_BrokerageFee+SellPrice_StampDuty)/SellPrice_ContractPrice;

        Answer=findViewById(R.id.Answer);
        Answer.setText(String.format("%.2f",SellPrice));
        Profit.setText(String.format("%.2f",SellPrice_ClearenceFee));
        BuyPrice.setText(String.format("%.2f",SellPrice_ContractPrice));
        Broker.setText(String.format("%.2f",Pre_SellPrice_StampDuty));
        TotalShare.setText(String.format("%.2f",Pre_SellPrice_BrokerageFee));

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
    //Buy Price Function
    public double Check_BuyPrice_BrokerageFee(Double x, Double y, Double z) {
        double answer;

        if (x==0.001)
        {
            answer=y*z*x;
            if(answer<8)
            {
                answer=8.47;
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
    public double Check_BuyPrice_StampDuty(Double x,Double y)
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
        }
        return  a;
    }
    //Sell Price Function
    public double Pre_Check_SellPrice_BrokerageFee(double x,double y)
    {
        double answer;
        if (x==0.001)
        {
            answer=y*x;

        }
        else
        {
            answer=10.0;
        }
        return answer;
    }
    public double Pre_Check_SellPrice_StampDuty(Double y)
    {
        int a,b;
        double Y=y;
        double total=y;
        if (total%1000==0) {
            int TotalShare= (int) Y;
            a = TotalShare / 1000;
        }
        else
        {
            int TotalShare= (int) Y;
            b = TotalShare/ 1000;
            a=b+1;
        }
        return  a;
    }
    //Finalise real Sell Price Function
    public double Check_SellPrice_BrokerageFee(Double x, Double y, Double z) {
        double answer;

        if (x==0.001)
        {
            answer=y*z*x;
            if(answer<8)
            {
                answer=8.47;
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
    public double Check_SellPrice_StampDuty(Double x,Double y)
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
        }
        return  a;
    }
}

