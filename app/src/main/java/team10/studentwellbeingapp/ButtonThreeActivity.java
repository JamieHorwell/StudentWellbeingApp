/*
Initial Author: Jason Ian Murray, Date: 26/11/2015
Notes:
    -   A simple placeholder class; the intention of this class
    is to allow others to see the functionality of the GUI as a whole.
    -   This can be extended or replaced by others. You need only copy
    and paste your code within the corresponding classes or refactor the
    name of this class to match yours then replace it with your own work.
*/

package team10.studentwellbeingapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;
import com.twitter.sdk.android.tweetui.TweetUi;
import com.twitter.sdk.android.tweetui.UserTimeline;

import io.fabric.sdk.android.Fabric;

public class ButtonThreeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TwitterAuthConfig authConfig =  new TwitterAuthConfig("xnhza9YzLRHzkFASRWlceJx8I", "C75gDnXLWlM2P57h7RhUeU9wBM9x5SuDfNJ7qA40axpRIVfIYK");
        Fabric.with(this, new TwitterCore(authConfig), new TweetUi());

        setContentView(R.layout.button_three);

        Toolbar toolbar = (Toolbar) findViewById(R.id.buttonThreeToolbar);
        setSupportActionBar(toolbar);

        UserTimeline userTimeline = new UserTimeline.Builder().screenName("NUSUMindTheGap").build();

        final TweetTimelineListAdapter adapter = new TweetTimelineListAdapter.Builder(this)
                .setTimeline(userTimeline)
                .build();

        ListView listview = (ListView) findViewById(R.id.listView2);

        listview.setAdapter(adapter);
    }
}
