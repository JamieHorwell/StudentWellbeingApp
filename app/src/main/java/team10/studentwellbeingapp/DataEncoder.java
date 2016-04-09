package team10.studentwellbeingapp;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by Jason on 4/9/2016.
 */
public class DataEncoder {
    String WORD_SEP = "{W>";
    String FRAG_SEP = "{F>";

    public DataEncoder(){

    }

    public String getNewDataEncoding(MoodEntry newEntry, String oldData){
        String newData = encodeNewEntry(newEntry);
        return encodeNewAndOld(newData, oldData);
    }

    private String encodeNewEntry(MoodEntry newEntry){
        return newEntry.getDate() + FRAG_SEP + newEntry.getMoodValue() + FRAG_SEP + newEntry.getMoodDescription();
    }

    private String encodeNewAndOld(String newData, String oldData){
        return newData + WORD_SEP + oldData;
    }

    public List<MoodEntry> decodeDataToList(String encodedData){
        List<MoodEntry> entries = new ArrayList<MoodEntry>();
        String [] words = encodedData.split(Pattern.quote(WORD_SEP));
        for(int x = 0; x < words.length; x++){
            String [] frag = words[x].split(Pattern.quote(FRAG_SEP));
            if(frag.length > 1) entries.add(new MoodEntry(frag[0], frag[1], frag[2]));
        }
        return entries;
    }

}
