package de.linkhal.daggermockingexample;

import dagger.Module;
import dagger.Provides;
import de.linkhal.daggermockingexample.dependencies.ApplicationService;

@Module(library = true)
public class DaggerApplicationModule {

    @Provides
    public ApplicationService applicationService(){
        return new ApplicationService();
    }
}
