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

    public void onMindTheGapBackButtonClick(View v) {
        ImageButton b = (ImageButton) v;
        if (b.getId() == R.id.contentMindTheGapBackButton)
            startActivity(new Intent(this, ContentMenuActivity.class));
    }
}
