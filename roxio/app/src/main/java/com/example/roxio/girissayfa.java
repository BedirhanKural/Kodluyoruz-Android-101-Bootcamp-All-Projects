package com.example.roxio;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import static android.telephony.PhoneNumberUtils.formatNumber;

public class girissayfa extends Activity {
  private ImageView imageView;
  private TextView  textView;
  private EditText editText;
  private Button button;
  String number;
    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.giris);
        imageView=findViewById(R.id.imageView2);
        textView=findViewById(R.id.textView);
        editText=findViewById(R.id.editTextPhone);
        button=findViewById(R.id.button);
        SpannableString string = new SpannableString("Welcome To Roxio");
        string.setSpan(new StyleSpan(Typeface.BOLD), 11, 16, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(string);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number=editText.getText().toString();
                Toast.makeText(getApplicationContext(),"number is correct",Toast.LENGTH_SHORT).show();
                 openSayfa2();

            }
            public void openSayfa2(){
                Intent intent1=new Intent(getApplicationContext(), Verification.class);
                intent1.putExtra("key",number);
                startActivity(intent1);
            }
        });



    }
}
