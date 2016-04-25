package team10.studentwellbeingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class ContentMindTheGapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_mind_the_gap);
    }


    //go back to previous activity
    public void backButtonClick(View v){
        startActivity(new Intent(this, ContentMenuActivity.class));
    }
}
