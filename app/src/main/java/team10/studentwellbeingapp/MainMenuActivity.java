/*
Title:
Initial Author: Jason Ian Murray, Date: 8/11/2015
Notes:
This is the activity that launches the main menu and at time of writing acts as the driver
class.

Extended By: Jason Ian Murray, Date: 10/11/15
Notes:
Extended to ad the button listeners for ImgButtons on
main_menu_activity.xml
*/

package team10.studentwellbeingapp;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainMenuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu_activity);
    }


    protected void menuButtonOnClick(View view){

    }
}
