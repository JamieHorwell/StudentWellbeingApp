package team10.studentwellbeingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.SimpleDateFormat;

public class MoodDiaryEntryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mood_diary_entry);
        setupTimeDate();
    }

    private void setupTimeDate(){
        long date = System.currentTimeMillis();
        TextView dateText = (TextView)findViewById(R.id.entry_date_time);
        SimpleDateFormat sdf = new SimpleDateFormat("h:mm, dd MMM, yyyy");
        String dateString = sdf.format(date);
        dateText.setText(dateString);
    }

    public void onClick(View v){
        ImageButton button = (ImageButton) v;
        switch (button.getId()) {
            case R.id.moodEntryBackButton:
                startActivity(new Intent(this, ButtonOneActivity.class));
                break;
            case R.id.moodEntryTickButton:
                startActivity(new Intent(this, MoodDiaryMenuActivity.class));
                break;
        }
    }
}
