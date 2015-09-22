package kale.ui.base;

import com.squareup.leakcanary.LeakCanary;

import android.app.Application;

/**
 * @author Jack Tony
 * @date 2015/9/22
 */
public class BaseApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        LeakCanary.install(this);
    }
}
