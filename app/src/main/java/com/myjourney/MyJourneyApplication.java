package com.myjourney;

import android.app.Application;
import android.content.Intent;

import com.myjourney.utils.viewinjection.ViewInjector;

public class MyJourneyApplication extends Application {


    @Override
    public void onCreate() {
        new ViewInjector()
                .bindTo(this);

        super.onCreate();
    }


    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
    }
}
