package phone.my.whereismyphone.models;

import android.location.Location;

import com.google.gson.annotations.SerializedName;

public class Position {

    @SerializedName("longitude")
    String mLong;

    @SerializedName("latitude")
    String mLat;

    @SerializedName("timestamp")
    String mTimestamp;

    public Position(Location location) {
        mLong = String.valueOf(location.getLongitude());
        mLat = String.valueOf(location.getLatitude());
        mTimestamp = String.valueOf(System.currentTimeMillis());
    }
}
