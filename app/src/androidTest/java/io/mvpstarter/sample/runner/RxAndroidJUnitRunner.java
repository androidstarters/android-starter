package io.mvpstarter.sample.runner;

import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.accessibility.AccessibilityChecks;
import android.support.test.runner.AndroidJUnitRunner;
import android.support.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import android.support.test.runner.lifecycle.Stage;

import io.mvpstarter.sample.util.RxIdlingScheduler;
import io.reactivex.Scheduler;
import io.reactivex.plugins.RxJavaPlugins;

import static android.view.WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD;
import static android.view.WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
import static android.view.WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON;

/**
 * Runner that registers a Espresso Indling resource that handles waiting for RxJava Observables to
 * finish. WARNING - Using this runner will block the tests if the application uses long-lived hot
 * Observables such us event buses, etc.
 */
public class RxAndroidJUnitRunner extends AndroidJUnitRunner {

    @Override
    public void onStart() {
//        enableAccessibilityChecks();
        dismissLockAndTurnScreenOn();
        monitorRxSchedulerForIdleness();
        super.onStart();
    }

    /**
     * Run some automated accessibility checks. See https://google.github.io/android-testing-support-library/docs/accesibility-checking
     */
    private void enableAccessibilityChecks() {
        AccessibilityChecks.enable();
    }

    private void dismissLockAndTurnScreenOn() {
        ActivityLifecycleMonitorRegistry.getInstance().addLifecycleCallback((activity, stage) -> {
            if (stage == Stage.PRE_ON_CREATE) {
                activity.getWindow().addFlags(FLAG_DISMISS_KEYGUARD | FLAG_TURN_SCREEN_ON | FLAG_KEEP_SCREEN_ON);
            }
        });
    }

    private void monitorRxSchedulerForIdleness() {
        RxJavaPlugins.setInitIoSchedulerHandler(schedulerCallable -> convertToIdlingScheduler(schedulerCallable.call()));
    }

    private static RxIdlingScheduler convertToIdlingScheduler(Scheduler scheduler) {
        RxIdlingScheduler rxIdlingResource = new RxIdlingScheduler(scheduler);
        IdlingRegistry.getInstance().register(rxIdlingResource.getCountingIdlingResource());
        return rxIdlingResource;
    }
}
