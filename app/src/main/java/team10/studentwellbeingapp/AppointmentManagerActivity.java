/*
Initial Author: Jason Ian Murray, Date: 26/11/2015
Notes:
    -   A simple placeholder class; the intention of this class
    is to allow others to see the functionality of the GUI as a whole.
    -   This can be extended or replaced by others. You need only copy
    and paste your code within the corresponding classes or refactor the
    name of this class to match yours then replace it with your own work.
*/

package team10.studentwellbeingapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class AppointmentManagerActivity extends AppCompatActivity {


    Appointment currentBookedAppointment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appointment_manager);

        Toolbar toolbar = (Toolbar) findViewById(R.id.buttonEightToolbar);
        setSupportActionBar(toolbar);

        currentBookedAppointment = null;
    }



    private void setAppointmentText(Appointment appointment) {
        TextView CurrentAppointment =  (TextView)findViewById(R.id.appointmentText);
        if(appointment == null) {
            CurrentAppointment.setText(appointment.toString());
        }
        else {
            CurrentAppointment.setText("No appointment currently booked");
        }
    }

    public void cancelAppointmentClicked(View v) {

        new cancelAppointment("2015-01-01 09:00:00", "123", "password").execute();

    }

    public void Alertdialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setCancelable(true);
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog warnNoMoreDays = builder.create();
        warnNoMoreDays.show();


    }

    public void setUpListView() {


    }



    class cancelAppointment extends AsyncTask<String, String, Boolean> {
        AppointmentAccessorNew appointmentAccessor;
        String aid;
        String studentNumber;
        String password;

        public cancelAppointment(String aid, String studentNumber, String password) {
            this.aid = aid;
            this.studentNumber = studentNumber;
            this.password = password;

        }
        protected Boolean doInBackground(String... args) {
            appointmentAccessor = new AppointmentAccessorNew();
            appointmentAccessor.cancelAppointment(studentNumber, aid , password);


            return true;
        }





        protected void onPostExecute(Boolean result) {

            //appointment cancelled, changes made to database
            if(result = true) {

                Alertdialog("Appointment Successfully Cancelled!");


            }
            //unsuccesfull connection to server, unable to cancel booking
            else {
                Alertdialog("Unable to connect to servers, please try again.");
            }

        }
    }

    class getStudentsAppointment extends AsyncTask<String, String, ArrayList<Appointment>> {
        AppointmentAccessorNew appointmentAccessor;

        String studentNumber;
        String password;
        ArrayList<Appointment> appointments;

        public getStudentsAppointment(String studentNumber, String password) {

            this.studentNumber = studentNumber;
            this.password = password;

        }
        protected ArrayList<Appointment> doInBackground(String... args) {
            appointmentAccessor = new AppointmentAccessorNew();

            appointments = appointmentAccessor.getUserAppointments(studentNumber, "password");




            return appointments;
        }

        protected void onPostExecute(Appointment result) {
            if(result == null) {
                setAppointmentText(null);
            }
            else {
                currentBookedAppointment = result;
                setAppointmentText(currentBookedAppointment);
            }

        }
    }


}
