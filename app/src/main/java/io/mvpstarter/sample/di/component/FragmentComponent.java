package io.mvpstarter.sample.di.component;

import dagger.Subcomponent;
import io.mvpstarter.sample.di.PerFragment;
import io.mvpstarter.sample.di.module.FragmentModule;

/** This component inject dependencies to all Fragments across the application */
@PerFragment
@Subcomponent(modules = FragmentModule.class)
public interface FragmentComponent {}
