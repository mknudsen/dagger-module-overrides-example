package de.linkhal.daggermockingexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

import dagger.ObjectGraph;
import timber.log.Timber;

public class DaggerActivity extends AppCompatActivity {

    private ObjectGraph activityObjectGraph;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final DaggerApplication application = (DaggerApplication) getApplication();

        final ObjectGraph applicationGraph = application.getGraph();

        final List<Object> activityOverrideModules = application.getActivityOverrideModules();

        activityObjectGraph = applicationGraph
                .plus(new ActivityModule());

        for(Object o : activityOverrideModules){
            Timber.d("adding %s to activityObjectGraph", o);
            activityObjectGraph = activityObjectGraph.plus(o);
        }

        activityObjectGraph.inject(this);
    }
}
