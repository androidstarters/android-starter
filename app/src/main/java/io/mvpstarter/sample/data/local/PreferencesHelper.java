package io.mvpstarter.sample.data.local;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.mvpstarter.sample.injection.ApplicationContext;

@Singleton
public class PreferencesHelper {

    public static final String PREF_FILE_NAME = "mvpstarter_pref_file";

    private final SharedPreferences preferences;

    @Inject
    public PreferencesHelper(@ApplicationContext Context context) {
        preferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
    }

    public void clear() {
        preferences.edit().clear().apply();
    }
}
