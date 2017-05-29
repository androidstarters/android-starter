package io.mvpstarter.sample.di.module;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.mvpstarter.sample.data.remote.MvpStarterServiceFactory;
import io.mvpstarter.sample.data.remote.PokemonApi;
import io.mvpstarter.sample.di.ApplicationContext;

import static io.mvpstarter.sample.common.constants.PrefConstants.PREF_FILE_NAME;

@Module
public class AppModule {
    private final Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    static PokemonApi provideMvpStarterService() {
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

    @Provides
    @ApplicationContext
    SharedPreferences provideSharedPreference(@ApplicationContext Context context) {
        return context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
    }
}
