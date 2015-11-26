/*
Title:
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
    - Added some experimental code to try and define the actionBar's
    layout/text values.
*/

package team10.studentwellbeingapp;
import android.app.ActionBar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.content.Intent;
import android.widget.TextView;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu_activity);
    }
    /*
    Need to build a custom toolbar for the following to work.
    Otherwise we get null pointer.
    public boolean onCreateOptionsMenu(Menu menu){
        ActionBar actionBar = this.getActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(R.layout.action_bar);

        TextView actionBarTitle = (TextView) findViewById(R.id.action_bar_text_view);
        actionBarTitle.setText("Student Wellbeing");
        return true;
    }
    */

    public void onButtonClick(View v){
        ImageButton button = (ImageButton) v;

        switch(button.getId()){
            case R.id.menuButton1:
                Intent intent = new Intent(this, ButtonOneActivity.class);
                startActivity(intent);
                break;
            case R.id.menuButton2:

                break;
            case R.id.menuButton3:

                break;
            case R.id.menuButton4:

                break;
            case R.id.menuButton5:

                break;
            case R.id.menuButton6:

                break;
            case R.id.menuButton7:

                break;
            case R.id.menuButton8:

                break;
        }

    }

}
