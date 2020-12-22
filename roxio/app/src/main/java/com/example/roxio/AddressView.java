package com.example.roxio;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AddressView extends AppCompatActivity {
    public String addressSon;
    private static final int earthRadius = 6371;
    TextView textView;
    Button button;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lokasyonlar);
        Intent intent = getIntent();
        String data = intent.getStringExtra("myLatitude");
        String data1 = intent.getStringExtra("myLongtude");
        String data2 = intent.getStringExtra("newLatitude");
        String data3 = intent.getStringExtra("newLongtude");
        double lati1 = Double.parseDouble(data);
        double longi1 = Double.parseDouble(data1);
        double lati2 = Double.parseDouble(data2);
        double longi2 = Double.parseDouble(data3);
        button=findViewById(R.id.buttonConfirm);
        addressSon =intent.getStringExtra("address1");
        textView=findViewById(R.id.textPlace);
        textView.setText(addressSon);
        button=findViewById(R.id.buttonDone);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(getApplicationContext(), RouteMap.class);
                intent1.putExtra("lat1",data);
                intent1.putExtra("long1",data1);
                intent1.putExtra("lat2",data2);
                intent1.putExtra("long2",data3);
                intent1.putExtra("addressSon",addressSon);
                startActivity(intent1);
            }
        });
    }
}
