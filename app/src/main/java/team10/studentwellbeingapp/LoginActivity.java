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

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class LoginActivity extends AppCompatActivity {


    String username;
    String password;
    EditText usernameEdit;
    EditText passwordEdit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        Toolbar toolbar = (Toolbar) findViewById(R.id.buttonSixToolbar);
        setSupportActionBar(toolbar);
        usernameEdit = (EditText)findViewById(R.id.editTextStudentID);
        passwordEdit = (EditText)findViewById(R.id.editTextStudentID);
    }
    public void onButtonClick(View v) {
        Button button = (Button) v;
        if(button.getId() == R.id.loginButton){
            Intent i = new Intent(this,BookingAppointmentActivity.class);
            i.putExtra("Username",username);
            i.putExtra("Password",password);
            startActivity(i);

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

}