package io.mvpstarter.sample.runner;

import android.annotation.SuppressLint;
import android.app.Application;
import android.app.KeyguardManager;
import android.os.PowerManager;
import android.support.test.runner.AndroidJUnitRunner;

import static android.content.Context.KEYGUARD_SERVICE;
import static android.content.Context.POWER_SERVICE;
import static android.os.PowerManager.ACQUIRE_CAUSES_WAKEUP;
import static android.os.PowerManager.FULL_WAKE_LOCK;
import static android.os.PowerManager.ON_AFTER_RELEASE;

public class UnlockDeviceAndroidJUnitRunner extends AndroidJUnitRunner {

    private PowerManager.WakeLock wakeLock;

    @SuppressLint("MissingPermission")
    @Override
    public void onStart() {
        Application application = (Application) getTargetContext().getApplicationContext();
        String simpleName = UnlockDeviceAndroidJUnitRunner.class.getSimpleName();
        // Unlock the device so that the tests can input keystrokes.
        ((KeyguardManager) application.getSystemService(KEYGUARD_SERVICE))
                .newKeyguardLock(simpleName)
                .disableKeyguard();
        // Wake up the screen.
        PowerManager powerManager = ((PowerManager) application.getSystemService(POWER_SERVICE));
        wakeLock =
                powerManager.newWakeLock(
                        FULL_WAKE_LOCK | ACQUIRE_CAUSES_WAKEUP | ON_AFTER_RELEASE, simpleName);
        wakeLock.acquire();
        super.onStart();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        wakeLock.release();
    }
}
