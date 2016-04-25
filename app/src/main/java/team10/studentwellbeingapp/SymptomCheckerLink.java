package team10.studentwellbeingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class SymptomCheckerLink extends AppCompatActivity{

    private int depressionScore;
    private int anxietyScore;
    private int bodyImageScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptom_checker_link);
        Toolbar toolbar = (Toolbar) findViewById(R.id.symptomLinkToolbar);
        setSupportActionBar(toolbar);
        checkSymptoms();
    }

    public void onButtonClick(View v) {
        Button button = (Button) v;
        switch (button.getId()) {
            case R.id.symptomLink1:
                //startActivity(new Intent(this, ButtonOneActivity.class));
                break;
            case R.id.symptomLink2:
                //startActivity(new Intent(this, ButtonTwoActivity.class));
                break;
            case R.id.symptomLink3:
                //startActivity(new Intent(this, ButtonThreeActivity.class));
                break;
            case R.id.symptomLinkHome:
                startActivity(new Intent(this, MainMenuActivity.class));
                break;
        }
    }

    //Checks to see which buttons will be enabled and which won't
    private void checkSymptoms(){
        String message;
        Boolean issueDiscovered = false;

        Button oneIssueFound, twoOrMoreIssuesFound, threeIssuesFound;
        TextView information;
        information = (TextView)findViewById(R.id.information);
        oneIssueFound = (Button)findViewById(R.id.bookingLink);
        twoOrMoreIssuesFound = (Button)findViewById(R.id.supportLink);
        oneIssueFound.setVisibility(View.GONE);
        twoOrMoreIssuesFound.setVisibility(View.GONE);


        //Checks to see if issues should be visible depending on symptoms checked
        if (depressionScore >= 3){
            oneIssueFound.setVisibility(View.VISIBLE);
            issueDiscovered = true;
        }

        if (anxietyScore >= 5){
            oneIssueFound.setVisibility(View.VISIBLE);
            issueDiscovered = true;
        }

        if (bodyImageScore >= 3){
            oneIssueFound.setVisibility(View.VISIBLE);
            issueDiscovered = true;
        }



        //Changes text depending on the results. Current text was just an example, can be changed
        if (issueDiscovered){
            information.setText("Your results have returned the following links shown below. If there are multiple links, you can return here after visiting a page and navigate to other pages.");
        }
        else {
            information.setText("Congratulations, our checker has not found any problems! You can return to the main menu using the button below.");
        }
    }
}
