/*
Initial Author: Jason Ian Murray, Date: 18/0/2016
Notes:
    -   Added functionality to verify input is as expected.
    -   Ensured that data is saved on the correct registration of a new account and that
        these datails are saved locally.
*/

package team10.studentwellbeingapp;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_activity);
    }

    public void onButtonClick(View v){
        Boolean complete = false;
        Button button = (Button) v;

        String [] data = getData();

        if(data != null) {
            if (validateData(data)) {
                saveData(data);
                complete = true;
            }
        }

        if(complete){
            startActivity(new Intent(this, ButtonSixActivity.class));
        }
    }

    public Boolean validateData(String [] data){
        Boolean complete = true;
        String errorMessage = "Cannot progress with registration: ";

        //Checking to make sure that none of the fields have been left blank
        for(int i = 0; i < data.length; i++){
            if(data[i].equals("")){
                //Error, field is blank.
                complete = false;
            }
        }
        if(!complete){
            errorMessage = errorMessage + "One or more fields is blank. ";
        }

        //Checking passwords match and are not too short
        if(!data[2].equals(data[3])){
            //Error, passwords do not match
            errorMessage = errorMessage + "Your passwords do not match. ";
            complete = false;
        } else {
            if(data[2].length() < 6){
                //Error, password too short
                errorMessage = errorMessage + " Your password is too short; it must be at least 6 characters long. ";
                complete = false;
            }
        }

        if(!complete){
            Toast toast = Toast.makeText(this, errorMessage, Toast.LENGTH_LONG);
            toast.show();
        }
        return complete;
    }

    private String [] getData(){
        //Storing all info within an array for easier manipulation:
        //data[0] = student id, data[1] = email, data[2] = password1, data[3] = password2
        Boolean complete = true;
        String [] data = new String[4];

        if(((EditText)findViewById(R.id.registrationStudentID)).getText() != null){
            data[0] = ((EditText) findViewById(R.id.registrationStudentID)).getText().toString();
        } else complete = false;

        if(((EditText)findViewById(R.id.registrationEmail)).getText() != null){
            data[1] = ((EditText) findViewById(R.id.registrationEmail)).getText().toString();
        } else complete = false;

        if(((EditText)findViewById(R.id.registrationPassword1)).getText() != null){
            data[2] = ((EditText) findViewById(R.id.registrationPassword1)).getText().toString();
        } else complete = false;

        if(((EditText)findViewById(R.id.registrationPassword2)).getText() != null){
            data[3] = ((EditText) findViewById(R.id.registrationPassword2)).getText().toString();
        } else complete = false;

        return (!complete) ? null : data;
    }

    private void saveData(String [] data){
        SharedPreferences.Editor editor = getSharedPreferences("user_prefs", MODE_PRIVATE).edit();
        editor.putString("student_number", data[0]);
        editor.putString("user_password", data[2]);
        editor.commit();
    }
}
