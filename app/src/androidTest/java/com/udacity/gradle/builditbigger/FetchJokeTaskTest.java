package com.udacity.gradle.builditbigger;

import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by ND on 7/23/2016.
 */

@RunWith(AndroidJUnit4.class)
@SmallTest
public class FetchJokeTaskTest {
    FetchJokeTask mFetchJokeTask;
    String mResult;

    @Test
    public void FetchJokeTest() throws TimeoutException, InterruptedException, ExecutionException{
        mFetchJokeTask = (FetchJokeTask) new FetchJokeTask(new FetchJokeTask.AsyncResponse() {
            @Override
            public void processFinish(String result) {
                mResult = result;

                //Test results != null or empty string
                assertNotNull("Test Failed", mResult);
                assertTrue("Test Failed", mResult.length() > 0);
            }
        }).execute();
    }
}
