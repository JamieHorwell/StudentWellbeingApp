package team10.studentwellbeingapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Jamie on 24/11/2015.
 */
public class AppointmentAdapter extends ArrayAdapter<Appointment> {
    Context context;
    int layoutResourceId;
    AppointmentDay day  = null;



    public AppointmentAdapter(Context context, int layoutResourceId, AppointmentDay data) {

        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.day = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        AppointmentHolder holder;
        final int finalpos = position;
        if(row == null) {
            LayoutInflater inflator = ((Activity)context).getLayoutInflater();
            row = inflator.inflate(layoutResourceId,parent,false);

            holder = new AppointmentHolder();
            holder.appointmentText = (TextView)row.findViewById(R.id.appointmentText);
            row.setTag(holder);

        }
        else {
            holder = (AppointmentHolder)row.getTag();
        }

        final String appointment = day.get(position).getDatetime();
        holder.appointmentText.setText(appointment);

        row.setClickable(true);
        row.setFocusable(true);
        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new bookAppointment(day.get(finalpos).getaid(), day.getStudent()).execute();

            }
        });


        return row;
    }





    static class AppointmentHolder {
            TextView appointmentText;

    }

    class bookAppointment extends AsyncTask<String, String, Boolean> {
        AppointmentAccessorNew appointmentAccessor;
        String aid;
        String studentNumber;
        public bookAppointment(String aid, String studentNumber) {
            this.aid = aid;
            this.studentNumber = studentNumber;

        }
        protected Boolean doInBackground(String... args) {
            String date;
            String time;
            appointmentAccessor = new AppointmentAccessorNew();
            appointmentAccessor.bookAppointment(studentNumber,aid,"password");


            return false;
        }

        protected void onPostExecute(Boolean result) {
            new AlertDialog.Builder(context).setTitle("Appointment Booked!").show();


        }
    }

}
