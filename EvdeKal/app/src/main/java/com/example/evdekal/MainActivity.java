package com.example.evdekal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText editText=findViewById(R.id.editTextNumber3);
        Button button=findViewById(R.id.button);
        TextView textView=findViewById(R.id.textView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"giriş yapıldı", Toast.LENGTH_SHORT).show();
                int yas=yasHesapla(Integer.parseInt(editText.getText().toString()));
                if (yas<20)
                {
                    if(13>=saathesapla()&&saathesapla()<16){
                        textView.setText("SERBEST");
                    }
                    else
                        textView.setText("YASAK");
                }
                else if(yas>65)
                {
                    if (10>=saathesapla()&&saathesapla()<13)
                    {
                        textView.setText("SERBEST");
                    }
                    else
                        textView.setText("YASAK");
                }
                else
                {
                    if(haftasonu())
                    {
                        if(10>=saathesapla()&&saathesapla()<20)
                        {
                            textView.setText("SERBEST");
                        }
                        else
                            textView.setText("YASAK");
                    }
                    else
                    {
                        textView.setText("SERBEST");
                    }
                }
            }


        }
        );

    }
    public int CurrentYear()
    {
        Date date= Calendar.getInstance().getTime();
        DateFormat dateFormat=new SimpleDateFormat("yyyy");
        int tarih= Integer.parseInt(dateFormat.format(date));
        return tarih;
    }
    public int yasHesapla(int yil){
        return CurrentYear()-yil;
    }
    public boolean haftasonu(){
        Date date1= Calendar.getInstance().getTime();
        DateFormat dateFormat1=new SimpleDateFormat("u");
        int gun=Integer.parseInt(dateFormat1.format(date1));
        if (gun==6||gun==7){
            return true;
        }
        else
            return false;
    }
    public int saathesapla(){
        Date date2= Calendar.getInstance().getTime();
        DateFormat dateformat2=new SimpleDateFormat("kk");
        int saat=Integer.parseInt(dateformat2.format(date2));
        return saat;
    }



}