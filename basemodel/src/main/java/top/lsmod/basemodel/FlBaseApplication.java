package top.lsmod.basemodel;

import android.app.Application;

import com.lazy.library.logging.Logcat;

public class FlBaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化Logcat
        Logcat.initialize(this);
    }
}
