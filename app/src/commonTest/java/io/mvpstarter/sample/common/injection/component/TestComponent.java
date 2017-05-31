package io.mvpstarter.sample.common.injection.component;

import javax.inject.Singleton;

import dagger.Component;
import io.mvpstarter.sample.common.injection.module.ApplicationTestModule;
import io.mvpstarter.sample.di.component.AppComponent;

@Singleton
@Component(modules = ApplicationTestModule.class)
public interface TestComponent extends AppComponent {
}
