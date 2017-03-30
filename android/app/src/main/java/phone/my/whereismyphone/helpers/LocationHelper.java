package phone.my.whereismyphone.helpers;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

import phone.my.whereismyphone.CustomApplication;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;


/**
 * Created by markus on 2017-02-12.
 */

public class LocationHelper {
    private static final int REQUEST_LOCATION = 12;

    public boolean requestLocationPermission(Activity activity) {
        if (ActivityCompat.checkSelfPermission(CustomApplication.sharedInstance(), ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(CustomApplication.sharedInstance(), ACCESS_COARSE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED) {
            String[] permissions = {ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION};
            ActivityCompat.requestPermissions(activity, permissions, REQUEST_LOCATION);
            return false;
        }

        return true;
    }

    public boolean onRequestPermissionsResult(int requestCode,
                                              String[] permissions,
                                              int[] grantResults) {
        switch (requestCode) {
            case REQUEST_LOCATION:
                for (int i = 0; i < permissions.length; i++) {
                    if ((permissions[i].equals(ACCESS_COARSE_LOCATION) ||
                            permissions[i].equals(ACCESS_FINE_LOCATION)) &&
                            grantResults[i] == PackageManager.PERMISSION_GRANTED) {

                        return true;

                    }
                }

                break;
        }
        return false;
    }

}

