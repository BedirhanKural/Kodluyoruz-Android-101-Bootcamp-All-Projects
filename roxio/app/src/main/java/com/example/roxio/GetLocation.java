package com.example.roxio;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import java.util.Arrays;

public class GetLocation extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    PlacesClient placesClient;
    private static final String TAG="INFO";
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    public double LocationLatitude;
    public double LocationLongitude;
    public double SelectedLatitude;
    public double SelectedLongitude;
    public String MyLatitude;
    public String MyLongitude;
    public String newLatidude;
    public String newLongtude;
    public String address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        String Apikey="AIzaSyCf-WhbFK6igoExGbTAUwdSAb7jITrqPB0";
        if(!Places.isInitialized()){
            Places.initialize(getApplicationContext(),Apikey);
        }
        placesClient=Places.createClient(this);
        AutocompleteSupportFragment autocompletesSupportFragment = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);
        autocompletesSupportFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.LAT_LNG,Place.Field.ADDRESS,Place.Field.NAME));
        autocompletesSupportFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(@NonNull Place place) {

                LatLng latLng=place.getLatLng();
                SelectedLatitude=latLng.latitude;
                newLatidude=Double.toString(SelectedLatitude);
                SelectedLongitude=latLng.longitude;
                newLongtude=Double.toString(SelectedLongitude);
                address=place.getAddress();
                String a=place.getAddress();
                Intent intent=new Intent(getApplicationContext(), AddressView.class);
                intent.putExtra("myLatitude",MyLatitude);
                intent.putExtra("myLongtude",MyLongitude);
                intent.putExtra("newLatitude",newLatidude);
                intent.putExtra("newLongtude",newLongtude);
                intent.putExtra("address1",address);
                startActivity(intent);
            }

            @Override
            public void onError(@NonNull Status status) {


            }
        });

    }




    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        enableMyLocation();
    }
    private void enableMyLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission to access the location is missing.
            PermissionUtils.requestPermission(this, LOCATION_PERMISSION_REQUEST_CODE,
                    Manifest.permission.ACCESS_FINE_LOCATION, true);
        } else if (mMap != null) {
            // Access to the location has been granted to the app.
            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(true);  // harita üzerinde bir çok özelliği burdan açabilirsiniz.
            GetLocation();
        }
    }
    private void GetLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        fusedLocationProviderClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations, this can be null.
                        if (location != null) {
                            LocationLatitude = location.getLatitude();
                            MyLatitude=Double.toString(LocationLatitude);
                            LocationLongitude = location.getLongitude();
                            MyLongitude=Double.toString(LocationLongitude);
                            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 12.0f));
                        }

                    }
                });


    }






}
