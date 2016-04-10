package team10.studentwellbeingapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
        if(((ImageButton) v).getId() == R.id.progressViewBackButton){
            startActivity(new Intent(this, MoodDiaryMenuActivity.class));
        }
    }

    public void deleteButtonClick(View v) {
        if(((Button) v).getId() == R.id.deleteButton){
            deleteDataCheck();
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
                TextView moodBox = (TextView) findViewById(R.id.progressMoodValueBox);
                TextView dateBox = (TextView) findViewById(R.id.progressDateBox);

                String moodValue = allEntries.get(i).getMoodValue() + "/100";
                String date = dates[i];
                String moodDesc = allEntries.get(i).getMoodDescription();
                moodBox.setText(moodValue);
                moodBox.setVisibility(View.VISIBLE);
                dateBox.setText(date);
                dateBox.setVisibility(View.VISIBLE);
                textBox.setText(moodDesc);
            }
        });
    }

    private void  deleteDataCheck(){
        AlertDialog.Builder dialog;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            dialog = new AlertDialog.Builder(this, android.R.style.Theme_Material_Light_Dialog_Alert);
        } else {
            dialog = new AlertDialog.Builder(this);
        }
        dialog.setTitle("Clear Diary?");
        dialog.setMessage("Are you sure you want to wipe the contents of your diary?");
        dialog.setNegativeButton("Cancel", null);
        dialog.setPositiveButton("Delete", new AlertDialog.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                clearData();
                finish();
                startActivity(getIntent());
            }
        });
        dialog.show();
    }

    private String getData(){ return prefs.getString("diary_entries", ""); }

    private void clearData(){
        prefs.edit().putString("diary_entries", "").commit();

    }
}
