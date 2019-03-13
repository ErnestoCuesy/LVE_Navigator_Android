package com.ernestosoft.lvenavigator;

import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    Button getThereButton;

    double startLatitude;
    double startLongitude;
    double destinationLatitude;
    double destinationLongitude;

    ArrayList<Marker> markers;
    Intent intent;
    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        intent = getIntent();
        index = intent.getIntExtra("index", 0);

        final FrameLayout frameLayout = findViewById(R.id.frameLayout);
        getThereButton = findViewById(R.id.getThereButton);

            frameLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {

                    if (mMap != null) {
                        frameLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                        markers = new ArrayList<>();

                        destinationLatitude = MainActivity.placesLatLng.get(index).latitude;
                        destinationLongitude = MainActivity.placesLatLng.get(index).longitude;

                        // Add a marker for selected location
                        LatLng selectedLoc = new LatLng(destinationLatitude, destinationLongitude);


                        markers.add(mMap.addMarker(new MarkerOptions()
                                .position(selectedLoc)
                                .title(MainActivity.placesNames.get(index))
                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))));

                        LatLng currentLocation = new LatLng(intent.getDoubleExtra("latitude", 0),
                                intent.getDoubleExtra("longitude", 0));
                        startLatitude = currentLocation.latitude;
                        startLongitude = currentLocation.longitude;

                        Location currentLoc = new Location("");
                        currentLoc.setLatitude(currentLocation.latitude);
                        currentLoc.setLongitude(currentLocation.longitude);

                        String snippetLocOut = "";
                        String snippetLoc = "";
                        String snippetLoc1 = "";
                        float nearDistance1 = 30.0f;

                        for (int i = 1; i < MainActivity.placesLatLng.size();i++){

                            Location location = new Location("");
                            location.setLatitude(MainActivity.placesLatLng.get(i).latitude);
                            location.setLongitude(MainActivity.placesLatLng.get(i).longitude);
                            // Log.i("Distance to",MainActivity.placesNames.get(i) + "is " + currentLoc.distanceTo(location));
                            float distance = currentLoc.distanceTo(location);
                            snippetLoc = MainActivity.placesNames.get(i);
                            if (distance < nearDistance1){
                                nearDistance1 = distance;
                                snippetLoc1 = snippetLoc;
                            }
                        }

                        if (snippetLoc1.matches("")){
                            snippetLocOut = "...";
                        }else{
                            snippetLocOut = "Near to " + snippetLoc1 + String.format(" %.0f metres", nearDistance1);
                        }

                        // Add a marker in current location
                        markers.add(mMap.addMarker(new MarkerOptions()
                                .position(currentLocation)
                                .title("You are here")
                                .snippet(snippetLocOut)
                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))));

                        LatLngBounds.Builder builder = new LatLngBounds.Builder();
                        for (Marker marker : markers) {

                            builder.include(marker.getPosition());

                        }
                        LatLngBounds bounds = builder.build();

                        int padding = 400;
                        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);
                        mMap.animateCamera(cu);

                        for (Marker marker : markers) {

                            marker.showInfoWindow();

                        }

                    } else {

                        Toast.makeText(MapsActivity.this, "Waiting for map...", Toast.LENGTH_SHORT).show();

                    }
                }
            });
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
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {

            }
        });

    }

    public void getThere(View view){

        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse("http://maps.google.com/maps?saddr="
                        + startLatitude
                        + ","
                        + startLongitude
                        + "&daddr="
                        + destinationLatitude
                        + ","
                        + destinationLongitude));
        startActivity(intent);

    }

}
