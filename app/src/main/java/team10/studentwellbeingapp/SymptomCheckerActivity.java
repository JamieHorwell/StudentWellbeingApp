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
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;


public class SymptomCheckerActivity extends AppCompatActivity {
    //Give all issues a score. If the score reaches a certain point, the button will activate on the next page
    private static int depression = 0;
    private static int anxiety = 0;
    private static int bodyImage = 0;
    private final int NO_OF_QUESTIONS = 7;
    private int[] scores = new int[NO_OF_QUESTIONS];



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.symptom_checker);
        Toolbar toolbar = (Toolbar) findViewById(R.id.buttonSixToolbar);
        setSupportActionBar(toolbar);
    }

    //submit button has been clicked, sums scores of issues
    public void onSymptomButtonClick(View v){

        RadioGroup radioGroupQ1 = (RadioGroup) findViewById(R.id.question1);

        //loop through radioGroups, getting the index of radio button checked in each instance
        for(int i = 0; i < NO_OF_QUESTIONS; i++ ) {
            String radioGroupID = "question" + Integer.toString(i);
            int resID = getResources().getIdentifier(radioGroupID, "id", getPackageName());
            RadioGroup questionRadioGroup = (RadioGroup) findViewById(resID);
            int radioButtonID = radioGroupQ1.getCheckedRadioButtonId();
            View radioButton = radioGroupQ1.findViewById(radioButtonID);
            int idx = radioGroupQ1.indexOfChild(radioButton);
            scores[i] = idx;
        }
        setDepression();
        setAnxiety();
        setBodyImage();


        startActivity(new Intent(this, SymptomCheckerLink.class));
    }

    public static int getDepression(){
        return depression;
    }
    public static int getAnxiety(){
        return anxiety;
    }
    public static int getBodyImage(){ return bodyImage; }


    //setters used to set scores of each issue relevant from scores array
    public void setDepression() {
            depression = scores[0] + scores[1];
    }
    public void setAnxiety() {
            anxiety = scores[2] + scores[3] + scores[4];
    }
    public void setBodyImage() {
            bodyImage = scores[5] + scores[6];
    }
}