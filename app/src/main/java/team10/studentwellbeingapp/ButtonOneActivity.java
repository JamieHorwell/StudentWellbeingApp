/*
Initial Author: Jason Ian Murray, Date: 25/11/2015
Notes:
    -   This is the activity that launches the the content for
    button one.

Extended By: Jason Ian Murray, Date: 26/11/2015
Notes:
    -   Added the toolbar; changed the previous implementation
    and replaced it with a more stable XML variant.
*/

package team10.studentwellbeingapp;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class ButtonOneActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.button_one);

        Toolbar toolbar = (Toolbar) findViewById(R.id.buttonOneToolbar);
        setSupportActionBar(toolbar);
    }
}
