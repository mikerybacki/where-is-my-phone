package phone.my.whereismyphone;

import android.app.Application;

import timber.log.Timber;

public class CustomApplication extends Application {
    private static CustomApplication sSharedInstance;

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
    }

}
