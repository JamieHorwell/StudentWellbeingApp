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
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class AppointmentManagerActivity extends AppCompatActivity {


    Appointment currentBookedAppointment;
    ListView usersAppointmentsListView;
    UsersAppointmentAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appointment_manager);

        Toolbar toolbar = (Toolbar) findViewById(R.id.buttonEightToolbar);
        setSupportActionBar(toolbar);

        new getStudentsAppointment(this,"123","password").execute();
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





    class getStudentsAppointment extends AsyncTask<String, String, ArrayList<Appointment>> {
        AppointmentAccessorNew appointmentAccessor;

        private Context mContext;
        private String studentNumber;
        private String password;
        ArrayList<Appointment> appointments;

        public getStudentsAppointment(Context context, String studentNumber, String password) {
            this.mContext = context;
            this.studentNumber = studentNumber;
            this.password = password;

        }
        protected ArrayList<Appointment> doInBackground(String... args) {
            appointmentAccessor = new AppointmentAccessorNew();

            appointments = appointmentAccessor.getUserAppointments(studentNumber, "password");




            return appointments;
        }

        protected void onPostExecute(ArrayList<Appointment> result) {

            adapter = new UsersAppointmentAdapter(mContext,R.layout.booked_appointment_item_row, result);

            usersAppointmentsListView = (ListView)findViewById(R.id.appointmentListView);
            usersAppointmentsListView.setAdapter(adapter);
            usersAppointmentsListView.setItemsCanFocus(true);

        }
    }


}
