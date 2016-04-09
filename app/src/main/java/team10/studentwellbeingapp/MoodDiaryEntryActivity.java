package team10.studentwellbeingapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
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
    EditText moodDesc;
    SeekBar slider;
    MoodEntry entry;
    SharedPreferences prefs;
    int sliderValue;
    String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mood_diary_entry);

        moodDesc = (EditText)findViewById(R.id.moodDescription);
        slider = (SeekBar)findViewById(R.id.seekBar);
        slider.setOnSeekBarChangeListener(this);
        prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        setupTimeDate();
    }

    private void setupTimeDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("h:mm, dd MMM, yyyy");
        date = sdf.format(System.currentTimeMillis());
        ((TextView) findViewById(R.id.entryDateTime)).setText(date);
    }

    public void onEntryButtonClick(View v){
        ImageButton button = (ImageButton) v;
        switch (button.getId()) {
            case R.id.moodEntryBackButton:
                startActivity(new Intent(this, MoodDiaryMenuActivity.class));
                break;
            case R.id.moodEntryTickButton:
                createMoodEntry();
                storeData(encodeData());
                toastUser();
                startActivity(new Intent(this, MoodDiaryMenuActivity.class));
                break;
        }
    }

    private void createMoodEntry(){
        String moodDescription = "No mood description entered.";
        if(moodDesc.getText() != null) moodDescription = moodDesc.getText().toString();
        entry = new MoodEntry(date, String.valueOf(sliderValue), moodDescription);
    }

    private String encodeData(){ return new DataEncoder().getNewDataEncoding(entry, getData()); }

    private void toastUser(){ Toast.makeText(this, "Your new entry has been recorded!", Toast.LENGTH_LONG).show(); }

    private void storeData(String data){ prefs.edit().putString("diary_entries", "Save: " + data).apply(); }

    private String getData(){ return prefs.getString("diary_entries", ""); }

    @Override
    public void onProgressChanged(SeekBar slider, int progress, boolean fromUser) { // TODO Auto-generated method stub
        sliderValue = progress;
    }

    @Override
    public void onStartTrackingTouch(SeekBar slider) { // TODO Auto-generated method stub
    }

    @Override
    public void onStopTrackingTouch(SeekBar slider) { // TODO Auto-generated method stub
    }
}
