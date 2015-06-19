package de.linkhal.daggermockingexample;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.MediumTest;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import dagger.Module;
import dagger.Provides;
import de.linkhal.daggermockingexample.dependencies.ActivityService;
import de.linkhal.daggermockingexample.dependencies.ApplicationService;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

@MediumTest
@RunWith(AndroidJUnit4.class)
public class MockingActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> rule =
            new ActivityTestRule<>(MainActivity.class, true, false);

    @Module(overrides = true, library = true)
    public static class MockActivityModule {
        @Provides
        public ActivityService activityService() {
            return new ActivityService() {
                @Override
                public String hello() {
                    return "hello from mock activity service";
                }
            };
        }
    }

    @Module(overrides = true, library = true)
    public static class MockApplicationModule {
        @Provides
        public ApplicationService applicationService() {
            return new ApplicationService() {
                @Override
                public String hello() {
                    return "hello from mock application service";
                }
            };
        }
    }

    @After
    public void tearDown() throws Exception {
        getApplication().resetOverrides();
    }

    @Test
    public void testDoesOverrideActivityModule() throws Exception {

        DaggerApplication application =
                getApplication();

        application.addActivityOverrideModule(new MockActivityModule());

        rule.launchActivity(new Intent());

        assertThat(rule.getActivity().activityService.hello(), containsString("mock activity service"));
    }

    private DaggerApplication getApplication() {
        return (DaggerApplication) InstrumentationRegistry.getInstrumentation()
                .getTargetContext().getApplicationContext();
    }

    @Test
    public void testDoesOverrideApplicationModule() throws Exception {

        DaggerApplication application = getApplication();

        application.addApplicationOverrideModule(new MockApplicationModule());

        rule.launchActivity(new Intent());

        assertThat(rule.getActivity().applicationService.hello(), containsString("mock application service"));
    }

    @Test
    public void testDoesOverrideBoth() throws Exception {

        DaggerApplication application = getApplication();

        application.addApplicationOverrideModule(new MockApplicationModule());
        application.addActivityOverrideModule(new MockActivityModule());

        rule.launchActivity(new Intent());

        assertThat(rule.getActivity().applicationService.hello(), containsString("mock application service"));
        assertThat(rule.getActivity().activityService.hello(), containsString("mock activity service"));
    }
}
