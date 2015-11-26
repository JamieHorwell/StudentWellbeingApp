package team10.studentwellbeingapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

/**
 * Created by Jamie on 24/11/2015.
 */
public class AppointmentAdapter extends ArrayAdapter<String> {
    Context context;
    int layoutResourceId;
    AppointmentDate data[] = null;
    String[] appointments;

    public AppointmentAdapter(Context context, int layoutResourceId, String[] data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.appointments = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        AppointmentHolder holder = null;

        if(row == null) {
            LayoutInflater inflator = ((Activity)context).getLayoutInflater();
            row = inflator.inflate(layoutResourceId,parent,false);

            holder = new AppointmentHolder();
            holder.appointmentText = (TextView)row.findViewById(R.id.appointmentText);
            holder.checkbox = (CheckBox)row.findViewById(R.id.checkBox);

        }
        else {
            holder = (AppointmentHolder)row.getTag();
        }

        String appointment = appointments[position];
        holder.appointmentText.setText(appointment);

        return row;
    }





    static class AppointmentHolder {
            TextView appointmentText;
            CheckBox checkbox;
    }
}
