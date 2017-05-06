package io.mvpstarter.sample.injection.component;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import io.mvpstarter.sample.data.DataManager;
import io.mvpstarter.sample.data.remote.MvpStarterService;
import io.mvpstarter.sample.injection.ApplicationContext;
import io.mvpstarter.sample.injection.module.ApplicationModule;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    @ApplicationContext
    Context context();

    Application application();

    DataManager dataManager();

    MvpStarterService mvpBoilerplateService();
}
