package de.linkhal.daggermockingexample;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;
import de.linkhal.daggermockingexample.dependencies.ActivityService;

@Module(injects = MainActivity.class, addsTo = DaggerApplicationModule.class)
public class ActivityModule {

    private final Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    ActivityService activityService(){
        return new ActivityService();
    }
}
