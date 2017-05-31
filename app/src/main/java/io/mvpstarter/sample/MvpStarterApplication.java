package io.mvpstarter.sample;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;
import com.squareup.leakcanary.LeakCanary;

import io.mvpstarter.sample.injection.component.AppComponent;
import io.mvpstarter.sample.injection.component.DaggerAppComponent;
import io.mvpstarter.sample.injection.module.AppModule;
import timber.log.Timber;

public class MvpStarterApplication extends Application {

    AppComponent appComponent;

    public static MvpStarterApplication get(Context context) {
        return (MvpStarterApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
            Stetho.initializeWithDefaults(this);
            LeakCanary.install(this);
        }
    }

    public AppComponent getComponent() {
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
        }
        return appComponent;
    }

    // Needed to replace the component with a test specific one
    public void setComponent(AppComponent appComponent) {
        this.appComponent = appComponent;
    }
}
