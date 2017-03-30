package phone.my.whereismyphone;

import android.Manifest;
import android.app.Application;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

import phone.my.whereismyphone.services.LocationService;
import timber.log.Timber;

public class CustomApplication extends Application {
    private static CustomApplication sSharedInstance;
    private boolean mLocationServiceIsRunning;

    public synchronized static CustomApplication sharedInstance() {
        if (sSharedInstance == null) {
            sSharedInstance = new CustomApplication();
        }

        return sSharedInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sSharedInstance = this;
        Timber.plant(new Timber.DebugTree());
        if (hasGpsPermission()) {
            startLocationService();
        }
    }

    private boolean hasGpsPermission() {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return false;
        }
        return true;
    }

    public void startLocationService() {
        if (!mLocationServiceIsRunning) {
            Intent serviceIntent = new Intent(this, LocationService.class);
            startService(serviceIntent);
        }
        mLocationServiceIsRunning = true;
    }

    public boolean locationServiceIsRunning() {
        return mLocationServiceIsRunning;
    }
}
