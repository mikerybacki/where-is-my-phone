package phone.my.whereismyphone.listeners;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

import phone.my.whereismyphone.Cache;
import phone.my.whereismyphone.network.Api;
import timber.log.Timber;

public class GPSLocationListener implements LocationListener {

    public GPSLocationListener() {
    }

    @Override
    public void onLocationChanged(Location location) {
        Timber.d("New location: %s", location);
        if (location != null && location.getAccuracy() <= 100) {
            Cache.sharedInstance().saveLocation(location);
            Api.sharedInstance().sendPosition(location);
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
