//package team10.studentwellbeingapp;
//
//import android.content.Context;
//import android.content.SharedPreferences;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by Jason on 4/7/2016.
// */
//public class MoodDiaryDataController {
//    List<MoodEntry> allEntries = new ArrayList<MoodEntry>();
//    private final static String DELIMITER = "~$^||Divides.Strings||^$~";
//    private String encodedData;
//
//    public MoodDiaryDataController(){
//        getEntries();
//    }
//
//    private void encodeListToString(){
//        encodedData = "";
//        for(int x = 0; x < allEntries.size() - 1; x ++){
//            encodedData = encodedData
//                    + allEntries.get(x).getEncodedData()
//                    + DELIMITER;
//        }
//        encodedData = encodedData + allEntries.get(allEntries.size() - 1);
//    }
//
//    private void decodeListFromString(){
//        allEntries = new ArrayList<MoodEntry>();
//        for(String dataEntry : encodedData.split(DELIMITER)){
//            allEntries.add(new MoodEntry(dataEntry));
//        }
//    }
//
//    public void addEntry(MoodEntry entry){
//        allEntries.add(entry);
//        storeEntries();
//    }
//
//    private void getEntries(){
//        //fetch all previously saved entries from storage
//
//    }
//
//    private void storeEntries(){
//        encodeListToString();
//
//    }
//
//}
