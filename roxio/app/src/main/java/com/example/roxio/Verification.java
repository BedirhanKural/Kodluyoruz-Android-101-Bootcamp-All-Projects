package com.example.roxio;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.StyleSpan;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class Verification extends Activity {
    private EditText editText;
    private EditText editText2;
    private EditText editText3;
    private EditText editText4;
    private TextView textView;
    private TextView textView2;
    private ImageView imageView;
    String num;
    String num1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent3=getIntent();
        num=intent3.getStringExtra("key");
        num1="(+90)"+num;
        setContentView(R.layout.verification);
        imageView=findViewById(R.id.imageView);
        editText=findViewById(R.id.editTextNumber);
        editText2=findViewById(R.id.editTextNumber2);
        editText3=findViewById(R.id.editTextNumber3);
        editText4=findViewById(R.id.editTextNumber4);
        textView=findViewById(R.id.textView);
        textView2=findViewById(R.id.textView2);
        SpannableString string = new SpannableString("Verification Code");
        string.setSpan(new StyleSpan(Typeface.BOLD), 13, 17, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView2.setText("Please type the verification code"+"\n"+"sent to  "+num1);
        textView.setText(string);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(editText.getText().toString().length()==1){
                    editText2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        editText2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(editText2.getText().toString().length()==1){
                    editText3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        editText3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(editText3.getText().toString().length()==1){
                    editText4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        editText4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Intent intent2=new Intent(getApplicationContext(), GetLocation.class);
                startActivity(intent2);
            }
        });




    }
}
