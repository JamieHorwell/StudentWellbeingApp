/*
Initial Author: Jason Ian Murray, Date: 26/11/2015
Notes:
    -   A simple placeholder class; the intention of this class
    is to allow others to see the functionality of the GUI as a whole.
    -   This can be extended or replaced by others. You need only copy
    and paste your code within the corresponding classes or refactor the
    name of this class to match yours then replace it with your own work.
*/
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
import android.widget.CheckBox;
import android.widget.RadioButton;


public class SymptomCheckerActivity extends AppCompatActivity {
    //Give all issues a score. If the score reaches a certain point, the button will activate on the next page
    private static int depression = 0;
    private static int issue2 = 0;
    private static int issue3 = 0;
    private static int issue4 = 0;
    CheckBox sym5;
    RadioButton sym1op1, sym1op2, sym1op3, sym1op4, sym2op1, sym2op2, sym2op3, sym2op4, sym3op1, sym3op2, sym3op3, sym3op4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.symptom_checker);
        Toolbar toolbar = (Toolbar) findViewById(R.id.buttonSixToolbar);
        setSupportActionBar(toolbar);
    }

    public void onSymptomButtonClick(View v){
        //resets values each time button is clicked
        depression = 0;
        issue2 = 0;
        issue3 = 0;
        issue4 = 0;

        sym1op1=(RadioButton)findViewById(R.id.symptom1Never);
        sym1op2=(RadioButton)findViewById(R.id.symptom1Rarely);
        sym1op3=(RadioButton)findViewById(R.id.symptom1Sometimes);
        sym1op4=(RadioButton)findViewById(R.id.symptom1Often);

        sym2op1=(RadioButton)findViewById(R.id.symptom2Never);
        sym2op2=(RadioButton)findViewById(R.id.symptom2Rarely);
        sym2op3=(RadioButton)findViewById(R.id.symptom2Sometimes);
        sym2op4=(RadioButton)findViewById(R.id.symptom2Often);

        sym3op1=(RadioButton)findViewById(R.id.symptom3Never);
        sym3op2=(RadioButton)findViewById(R.id.symptom3Rarely);
        sym3op3=(RadioButton)findViewById(R.id.symptom3Sometimes);
        sym3op4=(RadioButton)findViewById(R.id.symptom3Often);

        sym5=(CheckBox)findViewById(R.id.symptom5);

        //Give each checkbox a certain score for each issue. This will add up the score for the checkboxes that are checked.
        if(sym1op1.isChecked()){

        }
        else if(sym1op2.isChecked()){
            depression += 1;
            issue2 += 1;
        }
        else if(sym1op3.isChecked()){
            depression += 3;
            issue2 += 2;
        }
        else if(sym1op4.isChecked()){
            depression += 5;
            issue2 += 3;
            issue3 += 2;
        }

        if(sym2op1.isChecked()){
            depression += 0;
        }
        else if(sym2op2.isChecked()){
            depression += 1;
        }
        else if(sym2op3.isChecked()){
            depression += 3;
        }
        else if(sym2op4.isChecked()){
            depression += 5;
            issue3 += 5;
            issue4 += 2;
        }

        if(sym3op1.isChecked()){
            issue3 += 3;
            issue4 += 3;
            issue2 += 1;
        }
        else if(sym3op2.isChecked()){
            issue3 += 2;
            issue4 += 2;
        }
        else if(sym3op3.isChecked()){
            issue3 += 1;
            issue4 += 1;
        }
        else if(sym3op4.isChecked()){

        }

        if (sym5.isChecked()) {
            depression += 5;
            issue3 += 4;
        }

        startActivity(new Intent(this, SymptomCheckerLink.class));
    }

    public static int getDepression(){
        return depression;
    }
    public static int getIssue2(){
        return issue2;
    }
    public static int getIssue3(){
        return issue3;
    }
    public static int getIssue4(){
        return issue4;
    }

}