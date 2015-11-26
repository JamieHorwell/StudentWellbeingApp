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

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        startActivity(new Intent(this, MainMenuActivity.class));
        super.onCreate(savedInstanceState);
        finish();
    }
}
