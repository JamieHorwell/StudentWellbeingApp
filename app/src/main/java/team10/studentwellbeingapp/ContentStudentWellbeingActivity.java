package team10.studentwellbeingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class ContentStudentWellbeingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_student_wellbeing);
    }

    public void onStudentWellbeingBackButtonClick(View v) {
        ImageButton b = (ImageButton) v;
        if (b.getId() == R.id.contentStudentWellbeingBackButton)
            startActivity(new Intent(this, ContentMenuActivity.class));
    }
}