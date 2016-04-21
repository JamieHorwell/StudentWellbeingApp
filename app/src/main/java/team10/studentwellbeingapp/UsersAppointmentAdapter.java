package team10.studentwellbeingapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Jamie on 24/11/2015.
 */
public class UsersAppointmentAdapter extends ArrayAdapter<Appointment> {
    Context context;
    int layoutResourceId;
    ArrayList<Appointment> usersAppointments  = null;



    public UsersAppointmentAdapter(Context context, int layoutResourceId, ArrayList<Appointment> data) {

        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.usersAppointments = data;
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

        final String appointment = usersAppointments.get(position).getTime();
        holder.appointmentText.setText(appointment);
        holder.councillorText.setText(usersAppointments.get(position).getCouncillor());
        row.setClickable(true);
        row.setFocusable(true);
        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Alertdialog("Are you sure you wish to cancel this appointment?" , usersAppointments.get(finalpos));

            }
        });


        return row;
    }





    static class AppointmentHolder {
        TextView appointmentText;
        TextView councillorText;
    }

    public void Alertdialog(String message, final Appointment appointment) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message);
        builder.setCancelable(true);
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                new cancelAppointment(appointment.getaid(),appointment.getStudent()).execute();
            }
        });
        AlertDialog warnNoMoreDays = builder.create();
        warnNoMoreDays.show();


    }




    class cancelAppointment extends AsyncTask<String, String, Boolean> {
        AppointmentAccessorNew appointmentAccessor;
        String aid;
        String studentNumber;
        public cancelAppointment(String aid, String studentNumber) {
            this.aid = aid;
            this.studentNumber = studentNumber;

        }
        protected Boolean doInBackground(String... args) {
            String date;
            String time;
            appointmentAccessor = new AppointmentAccessorNew();
            appointmentAccessor.cancelAppointment(studentNumber, "password", aid);


            return false;
        }

        protected void onPostExecute(Boolean result) {
            new AlertDialog.Builder(context).setTitle("Appointment Cancelled!").show();


        }
    }

}
