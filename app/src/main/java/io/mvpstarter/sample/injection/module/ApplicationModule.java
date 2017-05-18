package io.mvpstarter.sample.injection.module;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.mvpstarter.sample.data.remote.MvpStarterService;
import io.mvpstarter.sample.data.remote.MvpStarterServiceFactory;
import io.mvpstarter.sample.injection.ApplicationContext;

@Module
public class ApplicationModule {
    private final Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    static MvpStarterService provideMvpStarterService() {
        return MvpStarterServiceFactory.makeStarterService();
    }

    @Provides
    Application provideApplication() {
        return application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return application;
    }
}
