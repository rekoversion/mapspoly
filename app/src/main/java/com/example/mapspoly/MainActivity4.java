package com.example.mapspoly;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.SeekBar.OnSeekBarChangeListener;
import com.google.android.gms.common.util.Strings;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class MainActivity4 extends FragmentActivity implements OnMapReadyCallback, OnSeekBarChangeListener {
Polygon polygon =null;
List<LatLng> latLngList = new ArrayList<>();
List<Marker> markerList =new ArrayList<>();
int lr=0,lg=0;
int lblue=0;
    private GoogleMap mMap;
    private String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private String COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    String TAG = "MapActivity4";
    SeekBar linewidth,green,red,blue;
    Button btndraw,btnclr;
    CheckBox checkBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(MainActivity4.this);
        checkBox=(CheckBox)findViewById(R.id.check_box);
        linewidth=(SeekBar) findViewById(R.id.seek1);
        green =(SeekBar) findViewById(R.id.seekg);
        red=(SeekBar) findViewById(R.id.seekr);
        blue=(SeekBar) findViewById(R.id.seekb);
        btndraw=(Button)findViewById(R.id.drawm);
        btnclr =(Button)findViewById(R.id.clr);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){if(polygon==null) return;
                         polygon.setFillColor(Color.rgb(lr,lg,lblue));


                }else{polygon.setFillColor(Color.TRANSPARENT);}
            }
        });
btndraw.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        //DRAW POLYLINE
        if(polygon!=null)polygon.remove();
       PolygonOptions polygonOptions=new PolygonOptions().addAll(latLngList).clickable(true);
      polygon=mMap.addPolygon(polygonOptions);
    polygon.setStrokeColor(Color.rgb(lr,lg,lblue));
    if (checkBox.isChecked()){
        polygon.setFillColor(Color.rgb(lr,lg,lblue));

    }
    }
});
btnclr.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        //CLEAR
        if (polygon!=null)polygon.remove();
        for(Marker marker: markerList) marker.remove();
            latLngList.clear();
            checkBox.setChecked(false);
            markerList.clear();
            linewidth.setProgress(3);
            red.setProgress(0);
            green.setProgress(0);
            blue.setProgress(0);

    }
});

     red.setOnSeekBarChangeListener(this);
green.setOnSeekBarChangeListener(this);
blue.setOnSeekBarChangeListener(this);

    }



    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
      Toast.makeText(this,"logged in",Toast.LENGTH_SHORT).show();
        mMap = googleMap;
mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
    @Override
    public void onMapClick(LatLng latLng) {
        //MARKER OPTIONS
        MarkerOptions markerOptions =new MarkerOptions().position(latLng);
        Marker marker = mMap.addMarker(markerOptions);
        latLngList.add(latLng);
        markerList.add(marker);

        SetWidth();

    }
});


    }

    private void SetWidth() {
        linewidth.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                //get progress
                int width = linewidth.getProgress();
                if(polygon!=null) polygon.setStrokeWidth(width);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        switch (seekBar.getId()) {
            case R.id.seekr:
                lr = i;
                break;
            case R.id.seekg:
                lg = i;
                break;
            case R.id.seekb:
                lblue = i;

        }
        if (polygon != null) {
            polygon.setStrokeColor(Color.rgb(lr, lg, lblue));
            if (checkBox.isChecked()) polygon.setFillColor(Color.rgb(lr, lg, lblue));
        }
    }
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}