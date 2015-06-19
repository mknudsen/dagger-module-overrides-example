package de.linkhal.daggermockingexample;

import dagger.Module;
import dagger.Provides;
import de.linkhal.daggermockingexample.dependencies.ActivityService;

@Module(injects = MainActivity.class, addsTo = DaggerApplicationModule.class)
public class ActivityModule {

    @Provides
    ActivityService activityService(){
        return new ActivityService();
    }
}
