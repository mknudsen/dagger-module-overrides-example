package de.linkhal.daggermockingexample;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

import dagger.ObjectGraph;
import timber.log.Timber;

public class DaggerApplication extends Application {

    private final List<Object> activityOverrideModules = new ArrayList<>();

    private ObjectGraph defaultGraph;
    private ObjectGraph effectiveGraph;

    public ObjectGraph getGraph() {
        return effectiveGraph;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Timber.plant(new Timber.DebugTree());

        defaultGraph = ObjectGraph.create(new DaggerApplicationModule());
        effectiveGraph = defaultGraph;
    }

    public List<Object> getActivityOverrideModules() {
        return activityOverrideModules;
    }

    public void resetOverrides() {
        effectiveGraph = defaultGraph;
        activityOverrideModules.clear();
    }

    public DaggerApplication addApplicationOverrideModule(final Object module) {
        effectiveGraph = effectiveGraph.plus(module);
        return this;
    }

    public DaggerApplication addActivityOverrideModule(final Object module) {
        activityOverrideModules.add(module);
        return this;
    }

}
