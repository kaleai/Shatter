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
import lombok.Getter;

/**
 * @author Jack Tony
 * @date 2015/6/28
 */
public class ShatterManager {

    private List<Shatter> mShatters = new ArrayList<>();

    @Getter
    private Activity activity;

    public ShatterManager(@NonNull Activity activity) {
        if (activity instanceof ShatterOwner) {
            this.activity = activity;
        } else {
            throw new IllegalArgumentException("Activity must be implements ShatterOwner");
        }
    }

    public ShatterManager add(@IdRes int containViewId, @NonNull Shatter shatter) {
        shatter.setContainId(containViewId);
        return add(activity.findViewById(containViewId), shatter);
    }

    public ShatterManager add(@NonNull View containView, @NonNull Shatter shatter) {
        shatter.setRootView(containView);
        shatter.attachActivity(activity);
        mShatters.add(shatter);
        return this;
    }

    public void remove(@NonNull Shatter block) {
        block.onDestroy();
        mShatters.remove(block);
    }

    @CheckResult
    public
    @Nullable
    <E extends Shatter> E findShatterByTag(@NonNull String tag) {
        for (Shatter block : mShatters) {
            if (tag.equals(block.getTag())) {
                return (E) block;
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

    /**
     * Call by {@link ShatterActivityAspect#callManagerMethods(JoinPoint)}
     */
    public void onDestroy() {
        mShatters.clear();
        mShatters = null;
        activity = null;
    }

}