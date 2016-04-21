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

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class ContentMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_menu);
    }

    public void onContentButtonClick(View v){
        Button b = (Button) v;
        switch(b.getId()){
            case R.id.studentWellbeingButton:
                startActivity(new Intent(this, ContentStudentWellbeingActivity.class));
                break;

            case R.id.mindTheGapButton:
                startActivity(new Intent(this, ContentMindTheGapActivity.class));
                break;

            case R.id.studentOfficersButton:
                startActivity(new Intent(this, ContentStudentOfficersActivity.class));
                break;

            case R.id.commonMentalHealthIssuesAmongStudentsButton:
                startActivity(new Intent(this, ContentCommonIssuesActivity.class));
                break;
        }
    }

    public void onContentMenuBackButtonClick(View v){
        ImageButton b = (ImageButton) v;
        if(b.getId() == R.id.contentMenuBackButton)
            startActivity(new Intent(this, MainMenuActivity.class));
    }
}
