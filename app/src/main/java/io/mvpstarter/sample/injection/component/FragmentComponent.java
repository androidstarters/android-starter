package io.mvpstarter.sample.injection.component;

import dagger.Subcomponent;
import io.mvpstarter.sample.injection.PerFragment;
import io.mvpstarter.sample.injection.module.FragmentModule;

/**
 * This component inject dependencies to all Fragments across the application
 */
@PerFragment
@Subcomponent(modules = FragmentModule.class)
public interface FragmentComponent {
}
