package io.mvpstarter.sample.di.component;

import dagger.Subcomponent;
import io.mvpstarter.sample.di.PerActivity;
import io.mvpstarter.sample.di.module.ActivityModule;
import io.mvpstarter.sample.features.detail.DetailActivity;
import io.mvpstarter.sample.features.main.MainActivity;

@PerActivity
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);

    void inject(DetailActivity detailActivity);
}
