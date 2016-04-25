package team10.studentwellbeingapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Jamie on 24/11/2015.
 * adapter to fetch information on appointments from database and display them in a listview
 * as well as provide booking implementation for each appointment
 */
public class FreeAppointmentAdapter extends ArrayAdapter<Appointment> {
    Context context;
    int layoutResourceId;
    AppointmentDay day  = null;
    String username = "";
    String password = "";


    public FreeAppointmentAdapter(Context context, int layoutResourceId, AppointmentDay data) {

        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.day = data;
        Bundle extras = ((Activity) context).getIntent().getExtras();
        if(extras !=null ) {
            username = extras.get("Username").toString();
            password = extras.get("Password").toString();
        }
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
            holder.councillorText = (TextView)row.findViewById(R.id.councillorText);
            row.setTag(holder);

        }
        else {
            holder = (AppointmentHolder)row.getTag();
        }

        final String appointment = day.get(position).getTime();
        holder.appointmentText.setText(appointment);

        row.setClickable(true);
        row.setFocusable(true);
        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bookingAlertdialog("Are you sure you wish to book this appointment?", day.get(finalpos), context);
            }
        });


        return row;
    }





    static class AppointmentHolder {
            TextView appointmentText;
            TextView councillorText;

    }

    public void bookingAlertdialog(String message, final Appointment appointment, final Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message);
        builder.setCancelable(true);
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                new checkStudentsAppointments(appointment, username, password).execute();

            }
        });
        builder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.cancel();
                    }
                }
        );
        AlertDialog bookingDialog = builder.create();
        bookingDialog.show();


    }

    public void generalAlertdialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message);
        builder.setCancelable(true);
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog generalAlert = builder.create();
        generalAlert.show();


    }


    /** Books currently selected appointment */
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

            appointmentAccessor.bookAppointment(username,password,aid);


            return false;
        }

        protected void onPostExecute(Boolean result) {
            new AlertDialog.Builder(context).setTitle("Appointment Booked!").show();



        }
    }


    /** Check user hasn't overbooked appointments, before attempting to book currently selected appointment */
    class checkStudentsAppointments extends AsyncTask<String, String, ArrayList<Appointment>> {

        AppointmentAccessorNew appointmentAccessor;

        private Appointment appointment;
        private String studentNumber;
        private String password;
        ArrayList<Appointment> usersAppointments;

        public checkStudentsAppointments(Appointment appointment, String studentNumber, String password) {
            this.appointment = appointment;
            this.studentNumber = studentNumber;
            this.password = password;

        }
        protected ArrayList<Appointment> doInBackground(String... args) {
            appointmentAccessor = new AppointmentAccessorNew();
            usersAppointments = appointmentAccessor.getUserAppointments(studentNumber, password);
            return usersAppointments;
        }

        protected void onPostExecute(ArrayList<Appointment> result) {
            //user hasn't exceeded booking limit
            if(result.size() < 2) {
                new bookAppointment(appointment.getaid(), appointment.getStudent()).execute();
            } else {
                generalAlertdialog("You already have the maximum allowed booked appointments.");
            }


        }
    }



}
