/*
Initial Author: Jason Ian Murray, Date: 26/11/2015
Notes:
    -   A simple placeholder class; the intention of this class
    is to allow others to see the functionality of the GUI as a whole.
    -   This can be extended or replaced by others. You need only copy
    and paste your code within the corresponding classes or refactor the
    name of this class to match yours then replace it with your own work.
Extended By: Jason Ian Murray, Date: 17/02/2016
Notes:
    -   Added code support to allow for a login screen to work. This is to work
    conjunction with the appointment booking feature.
*/


package team10.studentwellbeingapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class LoginActivity extends AppCompatActivity {


    String username;
    String password;
    EditText usernameEdit;
    EditText passwordEdit;
    AppointmentAccessorNew appointmentAccessor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        Toolbar toolbar = (Toolbar) findViewById(R.id.buttonSixToolbar);
        setSupportActionBar(toolbar);
        usernameEdit = (EditText)findViewById(R.id.editTextStudentID);
        passwordEdit = (EditText)findViewById(R.id.editTextPassword);
    }
    public void onButtonClick(View v) {
        Button button = (Button) v;
        if(button.getId() == R.id.loginButton){
//            Intent i = new Intent(this,BookingAppointmentActivity.class);
//            i.putExtra("Username",username);
//            i.putExtra("Password",password);
//            startActivity(i);
            String[] logindetails = getLoginDetails();
            new attemptLogin(logindetails[0],logindetails[1],this).execute();
        } else if(button.getId() == R.id.registerButton){
            startActivity(new Intent(this, RegistrationActivity.class));

        }
    }
    public String[] getLoginDetails() {
        String[] loginDetails = new String[2];
        loginDetails[0] = usernameEdit.getText().toString();
        loginDetails[1] = passwordEdit.getText().toString();
        return loginDetails;
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


    class attemptLogin extends AsyncTask<String, String, Boolean> {

        String username;
        String password;
        Context mcontext;
        loginResult loginResult;
        public attemptLogin(String username, String password, Context context) {
            this.username = username;
            this.password = password;
            mcontext = context;

        }
        protected Boolean doInBackground(String... args) {
            appointmentAccessor = new AppointmentAccessorNew();
            loginResult = appointmentAccessor.logIn(username, password);
            return true;
        }

        protected void onPostExecute(Boolean result) {

            Log.w("loginresultText", loginResult.logintext);
            if(loginResult.loggedin) {
                Intent i = new Intent(mcontext,AppointmentMenuActivity.class);
                i.putExtra("Username",username);
                i.putExtra("Password",password);
                startActivity(i);
            }
            else if(loginResult.logintext.equals("invalid username/password")) {
                Alertdialog("Invalid Username/Password combination, please try again.");

            }
            //login has failed due to too many attempts
            else {
                Alertdialog("Too many login attempts!!!!");
            }

        }
    }


}