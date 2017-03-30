package phone.my.whereismyphone;

import android.location.Location;

public class Cache {

    private static Cache sSharedInstance;
    private Location mLocation;

    public void saveLocation(Location location) {
        mLocation = location;
    }

    public Location getLocation() {
        return mLocation;
    }

    public static Cache sharedInstance() {
        if (sSharedInstance == null) {
            synchronized (Cache.class) {
                if (sSharedInstance == null) {
                    sSharedInstance = new Cache();
                }
            }
        }
        return sSharedInstance;
    }

}
