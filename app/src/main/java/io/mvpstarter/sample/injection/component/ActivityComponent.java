package io.mvpstarter.sample.injection.component;

import dagger.Subcomponent;
import io.mvpstarter.sample.injection.PerActivity;
import io.mvpstarter.sample.injection.module.ActivityModule;
import io.mvpstarter.sample.ui.base.BaseActivity;
import io.mvpstarter.sample.ui.detail.DetailActivity;
import io.mvpstarter.sample.ui.main.MainActivity;

@PerActivity
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(BaseActivity baseActivity);

    void inject(MainActivity mainActivity);

    void inject(DetailActivity detailActivity);
}
