/*
A class which connects to a twitter feed supplied by NUSU mind the gap and displays
the latest tweets in a listview
*/

package team10.studentwellbeingapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
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

        /*twitter key authentication */
        TwitterAuthConfig authConfig =  new TwitterAuthConfig("xnhza9YzLRHzkFASRWlceJx8I", "C75gDnXLWlM2P57h7RhUeU9wBM9x5SuDfNJ7qA40axpRIVfIYK");

        Fabric.with(this, new TwitterCore(authConfig), new TweetUi());

        setContentView(R.layout.twitter_feed);

        Toolbar toolbar = (Toolbar) findViewById(R.id.buttonThreeToolbar);
        setSupportActionBar(toolbar);

        /*display timeline using list adapter */
        UserTimeline userTimeline = new UserTimeline.Builder().screenName("NUSUMindTheGap").build();
        //no twitter feed can be recieved

        final TweetTimelineListAdapter adapter = new TweetTimelineListAdapter.Builder(this)
                .setTimeline(userTimeline)
                .build();

        ListView listview = (ListView) findViewById(R.id.listView2);




        listview.setAdapter(adapter);

        //no twitter feed can be recieved

    }
    @Override
    protected void onStart() {
        super .onStart();

    }


    //go back to previous activity
    public void backButtonClick(View v){
        startActivity(new Intent(this, MainMenuActivity.class));
    }

    public void Alertdialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setCancelable(true);
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        AlertDialog noConnection = builder.create();
        noConnection.show();
    }


}
