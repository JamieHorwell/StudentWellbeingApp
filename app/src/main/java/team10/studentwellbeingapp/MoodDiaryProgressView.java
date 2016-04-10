package team10.studentwellbeingapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MoodDiaryProgressView extends AppCompatActivity {
    List<MoodEntry> allEntries = new ArrayList<MoodEntry>();
    SharedPreferences prefs;
    int sliderValue;
    String date;
    String moodDesc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood_diary_progress_view);
        prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        getDataList();
        populateListView();
    }

    public void onProgressButtonClick(View v) {
        ImageButton button = (ImageButton) v;
        switch (button.getId()) {
            case R.id.progressViewBackButton:
                startActivity(new Intent(this, MoodDiaryMenuActivity.class));
                break;
        }
    }

    private void getDataList(){
        DataEncoder encoder = new DataEncoder();
        allEntries = encoder.decodeDataToList(getData());
    }

    private void populateListView(){
        final ListView list = (ListView)findViewById(R.id.listView);
        int dataSize = allEntries.size();
        MoodEntry current;
        final String [] dates = new String[dataSize];
        final String [] moodValues = new String[dataSize];

        for(int x = 0; x < dataSize; x++){
            current = allEntries.get(x);
            dates[x] = current.getDate().replace("Save: ", "");
            moodValues[x] = current.getMoodValue();
        }

        ListAdapter adapter = new MoodViewAdapter<String>(this, dates, moodValues);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                EditText textBox = (EditText) findViewById(R.id.moodPreviewBox);
                String date = "Date of entry: " + dates[i].replace("Save: ", "") + "\n";
                String moodValue = "Your mood rating: " + allEntries.get(i).getMoodValue() + "/100\n";
                String moodDesc = "Description: " + allEntries.get(i).getMoodDescription() + "\n";
                textBox.setText(date + moodValue + moodDesc);
            }
        });
    }

    public String getData(){ return prefs.getString("diary_entries", ""); }

    public void clearData(){
        prefs.edit().putString("diary_entries", "").commit();

    }
}
