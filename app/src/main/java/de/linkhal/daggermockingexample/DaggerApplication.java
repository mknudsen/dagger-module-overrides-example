package de.linkhal.daggermockingexample;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

import dagger.ObjectGraph;

public class DaggerApplication extends Application {

    private final List<Object> activityOverrideModules = new ArrayList<>();

    private ObjectGraph defaultGraph;
    private ObjectGraph effectiveGraph;

    public ObjectGraph getGraph() {
        initGraphIfNeeded();
        return effectiveGraph;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    private void initGraph() {
        defaultGraph = ObjectGraph.create(new DaggerApplicationModule(this));
        effectiveGraph = defaultGraph;
    }

    public void inject(final Object object) {
        initGraphIfNeeded();

        effectiveGraph.inject(object);
    }

    private void initGraphIfNeeded() {
        if (defaultGraph == null) {
            initGraph();
        }
    }

    public List<Object> getActivityOverrideModules() {
        return activityOverrideModules;
    }

    public void resetOverrides() {
        effectiveGraph = defaultGraph;
        activityOverrideModules.clear();
    }

    public DaggerApplication addApplicationOverrideModule(final Object module) {
        initGraphIfNeeded();

        effectiveGraph = effectiveGraph.plus(module);
        return this;
    }

    public DaggerApplication addActivityOverrideModule(final Object module) {
        activityOverrideModules.add(module);
        return this;
    }

}
