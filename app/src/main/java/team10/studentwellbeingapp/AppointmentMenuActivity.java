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

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class AppointmentMenuActivity extends AppCompatActivity {
    String username;
    String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appointment_menu);

        Toolbar toolbar = (Toolbar) findViewById(R.id.buttonSevenToolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        username = bundle.get("Username").toString();
        password = bundle.get("Password").toString();

    }

    //go back to previous activity
    public void backButtonClick(View v){
        startActivity(new Intent(this, MainMenuActivity.class));
    }


    //
    public void openAppointmentBooker(View v) {
        Intent i = new Intent(this,BookingAppointmentActivity.class);
        i.putExtra("Username",username);
        i.putExtra("Password",password);
        startActivity(i);
    }



    public void openAppointmentManager(View v) {
        Intent i = new Intent(this,AppointmentManagerActivity.class);
        i.putExtra("Username",username);
        i.putExtra("Password",password);
        startActivity(i);
    }
}