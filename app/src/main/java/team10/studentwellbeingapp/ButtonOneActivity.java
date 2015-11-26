/*
Initial Author: Jason Ian Murray, Date: 25/11/2015
Notes:
    -   This is the activity that launches the the content for
    button one.
    - Defined actionBar's layout/text values.

Extended By: , Date:
Notes:
*/

package team10.studentwellbeingapp;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ButtonOneActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.button_one);
    }
      /*
    Need to build a custom toolbar for the following to work.
    Otherwise we get null pointer.
    public boolean onCreateOptionsMenu(Menu menu){
        ActionBar actionBar = this.getActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(R.layout.action_bar);

        TextView actionBarTitle = (TextView) findViewById(R.id.action_bar_text_view);
        actionBarTitle.setText("Button One Template");
        return true;
    }
    */
}
