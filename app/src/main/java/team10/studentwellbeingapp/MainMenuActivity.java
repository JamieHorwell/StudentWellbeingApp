/*
Initial Author: Jason Ian Murray, Date: 8/11/2015
Notes:
    -   This is the activity that launches the main menu and at time
    of writing acts as the driver class.

Extended By: Jason Ian Murray, Date: 10/11/15
Notes:
    -   Extended to add the button listeners for ImgButtons on
    main_menu_activity.xml

Extended by: Jason Ian Murray, Date: 25/11/15
Notes:
    -   Replaced the somewhat clumsy button listeners with a simple
    onButtonClick() method. This means that all buttons can trigger this
    method and then be sorted by getId() for specific functionality. This
    seemed to be a more elegant, less buggy solution than the original.
    -   Prepared the switch statement to offer conditions based on
    button id.

Extended By: Jason Ian Murray, Date: 26/11/2015
Notes:
    -   Removed previous incarnation of toolbar code.
    -   Added the toolbar; changed the previous implementation
    and replaced it with a more stable XML variant.
*/

package team10.studentwellbeingapp;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainMenuActivity extends AppCompatActivity {
    Toast message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu_activity);

        Toolbar toolbar = (Toolbar) findViewById(R.id.menuToolbar);
        setSupportActionBar(toolbar);
    }

    public void onButtonClick(View v) {
        ImageButton button = (ImageButton) v;
        switch (button.getId()) {
            case R.id.learningResourcesButton:
                startActivity(new Intent(this, ContentMenuActivity.class));
                break;
            case R.id.moodDiaryButton:
                startActivity(new Intent(this, MoodDiaryMenuActivity.class));
                break;
            case R.id.mindTheGapTwitterButton:
                if (isNetworkConnected()) {
                    startActivity(new Intent(this, ButtonThreeActivity.class));
                } else{
                    toastUser();
                }
                break;
            case R.id.bookAppointmentButton:
                if (isNetworkConnected()){
                    startActivity(new Intent(this, LoginActivity.class));
                } else{
                    toastUser();
                }
                break;
//                SharedPreferences myPrefs;
//                myPrefs = getSharedPreferences("loginPrefs",MODE_PRIVATE);
//                Boolean loggedin = myPrefs.getBoolean("LoggedIn", true);
//                if(loggedin) {
//                    startActivity(new Intent(this, LoginActivity.class));
//                }
//                else {
//                    startActivity(new Intent(this,AppointmentMenuActivity.class));
            case R.id.symptomsCheckerBottom:
                startActivity(new Intent(this, SymptomCheckerActivity.class));
                break;
            case R.id.findSupportButton:
                startActivity(new Intent(this, FindSupportActivity.class));
                break;
        }
    }
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

    private void toastUser(){

        if (message != null) {
            message.setText("You must be connected to the Internet!");
            message.show();
        } else {
            message = Toast.makeText(this, "You must be connected to the Internet!", Toast.LENGTH_SHORT);
            message.show();
        }

    }
}
