package com.feedfacts.example.factfeeds;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.feedfacts.example.factfeeds.activities.MainActivity;
import com.feedfacts.example.factfeeds.adpater.FactFeedAdapter;
import com.feedfacts.example.factfeeds.network.NetworkUtils;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertEquals;

/**
 * Tests the data fetching mechanism.
 *
 * @author sneha_rakhonde
 */
@RunWith(AndroidJUnit4.class)
public class UiDataFetchUnitTest {
    private static final long WAIT = 10000;
    private MainActivity mainActivity;
    @Rule
    public ActivityTestRule<MainActivity> rule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() {
        rule.getActivity()
                .getSupportFragmentManager().beginTransaction();
         mainActivity = rule.getActivity();
        assertNotNull("MainActivity is null", mainActivity);
    }
/*to test if data download is successful or not*/
@Test
public void testDataDownloadSuccess() throws InterruptedException {
    MainActivity activity = rule.getActivity();
    View viewById = activity.findViewById(R.id.recycler_fact);
    assertThat(viewById, notNullValue());


    RecyclerView listView = (RecyclerView) viewById;
    FactFeedAdapter adapter = (FactFeedAdapter) listView.getAdapter();
    assertThat(adapter, instanceOf(RecyclerView.Adapter.class));

    if (NetworkUtils.isConnectedToInternet) {
        Thread.sleep(WAIT);
        assertThat(adapter.getItemCount(), greaterThan(0));
    }

}

/* Tests if downloaded data doesn't have any feed values.*/
@Test
public void testDataFetechFailed(){
    try {
        Thread.sleep(WAIT);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    TextView noData = (TextView) mainActivity
            .findViewById(R.id.txt_data_error);
    assertNotNull("EmptyStateTextView is null", noData);
    assertThat(noData.getVisibility(), lessThanOrEqualTo(View.VISIBLE));
}

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.feedfacts.example.factfeeds", appContext.getPackageName());
    }
}
