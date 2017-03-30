package phone.my.whereismyphone.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.IBinder;
import android.os.PowerManager;

import phone.my.whereismyphone.Cache;
import phone.my.whereismyphone.listeners.GPSLocationListener;
import timber.log.Timber;

public class LocationService extends Service {

    private static final int REQUEST_LOCATION = 12;
    private static final long MIN_TIME = 20000; //ms
    private static final float MIN_DISTANCE = 0;

    PowerManager.WakeLock wakeLock;

    private LocationManager mLocationManager;
    private GPSLocationListener mLocationListener;

    public LocationService() {
    }

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        PowerManager pm = (PowerManager) getSystemService(POWER_SERVICE);

        wakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "DoNotSleep");

        Timber.e("Service Created");
    }

    @SuppressWarnings("MissingPermission")
    @Override
    @Deprecated
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);

        Timber.e("Service Started");
        mLocationListener = new GPSLocationListener();

        mLocationManager = (LocationManager) getApplicationContext()
                .getSystemService(Context.LOCATION_SERVICE);


        mLocationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                MIN_TIME,
                MIN_DISTANCE,
                mLocationListener);
        mLocationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER,
                MIN_TIME,
                MIN_DISTANCE,
                mLocationListener);
        Location networkLocation = mLocationManager.getLastKnownLocation(
                LocationManager.NETWORK_PROVIDER);
        Location gpsLocation = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (networkLocation == null) {
            if (gpsLocation != null) {
                Cache.sharedInstance().saveLocation(gpsLocation);
            }
        } else {
            if (gpsLocation == null) {
                Cache.sharedInstance().saveLocation(networkLocation);
            } else {
                Location bestLocation;
                if (gpsLocation.getAccuracy() <= networkLocation.getAccuracy()) {
                    bestLocation = gpsLocation;
                } else {
                    bestLocation = networkLocation;
                }
                Cache.sharedInstance().saveLocation(bestLocation);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        wakeLock.release();
    }

    /*
    public static boolean isConnectingToInternet(Context _context) {
        ConnectivityManager connectivity = (ConnectivityManager) _context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }

        }
        return false;
    }
    */

}