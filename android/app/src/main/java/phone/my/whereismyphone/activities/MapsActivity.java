package phone.my.whereismyphone.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import phone.my.whereismyphone.R;
import phone.my.whereismyphone.helpers.LocationHelper;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private LocationHelper mLocationHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        initViews();
        setupHelpers();
        setupMap();
    }

    private void setupHelpers() {
        mLocationHelper = new LocationHelper();
    }

    private void setupMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                MapsActivity.this.onMapReady(googleMap);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mLocationHelper.onStart(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mLocationHelper.onStop();
    }

    private void initViews() {

    }

    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        mLocationHelper.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

}
