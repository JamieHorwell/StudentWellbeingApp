package team10.studentwellbeingapp;

/**
 * Created by Jason on 4/7/2016.
 */
public class MoodEntry {
    private final static String DELIMITER = "~$^||Between.Words||^$~";
    private String date;
    private String moodValue;
    private String moodDescription;
    private String encodedData;

    public MoodEntry(String date, String moodValue, String moodDescription){
        this.date = date;
        this.moodValue = moodValue;
        this.moodDescription = moodDescription;

        encodeData();
    }

    public MoodEntry(String encodedData){
        this.encodedData = encodedData;

        decodeData();
    }

    private void encodeData(){
        encodedData = date + DELIMITER + moodValue + DELIMITER + moodDescription;
    }

    private void decodeData(){
        int x = 0;
        for(String word : encodedData.split(DELIMITER)){
            if(x == 0) date = word;
            if(x == 1) moodValue = word;
            if(x == 2) moodDescription = word;
            x++;
        }
    }

    public String getDate(){
        return date;
    }

    public String getMoodValue() {
        return moodValue;
    }

    public String getMoodDescription(){
        return moodDescription;
    }

    public String getEncodedData(){
        return encodedData;
    }
}
