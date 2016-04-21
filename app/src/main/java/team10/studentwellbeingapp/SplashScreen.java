package team10.studentwellbeingapp;
/*
Initial Author: Jason Ian Murray, Date: 26/11/2015
Notes:
    -   This simply awaits the application to complete
    the startup (showing the splash screen) and then starts the
    MainMenuActivity intent.
*/

        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
import com.crashlytics.android.Crashlytics;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import io.fabric.sdk.android.Fabric;

public class SplashScreen extends AppCompatActivity {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "xnhza9YzLRHzkFASRWlceJx8I";
    private static final String TWITTER_SECRET = "C75gDnXLWlM2P57h7RhUeU9wBM9x5SuDfNJ7qA40axpRIVfIYK";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        startActivity(new Intent(this, MainMenuActivity.class));
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Crashlytics(), new Twitter(authConfig));
        finish();
    }
}
