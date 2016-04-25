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
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        depressionScore = (int) bundle.get("depressionScore");
        anxietyScore = (int) bundle.get("anxietyScore");
        bodyImageScore = (int) bundle.get("bodyImageScore");
        checkSymptoms();
    }

    public void onButtonClick(View v) {
        Button button = (Button) v;
        switch (button.getId()) {
            case R.id.information:
                startActivity(new Intent(this, ContentCommonIssuesActivity.class));
                break;
            case R.id.bookingLink:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.supportLink:
                startActivity(new Intent(this, FindSupportActivity.class));
                break;
            case R.id.homeLink:
                startActivity(new Intent(this, MainMenuActivity.class));
                break;
        }
    }

    //Checks to see which buttons will be enabled and which won't
    private void checkSymptoms(){
        String message = "";
        Boolean issueDiscovered = false;
        Boolean twoOrMoreIssuesDiscovered;
        Button oneIssueFound, twoOrMoreIssuesFound, threeIssuesFound;
        TextView information;
        information = (TextView)findViewById(R.id.information);
        oneIssueFound = (Button)findViewById(R.id.bookingLink);
        twoOrMoreIssuesFound = (Button)findViewById(R.id.supportLink);
        oneIssueFound.setVisibility(View.GONE);
        twoOrMoreIssuesFound.setVisibility(View.GONE);


        //Checks to see if issues should be visible depending on symptoms checked
        if (depressionScore >= 3){
            message = message + "depression ";
            oneIssueFound.setVisibility(View.VISIBLE);
            issueDiscovered = true;
        }

        if (anxietyScore >= 5){
            message = message + "anxiety ";
            oneIssueFound.setVisibility(View.VISIBLE);
            if(issueDiscovered) {
                twoOrMoreIssuesDiscovered = true;
                twoOrMoreIssuesFound.setVisibility(View.VISIBLE);
            }
            issueDiscovered = true;
        }

        if (bodyImageScore >= 3){
            message = message + " body image/eating disorder";
            oneIssueFound.setVisibility(View.VISIBLE);
            if(issueDiscovered) {
                twoOrMoreIssuesDiscovered = true;
                twoOrMoreIssuesFound.setVisibility(View.VISIBLE);
            }
            issueDiscovered = true;
        }



        //Changes text depending on the results. Current text was just an example, can be changed
        if (issueDiscovered){
            information.setText("Your results have returned sypmtoms for the following possible problems " + message + "." + " Consider booking a councilling appointment for a more formal evaluation, or check the learning resources for more information.");
        }
        else {
            information.setText("Congratulations, our checker has not found any problems! You can return to the main menu using the button below.");
        }
    }
}
