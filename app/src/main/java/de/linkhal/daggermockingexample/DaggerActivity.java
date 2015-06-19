package de.linkhal.daggermockingexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

import dagger.ObjectGraph;

public class DaggerActivity extends AppCompatActivity {

    private ObjectGraph activityObjectGraph;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final DaggerApplication application = (DaggerApplication) getApplication();

        final ObjectGraph applicationGraph = application.getGraph();

        final List<Object> activityOverrideModules = application.getActivityOverrideModules();

        activityObjectGraph = applicationGraph
                .plus(new ActivityModule(this))
                .plus(activityOverrideModules.toArray());

//        if (!activityOverrideModules.isEmpty()) {
//            for (final Object o : activityOverrideModules) {
//                activityObjectGraph = activityObjectGraph.plus(o);
//            }
//        }

        activityObjectGraph.inject(this);
    }
}
