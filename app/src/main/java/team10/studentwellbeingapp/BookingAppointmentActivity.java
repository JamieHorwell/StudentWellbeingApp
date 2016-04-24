package team10.studentwellbeingapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

public class BookingAppointmentActivity extends ActionBarActivity {

    AppointmentAccessorNew appointmentAccessor;
    AppointmentDay[] daysAppointments;
    int counter = 0;
    private Calendar currentday;
    private ListView listView1;
    FreeAppointmentAdapter adapter;
    String student;
    private String currentDay;
    String DateToDisplayFrom = "2016-05-05";

    TextView headerValue;
    View header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_appointment);
        Intent intent = getIntent();

        Bundle bundle = intent.getExtras();
        student = bundle.get("Username").toString();


        currentDay = getDaytoDisplayFrom();
        new retrieveData(this, currentDay).execute();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_booking_appointment, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public String convertToFormat(Calendar date) throws java.text.ParseException {
        Log.w("unformatted date", date.toString());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateWithoutTime = sdf.format(date.getTime());
        Log.w("formatted date:", dateWithoutTime);
        return dateWithoutTime;
    }

    public void nextDay(View v) {
        if(counter < 7) {
            counter++;
            listView1.removeHeaderView(header);
            currentday.add(Calendar.DATE, 1);
            try {
                new retrieveData(this, convertToFormat(currentday)).execute();
            }
            catch (java.text.ParseException e) {}
        }
        else {
            Alertdialog("No more days to show");
        }

    }

    public void previousDay(View v) {
        if(counter > 0) {
            counter--;
            listView1.removeHeaderView(header);
            currentday.add(Calendar.DATE, -1);
            try {
                new retrieveData(this, convertToFormat(currentday)).execute();
            }
            catch (java.text.ParseException e) {}
        } else {
            Alertdialog("No more days to show");
        }
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

    /* get the current date to display appointments from, a week in advance */
    public String getDaytoDisplayFrom() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, 7);
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-mm-dd");
        String formattedDate = formatDate.format(c.getTime());
        return formattedDate;
    }

    public void setCurrentday(Date currentday) {
        this.currentday = Calendar.getInstance();
    }

    public void getStudentDetails() {
            Bundle extras = getIntent().getExtras();
            if(extras !=null ) {
                String tempUsername = extras.get("Username").toString();
                String tempPassword = extras.get("Password").toString();
                Log.d("Username", tempUsername);
                Log.d("Password", tempPassword);

            }
    }


    //needs code added
    class retrieveData extends AsyncTask<String, String, AppointmentDay> {

        private Context mContext;
        private String dateToRetrieve;
        public retrieveData(Context context, String dateToRetrieve){
            mContext = context;
            this.dateToRetrieve = dateToRetrieve;
        }


        protected AppointmentDay doInBackground(String... args) {
          appointmentAccessor = new AppointmentAccessorNew();

         AppointmentDay testDay =   appointmentAccessor.getFreeAppointments(dateToRetrieve,student);




            return testDay;
        }
        @Override
        protected void onPostExecute(AppointmentDay result) {
                daysAppointments = new AppointmentDay[] {result};

            adapter = new FreeAppointmentAdapter(mContext,R.layout.appointment_item_row, daysAppointments[0]);

            listView1 = (ListView)findViewById(R.id.appointmentListView);

             header = (View)getLayoutInflater().inflate(R.layout.appointment_list_header_row, null);

            headerValue = (TextView) header.findViewById(R.id.DateTextView);
            headerValue.setText(daysAppointments[0].getDate());

            //this is first item in listview
            listView1.addHeaderView(header);
            listView1.setAdapter(adapter);
            listView1.setItemsCanFocus(true);
            adapter.notifyDataSetChanged();
        }

    }



}
