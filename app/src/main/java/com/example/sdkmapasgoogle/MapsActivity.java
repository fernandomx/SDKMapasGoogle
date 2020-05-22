package com.example.sdkmapasgoogle;

import androidx.fragment.app.FragmentActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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
        mMap = googleMap;

        //Mudar a exibição do mapa
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

        // Add a marker in Ibirapuera and move the camera
        LatLng ibirapuera = new LatLng(-23.5881321, -46.6594347);
        //-23.5881321,-46.6594347

        //Circles
        CircleOptions circleOptions = new CircleOptions();
        circleOptions.center(ibirapuera);
        circleOptions.radius(500); //em metros distancia
        circleOptions.strokeWidth(10);
        circleOptions.strokeColor(Color.GREEN);
        circleOptions.fillColor(Color.argb(128,255,153,0));
        mMap.addCircle(circleOptions);

        //Polygon
        /*
        PolygonOptions polygonOptions = new PolygonOptions();
        polygonOptions.add(new LatLng(-23.586332, -46.658754));
        polygonOptions.add(new LatLng(-23.585615, -46.656662));
        polygonOptions.add(new LatLng(-23.587158, -46.657037));
        polygonOptions.add(new LatLng( -23.587247, -46.658797));
        polygonOptions.strokeColor(Color.GREEN);
        polygonOptions.strokeWidth(10);
        mMap.addPolygon(polygonOptions);
*/


        //Add event clic on map
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {

                Double latitude = latLng.latitude;
                Double longitude = latLng.longitude;

                Toast.makeText(MapsActivity.this,
                        "Lat: " + latitude + "long: " + longitude,
                        Toast.LENGTH_SHORT).show();

                mMap.addMarker(
                        new MarkerOptions()
                                .position(latLng)
                                .title("Local")
                                .snippet("Descrição")
                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))

                );



            }
        });

        mMap.addMarker(
                new MarkerOptions()
                        .position(ibirapuera)
                        .title("Marcador em Parque Ibirapuera")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))

        );

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ibirapuera,15)); //2.0 ate 21.0

        //mMap.moveCamera(CameraUpdateFactory.newLatLng(ibirapuera));
    }
}
