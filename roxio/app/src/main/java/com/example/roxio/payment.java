package com.example.roxio;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class payment extends AppCompatActivity {
    public double km;

    private static double payBykm=0.40;
    TextView textView;
    TextView textView2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment);
        Intent intent=getIntent();
        textView=findViewById(R.id.textViewPrice);
        textView2=findViewById(R.id.textView7);
        Bundle b = getIntent().getExtras();
        String costbykm=Double.toString(payBykm);
        String data1 = b.getString("DATA");
        km=Double.parseDouble(data1);
        textView2.setText("Distance="+data1+" km"+"\n"+"cost per KM="+costbykm+"$");
        String price=String.format("%.2f",CalculatePrice(km));
        String LastPrice="$"+price;


        textView.setText(LastPrice);




    }
    public static Double CalculatePrice(double a){
        return a*payBykm;

    }


}

