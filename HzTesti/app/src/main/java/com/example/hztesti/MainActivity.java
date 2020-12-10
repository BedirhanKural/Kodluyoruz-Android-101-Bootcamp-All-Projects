package com.example.hztesti;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import java.util.Timer;


import com.jignesh13.speedometer.SpeedoMeterView;

import java.util.TimerTask;
import java.util.concurrent.Delayed;

import fr.bmartel.speedtest.SpeedTestReport;
import fr.bmartel.speedtest.SpeedTestSocket;
import fr.bmartel.speedtest.inter.ISpeedTestListener;
import fr.bmartel.speedtest.model.SpeedTestError;

public class MainActivity extends AppCompatActivity {
    SpeedoMeterView speedoMeterView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        speedoMeterView=findViewById(R.id.speedometerview);
        speedoMeterView.setSpeed(0,true);//speed set 0 to 140
        Button button=findViewById(R.id.button);
        Button button2=findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SpeedTestTask speedTestTask=new SpeedTestTask();
                speedTestTask.execute();
                speedoMeterView.setSpeed(0,true);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speedoMeterView.setSpeed(0,true);
            }
        });



    }

     class SpeedTestTask extends AsyncTask<Void,Void,Void>{

         @Override
         protected Void doInBackground(Void... voids) {
             SpeedTestSocket speedTestSocket = new SpeedTestSocket();

             // add a listener to wait for speedtest completion and progress
             speedTestSocket.addSpeedTestListener(new ISpeedTestListener() {

                 @Override
                 public void onCompletion(SpeedTestReport report) {
                     // called when download/upload is finished
                     Log.v("speedtest", "[COMPLETED] rate in octet/s : " + report.getTransferRateOctet());
                     Log.v("speedtest", "[COMPLETED] rate in bit/s   : " + report.getTransferRateBit());
                 }

                 @Override
                 public void onError(SpeedTestError speedTestError, String errorMessage) {
                     // called when a download/upload error occur
                 }

                 @Override
                 public void onProgress(float percent, SpeedTestReport report) {
                     int deger= report.getTransferRateBit().intValue()/100000;
                     runOnUiThread(new Runnable() {
                         @Override
                         public void run() {
                             speedoMeterView.setSpeed(deger,true);
                         }

                     });
                     // called to notify download/upload progress
                     Log.v("speedtest", "[PROGRESS] progress : " + percent + "%");
                     Log.v("speedtest", "[PROGRESS] rate in octet/s : " + report.getTransferRateOctet());
                     Log.v("speedtest", "[PROGRESS] rate in bit/s   : " + report.getTransferRateBit());
                 }
             });

             speedTestSocket.startDownload("http://ipv4.ikoula.testdebit.info/1M.iso");
             return null;
         }
     }


}