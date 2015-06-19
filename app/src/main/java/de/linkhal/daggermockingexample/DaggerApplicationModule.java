package de.linkhal.daggermockingexample;

import android.app.Application;

import dagger.Module;
import dagger.Provides;
import de.linkhal.daggermockingexample.dependencies.ApplicationService;

@Module(library = true)
public class DaggerApplicationModule {

    private final Application daggerApplication;

    public DaggerApplicationModule(final Application daggerApplication) {
        this.daggerApplication = daggerApplication;
    }

    @Provides
    public Application application() {
        return daggerApplication;
    }

    @Provides
    public ApplicationService applicationService(){
        return new ApplicationService();
    }
}
