package team10.studentwellbeingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Jason on 4/10/2016.
 */
class MoodViewAdapter<S> extends ArrayAdapter<String> {
    String [] dates;
    String [] moodValue;

    public MoodViewAdapter(Context context, String [] dates, String[] moodValue) {
        super(context, R.layout.listview_row, dates);
        this.dates = dates;
        this.moodValue = moodValue;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View v = inflater.inflate(R.layout.listview_row, parent, false);
        String date = dates[i].replace("Save: ", "");
        TextView text = (TextView) v.findViewById(R.id.listViewRowText);
        text.setText(date);

        ImageView image = (ImageView) v.findViewById(R.id.listViewRowImage);
        if(Integer.valueOf(moodValue[i]) < 50){
            image.setImageResource(R.drawable.unhappy_face);
        } else if(Integer.valueOf(moodValue[i]) >= 50){
            image.setImageResource(R.drawable.happy_face);
        }

        return v;
    }
}
