package com.example.myapplication;

import android.app.Activity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * desc   : Activity栈管理
 * author : zhukai
 * date   : 2020/5/28
 */
public class ActivityManager {

    private static ActivityManager instance;
    public List<Activity> activities = new ArrayList<>();

    private ActivityManager() {
    }

    public static final ActivityManager getInstance() {
        if (instance == null) {
            instance = new ActivityManager();
        }
        return ActivityManagerHolder.instance;
    }
    private static class ActivityManagerHolder {
        private static final ActivityManager instance = new ActivityManager();
    }



    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        for (Activity activity : activities) {
            if (activity.getClass().equals(cls)) {
                activity.finish();
            }
        }
    }
    //添加activity
    public void addActivity(Activity activity) {
        if (!activities.contains(activity)) {
            activities.add(activity);
        }
    }

    //关闭指定的Activity
    public void removeActivity(Activity activity) {
        if (activity != null) {
            if (activities.contains(activity)) {
                activities.remove(activity);
            }
            activity.finish();
            activity = null;
        }
    }

    //将activity全部关闭掉
    public void clearAll() {
        for (Activity activity : activities) {
            activity.finish();
        }
    }

    //将activity全部关闭掉,除掉MainAcitiy
    public void clearOther() {

        for (Activity activity : activities) {
            Log.e("eerrree", activity.getClass().getSimpleName());

            if (activity.getClass().getSimpleName().equals("MainActivity")) {

                continue;
            }
            activity.finish();
        }
    }

    /**
     * 获得当前的Activity
     *
     * @return 当前Activity
     */
    public Activity currentActivity() {
        if (activities.size() > 0) {
            return activities.get(activities.size() - 1);
        }
        return null;
    }
}
