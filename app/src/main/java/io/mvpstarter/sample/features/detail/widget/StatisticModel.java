package io.mvpstarter.sample.features.detail.widget;

import android.animation.ValueAnimator;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.animation.DecelerateInterpolator;

import io.mvpstarter.sample.BR;

/*
 * Created by shivam on 29/7/17.
 */
public class StatisticModel extends BaseObservable {

    private String name;
    private int progress;

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public int getProgress() {
        return progress;
    }

    // This method will update the progressBar's
    // progress with the help of ValueAnimators.
    public void setProgress(int value) {
        ValueAnimator progressAnimator = ValueAnimator.ofInt(0, value);
        progressAnimator.addUpdateListener((valueAnimator) -> {
                    progress = (int) valueAnimator.getAnimatedValue();
                    // Update view as progress updates
                    notifyPropertyChanged(BR.progress);
                }
        );
        progressAnimator.setDuration(250);
        progressAnimator.setInterpolator(new DecelerateInterpolator());
        progressAnimator.start();
    }
}
