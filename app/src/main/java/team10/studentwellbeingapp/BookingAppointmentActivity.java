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
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

public class BookingAppointmentActivity extends ActionBarActivity {

    AppointmentAccessor appointmentAccessor;
    AppointmentDay[] weeksAppointments;
    int counter = 0;
    private Calendar currentday;
    private ListView listView1;
    AppointmentAdapter adapter;
    String student = "150068502";

    String DateToDisplayFrom = "2015-00-01";

    TextView headerValue;
    View header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_appointment);
        Intent intent = getIntent();

        Bundle bundle = intent.getExtras();
        student = bundle.get("Username").toString();



        currentday = Calendar.getInstance();
        currentday.set(2015, 00, 01);
        try {
            new retrieveData(this, convertToFormat(currentday)).execute();
        }
        catch (java.text.ParseException e) {

        }
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
        if(counter < 2) {
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

    public Calendar getCurrentday() {
        return currentday;
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
          appointmentAccessor = new AppointmentAccessor();

         AppointmentDay testDay =   appointmentAccessor.getFreeAppointments(dateToRetrieve,student);
            for(int i = 0; i < testDay.size(); i++) {

            }



            return testDay;
        }
        @Override
        protected void onPostExecute(AppointmentDay result) {
                weeksAppointments = new AppointmentDay[] {result};

            adapter = new AppointmentAdapter(mContext,R.layout.appointment_item_row, weeksAppointments[0]);

            listView1 = (ListView)findViewById(R.id.appointmentListView);

             header = (View)getLayoutInflater().inflate(R.layout.appointment_list_header_row, null);

            headerValue = (TextView) header.findViewById(R.id.DateTextView);
            headerValue.setText(weeksAppointments[0].getDate());
            //this is first item in listview
            listView1.addHeaderView(header);

            listView1.setAdapter(adapter);
            listView1.setItemsCanFocus(true);
            //will return time of appointment trying to book
//            listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                    String appointmentToBook = weeksAppointments[counter].get(position - 1).getDatetime();
//                    Alertdialog("Book this slot? " + appointmentToBook);
//                    new bookAppointment(weeksAppointments[counter].get(position - 1).getDatetime(), student).execute();
//                }
//            });
            adapter.notifyDataSetChanged();
        }

    }

    class bookAppointment extends AsyncTask<String, String, Boolean> {

        String dateTime[];
        String studentNumber;
        public bookAppointment(String dateTime, String studentNumber) {
                this.dateTime = dateTime.split("\\s+");;
                this.studentNumber = studentNumber;

        }
        protected Boolean doInBackground(String... args) {
            String date;
            String time;
            appointmentAccessor = new AppointmentAccessor();
            appointmentAccessor.bookAppointment(studentNumber,dateTime[0],dateTime[1],"password");


            return false;
        }

        protected void onPostExecute(Boolean result) {
            Alertdialog("Appointment Booked! ");


        }
    }


}
