package com.sourcey.materiallogindemo;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.idling.CountingIdlingResource;

import junit.framework.AssertionFailedError;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;

import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.not;

public class WaitUntilVisible {
    CountingIdlingResource mIdlingRes = new CountingIdlingResource("WaitVisible");
    public void WaitByID(int Idverify) {
        try {
            onView(withId(Idverify)).check(matches(isDisplayed()));
        } catch (AssertionFailedError one) {
            System.out.print("Fail");
            mIdlingRes.increment();
            try {
                onView(withId(Idverify)).check(matches(not(isDisplayed())));
            } catch (AssertionFailedError two) {
                mIdlingRes.decrement();
            }
        }
    }

    public CountingIdlingResource getIdlingResource()
    {
        return mIdlingRes;
    }

    public void GetregisterWait() {
        IdlingRegistry.getInstance().register(mIdlingRes);
    }
}