package com.example.roxio;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class RouteMap extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private static int delayTime=5000;
    String ankara;
    private static final int earthRadius = 6371;
    public double lati1;
    public double longi1;
    public double lati2;
    public double longi2;
    public String addressData;
    public double km;
    private static LatLng latLng1;
    private static LatLng latLng2;
    String Distance;
    Button button;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newmap);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map1);
        mapFragment.getMapAsync(this);
        Intent intent = getIntent();;
        String data = intent.getStringExtra("lat1");
        String data1 = intent.getStringExtra("long1");
        String data2 = intent.getStringExtra("lat2");
        String data3 = intent.getStringExtra("long2");
        addressData=intent.getStringExtra("addressSon");
        lati1 = Double.parseDouble(data);
        longi1 = Double.parseDouble(data1);
        lati2 = Double.parseDouble(data2);
        longi2 = Double.parseDouble(data3);
        latLng1=new LatLng(lati1,longi1);
        latLng2=new LatLng(lati2,longi2);
        button=findViewById(R.id.buttonConfirm);
        km=calculateDistance(lati1,longi1,lati2,longi2);
        Distance=Double.toString(km);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(RouteMap.this, CreditCard.class);
                i.putExtra("DATA",Distance);
                startActivity(i);

            }
        });



    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng ankara=new LatLng(lati2,longi2);
        LatLng myLocation=new LatLng(lati1,longi1);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mMap.addMarker(new MarkerOptions().position(latLng2).title("Next Location:"+addressData).
                        icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))).showInfoWindow();
                mMap.addMarker(new MarkerOptions().position(latLng1).title("Current Location")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng2,12.f));
            }
        },delayTime);
        mMap.clear();
        mMap.addMarker(new MarkerOptions().position(latLng2).title("Next Location:"+addressData));
        mMap.addMarker(new MarkerOptions().position(latLng1).title("Current Location")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))).showInfoWindow();
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng1,12.f));

        mMap.addPolyline((new PolylineOptions())
                .add(latLng1,latLng2).width(5).color(Color.BLACK).geodesic(true));






    }
    public static double calculateDistance(double lat1, double lon1, double lat2, double lon2)
    {
        double dLat = (double) Math.toRadians(lat2 - lat1);
        double dLon = (double) Math.toRadians(lon2 - lon1);
        double a =
                (double) (Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(Math.toRadians(lat1))
                        * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2) * Math.sin(dLon / 2));
        double c = (double) (2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a)));
        double d = earthRadius * c;
        return d;
    }
}
