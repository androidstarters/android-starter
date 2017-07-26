package io.mvpstarter.sample.features.detail.widget;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.animation.DecelerateInterpolator;
import android.widget.RelativeLayout;

import io.mvpstarter.sample.BR;
import io.mvpstarter.sample.data.model.response.Statistic;
import io.mvpstarter.sample.databinding.ViewStatisticBinding;
import io.mvpstarter.sample.util.Utils;

public class StatisticView extends RelativeLayout {

    private ViewStatisticBinding mViewStatisticBinding;

    public StatisticView(Context context) {
        super(context);
        init();
    }

    public StatisticView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public StatisticView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public StatisticView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        mViewStatisticBinding = ViewStatisticBinding.inflate(LayoutInflater.from(getContext()),
                this, false);
        /* Adding view to viewGroup */
        addView(mViewStatisticBinding.getRoot());
    }

    @SuppressLint("SetTextI18n")
    public void setStat(Statistic statistic) {
        StatisticModel statisticModel = new StatisticModel();
        statisticModel.setName(Utils.toCameCase(statistic.stat.name));
        statisticModel.setProgress(statistic.baseStat);
        mViewStatisticBinding.setStatistics(statisticModel);
    }

    //TODO: Need to move this class to appropriate package.
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
}
