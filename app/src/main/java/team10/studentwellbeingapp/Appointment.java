package team10.studentwellbeingapp;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Appointment {

    private String student;
    private String datetime;
    private String councillor;
    private String aid;

    public Appointment(String datetime, String councillor, String aid){
        this.datetime = datetime;
        this.councillor = councillor;
        this.aid = aid;
    }

    public String getDatetime(){
        return datetime;
    }

    public String getTime() {

        String time = datetime.split("\\s")[1];
        return time.substring(0,time.length() - 3);
    }


    public String getStudent(){
        return student;
    }

    public String getCouncillor(){
        return councillor;
    }

    public String getaid() { return aid; }

    @Override
    public String toString() {
        return "Appointment Time: " + datetime.toString() + " with " +  councillor.toString();
    }
}
