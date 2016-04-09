/*
Initial Author: Jason Ian Murray, Date: 26/11/2015
Notes:
    -   A simple placeholder class; the intention of this class
    is to allow others to see the functionality of the GUI as a whole.
    -   This can be extended or replaced by others. You need only copy
    and paste your code within the corresponding classes or refactor the
    name of this class to match yours then replace it with your own work.
*/

package team10.studentwellbeingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class MoodDiaryMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mood_diary_menu);

        Toolbar toolbar = (Toolbar) findViewById(R.id.buttonTwoToolbar);
        setSupportActionBar(toolbar);
    }

    public void onButtonClick(View v) {
        Button button = (Button) v;
        switch (button.getId()) {
            case R.id.editMoodButton:
                startActivity(new Intent(this, MoodDiaryEntryActivity.class));
                break;
            case R.id.viewProgressButton:
                startActivity(new Intent(this, MoodDiaryProgressView.class));
                break;        }
    }
}
