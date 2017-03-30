package phone.my.whereismyphone.activities;

import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import phone.my.whereismyphone.Cache;
import phone.my.whereismyphone.CustomApplication;
import phone.my.whereismyphone.R;
import phone.my.whereismyphone.helpers.LocationHelper;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private LocationHelper mLocationHelper;
    private View mFab;
    private Marker mCurrentPlaceMarker;
    private boolean mFirstMarker = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        initViews();
        setupCallbacks();
        setupHelpers();
        setupMap();
    }

    private void setupCallbacks() {
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatePositions();
            }
        });
    }

    private void updatePositions() {
        if (mMap != null) {
            Location location = Cache.sharedInstance().getLocation();
            if (location != null) {
                LatLng mCurrentLatLng = new LatLng(location.getLatitude(), location.getLongitude());
                if (mCurrentPlaceMarker == null) {
                    MarkerOptions marker = new MarkerOptions().position(
                            mCurrentLatLng);
                    mCurrentPlaceMarker = mMap.addMarker(marker);
                } else {
                    mCurrentPlaceMarker.setPosition(mCurrentLatLng);
                }
                if (mFirstMarker) {
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(mCurrentLatLng));
                    //mMap.animateCamera(CameraUpdateFactory.zoomTo(17));
                    mFirstMarker = false;
                }
            }
        }
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
        if (!CustomApplication.sharedInstance().locationServiceIsRunning()) {
            if (mLocationHelper.requestLocationPermission(this)) {
                CustomApplication.sharedInstance().startLocationService();
            }
        }
    }

    private void initViews() {
        mFab = findViewById(R.id.fab);

    }

    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        updatePositions();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (mLocationHelper.onRequestPermissionsResult(requestCode, permissions, grantResults)) {
            CustomApplication.sharedInstance().startLocationService();
        }
    }

}
