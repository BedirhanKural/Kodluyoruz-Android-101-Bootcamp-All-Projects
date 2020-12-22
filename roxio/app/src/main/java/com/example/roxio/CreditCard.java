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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class CreditCard extends Activity {
    private TextView textView3;
    private TextView textView5;
    private TextView textView;
    private EditText editText;
    private EditText editText2;
    private Button button;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.credit_card);
        button=findViewById(R.id.button);
        editText=findViewById(R.id.editTextTextPersonName);
        editText2=findViewById(R.id.editTextCardNumber);
        textView3=findViewById(R.id.textView3);
        textView=findViewById(R.id.textView);
        SpannableString string = new SpannableString("Add Card");
        string.setSpan(new StyleSpan(Typeface.BOLD), 4, 8, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView5=findViewById(R.id.textView5);
        textView.setText(string);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                textView3.setText(editText.getText().toString());
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
                textView5.setText(editText2.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               Intent newintent=new Intent(CreditCard.this,payment.class);
               Bundle bundle=getIntent().getExtras();
               if(bundle!=null){
                   newintent.putExtras(bundle);
               }
               startActivity(newintent);
            }
        });


    }
}
