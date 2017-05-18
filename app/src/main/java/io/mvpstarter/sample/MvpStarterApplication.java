package io.mvpstarter.sample;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;
import com.squareup.leakcanary.LeakCanary;

import io.mvpstarter.sample.injection.component.ApplicationComponent;
import io.mvpstarter.sample.injection.component.DaggerApplicationComponent;
import io.mvpstarter.sample.injection.module.ApplicationModule;
import timber.log.Timber;

public class MvpStarterApplication extends Application {

    ApplicationComponent applicationComponent;

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

    public ApplicationComponent getComponent() {
        if (applicationComponent == null) {
            applicationComponent =
                    DaggerApplicationComponent.builder()
                            .applicationModule(new ApplicationModule(this))
                            .build();
        }
        return applicationComponent;
    }

    // Needed to replace the component with a test specific one
    public void setComponent(ApplicationComponent applicationComponent) {
        this.applicationComponent = applicationComponent;
    }
}
