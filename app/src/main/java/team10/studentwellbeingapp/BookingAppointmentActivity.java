package team10.studentwellbeingapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
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


    AppointmentDay[] weeksAppointments;
    int counter = 0;
    private Calendar currentday;
    private ListView listView1;
    AppointmentAdapter adapter;
    TextView headerValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_appointment);
        Intent intent = getIntent();

        testAppointments();



         adapter = new AppointmentAdapter(this,R.layout.appointment_item_row, weeksAppointments[0]);

        listView1 = (ListView)findViewById(R.id.appointmentListView);

        View header = (View)getLayoutInflater().inflate(R.layout.appointment_list_header_row, null);

         headerValue = (TextView) header.findViewById(R.id.DateTextView);
        headerValue.setText(weeksAppointments[0].getDate());
        //this is first item in listview
        listView1.addHeaderView(header);

        listView1.setAdapter(adapter);

        //will return time of appointment trying to book
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String appointmentToBook = weeksAppointments[counter].get(position-1).getDatetime();
                Alertdialog("Book this slot? " + appointmentToBook);
            }
        });

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

    //setup test appointments
    public void testAppointments() {
        Appointment day1appt1= new Appointment(Calendar.getInstance().toString(), "Jan");
        Appointment day1appt2= new Appointment(Calendar.getInstance().toString(), "Jan");
        Appointment day2appt1= new Appointment(Calendar.getInstance().toString(), "Jan");
        Appointment day2appt2= new Appointment(Calendar.getInstance().toString(), "Jan");
        weeksAppointments = new AppointmentDay[] {day1,day2,day3};

    }


    public void nextDay(View v) {
        if(counter < 2) {
            counter++;
            headerValue.setText(weeksAppointments[counter].getDate());
            AppointmentAdapter newAdapter = new AppointmentAdapter(this, R.layout.appointment_item_row, weeksAppointments[counter]);
            listView1.setAdapter(newAdapter);
        }
        else {
            Alertdialog("No more days to show");
        }

    }

    public void previousDay(View v) {
        if(counter > 0) {
            counter--;
            headerValue.setText(weeksAppointments[counter].getDate());
            AppointmentAdapter newAdapter = new AppointmentAdapter(this, R.layout.appointment_item_row, weeksAppointments[counter]);
            listView1.setAdapter(newAdapter);
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
}
