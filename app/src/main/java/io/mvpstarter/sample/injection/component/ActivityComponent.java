package io.mvpstarter.sample.injection.component;

import dagger.Subcomponent;
import io.mvpstarter.sample.features.base.BaseActivity;
import io.mvpstarter.sample.features.detail.DetailActivity;
import io.mvpstarter.sample.features.main.MainActivity;
import io.mvpstarter.sample.injection.PerActivity;
import io.mvpstarter.sample.injection.module.ActivityModule;

@PerActivity
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(BaseActivity baseActivity);

    void inject(MainActivity mainActivity);

    void inject(DetailActivity detailActivity);
}
