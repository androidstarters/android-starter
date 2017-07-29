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
}
