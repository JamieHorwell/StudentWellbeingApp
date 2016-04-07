package team10.studentwellbeingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import java.text.SimpleDateFormat;

public class MoodDiaryEntryActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {
    SimpleDateFormat sdf = new SimpleDateFormat("h:mm, dd MMM, yyyy");
    EditText moodDesc;
    SeekBar slider;
    MoodEntry entry;
    MoodDiaryDataController dControl = new MoodDiaryDataController();

    int sliderValue;
    String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mood_diary_entry);

        moodDesc = (EditText)findViewById(R.id.moodDescription);
        slider = (SeekBar)findViewById(R.id.seekBar);
        slider.setOnSeekBarChangeListener(this);

        setupTimeDate();
    }

    public void onEntryButtonClick(View v){
        ImageButton button = (ImageButton) v;
        switch (button.getId()) {
            case R.id.moodEntryBackButton:
                startActivity(new Intent(this, MoodDiaryMenuActivity.class));
                break;
            case R.id.moodEntryTickButton:
                entry = createMoodEntry();
                dControl.addEntry(entry);

                Toast toast = Toast.makeText(this, "Your new entry has been recorded!", Toast.LENGTH_LONG);
                toast.show();
                startActivity(new Intent(this, MoodDiaryMenuActivity.class));
                break;
        }
    }

    private void setupTimeDate(){
        long currentDate = System.currentTimeMillis();
        TextView dateText = (TextView)findViewById(R.id.entryDateTime);
        date = sdf.format(currentDate);
        dateText.setText(date);
    }

    public MoodEntry createMoodEntry(){
        String moodValue = String.valueOf(sliderValue);
        String moodDescription;

        if(moodDesc.getText() != null) {
            moodDescription = moodDesc.getText().toString();
        } else {
            moodDescription = "No mood description entered.";
        }

        return new MoodEntry(date, moodValue, moodDescription);
    }

    @Override
    public void onProgressChanged(SeekBar slider, int progress, boolean fromUser) {
        // TODO Auto-generated method stub

        sliderValue = progress;
    }

    @Override
    public void onStartTrackingTouch(SeekBar slider) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onStopTrackingTouch(SeekBar slider) {
        // TODO Auto-generated method stub

    }
}
