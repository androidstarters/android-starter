package io.mvpstarter.sample.common.injection.component;

import javax.inject.Singleton;

import dagger.Component;
import io.mvpstarter.sample.common.injection.module.ApplicationTestModule;
import io.mvpstarter.sample.injection.component.AppComponent;

@Singleton
@Component(modules = ApplicationTestModule.class)
public interface TestComponent extends AppComponent {
}
