package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.InstrumentationTestCase;
import android.test.suitebuilder.annotation.SmallTest;
import android.util.Pair;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by Cencil on 2/17/2016.
 */
@RunWith(AndroidJUnit4.class)
@SmallTest
public class EndpointsTest extends InstrumentationTestCase implements EndpointsAsyncTask.Callback {
    Context mContext;

    // create a signal to let us know when our task is done.
    final CountDownLatch signal = new CountDownLatch(1);

    private String mJoke = null;

    @Before
    public void setUp() throws Exception {
        super.setUp();

        // have to programmatically inject the instrumentation...
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        mContext = getInstrumentation().getContext();
    }

    @Test
    public void testFetchJoke() throws Throwable {
        final EndpointsTest callback = this;

        // Execute the async task on the UI thread!
        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                new EndpointsAsyncTask(callback).execute(new Pair<Context, String>(mContext, "test"));
            }
        });

        /* The testing thread will wait here until the UI thread releases it
         * above with the countDown() or 30 seconds passes and it times out.
         */
        signal.await(30, TimeUnit.SECONDS);

        assertNotNull(mJoke);
    }

    @Override
    public void onPreExecute() {

    }

    @Override
    public void onFetchJokeFinished(String joke) {
        mJoke = joke;

        signal.countDown();
    }
}
