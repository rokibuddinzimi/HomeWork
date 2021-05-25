package com.home.work;

import androidx.test.rule.ActivityTestRule;

import com.home.work.view.PaymentMethodsListViewActivity;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class PaymentMethodsListViewActivityInstrumentedTest {

    @Rule
    public ActivityTestRule<PaymentMethodsListViewActivity> mActivityRule = new ActivityTestRule<PaymentMethodsListViewActivity>(PaymentMethodsListViewActivity.class);

    @Test
    public void listViewClick() {
        onView(withId(R.id.list))
                .perform(click())
                .check(matches(isDisplayed()));
    }

    @Test
    public void headerText() {
        onView(withId(R.id.text_Header_text))
                .check(matches(withText("Payment Methods")));
    }

    @Test
    public void listClick() {
        onView(withId(R.id.list)).perform(click());
    }
}
