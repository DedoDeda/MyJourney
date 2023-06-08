package com.myjourney.utils.viewinjection;

import static com.myjourney.utils.Utils.findViewById;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.lang.reflect.Field;

public class ViewInjector implements Application.ActivityLifecycleCallbacks {

    public static final String TAG = ViewInjector.class.getName();

    public ViewInjector() {

    }

    public void bindTo(Application app) {
        app.registerActivityLifecycleCallbacks(this);
    }

    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {

    }

    @Override
    public void onActivityPostCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
        Application.ActivityLifecycleCallbacks.super.onActivityPostCreated(activity, savedInstanceState);
        injectViews(activity);
    }

    private void injectViews(Activity activity) {
        Class<?> currentClass = activity.getClass();
        do {
            // getFields() doesn't return private fields so we must use getDeclaredFields().
            for (Field field : activity.getClass().getDeclaredFields()) {
                InjectView annotation = field.getAnnotation(InjectView.class);
                if (annotation == null) {
                    continue;
                }

                try {
                    field.setAccessible(true);
                    String annotationId = annotation.id();
                    String id = annotationId.isEmpty() ? field.getName() : annotationId;
                    Object value = findViewById(activity, id);
                    if (field.getType().isInstance(value)) {
                        field.set(activity, value);
                    } else {
                        Log.w(TAG, String.format("Trying to inject a view of type %s to field of type %s.",
                                value.getClass(), field.getType()));
                    }
                } catch (IllegalAccessException e) {
                    Log.e(TAG, e.getMessage());
                }

            }

        } while ((currentClass = currentClass.getSuperclass()) != null);
    }

    @Override
    public void onActivityStarted(@NonNull Activity activity) {
    }

    @Override
    public void onActivityResumed(@NonNull Activity activity) {

    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {

    }

    @Override
    public void onActivityStopped(@NonNull Activity activity) {
    }

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {

    }
}