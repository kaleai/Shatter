package kale.ui.shatter;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.support.annotation.CheckResult;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import org.aspectj.lang.JoinPoint;

import kale.ui.shatter.lifecycle.ShatterActivityAspect;

/**
 * @author Jack Tony
 * @date 2015/6/28
 */
public class ShatterManager {

    private List<Shatter> mShatters = new ArrayList<>();

    private Activity mActivity;

    public ShatterManager(@NonNull Activity activity) {
        if (activity instanceof IShatterActivity) {
            this.mActivity = activity;
        } else {
            throw new IllegalArgumentException("Activity must be implements IShatterActivity");
        }
    }

    public ShatterManager add(@IdRes int containViewId, @NonNull Shatter shatter) {
        shatter.setContainId(containViewId);
        return add(mActivity.findViewById(containViewId), shatter);
    }

    public ShatterManager add(@NonNull View containView, @NonNull Shatter shatter) {
        shatter.setRootView(containView);
        shatter.attachActivity(mActivity);
        mShatters.add(shatter);
        return this;
    }

    public void remove(@NonNull Shatter shatter) {
        shatter.onSelfDestroy();
        mShatters.remove(shatter);
    }

    @CheckResult
    public
    @Nullable
    <E extends Shatter> E findShatterByTag(@NonNull String tag) {
        for (Shatter shatter : mShatters) {
            if (tag.equals(shatter.getTag())) {
                return (E) shatter;
            }
        }
        return null;
    }

    @CheckResult
    public
    @Nullable
    Shatter findShatterByContainViewId(int id) {
        for (Shatter shatter : mShatters) {
            if (shatter.getContainId() == id) {
                return shatter;
            }
        }
        return null;
    }

    @CheckResult
    @NonNull
    public List<Shatter> getShatters() {
        return mShatters;
    }

    public Activity getActivity() {
        return mActivity;
    }

    /**
     * Call by {@link ShatterActivityAspect#callManagerMethods(JoinPoint)}
     */
    public void destroy() {
        mShatters.clear();
        mShatters = null;
        mActivity = null;
    }

}