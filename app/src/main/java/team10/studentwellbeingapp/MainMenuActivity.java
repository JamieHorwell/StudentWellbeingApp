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
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

public class MainMenuActivity extends AppCompatActivity {

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
            case R.id.menuButton1:
                startActivity(new Intent(this, ButtonOneActivity.class));
                break;
            case R.id.menuButton2:
                startActivity(new Intent(this, ButtonTwoActivity.class));
                break;
            case R.id.menuButton3:
                startActivity(new Intent(this, ButtonThreeActivity.class));
                break;
            case R.id.menuButton4:
                startActivity(new Intent(this, ButtonFourActivity.class));
                break;
            case R.id.bookAppointmentButton:
                startActivity(new Intent(this, BookingAppointmentActivity.class));
                break;
            case R.id.menuButton6:
                startActivity(new Intent(this, SymptomCheckerActivity.class));
                break;
            case R.id.menuButton7:
                startActivity(new Intent(this, ButtonSevenActivity.class));
                break;
            case R.id.menuButton8:
                startActivity(new Intent(this, ButtonEightActivity.class));
                break;
        }
    }
}
