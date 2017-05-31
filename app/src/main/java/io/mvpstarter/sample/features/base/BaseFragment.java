package io.mvpstarter.sample.features.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.util.LongSparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.concurrent.atomic.AtomicLong;

import butterknife.ButterKnife;
import io.mvpstarter.sample.MvpStarterApplication;
import io.mvpstarter.sample.injection.component.ConfigPersistentComponent;
import io.mvpstarter.sample.injection.component.DaggerConfigPersistentComponent;
import io.mvpstarter.sample.injection.component.FragmentComponent;
import io.mvpstarter.sample.injection.module.FragmentModule;
import timber.log.Timber;

/**
 * Abstract Fragment that every other Fragment in this application must implement. It handles
 * creation of Dagger components and makes sure that instances of ConfigPersistentComponent are kept
 * across configuration changes.
 */
public abstract class BaseFragment extends Fragment {

    private static final String KEY_FRAGMENT_ID = "KEY_FRAGMENT_ID";
    private static final LongSparseArray<ConfigPersistentComponent> componentsArray =
            new LongSparseArray<>();
    private static final AtomicLong NEXT_ID = new AtomicLong(0);

    private long fragmentId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Create the FragmentComponent and reuses cached ConfigPersistentComponent if this is
        // being called after a configuration change.
        fragmentId =
                savedInstanceState != null
                        ? savedInstanceState.getLong(KEY_FRAGMENT_ID)
                        : NEXT_ID.getAndIncrement();
        ConfigPersistentComponent configPersistentComponent;
        if (componentsArray.get(fragmentId) == null) {
            Timber.i("Creating new ConfigPersistentComponent id=%d", fragmentId);
            configPersistentComponent =
                    DaggerConfigPersistentComponent.builder()
                            .appComponent(MvpStarterApplication.get(getActivity()).getComponent())
                            .build();
            componentsArray.put(fragmentId, configPersistentComponent);
        } else {
            Timber.i("Reusing ConfigPersistentComponent id=%d", fragmentId);
            configPersistentComponent = componentsArray.get(fragmentId);
        }
        FragmentComponent fragmentComponent =
                configPersistentComponent.fragmentComponent(new FragmentModule(this));
        inject(fragmentComponent);
        attachView();
    }

    @Nullable
    @Override
    public View onCreateView(
            LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayout(), container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    protected abstract int getLayout();

    protected abstract void inject(FragmentComponent fragmentComponent);

    protected abstract void attachView();

    protected abstract void detachPresenter();

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong(KEY_FRAGMENT_ID, fragmentId);
    }

    @Override
    public void onDestroy() {
        if (!getActivity().isChangingConfigurations()) {
            Timber.i("Clearing ConfigPersistentComponent id=%d", fragmentId);
            componentsArray.remove(fragmentId);
        }
        detachPresenter();
        super.onDestroy();
    }
}
