package team10.studentwellbeingapp;

/**
 * Created by Jason on 4/7/2016.
 */
public class MoodEntry {
    private String date;
    private String moodValue;
    private String moodDescription;

    public MoodEntry(String date, String moodValue, String moodDescription){
        this.date = date;
        this.moodValue = moodValue;
        this.moodDescription = moodDescription;
    }

    public String getDate(){
        return date;
    }

    public String getMoodValue() {
        return moodValue;
    }

    public String getMoodDescription(){ return moodDescription; }
}
